package excel;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @date 2020/2/16
 */
public class CustomerHandler extends AbstractSpreadsheetRowsHandler {

    private int totalRowNum;

    @Override
    public void handleRows(Map<Integer, List<String>> rowsMap) {
        System.out.println("totalRowNum:"+totalRowNum);
        System.out.println(new Gson().toJson(rowsMap));
    }

    @Override
    public void setTotalRowNum(int totalRowNum) {
        this.totalRowNum = totalRowNum;
    }
}
