package classTest;

/**
 * @author Cain
 * @Description
 * @date 2018/11/4
 */
public class Son extends Father {
    private String firstName;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
