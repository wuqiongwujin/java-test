package excel;

import java.io.InputStream;

/**
 * @Description 电子表格解析接口
 * @date 2020/2/16
 */
public interface SpreadsheetParseInterface {

    /**
     * 解析文件流
     * @param inputStream 文件流
     */
    void parseFile(InputStream inputStream);
}
