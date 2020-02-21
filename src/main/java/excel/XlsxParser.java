package excel;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @Description xlsx格式电子表格解析器
 * @date 2020/2/17
 */
public class XlsxParser extends DefaultHandler implements SpreadsheetParseInterface {

    /** 共享字符串表 */
    private SharedStringsTable sst;
    /** 上一次的内容 */
    private String lastContents;
    /** 单元格集合 */
    private List<String> row = new ArrayList<>();
    /** 行集合 */
    private Map<Integer, List<String>> rowsMap = new HashMap<>();
    /** 当前列 */
    private int curCol = 0;
    /** 单元格数据类型，默认为字符串类型 */
    private CellDataType nextDataType = CellDataType.SSTINDEX;
    private final DataFormatter formatter = new DataFormatter();
    private short formatIndex;
    private String formatString;
    /** 单元格*/
    private StylesTable stylesTable;

    private SpreadsheetRowsHandler rowsHandler;
    private int batchSize;
    private int rowNumber;

    public XlsxParser(int batchSize, SpreadsheetRowsHandler rowsHandler) {
        this.batchSize = batchSize;
        this.rowsHandler = rowsHandler;
    }

    @Override
    public void parseFile(InputStream inputStream) {
        this.rowNumber = 0;
        OPCPackage opcPackage;
        XSSFReader xssfReader;
        SharedStringsTable sst;
        XMLReader parser;
        Iterator<InputStream> sheets;
        try {
            opcPackage = OPCPackage.open(inputStream);
            // 设置总行数
            rowsHandler.setTotalRowNum(getTotalRowNum(opcPackage));
            xssfReader = new XSSFReader(opcPackage);
            stylesTable = xssfReader.getStylesTable();
            sst = xssfReader.getSharedStringsTable();
            parser = this.fetchSheetParser(sst);
            sheets = xssfReader.getSheetsData();
        }catch (IOException | OpenXML4JException e){
            throw new RuntimeException("读取文件出错",e);
        }
        while (sheets.hasNext()) {
            try(InputStream sheet = sheets.next()) {
                InputSource sheetSource = new InputSource(sheet);
                parser.parse(sheetSource);
            }catch (IOException | SAXException e){
                throw new RuntimeException("文件解析出错",e);
            }
            // 每张表格最后一批处理
            if(rowsMap.size() > 0){
                rowsHandler.handleRows(rowsMap);
                rowsMap = new HashMap<>();
            }
        }
    }

    /**
     * 获取第一页总行数
     * @param opcPackage
     * @return
     * @throws IOException
     */
    private int getTotalRowNum(OPCPackage opcPackage) throws IOException {
        Workbook wb = new XSSFWorkbook(opcPackage);
        // 获取第一个表格
        Sheet sheet = wb.getSheetAt(0);
        //获取最大行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        return rowNum;
    }

    /**
     * 单元格中的数据可能的数据类型
     */
    enum CellDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
    }

    private XMLReader fetchSheetParser(SharedStringsTable sst) {
        try {
            XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            this.sst = sst;
            parser.setContentHandler(this);
            return parser;
        }catch (SAXException e){
            throw new RuntimeException("找不到org.apache.xerces.parsers.SAXParser",e);
        }
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        // c => 单元格；v => 单元格的内容
        if ("c".equals(name)) {
            // 设定单元格类型
            this.setNextDataType(attributes);
            String ref = attributes.getValue("r");
            // 超过26列会有问题
            int interval = ref.charAt(0) - 65;
            for(int i = row.size() ; i < interval ; i ++){
                row.add("");
                curCol++;
            }
        }
        // 置空
        lastContents = "";
    }

    /**
     * 处理数据类型
     */
    private void setNextDataType(Attributes attributes) {
        nextDataType = CellDataType.NUMBER;
        formatIndex = -1;
        formatString = null;
        String cellType = attributes.getValue("t");
        String cellStyleStr = attributes.getValue("s");

        if ("b".equals(cellType)) {
            nextDataType = CellDataType.BOOL;
        } else if ("e".equals(cellType)) {
            nextDataType = CellDataType.ERROR;
        } else if ("inlineStr".equals(cellType)) {
            nextDataType = CellDataType.INLINESTR;
        } else if ("s".equals(cellType)) {
            nextDataType = CellDataType.SSTINDEX;
        } else if ("str".equals(cellType)) {
            nextDataType = CellDataType.FORMULA;
        }

        if (cellStyleStr != null) {
            int styleIndex = Integer.parseInt(cellStyleStr);
            XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
            formatIndex = style.getDataFormat();
            formatString = style.getDataFormatString();

            if (formatString == null) {
                nextDataType = CellDataType.NULL;
                formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
            }
        }
    }

    /**
     * 对解析出来的数据进行类型处理
     *  @param value
     * 单元格的值（这时候是一串数字）
     */
    private String getDataValue(String value) {
        String thisStr;
        switch (nextDataType) {
            // 这几个的顺序不能随便交换，交换了很可能会导致数据错误
            case SSTINDEX:
                try {
                    // 根据SST的索引值的到单元格的真正要存储的字符串
                    thisStr = new XSSFRichTextString(sst.getEntryAt(Integer.parseInt(value))).toString();
                } catch (NumberFormatException ex) {
                    thisStr = value;
                }
                break;
            case INLINESTR:
                XSSFRichTextString xssfRichTextString = new XSSFRichTextString(value);
                thisStr = xssfRichTextString.toString();
                break;
            case NUMBER:
                if (formatString != null) {
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
                } else {
                    thisStr = value;
                }
                break;
            case ERROR:
                throw new RuntimeException("第" + (rowNumber + 1) + "行数据异常，文件解析出错");
            default:
                thisStr = "";
                break;
        }

        return thisStr;
    }

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        if ("v".equals(name)) {
            // v => 单元格的值
            String value = this.getDataValue(lastContents.trim());
            row.add(curCol, value);
            curCol++;
        } else if (name.equals("row")) {
            // 如果标签名称为 row ，这说明已到行尾
            rowNumber ++;
            if(row.size() > 0){
                rowsMap.put(rowNumber, row);
                if(rowsMap.size() >= batchSize){
                    rowsHandler.handleRows(rowsMap);
                    rowsMap = new HashMap<>();
                }
            }
            row = new ArrayList<>();
            curCol = 0;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 得到单元格内容的值
        lastContents += new String(ch, start, length);
    }
}
