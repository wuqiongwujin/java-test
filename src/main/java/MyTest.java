import bean.Animal;
import bean.Student;
import calenderTest.DateUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hupun.calf.exception.StandardRuntimeException;
import com.hupun.membership.enums.RechargeMethod;
import com.hupun.nr.common.utils.DateUtil;
import com.hupun.nr.crm.domain.member.query.NRMemberQuery;
import com.hupun.nr.crm.domain.weimob.extension.base.request.WeimobCommonRequest;
import com.hupun.nr.crm.domain.weimob.extension.base.response.WeimobBaseResponse;
import com.hupun.nr.crm.domain.weimob.extension.points.request.WeimobQueryMemberPointInfoRequest;
import com.hupun.nr.crm.exception.CrmExceptionCodes;
import com.hupun.scm.common.util.JsonConverter;
import com.hupun.scm.common.util.UUIDGenerator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description volatile修饰的变量与其他变量联合加入if判断会出现数据不安共享
 * @Author Cain
 * @date 2020/5/11
 */
public class MyTest {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws Exception {
        System.out.println(BigDecimal.valueOf(3.12).toString());
    }

}
