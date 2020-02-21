package excel;

import au.com.bytecode.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Description csv格式文件解析器
 * @date 2020/2/17
 */
public class CsvParser extends AbstractSpreadsheetParser {

    public CsvParser(int batchRowSize, SpreadsheetRowsHandler rowsHandler) {
        super(batchRowSize, rowsHandler);
    }

    @Override
    public void parseFile(InputStream inputStream) {
        // 表格行集合
        Map<Integer,List<String>> rows = new HashMap<>();
        InputStreamReader inputStreamReader;
        BufferedReader br;
        CSVReader csvReader;
        String[] line;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF8");
            br = new BufferedReader(inputStreamReader);
            new CSVReader(new BufferedReader(inputStreamReader)).readNext();
            csvReader = new CSVReader(br);
            CSVReader r2 = new CSVReader(br);
            r2.readNext();
            //rowsHandler.setTotalRowNum(totalNum);
            line = csvReader.readNext();
        }catch (Exception e){
            throw new RuntimeException("文件读取出错", e);
        }
        int currentRowNum = 0;
        while(line != null){
            currentRowNum ++;
            List<String> row = Arrays.asList(line);
            rows.put(currentRowNum, row);
            if(rows.size() >= batchRowSize){
                rowsHandler.handleRows(rows);
                rows = new HashMap<>();
            }
            try {
                line = csvReader.readNext();
            } catch (IOException e) {
                throw new RuntimeException("读取一行出错", e);
            }
        }
        if(rows.size() > 0){
            rowsHandler.handleRows(rows);
        }
    }
}
