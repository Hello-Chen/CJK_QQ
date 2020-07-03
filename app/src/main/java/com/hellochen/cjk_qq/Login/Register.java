package com.hellochen.cjk_qq.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hellochen.cjk_qq.R;
import com.hellochen.cjk_qq.user.User;
import com.hellochen.cjk_qq.DatabaseQuery.AccountDao;
import com.hellochen.cjk_qq.DatabaseQuery.UserDBHelper;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private UserDBHelper userDBHelper;
    private AccountDao accountDao;
    private SQLiteDatabase sqLiteDatabase;


    private EditText et_register_username;
    private EditText et_register_name;
    private EditText et_register_sex;
    private EditText et_register_age;
    private EditText et_register_password;
    private Button bt_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //  getSupportActionBar().hide();

        userDBHelper = new UserDBHelper(this);
        accountDao = new AccountDao(this);
        sqLiteDatabase = userDBHelper.getWritableDatabase();

        findViews();
    }

    private void findViews() {
        et_register_username = (EditText) findViewById(R.id.et_register_username);
        et_register_name = (EditText) findViewById(R.id.et_register_name);
        et_register_sex = (EditText) findViewById(R.id.et_register_sex);
        et_register_age = (EditText) findViewById(R.id.et_register_age);
        et_register_password = (EditText) findViewById(R.id.et_register_password);
        bt_register = (Button) findViewById(R.id.bt_register);

        bt_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == bt_register) {
            getUser();
        }
    }

    private void getUser() {
        String username, name, sex, age, password;
        //获取输入框文本值
        username = et_register_username.getText().toString();
        name = et_register_name.getText().toString();
        sex = et_register_sex.getText().toString();
        age = et_register_age.getText().toString();
        password = et_register_password.getText().toString();

        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            Toast.makeText(Register.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = accountDao.getAccountuser(username);

        if (user == null) {
            user = new User(username, name, sex, age, password);
            accountDao.addAdcount(user);
            Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(Register.this, "用户以存在！", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}