package com.hellochen.cjk_qq.Login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hellochen.cjk_qq.MainActivity;
import com.hellochen.cjk_qq.MyActivity;
import com.hellochen.cjk_qq.R;
import com.hellochen.cjk_qq.mode.AccountDao;
import com.hellochen.cjk_qq.mode.UserDBHelper;

public class Login extends Activity {
    private UserDBHelper userDBHelper;
    private AccountDao accountDao;

    private TextView getTV_1, getTV_2, getTV_3;
    private EditText et_login_username,et_login_password;
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

        getIB_1.setOnClickListener(V -> {
            progressDialog();
        });
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
    public void progressDialog(){
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

                                Toast.makeText(Login.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                progressDialog.dismiss();
            }
        }.start();
        progressDialog.show();
    }

    private void getLoginUser(){
        String username,passeord,
    }
}