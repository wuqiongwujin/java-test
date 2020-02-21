package excel;

import java.util.List;
import java.util.Map;

/**
 * @Description 电子表格行处理接口
 * @date 2020/2/16
 */
public interface SpreadsheetRowsHandler {

    /**
     * 处理表格内容行集合
     * @param rowsMap key(电子表格的行下标);values(该行单元格集合)
     */
    void handleRows(Map<Integer, List<String>> rowsMap);

    /**
     * 设置总行数
     * @param totalRowNum 总行数
     */
    void setTotalRowNum(int totalRowNum);
}
