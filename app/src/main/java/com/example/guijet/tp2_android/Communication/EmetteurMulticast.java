package com.example.guijet.tp2_android.Communication;

import java.net.*;
import java.io.*;

public class EmetteurMulticast {
    static final String DESTINATION = "230.0.0.30";
    static final int PORT = 30000;
    static final int DELAI = 2000;
    static String username;
    static BufferedReader reader;
    static String ligne = null;

    public void emettre(String chaine) {
        try {
            byte tampon[] = chaine.getBytes();
            InetAddress adresse = InetAddress.getByName(DESTINATION);
            DatagramPacket paquet = new DatagramPacket(tampon, 0, tampon.length, adresse, PORT);
            MulticastSocket socket = new MulticastSocket();
            socket.send(paquet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        if(args.length > 0) {
            username = args[0];
            if(username.length() > 1 && username.length() < 13) {
                while(true) {
                    try {
                        reader = new BufferedReader(new InputStreamReader(System.in));
                        ligne = reader.readLine();
                        if(ligne.trim().isEmpty())
                            break;
                        if(ligne.length() < 61)
                            new EmetteurMulticast().emettre(username + ": " + ligne);
                        else
                            System.out.println("Le message doit avoir un maximum de 60 caracteres");
                    } catch (IOException ioe) {
                        System.err.println(ioe);
                        System.exit(1);
                    }
                }
            }
            else
                System.out.println("Le pseudonyme doit etre entre 2 et 12 caracteres");
        }
        else
            System.out.println("Veuillez specifier une pseudonyme");
    }
}