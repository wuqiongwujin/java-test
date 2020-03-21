package bean;

import org.springframework.stereotype.Service;

/**
 * @author Cain
 * @Package PACKAGE_NAME
 * @Description
 * @date 2018/10/6
 */
@Service
public class People extends Animal{

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    void speek(String arg){
        System.out.println(arg);
    }

    protected void work(String arg){
        System.out.println(arg);
    }

    private void eat(String arg){
        System.out.println(arg);
    }

    public void privateMethod(){

    }
}
