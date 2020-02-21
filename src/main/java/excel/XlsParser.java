package excel;

import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description .xls电子表格解析器
 * @date 2020/2/16
 */
public class XlsParser extends AbstractSpreadsheetParser implements HSSFListener {

    /** 表格行集合 key(电子表格的行下标);values(该行单元格集合) */
    Map<Integer, List<String>> rowsMap = new HashMap<>();
    /** 当前处理行数 */
    int rowNumber = 0;
    /** 表格的一行 */
    List<String> row = new ArrayList<>();

    private SSTRecord sstRecord;
    private FormatTrackingHSSFListener formatListener;

    public XlsParser(int batchRowSize, SpreadsheetRowsHandler rowsHandler) {
        super(batchRowSize, rowsHandler);
    }

    @Override
    public void parseFile(InputStream inputStream) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            rowsHandler.setTotalRowNum(getTotalRowNum(fs));
            //触发所有单元格（包括空白单元各和空单元格）
            MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
            //查找单元格使用的格式类型
            formatListener = new FormatTrackingHSSFListener(listener);
            HSSFEventFactory factory = new HSSFEventFactory();
            HSSFRequest request = new HSSFRequest();
            request.addListenerForAllRecords(formatListener);
            factory.processWorkbookEvents(request, fs);
            // 最后一批数据行处理
            if(rowsMap.size() > 0){
                rowsHandler.handleRows(rowsMap);
            }
        }catch (IOException e){
            throw new RuntimeException("文件格式出错");
        }
    }

    /**
     * 获取总行数
     * @param fs
     * @return
     * @throws IOException
     */
    private int getTotalRowNum(POIFSFileSystem fs) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(fs);
        //得到第一个表单
        HSSFSheet aSheet = workbook.getSheetAt(0);
        //得到最后一行的行下标,行下标从0开始
        return aSheet.getLastRowNum() + 1;
    }

    /**
     * HSSFListener 监听方法，处理 Record(一个单元格)
     */
    @Override
    public void processRecord(Record record) {
        switch (record.getSid()) {
            //当前sheet页下所有字符串类型
            case SSTRecord.sid:
                sstRecord = (SSTRecord) record;
                break;
            //单元格为空白
            case BlankRecord.sid:
                BlankRecord blankRecord = (BlankRecord) record;
                row.add(blankRecord.getColumn(), "");
                break;
            // 单元格为字符串类型
            case LabelSSTRecord.sid:
                LabelSSTRecord labelSSTRecord = (LabelSSTRecord) record;
                // 加入单元格内容
                row.add(labelSSTRecord.getColumn(), sstRecord.getString(labelSSTRecord.getSSTIndex()).toString().trim());
                break;
            // 单元格为数字类型
            case NumberRecord.sid:
                NumberRecord numberRecord = (NumberRecord) record;
                // 加入单元格内容
                row.add(numberRecord.getColumn(), formatListener.formatNumberDateCell(numberRecord).trim());
                break;
            default:
                break;
        }

        // 空白单元格的操作
        if (record instanceof MissingCellDummyRecord) {
            MissingCellDummyRecord missingCellDummyRecord = (MissingCellDummyRecord) record;
            row.add(missingCellDummyRecord.getColumn(), "");
        }
        // 每行结束时的操作
        if (record instanceof LastCellOfRowDummyRecord) {
            rowNumber ++;
            if(row.size() > 0){
                rowsMap.put(rowNumber, row);
                if(rowsMap.size() >= batchRowSize){
                    rowsHandler.handleRows(rowsMap);
                    rowsMap = new HashMap<>();
                }
            }
            row = new ArrayList<>();
        }
    }
}
