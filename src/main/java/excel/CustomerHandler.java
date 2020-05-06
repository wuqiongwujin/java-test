package excel;

import com.hupun.nr.crm.domain.customer.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import recharge.RechargeChargeStore;
import recharge.RechargeRecordDO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @date 2020/2/16
 */
public class CustomerHandler extends AbstractSpreadsheetRowsHandler {

    private static final String COMPANYID = "88393272685761";
    private static final String GENERAL_CARDID = "88393272685761";
    private static final String HOST = "http://172.16.32.244:9207/";
    private static final String RECHARGE_URL = HOST + "service/nr/crm/customer/recharge/charge/store?companyID="+ COMPANYID;
    private static final String QUERY_CUSTOMER_URL = HOST + "/service/nr/crm/customer/query/customer?companyID="+ COMPANYID;
    private static final String GET_CUSTOMER_URL = HOST + "/service/nr/crm/customer/getCustomer?companyID="+ COMPANYID+"&sourceType={sourceType}&sourceID={sourceID}";
    private static RestTemplate restTemplate = new RestTemplate();

    private int totalRowNum;

    @Override
    public void handleRows(Map<Integer, List<String>> rowsMap) {
        for (Map.Entry<Integer,List<String>> entry : rowsMap.entrySet()) {
            Integer row = entry.getKey();
            List<String> params = entry.getValue();
            if (row.equals(1)) {
                continue;
            } else {
                if (StringUtils.hasText(params.get(0)) && StringUtils.hasText(params.get(7))) {
                    String mobile = params.get(0);
                    double money = 0;
                    try {
                        money = Double.valueOf(params.get(7));
                    } catch (Exception e) {
                        System.out.println("转换金额出错,row:"+row);
                        continue;
                    }
                    if (money == 0) continue;
                    CustomerDTO customer = getCustomer(mobile);
                    if (customer != null) {
                        try {
                            recharge(customer.getCustomID(), money);
                            String result = "充值成功,row:"+row+",moilbe:"+mobile;
                            System.out.println(result);
                            wirte(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("充值出错,row:"+row);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setTotalRowNum(int totalRowNum) {
        this.totalRowNum = totalRowNum;
    }

    private CustomerDTO getCustomer(String mobile) throws RuntimeException {
        Map<String, Object> param = new HashMap<>();
        param.put("sourceType", -2);
        param.put("sourceID", mobile);
        try {
            ResponseEntity<CustomerDTO> customerEntity = restTemplate.getForEntity(GET_CUSTOMER_URL, CustomerDTO.class, param);
            return customerEntity.getBody();
        } catch (Exception e) {
            throw new RuntimeException("查询客户出错,mobile:"+mobile);
        }
    }

    private boolean recharge(String customerID, double money) {
        RechargeChargeStore recharge = new RechargeChargeStore();
        recharge.setCustomID(customerID);
        recharge.setChannelID(GENERAL_CARDID);
        recharge.setMoney(money);
        try {
            RechargeRecordDO shomopMemberMessageResponse = restTemplate.postForObject(RECHARGE_URL,recharge, RechargeRecordDO.class);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("充值出错,客户ID:"+customerID);
        }
    }

    private void wirte(String desc) {
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
            osw.write(desc);
            osw.write("\r\n");
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
