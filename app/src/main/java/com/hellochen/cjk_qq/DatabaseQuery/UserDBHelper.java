package com.hellochen.cjk_qq.DatabaseQuery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDBHelper extends SQLiteOpenHelper {
    /**
     * 构造函数
     * @param context 应用程序上下文
     *
     */
    public UserDBHelper(@Nullable Context context) {
        super(context,"account.db", null,2);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(AccountTable.CREATE_TAB);  //执行建表语句
    }

    /**
     * 更新数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
