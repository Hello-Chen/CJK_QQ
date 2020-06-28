package com.hellochen.cjk_qq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class MyActivity extends AppCompatActivity {
    private Button mbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mbtn=findViewById(R.id.jd);
        mbtn.setOnClickListener(V->{
            ProgressDialog progressDialog = new ProgressDialog(MyActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
           // progressDialog.setTitle("正在加载");
            progressDialog.setMessage("正在登陆，请稍后...");
            new Thread(){
                @Override
                public void run() {
                    for (int i=0;i<=100;i++){
                        progressDialog.setProgress(i);
                        try {
                            sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    progressDialog.dismiss();
                }
            }.start();
            progressDialog.show();
        });
        findViewById(R.id.tc).setOnClickListener(V->{
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("退出（普通对话框）")
                    .setMessage("确定要退出码？")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create();//2.创建
            alertDialog.show();//3.显示（show）
        });
    }
}