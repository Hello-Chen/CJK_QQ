package com.hellochen.cjk_qq.user;

public class User {
    private String account;
    private String name;
    private String sex;
    private int age;
    private String password;

    public User(String account, String name, String sex, int age, String password) {
        this.account = account;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
