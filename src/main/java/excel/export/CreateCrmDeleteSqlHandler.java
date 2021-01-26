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
 * @Description 生成更新会员卡数据sql（误迁移）
 * @Author Cain
 * @date 2021/1/26
 */
public class CreateCrmDeleteSqlHandler extends AbstractSpreadsheetRowsHandler {
    @Override
    public void handleRows(Map<Integer, List<String>> rowsMap) {
        List<List<String>> dataList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : rowsMap.entrySet()) {
            List<String> rows = entry.getValue();
            String companyID = rows.get(0).trim();
            String db = rows.get(1).trim();
            if (StringUtils.isEmpty(companyID)) continue;
            //String cardCodeSql = "update "+db+".cus_card_code o inner join "+db+".custom c on o.com_uid=c.com_uid and o.cus_uid=c.unit_uid and o.com_uid='"+companyID+"' and o.card_type=1 set o.cus_uid=c.cus_uid;";
            String syncOldCrmRecordSql = "delete from "+db+".sync_old_crm_record where company_id='"+companyID+"';";
            dataList.add(Arrays.asList(db,syncOldCrmRecordSql));
        }
        String filePatch = "/Users/wuqiong/Downloads/deleteCrmSql.xls";
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
