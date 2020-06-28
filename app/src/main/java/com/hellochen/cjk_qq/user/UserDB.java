package com.hellochen.cjk_qq.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

    SQLiteDatabase writableDatabase;
    private static String DB_NAME = "user.db";
    private static int DB_VERSION = 1;
    private static String SQL = "create table userinfo (" +
            "       id integer primary key autoincrement," +
            "       account text," +
            "       name text," +
            "       sex text," +
            "       age integer," +
            "       address text," +
            "       email text," +
            "       phone text," +
            "       pwd text" +
            ")";

    public User findUserByAccount(String account) {
        Cursor userinfo = writableDatabase.query("userinfo",null,"account = ?",new String[]{account},null,null,null);
        User user = null;
        while (userinfo.moveToNext()){
            String acc = userinfo.getString(userinfo.getColumnIndex("account"));
            String name = userinfo.getString(userinfo.getColumnIndex("name"));
            String sex = userinfo.getString(userinfo.getColumnIndex("sex"));
            int age = Integer.parseInt(userinfo.getString(userinfo.getColumnIndex("age")));
            String address = userinfo.getString(userinfo.getColumnIndex("address"));
            String email = userinfo.getString(userinfo.getColumnIndex("email"));
            String phone = userinfo.getString(userinfo.getColumnIndex("phone"));
            String pwd = userinfo.getString(userinfo.getColumnIndex("pwd"));
            user = new User(acc, name, sex, age, pwd);
        }
       return user;
    }
    //修改的方法 update userinfo set name = ? ,phone = ? where account = ?
    public long UserUpdate(String account,String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        return writableDatabase.update("userinfo",contentValues,"account = ?",new String[]{account}) ;
    }


    public static class UserDbOpenHelper extends SQLiteOpenHelper {
        private Context context;

        public UserDbOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.context = context;
        }

        /*
        创建数据库
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
//            执行sql语句
            db.execSQL(SQL);
            Toast.makeText(context, "创建sqlite成功", Toast.LENGTH_SHORT).show();
        }

        /*
        更新数据库（当版本号发生变化的时候）
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists userinfo");
            db.execSQL(SQL);
        }
    }

    /*
    打开数据库
     */
    public void OpenUserDb(Context mcontext) {
        UserDbOpenHelper userDbOpenHelper = new UserDbOpenHelper(mcontext);
        writableDatabase = userDbOpenHelper.getWritableDatabase();
    }

    /*
    新增数据insert
    用于用户注册(--新增语句
insert into student(name,sex,age)values
('小名','男','hello')
--字段名和值要一一对应
--删除语句
delete from student  where name = '小名')
     */
    public long insertUserData(User user) {
        String account = user.getAccount();
        String name = user.getName();
        String sex = user.getSex();
        int age = user.getAge();
        String pwd = user.getPassword();

        ContentValues contentValues = new ContentValues();

        contentValues.put("account", account);
        contentValues.put("name", name);
        contentValues.put("sex", sex);
        contentValues.put("age", age);
        contentValues.put("pwd", pwd);

        return writableDatabase.insert("userinfo", null, contentValues);
    }

    public List<User> FindAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor userinfo = writableDatabase.query("userinfo", null, null, null, null, null, null, null);
        while (userinfo.moveToNext()) {
            String account = userinfo.getString(userinfo.getColumnIndex("account"));
            String name = userinfo.getString(userinfo.getColumnIndex("name"));
            String sex = userinfo.getString(userinfo.getColumnIndex("sex"));
            int age = Integer.parseInt(userinfo.getString(userinfo.getColumnIndex("age")));
            String address = userinfo.getString(userinfo.getColumnIndex("address"));
            String email = userinfo.getString(userinfo.getColumnIndex("email"));
            String phone = userinfo.getString(userinfo.getColumnIndex("phone"));
            String pwd = userinfo.getString(userinfo.getColumnIndex("pwd"));
            Log.d("mas", "取值");
            User user = new User(account, name, sex, age,pwd);
            users.add(user);
        }
        return users;
    }

    public boolean delete(String account) {
        return writableDatabase.delete("userinfo", "account = ?", new String[]{account})>0;
    }
}
