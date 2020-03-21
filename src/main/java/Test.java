import java.util.Date;

/**
 * @Description
 * @Author Cain
 * @date 2020/2/17
 */
public class Test {

    public static void main(String[] args) {
        int a = 1;
        switch (a) {
            case 0:
                System.out.println(0);
            case 1:
                System.out.println(1);
            case 2:{
                System.out.println(2);
            }
        }
    }

}
