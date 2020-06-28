package com.hellochen.cjk_qq.user;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hellochen.cjk_qq.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> lists;
    private TextView tv_item_username,tv_item_name,tv_item_sex,tv_item_age;

    public UserAdapter(Context context, List<User> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.user_list_item,null);

        tv_item_username=convertView.findViewById(R.id.tv_item_username);
        tv_item_name=convertView.findViewById(R.id.tv_item_name);
        tv_item_sex=convertView.findViewById(R.id.tv_item_sex);
        tv_item_age=convertView.findViewById(R.id.tv_item_age);

        User user = lists.get(position);
        tv_item_username.setText(user.getUsername());
        tv_item_name.setText(user.getName());
        tv_item_sex.setText(user.getSex());
        tv_item_age.setText(user.getAge());
        return convertView;
    }


}
