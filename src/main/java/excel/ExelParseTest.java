package excel;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Description excel解析
 * @date 2020/2/16
 */
public class ExelParseTest {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/wuqiong/Downloads/customUploadModel-new.xls");
        FileInputStream fileInputStream = new FileInputStream(file);

        checkFile(file);
        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        CustomerHandler handler = new CustomerHandler();
        switch (SpreadsheetFormatEnum.valueOfExtension(ext)) {
            case EXCEL03_EXTENSION: {
                XlsParser parser = new XlsParser(100, handler);
                parser.parseFile(fileInputStream);
                break;
            }
            case EXCEL07_EXTENSION: {
                XlsxParser parser = new XlsxParser(10000, handler);
                parser.parseFile(fileInputStream);
                break;
            }
            case SV_EXTENSION: {
                CsvParser parser = new CsvParser(1000, handler);
                parser.parseFile(fileInputStream);
                break;
            }
            default:throw new RuntimeException("暂不支持该格式的文件");
        }
    }

    private static void checkFile(File file) throws RuntimeException {
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(new FileInputStream(file));
        } catch (Exception e) {
            throw new RuntimeException("读取文件出错");
        }
        Sheet sheet = wb.getSheet(0);
        if (sheet == null) {
            throw new RuntimeException("文件内容为空");
        }
        Cell[] firstRow = sheet.getRow(0);
        if (firstRow == null || firstRow.length <= 0) {
            throw new RuntimeException("文件格式错误");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < firstRow.length; j++) {
            Cell cell = firstRow[j];
            String head = cell.getContents();
            stringBuffer.append(head);
        }
        String firstRowCont = stringBuffer.toString();
        System.out.println(firstRowCont);
        if (true) {
            if (!firstRowCont.contains("手机<手机或者卡号必填>")) {
                throw new RuntimeException("文件格式不正确");
            }
        } else {
        }
    }
}
