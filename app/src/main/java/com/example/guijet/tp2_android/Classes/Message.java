package com.example.guijet.tp2_android.Classes;

/**
 * Created by Guijet on 2017-10-26.
 */

public class Message {

    private String message;
    private String username;
    private Boolean isSender;

    public Message(String message, String username, Boolean isSender){
        this.message = message;
        this.username = username;
        this.isSender = isSender;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSender() {
        return isSender;
    }

    public String getUsername() {
        return username;
    }
}
