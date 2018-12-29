package enumTest;

/**
 * @author Cain
 * @Description
 * @date 2018/10/29
 */
public enum Color {

    INSTANCE;

    private Blue blue;

    private Color(){
        blue = new Blue();
    }

    public static Blue getInstance(){
        return Color.INSTANCE.blue;
    }
}
