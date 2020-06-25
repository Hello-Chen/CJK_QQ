package com.hellochen.cjk_qq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
            progressDialog.setTitle("dds");
            progressDialog.setMessage("正在下载...");
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

    }
}