package excel.export;

import excel.AbstractSpreadsheetRowsHandler;
import jxl.write.WriteException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description 创建更新散客sql处理器
 * @Author Cain
 * @date 2021/2/10
 */
public class CreateUpdateGuestSqlHandler extends AbstractSpreadsheetRowsHandler {
    @Override
    public void handleRows(Map<Integer, List<String>> rowsMap) {
        List<List<String>> dataList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : rowsMap.entrySet()) {
            List<String> rows = entry.getValue();
            String companyID = rows.get(0).trim();
            String db = rows.get(1).trim();
            if (StringUtils.isEmpty(companyID)) continue;
            String part = part(companyID);
            String updateSrcSql = "delete from "+db+".mbr_custom"+part+" where `com_id` ='"+companyID+
                    "' and `cus_id` in(select `cus_id`  from "+db+".mbr_cus_src"+part+" where `com_id` ='"+companyID+
                    "' and src_type=-3 and src_val ='' and nick_val =0);";
            String updateCustomSql = "delete from "+db+".mbr_cus_src"+part+" where `com_id` ='"+companyID+
                    "' and src_type=-3 and src_val ='' and nick_val =0;";
            dataList.add(Arrays.asList(db,updateSrcSql));
            dataList.add(Arrays.asList(db,updateCustomSql));
        }
        String filePatch = "/Users/wuqiong/Downloads/deleteGuestSql2.xls";
        File file = new File(filePatch);
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ExportUtils.createExcel(stream, Arrays.asList("sql"), dataList);
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTotalRowNum(int totalRowNum) {
        System.out.println("文件总行数:"+totalRowNum);
    }
}
