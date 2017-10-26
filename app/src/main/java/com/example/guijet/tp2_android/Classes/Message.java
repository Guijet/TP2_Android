package com.example.guijet.tp2_android.Classes;

/**
 * Created by Guijet on 2017-10-26.
 */

public class Message {

    private String message;
    private User user;
    private Boolean isSender;

    public Message(String message, User user, Boolean isSender){
        this.message = message;
        this.user = user;
        this.isSender = isSender;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSender() {
        return isSender;
    }

    public User getUser() {
        return user;
    }
}
