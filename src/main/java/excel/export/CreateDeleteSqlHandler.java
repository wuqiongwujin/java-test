package excel.export;

import excel.AbstractSpreadsheetRowsHandler;
import jxl.write.WriteException;
import org.dommons.core.string.Stringure;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;

/**
 * @Description 生成删除会员中心数据sql（误迁移数据恢复）
 * @Author Cain
 * @date 2021/1/25
 */
public class CreateDeleteSqlHandler extends AbstractSpreadsheetRowsHandler {

    @Override
    public void handleRows(Map<Integer, List<String>> rowsMap) {
        List<List<String>> dataList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : rowsMap.entrySet()) {
            List<String> rows = entry.getValue();
            String companyID = rows.get(0).trim();
            String alias = rows.get(1).trim();
            String catalog = rows.get(2).trim();
            String part = part(companyID);
            if (StringUtils.isEmpty(companyID)) continue;
            String pointsValueSql = "delete from "+catalog+".`mbr_points_val"+part+"` where `com_id`= '"+companyID+"' and `rec_created` >'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,pointsValueSql));
            String pointsRecordSql = "delete from "+catalog+".`mbr_points_rec"+part+"` where `com_id`= '"+companyID+"' and `rec_created` >'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,pointsRecordSql));
            String rechargeValueSql = "delete from "+catalog+".`mbr_recharge_val"+part+"` where `com_id`= '"+companyID+"' and rv_crt>'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,rechargeValueSql));
            String rechargeRecordSql = "delete from "+catalog+".`mbr_recharge_rec"+part+"` where `com_id`='"+companyID+"' and `rec_time` >'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,rechargeRecordSql));
            String growValueSql = "delete from "+catalog+".`mbr_grow_val"+part+"` where `com_id` ='"+companyID+"' and `rec_created` >'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,growValueSql));
            String growRecordSql = "delete from "+catalog+".`mbr_grow_rec"+part+"` where `com_id`='"+companyID+"' and `rec_created` >'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,growRecordSql));
            String rechargeChnSql = "delete from "+catalog+".`mbr_recharge_chn` where `com_id`='"+companyID+"' and chn_mod>'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,rechargeChnSql));
            String memberLineSql = "delete from "+catalog+".`mbr_member_line` where `com_id` ='"+companyID+"' and ln_mod>'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,memberLineSql));
            String memberLevelSql = "delete from "+catalog+".`mbr_member_level` where `com_id`='"+companyID+"' and lv_modified>'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,memberLevelSql));
            String memberBindSql = "delete from "+catalog+".`mbr_member_bind"+part+"` where `com_id`='"+companyID+"' and mr_mod>'2020-01-24 21:33:58';";
            dataList.add(Arrays.asList(alias,memberBindSql));
        }
        String filePatch = "/Users/wuqiong/Downloads/deleteSql.xls";
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

    /**
     * 计算数据表分区
     * @return 分区
     */
    protected String part(String companyID) {
        return part(endIndex(companyID));
    }

    /**
     * 计算分区值
     * @param index 序号
     * @return 分区值
     */
    protected String part(int index) {
        StringBuilder buf = new StringBuilder(2).append('_');
        buf.append(Math.abs(index) % parts());
        return buf.toString();
    }

    protected int parts() {
        return 10;
    }

    /**
     * 计算尾数
     * @param companyID 公司ID
     * @return 数值
     */
    private int endIndex(String companyID) {
        companyID = Stringure.trim(companyID);
        // 取最后一位数字
        int len = companyID.length();
        if (len == 0) return -1;
        Character let = null;
        for (int i = len; i > 0; i--) {
            char c = companyID.charAt(i - 1);
            if (c >= '0' && c <= '9') return c - '0';
            else if (let == null && Character.isLetter(c)) let = c;
        }
        // 无数字取最后一位字母36进制换算
        if (let != null) return Character.toLowerCase(let) - 'a' + 11;
        // 无字母取最后一位字符asc码值
        return Math.abs(companyID.charAt(len - 1));
    }
}
