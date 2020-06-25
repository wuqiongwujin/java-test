import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @date 2020/2/17
 */
public class RegionTest {

    private static Map<String,Region> regionMap = new HashMap<>();

    public static void main(String[] args) {
        regionMap.put("330000", new Region("330000", "浙江省", null));
        regionMap.put("330100", new Region("330100", "杭州", "330000"));
        regionMap.put("330101", new Region("330101", "西湖区", "330100"));
        regionMap.put("310000", new Region("310000", "上海市", null));
        regionMap.put("310100", new Region("310100", "上海市", "310000"));
        regionMap.put("310105", new Region("310105", "闸北区", "310100"));
        List<Region> regionList = new ArrayList<>();
        String targetRegionCode = "330000";
        getRegion(targetRegionCode, regionList);
        String result = "";
        for (int i=regionList.size()-1; i >= 0; i--) {
            result += regionList.get(i).getRegionName();
        }
        System.out.println(result);
    }

    public static void getRegion(String regionCode, List<Region> regionList) {
        Region region = regionMap.get(regionCode);
        regionList.add(region);
        if (region.getParentRegionCode() != null) {
            getRegion(region.getParentRegionCode(), regionList);
        }
    }

    static class Region {
        private String regionCode;
        private String regionName;
        private String parentRegionCode;

        public Region(String regionCode, String regionName, String parentRegionCode) {
            this.regionCode = regionCode;
            this.regionName = regionName;
            this.parentRegionCode = parentRegionCode;
        }

        public String getRegionCode() {
            return regionCode;
        }
        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }
        public String getRegionName() {
            return regionName;
        }
        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }
        public String getParentRegionCode() {
            return parentRegionCode;
        }
        public void setParentRegionCode(String parentRegionCode) {
            this.parentRegionCode = parentRegionCode;
        }
    }

}
