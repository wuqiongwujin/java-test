package extendsTest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description
 * @Author Cain
 * @date 2022/2/7
 */
public class MemberAction extends AbstractAction {
    @Override
    protected void deal() {
        System.out.println("MemberAction deal");
    }

    public static void main(String[] args) {
        new MemberAction().setIdList(new ArrayList<>(Arrays.asList("1"))).deal();
    }
}
