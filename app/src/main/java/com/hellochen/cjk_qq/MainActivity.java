package com.hellochen.cjk_qq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hellochen.cjk_qq.exam.examActivity;
import com.hellochen.cjk_qq.DatabaseQuery.AccountDao;
import com.hellochen.cjk_qq.DatabaseQuery.AccountTable;
import com.hellochen.cjk_qq.DatabaseQuery.UserDBHelper;
import com.hellochen.cjk_qq.other.TipHelper;
import com.hellochen.cjk_qq.user.QueryUser;
import com.hellochen.cjk_qq.wechat.SmartRoot;


public class MainActivity extends AppCompatActivity {
    private ImageButton head;
    private TextView welcome;
    private UserDBHelper userDBHelper;
    private AccountDao accountDao;
    private String username,name,sex,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDBHelper = new UserDBHelper(this);
        accountDao = new AccountDao(this);

        //点击头像弹出信息
        head = findViewById(R.id.head);
        welcome = findViewById(R.id.welcome);

        Intent intent = getIntent();

        username=intent.getStringExtra(AccountTable.COL_USERNAME);
        name=intent.getStringExtra(AccountTable.COL_NAME);
        sex=intent.getStringExtra(AccountTable.COL_SEX);
        age=intent.getStringExtra(AccountTable.COL_AGE);
        Log.d("TAG", name);

        welcome.setText("欢迎用户"+username);


        head.setOnClickListener(V->{
            AlertDialog alertDialog;
            alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("用户信息")
                    .setMessage("用户: "+username+"\r\n"+"名字: "+name+"\r\n"+"性别: "+sex+"\r\n"+"年龄: "+age+"\r\n" )
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //提示性对话框

                            dialog.dismiss();
                        }
                    }).create();
            alertDialog.show();
        });



        //跳转百度
        findViewById(R.id.baidu).setOnClickListener(V -> {
            Intent intent1 = new Intent();
            intent1.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse("https://www.baidu.com/");//此处填链接
            intent1.setData(content_url);
            startActivity(intent1);
            TipHelper.Vibrate(this, new long[]{80,10}, false);
        });

        // 1 Android直接拨打电话
        findViewById(R.id.phone).setOnClickListener(V -> {
            Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 10086));//直接拨打电话
            TipHelper.Vibrate(this, new long[]{80,10}, false);
            Toast.makeText(MainActivity.this, "正在拨打10086", Toast.LENGTH_SHORT).show();
            startActivity(dialIntent);

           /*// 2 Android跳转到拨号界面
            Intent dialIntent =  new Intent(Intent.ACTION_CALL_BUTTON);//跳转到拨号界面
            startActivity(dialIntent);
          //  3 Android跳转到拨号界面，同时传递电话号码
            Intent dialIntent =  new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
            startActivity(dialIntent);*/
        });
        findViewById(R.id.phone).setOnLongClickListener(v -> {
            Intent dialIntent1 = new Intent(Intent.ACTION_CALL_BUTTON);//跳转到拨号界面
            startActivity(dialIntent1);
            TipHelper.Vibrate(this, new long[]{80,10}, false);
            return false;
        });
        findViewById(R.id.wechat).setOnClickListener(V->{
            Intent Intent = new Intent(MainActivity.this, SmartRoot.class);
            startActivity(Intent);
            TipHelper.Vibrate(this, new long[]{80,10}, false);
        });
        findViewById(R.id.exam).setOnClickListener(V->{
            Intent Intent = new Intent(MainActivity.this, examActivity.class);
            startActivity(Intent);
            TipHelper.Vibrate(this, new long[]{80,10}, false);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.allUser:
                //跳转查询界面
                Intent dialIntent1 = new Intent(MainActivity.this, QueryUser.class);
                startActivity(dialIntent1);
                TipHelper.Vibrate(this, new long[]{80,10}, false);
                return true;
            case R.id.about:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://www.hello-chen.cn/");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
                TipHelper.Vibrate(this, new long[]{80,10}, false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}