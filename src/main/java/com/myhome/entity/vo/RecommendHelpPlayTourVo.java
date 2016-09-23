package com.myhome.entity.vo;

import com.myhome.entity.AbstractEntity;
import com.myhome.entity.User;

/**
 * 
 * 我推荐的
 * 
 * @author gwb
 */
public class RecommendHelpPlayTourVo extends AbstractEntity {

    private User    userFrom; // 推荐/打赏/帮助人

    private User    userTo;  // 被推荐/打赏/帮助人

    private Double  money;   // 打赏的钱

    private Integer type;    // 角色类型 1普通用（默认）,2画家，3受捐者，4老师，5赞助商,6同时是画家和受捐者

    private String  message; // 寄语

    /**
     * 2.1
     */
    private String  alipayAccount;   // 支付宝账户

    private String  bankAccount;     // 银行卡号

    private String  accountName;     // 开户人

    private String  weChatAccount;   // 微信账户
    
    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getWeChatAccount() {
        return weChatAccount;
    }

    public void setWeChatAccount(String weChatAccount) {
        this.weChatAccount = weChatAccount;
    }

}
