package excel;

import java.util.List;
import java.util.Map;

/**
 * @Description 抽象电子表格行处理器
 * @date 2020/2/16
 */
public abstract class AbstractSpreadsheetRowsHandler implements SpreadsheetRowsHandler {

    /**
     * 处理表格内容行集合
     * @param rowsMap key(电子表格的行下标);values(该行单元格集合)
     */
    @Override
    public abstract void handleRows(Map<Integer, List<String>> rowsMap);


}
