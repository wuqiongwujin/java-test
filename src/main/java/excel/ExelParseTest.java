package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Description excel解析
 * @date 2020/2/16
 */
public class ExelParseTest {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/wuqiong/Downloads/会员文本.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

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
}
