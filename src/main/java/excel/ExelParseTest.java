package excel;


import com.google.gson.Gson;
import com.hupun.nr.crm.domain.customer.CustomerDTO;
import org.springframework.http.ResponseEntity;
import recharge.RechargeChargeStore;
import recharge.RechargeRecordDO;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description excel解析
 * @date 2020/2/16
 */
public class ExelParseTest {

    private static int BATCH_IMPORT_LIMIT = 20;

    public static void main(String[] args) {
        try {
            redFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void redFile() throws FileNotFoundException {
        File file = new File("/Users/wuqiong/Downloads/linshishang2.xls");
        FileInputStream fileInputStream = new FileInputStream(file);

        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        CustomerHandler handler = new CustomerHandler();
        switch (SpreadsheetFormatEnum.valueOfExtension(ext)) {
            case EXCEL03_EXTENSION: {
                XlsParser parser = new XlsParser(BATCH_IMPORT_LIMIT, handler);
                parser.parseFile(fileInputStream);
                break;
            }
            case EXCEL07_EXTENSION: {
                XlsxParser parser = new XlsxParser(BATCH_IMPORT_LIMIT, handler);
                parser.parseFile(fileInputStream);
                break;
            }
            case SV_EXTENSION: {
                CsvParser parser = new CsvParser(BATCH_IMPORT_LIMIT, handler);
                parser.parseFile(fileInputStream);
                break;
            }
            default:throw new RuntimeException("暂不支持该格式的文件");
        }
    }

    private static void wirte(String desc) {
        File file = new File("/Users/wuqiong/Downloads/rechage.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            osw.write("test1");
            osw.write("\r\n");
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}