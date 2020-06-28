package com.hellochen.cjk_qq.bean;

/**
 * 文件名: User
 * 创建者:
 * 创建日期: 2020/6/25 11:34
 * 邮箱:
 * 描述: 个人信息类
 */
public class User {
    private String name;// 名字
    private String username;// 用户账号
    private String password;//密码
    private String sex;// 性别
    private String age;//年龄

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User(String username, String name, String sex, String age, String password) {
        this.username = username;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.password = password;
    }
}
