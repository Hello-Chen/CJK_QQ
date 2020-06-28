package com.hellochen.cjk_qq.mode;

public class AccountTable {
    public static final String TAB_NAME="account";
    public static final String COL_USERNAME="username";
    public static final String COL_NAME="name";
    public static final String COL_SEX="sex";
    public static final String COL_AGE="age";
    public static final String COL_PASSWROD="passwrod";
    public static final String CREATE_TAB = "create table "
            + TAB_NAME + " ("
            + COL_USERNAME + " text primary key,"
            + COL_NAME + " text,"
            + COL_SEX + " text,"
            + COL_AGE + " text,"
            + COL_PASSWROD + " text );";
}
