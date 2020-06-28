package com.hellochen.cjk_qq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //跳转百度
        findViewById(R.id.baidu).setOnClickListener(V -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse("https://www.baidu.com/");//此处填链接
            intent.setData(content_url);
            startActivity(intent);
        });

        // 1 Android直接拨打电话
        findViewById(R.id.phone).setOnClickListener(V -> {
            Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 10086));//直接拨打电话
            Toast.makeText(MainActivity.this, "正在拨打10086", Toast.LENGTH_SHORT).show();
            startActivity(dialIntent);
            findViewById(R.id.phone).setOnLongClickListener(v -> {
                Intent dialIntent1 = new Intent(Intent.ACTION_CALL_BUTTON);//跳转到拨号界面
                startActivity(dialIntent1);
                return false;
            });
           /*// 2 Android跳转到拨号界面
            Intent dialIntent =  new Intent(Intent.ACTION_CALL_BUTTON);//跳转到拨号界面
            startActivity(dialIntent);
          //  3 Android跳转到拨号界面，同时传递电话号码
            Intent dialIntent =  new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
            startActivity(dialIntent);*/
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取MenuInflater
        MenuInflater inflater = getMenuInflater();
        //加载Menu资源
        inflater.inflate(R.menu.menu_user, menu);

        //此方法必须返回true，否则不予显示
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.allUser:
                //跳转查询界面
                return true;
        }
        return super.onContextItemSelected(item);

    }
}