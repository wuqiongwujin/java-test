//package excel.handler.amount;
//
//import com.hupun.weimob.xinyun.DefaultWeimobXinYunSession;
//import com.hupun.weimob.xinyun.WeimobXinYunSession;
//import com.hupun.weimob.xinyun.bean.amount.AmountChangeLog;
//import com.hupun.weimob.xinyun.bean.amount.AmountLogChannelType;
//import com.hupun.weimob.xinyun.bean.amount.request.MemberAmountLogsRequest;
//import com.hupun.weimob.xinyun.bean.amount.response.AmountChangeLogPage;
//import com.hupun.weimob.xinyun.exception.WeimobXinYunException;
//import excel.AbstractSpreadsheetRowsHandler;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @Description 聚茂公司储值扣减记录再补全一次
// * @Author Cain
// * @date 2020/10/29
// */
//public class RechargeRecordHandler extends AbstractSpreadsheetRowsHandler {
//
//    public static final WeimobXinYunSession session = new DefaultWeimobXinYunSession("可以有会员商城(店铺)(店铺)", "3e965532-7dc7-4b5f-a306-a18860a085f8") ;
//
//    @Override
//    public void handleRows(Map<Integer, List<String>> rowsMap) {
//        for (Map.Entry<Integer,List<String>> entry : rowsMap.entrySet()) {
//            Integer row = entry.getKey();
//            List<String> valueArray = entry.getValue();
//            if (row.equals(1)) {
//                continue;
//            } else {
//                String first = valueArray.get(0);
//                if (StringUtils.isEmpty(first)) continue;
//                long wid = Long.valueOf(first);
//                dealByWid(wid);
//            }
//        }
//    }
//
//    @Override
//    public void setTotalRowNum(int totalRowNum) {
//        System.out.println("文件总行数:"+totalRowNum);
//    }
//
//    private void dealByWid(long wid) {
//        List<AmountChangeLog> amountLogs = null;
//        try {
//            amountLogs = amountLog(wid);
//        } catch (Exception e) {
//            String err = "查询客户余额流水出错,wid:"+wid;
//            System.out.println(err);
//            wirte(err);
//            return;
//        }
//        if (CollectionUtils.isEmpty(amountLogs)) {
//            return;
//        }
//        AmountChangeLog importLog = null;
//        for (AmountChangeLog log : amountLogs) {
//            if (log.getChannelType().intValue() == AmountLogChannelType.OFFLINE_IMPORT_CUSTOMER.type) {
//                importLog = log;
//                break;
//            }
//        }
//        if (importLog == null) {
//            String err = "该客户没有导入余额记录,wid:"+wid;
//            System.out.println(err);
//            wirte(err);
//            return;
//        }
//        AmountChangeLog menuDeductLog = null;
//        for (AmountChangeLog log : amountLogs) {
//            if (log.getChannelType() != null
//                    && log.getChannelType() == AmountLogChannelType.OFFLINE_SUB_AMOUNT.type
//                    && log.getChangeReason().equals("扣减导入客户余额")) {
//                menuDeductLog = log;
//                break;
//            }
//        }
//        if (menuDeductLog == null) {
//            String err = "该客户没有扣减余额记录,wid:"+wid;
//            System.out.println(err);
//            wirte(err);
//            return;
//        }
//        if (Math.abs(menuDeductLog.getChangeMoney().intValue()) == Math.abs(importLog.getChangeMoney().intValue())) {
//            String err = "扣减导入余额正常,wid:"+wid;
//            System.out.println(err);
//            return;
//        } else if(Math.abs(menuDeductLog.getChangeMoney().intValue()) < Math.abs(importLog.getChangeMoney().intValue())) {
//            String err = "客户余额不够扣减,wid:"+wid;
//            System.out.println(err);
//            wirte(err);
//            return;
//        }
//    }
//
//    private List<AmountChangeLog> amountLog(long wid) throws WeimobXinYunException {
//        MemberAmountLogsRequest request = new MemberAmountLogsRequest();
//        request.setWid(wid);
//        request.setPage(1);
//        request.setPageSize(100);
//        AmountChangeLogPage amountChangeLogPage = session.queryAmountChangeLog(request);
//        if (amountChangeLogPage == null || CollectionUtils.isEmpty(amountChangeLogPage.getItems())) {
//            return null;
//        }
//        return amountChangeLogPage.getItems();
//    }
//
//    private void wirte(String desc) {
//        File file = new File("/Users/wuqiong/Downloads/jmamount.txt");
//        if(!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            FileOutputStream out = new FileOutputStream(file, true);
//            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
//            osw.write(desc);
//            osw.write("\r\n");
//            osw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        RechargeRecordHandler handler = new RechargeRecordHandler();
//        handler.dealByWid(2031672781);
//    }
//}
