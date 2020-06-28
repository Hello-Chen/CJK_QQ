package com.hellochen.cjk_qq;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private TextView getTV_1, getTV_2, getTV_3;
    private ImageButton getIB_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取控件id并赋值
        getTV_1 = findViewById(R.id.forgetPassword);
        getTV_2 = findViewById(R.id.userRegister);
        getTV_3 = findViewById(R.id.designer);
        getIB_1 = findViewById(R.id.login);

        //监听事件函数
        setListeners();
    }

    private void setListeners() {
        OnClick onClick = new OnClick();
        getTV_1.setOnClickListener(onClick);
        getTV_2.setOnClickListener(onClick);
        getTV_3.setOnClickListener(onClick);
        getIB_1.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        Intent intent = null;

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.forgetPassword:
                    intent = new Intent(Login.this, forgetPassword.class);
                    break;
                case R.id.designer:
                    intent = new Intent(Login.this, MyActivity.class);
                    break;
                case R.id.userRegister:
                    intent=new Intent(Login.this,register.class);
                    break;
                case R.id.login:
                    ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setTitle("正在加载");
                    progressDialog.setMessage("正在下载...");
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
                            }
                            progressDialog.dismiss();
                        }
                    }.start();
                    progressDialog.show();
                    intent = new Intent(Login.this, MainActivity.class);
            }
            startActivity(intent);
        }
    }

}