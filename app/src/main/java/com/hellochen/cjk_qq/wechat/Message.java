package com.hellochen.cjk_qq.wechat;

public class Message {
    public static final int TYPE_RECIVE = 0;
    public static final int TYPE_SEND = 1;
    private String content;
    private int type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Message(String content, int type){
        this.content = content;
        this.type = type;
    }
}
