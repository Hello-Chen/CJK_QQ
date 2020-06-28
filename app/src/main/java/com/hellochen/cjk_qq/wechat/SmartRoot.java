package com.hellochen.cjk_qq.wechat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.hellochen.cjk_qq.R;
import com.hellochen.cjk_qq.wechat.Answer;
import com.hellochen.cjk_qq.wechat.Message;
import com.hellochen.cjk_qq.wechat.MsgAtapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmartRoot extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Message> msgList = new ArrayList<>();
    private HashMap ansewer = Answer.createHashMap();
    private MsgAtapter msgAtapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartroot_layout);

        initmsg();
        recyclerView = findViewById(R.id.myrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        msgAtapter = new MsgAtapter(this,msgList);
        recyclerView.setAdapter(msgAtapter);
    }

    private List<Message> initmsg() {
        Message message = new Message("hello,what do you know?",Message.TYPE_RECIVE);
        msgList.add(message);
        return msgList;
    }

    public void send(View v){
        EditText msget = findViewById(R.id.msgEt);
        String msg = msget.getText().toString().trim();
        Message sendMessage = new Message(msg,Message.TYPE_SEND);
        msgList.add(sendMessage);
        String reciveAnswer  = (String) ansewer.get((int) (Math.random() * (ansewer.size() - 1)));
        Message reclveMessage = new Message(reciveAnswer,Message.TYPE_RECIVE);
        msgList.add(reclveMessage);
        msgAtapter.notifyDataSetChanged();
    }
}
