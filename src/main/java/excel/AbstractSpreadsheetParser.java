package excel;

import java.io.InputStream;

/**
 * @Description 抽象电子表格解析器
 * @date 2020/2/16
 */
public abstract class AbstractSpreadsheetParser implements SpreadsheetParseInterface {

    /** 每批处理行数 */
    protected int batchRowSize;
    /** 电子表格行处理器 */
    protected SpreadsheetRowsHandler rowsHandler;

    public AbstractSpreadsheetParser(int batchRowSize, SpreadsheetRowsHandler rowsHandler) {
        this.batchRowSize = batchRowSize;
        this.rowsHandler = rowsHandler;
    }

    /**
     * 解析文件流
     * @param inputStream 文件流
     */
    @Override
    public abstract void parseFile(InputStream inputStream);
}
