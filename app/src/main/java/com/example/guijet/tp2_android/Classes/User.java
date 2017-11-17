package com.example.guijet.tp2_android.Classes;

/**
 * Created by Guijet on 2017-10-26.
 */

public class User {

    private String ipAdress;
    private String username;

    public User(String toShow,Boolean isIpAdress) {
        if (isIpAdress) {
            this.username = toShow;
        } else {
            this.username = toShow;
            this.ipAdress = null;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getIpAdress() {
        return ipAdress;
    }
}
