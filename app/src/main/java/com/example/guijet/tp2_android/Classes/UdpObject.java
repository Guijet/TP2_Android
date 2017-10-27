package com.example.guijet.tp2_android.Classes;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guertz on 27/10/17.
 */

public class UdpObject {
    private String adresseName;
    private String username;
    private String port;
    private String adresseMulticast;

    public UdpObject(String adresseName, String username, String port, String adresseMulticast) {
        setAdresseName(adresseName);
        setUsername(username);
        setPort(port);
        setAdresseMulticast(adresseMulticast);
    }

    //region get-set

    public String getAdresseMulticast() {
        return adresseMulticast;
    }

    public String getAdresseName() {
        return adresseName;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public void setAdresseMulticast(String adresseMulticast) {
        this.adresseMulticast = adresseMulticast;
    }

    public void setAdresseName(String adresseName) {
        this.adresseName = adresseName;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //endregion
}