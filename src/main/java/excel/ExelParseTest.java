package excel;


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
        File file = new File("/Users/wuqiong/Downloads/最终结果.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        //CustomerHandler handler = new CustomerHandler();
        // 处理聚茂导入客户余额问题
        //RechargeRecordHandler handler = new RechargeRecordHandler();
        // 迁移公司功能使用情况导出
        StatisticUseFunctionHandler handler = new StatisticUseFunctionHandler();
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