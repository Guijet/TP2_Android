package com.example.guijet.tp2_android.Classes;

/**
 * Created by Guijet on 2017-10-26.
 */

public class Message {

    private String content;
    private User user;
    private Integer isSender;

    public Message(String content, User user, Integer isSender){
        this.content = content;
        this.user = user;
        this.isSender = isSender;
    }

    public String getContent() {
        return content;
    }

    public Integer getSender() {
        return isSender;
    }

    public User getUser() {
        return user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsSender(Integer isSender) {
        this.isSender = isSender;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
