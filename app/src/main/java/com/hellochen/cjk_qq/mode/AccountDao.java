package com.hellochen.cjk_qq.mode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hellochen.cjk_qq.user.User;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private SQLiteOpenHelper mHelper;
    public AccountDao(Context context) {
        mHelper = new UserDBHelper(context);
    }
    //添加用户到数据库
    public void addAdcount(User user) {
        Log.e("TAG",user.toString()+"2545454545787777777777777777777");
        //获取数据库对象
        SQLiteDatabase db = mHelper.getWritableDatabase();

        Log.e("TAG",db.toString()+"+++++++++++++++++++++++++++");
        //执行添加操作
        ContentValues values = new ContentValues();
        values.put(AccountTable.COL_USERNAME,user.getUsername());
        values.put(AccountTable.COL_NAME,user.getName());
        values.put(AccountTable.COL_SEX,user.getSex());
        values.put(AccountTable.COL_AGE,user.getAge());
        values.put(AccountTable.COL_PASSWROD,user.getPassword());
        db.replace(AccountTable.TAB_NAME, null, values);
    }

    /**
     * 获取个人所有用户信息
     */
    public User getAccountuser(String hxId) {
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from " +AccountTable.TAB_NAME + " where " + AccountTable.COL_USERNAME + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
        User userInfo = null;
        if (cursor.moveToNext()) {
            userInfo = new User();
            //封装对象
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex(AccountTable.COL_USERNAME)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(AccountTable.COL_NAME)));
            userInfo.setSex(cursor.getString(cursor.getColumnIndex(AccountTable.COL_SEX)));
            userInfo.setAge(cursor.getString(cursor.getColumnIndex(AccountTable.COL_AGE)));
            userInfo.setPassword(cursor.getString(cursor.getColumnIndex(AccountTable.COL_PASSWROD)));
        }
        //关闭资源
        cursor.close();
        //返回数据r
        return userInfo;
    }


    /**
     * 获取所有用户信息
     */

    public List<User> getAccountAll() {
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        List<User> userInfoList = new ArrayList<>();
        //执行查询语句
        String sql = "select * from " + AccountTable.TAB_NAME ;
        Cursor cursor = db.rawQuery(sql,null);
        User userInfo = null;
        while (cursor.moveToNext()) {
            userInfo = new User();
            //封装对象
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex(AccountTable.COL_USERNAME)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(AccountTable.COL_NAME)));
            userInfo.setSex(cursor.getString(cursor.getColumnIndex(AccountTable.COL_SEX)));
            userInfo.setAge(cursor.getString(cursor.getColumnIndex(AccountTable.COL_AGE)));
            userInfo.setPassword(cursor.getString(cursor.getColumnIndex(AccountTable.COL_PASSWROD)));
            userInfoList.add(userInfo);
        }
        //关闭资源
        cursor.close();
        //返回数据r
        return userInfoList;
    }
    //

    /**
     * 删除联系人信息
     * @param hxId
     */
    public void deleteContactByHxId(String hxId){
        if (hxId == null)
        {
            return;
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.delete(AccountTable.TAB_NAME,AccountTable.COL_USERNAME+"=?",new String[]{hxId});
    }
}
