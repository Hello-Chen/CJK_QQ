package com.hellochen.cjk_qq.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hellochen.cjk_qq.R;
import com.hellochen.cjk_qq.user.User;
import com.hellochen.cjk_qq.DatabaseQuery.AccountDao;
import com.hellochen.cjk_qq.DatabaseQuery.UserDBHelper;

public class ForgetPassword extends AppCompatActivity {
    private EditText et_find_username;
    private TextView tv_find_password;
    private Button bt_find;

    private UserDBHelper userDBHelper;
    private AccountDao accountDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();

        et_find_username = findViewById(R.id.et_find_username);
        tv_find_password = findViewById(R.id.tv_find_password);
        bt_find = findViewById(R.id.bt_find);

        userDBHelper = new UserDBHelper(this);
        accountDao = new AccountDao(this);

        bt_find.setOnClickListener(V->{
            String username = et_find_username.getText().toString();
            User user = accountDao.getAccountuser(username);
            if (user==null){
                 Toast.makeText(ForgetPassword.this, "没有找到用户", Toast.LENGTH_SHORT).show();
                 return;
            }else{
                tv_find_password.setVisibility(View.VISIBLE);
                tv_find_password.setText("密码："+user.getPassword());
            }
        });
    }
}