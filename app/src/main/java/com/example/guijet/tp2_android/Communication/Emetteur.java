package com.example.guijet.tp2_android.Communication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.guijet.tp2_android.Classes.Message;
import com.example.guijet.tp2_android.Classes.UdpObject;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by guertz on 27/10/17.
 */

public class Emetteur extends AsyncTask<Void,Message,Boolean> {

    private UdpObject udpObject;
    private Context ctx;
    private Message message;

    public Emetteur(Context ctx,UdpObject udpObject,Message message) {
        this.udpObject = udpObject;
        this.ctx = ctx;
        this.message = message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            byte tampon[] = message.getContent().getBytes();
            InetAddress adresse = InetAddress.getByName(udpObject.getAdresseMulticast());
            DatagramPacket paquet = new DatagramPacket(tampon, 0, tampon.length, adresse, Integer.parseInt(udpObject.getPort()));
            MulticastSocket socket = new MulticastSocket();
            socket.send(paquet);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((Activity) ctx).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx, "Erreur réseau.", Toast.LENGTH_SHORT).show();
                }
            });
            return false;
        }
    }
}
