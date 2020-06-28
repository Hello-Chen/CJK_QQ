package com.hellochen.cjk_qq.wechat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hellochen.cjk_qq.R;

import java.util.List;

public class MsgAtapter extends RecyclerView.Adapter<MsgAtapter.MyViewHoder> {

    private Context mcontext;
    private List<Message> msg;

    public MsgAtapter(Context context, List<Message> msgList){
        this.mcontext = context;
        this.msg = msgList;
    }
    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_recyclerview_layout,parent,false);
        MyViewHoder myViewHoder = new MyViewHoder(view);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
        Message message = msg.get(position);
        if(message.getType()== Message.TYPE_RECIVE){
            holder.leftlayout.setVisibility(View.VISIBLE);
            holder.rightlayout.setVisibility(View.GONE);
            holder.leftmsg.setText(message.getContent());
        }
        if(message.getType()==Message.TYPE_SEND){
            holder.leftlayout.setVisibility(View.GONE);
            holder.rightlayout.setVisibility(View.VISIBLE);
            holder.rightmsg.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msg.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder{

        private LinearLayout rightlayout;
        private LinearLayout leftlayout;
        private TextView rightmsg;
        private TextView leftmsg;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            rightlayout = itemView.findViewById(R.id.right_layout);
            leftlayout = itemView.findViewById(R.id.left_layout);
            rightmsg = itemView.findViewById(R.id.right_msg);
            leftmsg = itemView.findViewById(R.id.left_msg);
        }
    }
}
