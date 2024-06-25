package com.example.chatroom;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class Clinte {


    public static void main(String[] args) {

        new Clinte();

    }

    public static String  Username;
    public static String Operation = "Failed";
    public static String MessageToAll;


    public Clinte() {

    }

    static Vector<String> allMessages = new Vector<String>();


    public Clinte(String Message) {
        try {
            Socket mySocket = new Socket("192.168.1.49", 5005);
            DataInputStream dis = new DataInputStream(mySocket.getInputStream());
            PrintStream ps = new PrintStream(mySocket.getOutputStream());
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        //String Message = input.next();
                        ps.println(Message);
                        if (Message.trim().toLowerCase().equals("exit")) {
                            System.exit(0);
                        }
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                        try {
                            String OperationResponse = dis.readLine();
                            String[] cutter = OperationResponse.split("\\.");

                             Operation = cutter[0];
                            if (Operation.equals("Success")) {
                                Username = cutter[1];

                            while (true) {
                                String receivedMessage = dis.readLine();
                                allMessages.add(receivedMessage);
                            }
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            .start();
        }catch (IOException e) {
            e.printStackTrace();
        }



    }

}