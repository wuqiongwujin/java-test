package recharge;

import java.io.Serializable;

/**
 * @Description
 * @Author Cain
 * @date 2020/4/29
 */
public class RechargeChargeStore implements Serializable {

    private static final long serialVersionUID = -3306373399447494597L;

    private String customID;
    private String channelID;
    private double money;

    public String getCustomID() {
        return customID;
    }

    public void setCustomID(String customID) {
        this.customID = customID;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
