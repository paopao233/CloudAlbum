package com.pojo;

/**
 * @author x1yyy
 */
public class User {
    private int userKey;
    private String account;
    private String password;

    public User() {
    }

    public User(int userKey, String account, String password) {
        this.userKey = userKey;
        this.account = account;
        this.password = password;
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userKey=" + userKey +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
