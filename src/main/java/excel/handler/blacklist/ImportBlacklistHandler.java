package excel.handler.blacklist;

import com.hupun.nr.crm.domain.member.MemberBlacklist;
import excel.AbstractSpreadsheetRowsHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * @Description 导入黑名单规则处理器
 * @Author Cain
 * @date 2021/4/7
 */
public class ImportBlacklistHandler extends AbstractSpreadsheetRowsHandler {

    private static final String COMPANYID = "48749475695930";
    private static final String HOST = "http://172.16.33.61:9207/";
    private static String ADD_MEMBER_BLACKLIST_URL =
            HOST + "/service/nr/crm/member/blacklist/add?companyID="+COMPANYID+"&markBlacklistImmediately=false";
    private static RestTemplate restTemplate = new RestTemplate();

    @Override
    public void handleRows(Map<Integer, List<String>> rowsMap) {
        for (Map.Entry<Integer,List<String>> entry : rowsMap.entrySet()) {
            int row = entry.getKey();
            if (row < 2470) continue;
            List<String> cells = entry.getValue();
            if (CollectionUtils.isEmpty(cells)) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (String cell : cells) {
                if (StringUtils.isEmpty(cell)) continue;
                cell = StringUtils.replace(cell, " ", "");
                builder.append(cell);
            }
            String rule = builder.toString();
            if (StringUtils.hasText(rule)) {
                MemberBlacklist blacklist = new MemberBlacklist();
                blacklist.setRule(rule);
                blacklist.setRange("2,1");
                blacklist.setMatchType(2);
                try {
                    restTemplate.postForEntity(ADD_MEMBER_BLACKLIST_URL, blacklist, MemberBlacklist.class);
                    System.out.println("第"+row+"行成功");
                } catch (Exception e) {
                    String memssage = "第"+row+"行失败!!!!!!!!!!!!!!";
                    System.out.println(memssage);
                    wirte(memssage);
                }
            }
        }
    }

    @Override
    public void setTotalRowNum(int totalRowNum) {
        System.out.println("文件总行数:"+totalRowNum);
    }

    private void wirte(String desc) {
        File file = new File("/Users/wuqiong/Downloads/failed.txt");
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
