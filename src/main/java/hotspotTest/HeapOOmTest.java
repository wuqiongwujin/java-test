package hotspotTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cain
 * @Description
 * @date 2019/1/1
 */
public class HeapOOmTest {
    public static void main(String[] args) {
        List<OOMOject> list = new ArrayList<OOMOject>();
        while (true) {
            list.add(new OOMOject());
        }
    }
}

class OOMOject {
    private byte[] bytes = new byte[2048];
}