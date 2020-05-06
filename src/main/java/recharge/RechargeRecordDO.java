package recharge;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author Cain
 * @date 2020/4/29
 */
public class RechargeRecordDO implements Serializable {

    private static final long serialVersionUID = -7011647452610912404L;

    private String recordID;
    private String customID;
    private String memberID;
    private String channelID;

    private int method;

    private double value;
    private double money;
    private double giving;

    private String shopID;

    private int billType;
    private String billID;
    private String billCode;

    private String description;
    private Date time;

    private String handleUserID;

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getCustomID() {
        return customID;
    }

    public void setCustomID(String customID) {
        this.customID = customID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getGiving() {
        return giving;
    }

    public void setGiving(double giving) {
        this.giving = giving;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getHandleUserID() {
        return handleUserID;
    }

    public void setHandleUserID(String handleUserID) {
        this.handleUserID = handleUserID;
    }
}
