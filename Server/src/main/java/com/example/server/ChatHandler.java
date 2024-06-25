package com.example.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class ChatHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
    // String name;
    //static String Username;


    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();

    public ChatHandler(Socket cs) throws IOException {
        dis = new DataInputStream(cs.getInputStream());
        ps = new PrintStream(cs.getOutputStream());
        clientsVector.add(this);
        //this.name = name;
        start();
    }

    @Override
    public void run() {
            try {
                String CommandMessage = dis.readLine();
                String[] cutter = CommandMessage.split("\\.");
                String Operation = cutter[0];
                String Username = cutter[1];
                String Password = cutter[2];

                if (Operation.equals("Login")) {
                    boolean loginConfirm = DAO.Select_loginInfo(Username,Password);
                    if (loginConfirm) {
                        String OperationResponse = "Success."+Username+". ";
                        ps.println(OperationResponse);
                        DAO.changeStatus(true,Username);


                    } else {
                        String OperationResponse = "Failed."+Username+". ";
                        ps.println(OperationResponse);
                    }
                } else if (Operation.equals("SignUp")) {

                    DAO.insertUser(Username,Password);


                } else if (Operation.equals("changeStatus")) {
                    DAO.changeStatus(false,Username);
                }

                else {
                    sendMessageToAll(Username,Password);
                }



            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

    }



    public void sendMessageToAll(String user,String msg) {

        for (ChatHandler ch : clientsVector) {

            String Talk = "Message."+user+"."+msg;

            ch.ps.println(Talk);
        }
    }
}
