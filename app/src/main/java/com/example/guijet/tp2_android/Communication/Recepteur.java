package com.example.guijet.tp2_android.Communication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.guijet.tp2_android.Activity2.SecondActivity;
import com.example.guijet.tp2_android.Classes.Message;
import com.example.guijet.tp2_android.Classes.UdpObject;
import com.example.guijet.tp2_android.Classes.User;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by guertz on 27/10/17.
 */

public class Recepteur extends AsyncTask<Void,Message,Boolean> {
    private static final int LONG_TAMPON = 1024;
    private UdpObject udpObject;
    private Context ctx;
    private Boolean showIp;

    public Recepteur(Context ctx,UdpObject object) {
        this.ctx = ctx;
        this.udpObject = object;
        showIp = false;
    }

    public void setShowIp(boolean showIp) {
        this.showIp = showIp;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            byte tampon[] = new byte[LONG_TAMPON];
            InetAddress adress = InetAddress.getByName(udpObject.getAdresseMulticast());
            DatagramPacket paquet =
                    new DatagramPacket(tampon, 0, LONG_TAMPON);
            MulticastSocket socket = new MulticastSocket(Integer.parseInt(udpObject.getPort()));
            socket.joinGroup(adress);
            boolean fini = false;
            while (!fini) {
                socket.receive(paquet);

                // fabrication d'une chaîne à partir d'un tableau d'octets
                String chaine = new String(paquet.getData(),
                        paquet.getOffset(), paquet.getLength() );
                Message message = null;
                if (showIp) {
                    String username = "";
                    int i = chaine.indexOf(":");
                    if (i > 0) {
                        username = chaine.substring(0, i);
                        chaine = chaine.substring(i + 1, chaine.length());
                    }
                    publishProgress(new Message(chaine,new User(paquet.getAddress().toString().replace("/",""),true),username.equals(udpObject.getUsername()) ? 1 : 0));
                } else {
                    String username = "";
                    int i = chaine.indexOf(":");
                    if (i > 0) {
                        username = chaine.substring(0,i);
                        chaine = chaine.substring(i + 1, chaine.length());
                    }
                    publishProgress(new Message(chaine,new User(username,false),username.equals(udpObject.getUsername()) ? 1 : 0));
                }
            }
            return true;
        } catch( Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onProgressUpdate(Message... messages) {
        SecondActivity.messages.add(messages[0]);
        SecondActivity.adapter.notifyDataSetChanged();
        SecondActivity.recyclerView.smoothScrollToPosition(SecondActivity.messages.size());
    }
}
