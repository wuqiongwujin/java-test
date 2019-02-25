/**
 * @author Cain
 * @Package PACKAGE_NAME
 * @Description
 * @date 2018/10/6
 */
public class Animal {
    private String type;

    public Animal() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!this.type.equals(((Animal)obj).type)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = 31 * hash + (type == null ? 0 : type.hashCode());
        return hash;
    }
}
