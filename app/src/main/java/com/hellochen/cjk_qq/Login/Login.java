package com.hellochen.cjk_qq.Login;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hellochen.cjk_qq.MainActivity;
import com.hellochen.cjk_qq.MyActivity;
import com.hellochen.cjk_qq.R;
import com.hellochen.cjk_qq.mode.AccountTable;
import com.hellochen.cjk_qq.user.User;
import com.hellochen.cjk_qq.mode.AccountDao;
import com.hellochen.cjk_qq.mode.UserDBHelper;

import java.util.Random;

public class Login extends Activity {
    private UserDBHelper userDBHelper;
    private AccountDao accountDao;

    private TextView getTV_1, getTV_2, getTV_3, tv_auth;
    private EditText et_login_username, et_login_password, et_auth;
    private ImageButton getIB_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDBHelper = new UserDBHelper(this);
        accountDao = new AccountDao(this);


        //获取控件id并赋值
        getTV_1 = findViewById(R.id.forgetPassword);
        getTV_2 = findViewById(R.id.userRegister);
        getTV_3 = findViewById(R.id.designer);
        getIB_1 = findViewById(R.id.login);
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        tv_auth = findViewById(R.id.tv_auth);
        et_auth = findViewById(R.id.et_auth);

        getIB_1.setOnClickListener(V -> {
            progressDialog();

        });
        tv_auth.setOnClickListener(V -> {
            tv_auth.setText(createRandomCharData(4) + "");

        });

        //验证码
        tv_auth.setText(createRandomCharData(4) + "");
        //监听事件函数
        setListeners();
    }

    private void setListeners() {
        OnClick onClick = new OnClick();
        getTV_1.setOnClickListener(onClick);
        getTV_2.setOnClickListener(onClick);
        getTV_3.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        Intent intent = null;

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.forgetPassword:
                    intent = new Intent(Login.this, ForgetPassword.class);
                    break;
                case R.id.designer:
                    intent = new Intent(Login.this, MyActivity.class);
                    break;
                case R.id.userRegister:
                    intent = new Intent(Login.this, Register.class);
                    break;
            }
            startActivity(intent);
        }
    }

    /**
     * 自定义progressDialog
     */
    public void progressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在登录...");
        // progressDialog.setMessage("正在下载...");
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    progressDialog.setProgress(i);
                    try {
                        sleep(10);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 100) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getLoginUser();
                            }
                        });
                    }
                }
                progressDialog.dismiss();
            }
        }.start();
        progressDialog.show();
    }

    private void getLoginUser() {
        String username, password, authCode;

        username = et_login_username.getText().toString();
        password = et_login_password.getText().toString();
        authCode = et_auth.getText().toString();
        User user = accountDao.getAccountuser(username);
        if (user == null) {
            Toast.makeText(Login.this, "此用户未注册", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(authCode) || !authCode.equals(tv_auth.getText().toString())) {
            Toast.makeText(Login.this, "验证码错误或为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {

            Intent intent = new Intent(Login.this, MainActivity.class);

            intent.putExtra(AccountTable.COL_USERNAME,user.getUsername());
            intent.putExtra(AccountTable.COL_NAME,user.getName());
            intent.putExtra(AccountTable.COL_SEX,user.getSex());
            intent.putExtra(AccountTable.COL_AGE,user.getAge());

            startActivity(intent);
            Toast.makeText(Login.this, "登录成功！", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(Login.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    //根据指定长度生成字母和数字的随机数
    //0~9的ASCII为48~57
    //A~Z的ASCII为65~90
    //a~z的ASCII为97~122
    public String createRandomCharData(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();//随机用以下三个随机生成器
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch (index) {
                case 0:
                    data = randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;//保证只会产生65~90之间的整数
                    sb.append((char) data);
                    break;
                case 2:
                    data = randdata.nextInt(26) + 97;//保证只会产生97~122之间的整数
                    sb.append((char) data);
                    break;
            }
        }
        String result = sb.toString();
        return result;
    }
}