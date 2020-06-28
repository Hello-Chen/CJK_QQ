package com.hellochen.cjk_qq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class examActivity extends AppCompatActivity implements View.OnClickListener{
    //控件初始化
    private RadioGroup Radiogroup;
    private RadioButton a1;
    private RadioButton b2;
    private TextView textView;
    //给一个统计分数的变量
    private int count = 0;
    //
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        //初始化控件
        findViews();
    }
    //绑定控件信息
    private void findViews() {
        Radiogroup = (RadioGroup)findViewById(R.id.Radiobutton);
        a1 = (RadioButton)findViewById( R.id.a1 );
        b2 = (RadioButton)findViewById( R.id.b2 );
        textView = (TextView)findViewById(R.id.Total_Points);
    }
    //点击事件
    @Override
    public void onClick(View v) {
        if ( v == Radiogroup ) {
        }
    }
    public void Dialog(){
        //进度条对话框
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        new Thread(){
            @Override
            public void run() {
                for(int i=0 ; i<=100 ; i++){
                    progressDialog.setProgress(i);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        progressDialog.show();
    }
    //提交的点击事件
    public void Putin(View view) {
        //提示提交的对话框设置
        alertDialog = new AlertDialog.Builder(this)
                .setMessage("确定提交！")
                .setNegativeButton("取消",null)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //提示性对话框
                        //Dialog();
                        if(a1.isChecked()){
                            count = count + 10;
                        }else{
                            count += 0;
                        }
                        if(b2.isChecked()){
                            count = count + 10;
                        }else {
                            count += 0;
                        }
                        textView.setText(String.valueOf(count));
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.show();
    }
}