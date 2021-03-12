package excel;


import excel.export.CreateCrmDeleteSqlHandler;
import excel.export.CreateDeleteSqlHandler;
import excel.export.CreateUpdateGuestSqlHandler;
import excel.export.StatisticUseFunctionHandler;
import excel.handler.amount.RechargeRecordHandler;
import excel.handler.amount.UseAmountHandler;

import java.io.*;

/**
 * @Description excel解析
 * @date 2020/2/16
 */
public class ExelParseTest {

    private static int BATCH_IMPORT_LIMIT = 2000;

    public static void main(String[] args) {
        try {
            redFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     * @throws FileNotFoundException
     */
    private static void redFile() throws FileNotFoundException {
        File file = new File("/Users/wuqiong/Downloads/公司统计.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        // 迁移公司功能使用情况导出
        StatisticUseFunctionHandler handler = new StatisticUseFunctionHandler();
        //CreateUpdateGuestSqlHandler handler = new CreateUpdateGuestSqlHandler();
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