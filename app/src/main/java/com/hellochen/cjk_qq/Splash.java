package com.hellochen.cjk_qq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.hellochen.cjk_qq.Login.Login;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        //加载启动界面
        setContentView(R.layout.activity_splash);
        //去掉窗口标题 必须继承Activity
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏顶部状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题栏注意这句一定要写在setContentView()方法的后面
        getSupportActionBar().hide();
        int time = 1500;    //设置等待时间，单位为毫秒
        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, Login.class));
                Splash.this.finish();
            }
        }, time);
    }
}