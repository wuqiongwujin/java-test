package excel.export;

import com.google.gson.Gson;
import com.hupun.nr.crm.domain.statistic.UseFunctionStatistic;
import excel.AbstractSpreadsheetRowsHandler;
import jxl.write.WriteException;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

/**
 * @Description 系统功能使用情况统计
 * @Author Cain
 * @date 2020/12/8
 */
public class StatisticUseFunctionHandler extends AbstractSpreadsheetRowsHandler {

    private static final String HOST = "http://172.16.33.61:9207/";
//    private static final String HOST = "http://127.0.0.1:9233/";
    private static String STATISTIC_USE_FUNCTION_URL = HOST + "service/nr/crm/sys/statistic/use/function/statisticUseFunction?companyID=";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        Map<Integer,List<String>> map = new HashMap<>();
        map.put(1,new ArrayList<>(Arrays.asList("A52AA8A7A7DA3EEAB4B8038D383D5216")));
        StatisticUseFunctionHandler handler = new StatisticUseFunctionHandler();
        handler.handleRows(map);
    }

    @Override
    public void handleRows(Map<Integer, List<String>> rowsMap) {
        // 读取公司ID
        List<String> companyIDList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : rowsMap.entrySet()) {
            int row = entry.getKey();
//            if (row == 1) continue;
            List<String> rows = entry.getValue();
            String companyID = rows.get(0).trim();
            if (StringUtils.isEmpty(companyID)) continue;
            companyIDList.add(companyID);
        }
        List<UseFunctionStatistic> statisticList = new ArrayList<>();
        for (String companyID : companyIDList) {
            String url = STATISTIC_USE_FUNCTION_URL + companyID;
            try {
                UseFunctionStatistic statistic = restTemplate.postForObject(url, null, UseFunctionStatistic.class);
                statisticList.add(statistic);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("统计出错,公司ID:"+companyID);
                UseFunctionStatistic statistic = new UseFunctionStatistic();
                statistic.setCompanyID(companyID);
                statistic.setCompanyName("crm公司不存在");
                statisticList.add(statistic);
            }
        }
        String filePatch = "/Users/wuqiong/Downloads/UseFunctionStatistic.xls";
        File file = new File(filePatch);
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> titles = new ArrayList<>(Arrays.asList("公司ID","公司名称","是否设置等级规则","等级规则内容",
                "是否有生效中交易送积分规则","是否有多个生效中交易送积分规则","是否开启积分规则时间限制","是否设置积分规则首单倍送",
                "按金额是否设置排除商品","按分类是否设置排除商品","是否开通储值卡","在用且有有余额的储值卡张数",
                "是否有生效中的微盟店铺","现有会员数","有储值余额会员数","有预收款没有客户的数量","标签数","业务员数","erp会员数","营销活动数",
                "客户分组数","个性化包裹数","erp是否开通会员中心","会员数据库","pos开单量","pos近7天开单量","erp开单量","crm版本","是否开通融合开关"));
        List<List<String>> dataList = new ArrayList<>();
        for (UseFunctionStatistic statistic : statisticList) {
            List<String> datas = new ArrayList<>();
            datas.add(statistic.getCompanyID());
            datas.add(statistic.getCompanyName());
            datas.add(statistic.isEnableCrmLevel() ? "是" : "否");
            datas.add(new Gson().toJson(statistic.getCrmLevelContents()));
            datas.add(statistic.isEnableTradePointsRule() ? "是" : "否");
            datas.add(statistic.isEnableMultiTradePointsRule() ? "是" : "否");
            datas.add(statistic.isPointsRuleTimeLimit() ? "是" : "否");
            datas.add(statistic.isPointsRuleMultiReward() ? "是" : "否");
            datas.add(statistic.isPointsRuleExcludeItem() ? "是" : "否");
            datas.add(statistic.isPointsRuleMultiReward() ? "是" : "否");
            datas.add(statistic.isEnableStoreValueCard() ? "是" : "否");
            datas.add(String.valueOf(statistic.getEnableStoreValueCardCount()));
            datas.add(statistic.isUsingWeimobShop() ? "是" : "否");
            datas.add(String.valueOf(statistic.getCustomerCount()));
            datas.add(String.valueOf(statistic.getHasDebetCustomerCount()));
            datas.add(String.valueOf(statistic.getHasDebetNotCustomerCount()));
            datas.add(String.valueOf(statistic.getLabelCount()));
            datas.add(String.valueOf(statistic.getSalesmanCount()));
            datas.add(String.valueOf(statistic.getErpCustomerCount()));
            datas.add(String.valueOf(statistic.getMarketingCount()));
            datas.add(String.valueOf(statistic.getCustomGroupCount()));
            datas.add(String.valueOf(statistic.getLargessActivityCount()));
            datas.add(String.valueOf(statistic.getOpenErpMemberCenter()));
            datas.add(String.valueOf(statistic.getMemberDB()));
            datas.add(String.valueOf(statistic.getPosTradeCount()));
            datas.add(String.valueOf(statistic.getPosTradeLastCount()));
            datas.add(String.valueOf(statistic.getRcptTradeCount()));
            datas.add(String.valueOf(statistic.getCrmVersion()));
            datas.add(String.valueOf(statistic.isOpenCombine()));
            dataList.add(datas);
        }
        try {
            ExportUtils.createExcel(stream, titles, dataList);
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
