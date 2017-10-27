package com.example.guijet.tp2_android.Communication;

import java.net.*;

public class RecepteurMulticast {
    private static final int LONG_TAMPON = 1024;
    private static final int PORT = 30000;
    private Boolean showIp = false;

    private RecepteurMulticast(Boolean ip) {
        this.showIp = ip;
    }

    private void recevoir() {
        try {
            byte tampon[] = new byte[LONG_TAMPON];
            InetAddress adress = InetAddress.getByName("230.0.0.30");
            DatagramPacket paquet =
                    new DatagramPacket(tampon, 0, LONG_TAMPON);
            MulticastSocket socket = new MulticastSocket(PORT);
            socket.joinGroup(adress);
            boolean fini = false;
            System.out.println("Attente de messages...");
            while (!fini) {
                socket.receive(paquet);

                // fabrication d'une chaîne à partir d'un tableau d'octets
                String chaine = new String(paquet.getData(),
                        paquet.getOffset(), paquet.getLength() );

                if (showIp) {
                    int i = chaine.indexOf(":");
                    if (i > 0)
                        chaine = chaine.substring(i + 1, chaine.length());
                    chaine = paquet.getAddress().toString().replace("/","")+":" + chaine;
                    System.out.println(chaine);
                } else {
                    System.out.println(chaine);
                }
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0 && args.length == 1) {
            if (args[0].equals("-ip"))
                new RecepteurMulticast(true).recevoir();
            else {
                System.out.println("Mauvaise commande.");
            }
        } else if (args.length > 1) {
            System.out.println("Mauvaise commande, trop de parametres entres.");
        } else {
            new RecepteurMulticast(false).recevoir();
        }
    }
}
