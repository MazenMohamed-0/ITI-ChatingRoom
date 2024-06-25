package com.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(5005);
        while (true) {
            Socket s = serverSocket.accept();
            new ChatHandler(s);
        }

    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

}
