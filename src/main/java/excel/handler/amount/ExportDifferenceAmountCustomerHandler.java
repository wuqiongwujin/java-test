package excel.handler.amount;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hupun.calf.entity.TimelineData;
import com.hupun.membership.domain.member.MemberDO;
import com.hupun.nr.crm.domain.member.query.NRTimedMemberQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 导出余额有差异客户
 * @Author Cain
 * @date 2021/1/26
 */
public class ExportDifferenceAmountCustomerHandler {

    private static final String COMPANYID = "95F4C165F4723564B61BD9E9E85045A1";
    private static final String HOST = "http://172.16.33.61:9207/";
    private static String QUERY_MODIFIED_URL = HOST + "/service/nr/crm/member/member/modified/query?companyID="+COMPANYID;
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        ExportDifferenceAmountCustomerHandler handler = new ExportDifferenceAmountCustomerHandler();
        handler.export();
    }

    private void export() {
        // 2019-01-01
        Date begTime = new Date(1546272000000L);
        TimelineData<MemberDO> page = new TimelineData<>();
        page.setHasMore(true);
        while (page.isHasMore()) {
            page = queryMember(begTime);
            if (!CollectionUtils.isEmpty(page.getData())) {
                List<String> memberIDList = page.getData().stream().map(MemberDO::getMemberID).distinct().collect(Collectors.toList());
            }
            if (page.getTimeline() == null) break;
            begTime = page.getTimeline();
        }
    }

    /**
     * 分页查询会员
     * @param beginTime
     * @return
     */
    private TimelineData<MemberDO> queryMember(Date beginTime) {
        NRTimedMemberQuery query = new NRTimedMemberQuery();
        query.setCompanyID(COMPANYID);
        query.setLoadBalance(true);
        query.setBegin(beginTime);
        query.setLimit(1000);
        ResponseEntity<String> response = restTemplate.postForEntity(QUERY_MODIFIED_URL, query, String.class);
        TimelineData<MemberDO> page = JSON.parseObject(response.getBody(), new TypeReference<TimelineData<MemberDO>>(){});
        return page;
    }

}
