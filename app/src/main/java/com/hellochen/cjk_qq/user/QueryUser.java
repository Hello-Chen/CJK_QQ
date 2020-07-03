package com.hellochen.cjk_qq.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hellochen.cjk_qq.R;
import com.hellochen.cjk_qq.DatabaseQuery.AccountDao;
import com.hellochen.cjk_qq.DatabaseQuery.UserDBHelper;
import com.hellochen.cjk_qq.other.TipHelper;

import java.util.List;

public class QueryUser extends AppCompatActivity {
    private ListView lv_queryuser;
    private UserDBHelper userDBHelper;
    private AccountDao accountDao;
    private UserAdapter userAdapter;
    private List<User> lists;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
          switch (msg.what){
              case 1:
                  lists = accountDao.getAccountAll();
                  userAdapter =new UserAdapter(QueryUser.this,lists);

                  lv_queryuser.setAdapter(userAdapter);
                  break;
          }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_user);

        userDBHelper = new UserDBHelper(this);
        accountDao = new AccountDao(this);

        lv_queryuser = findViewById(R.id.lv_queryuser);

        lists = accountDao.getAccountAll();

        userAdapter =new UserAdapter(this,lists);

        lv_queryuser.setAdapter(userAdapter);
        lv_queryuser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = lists.get(position);
                accountDao.deleteContactByHxId(user.getUsername());
                TipHelper.Vibrate(QueryUser.this, new long[]{150,10}, false);
                Toast.makeText(QueryUser.this, "删除成功！", Toast.LENGTH_SHORT).show();
                Message msg = Message.obtain();
                msg.what=1;
                handler.sendMessage(msg);

                return false;
            }
        });
    }
}
