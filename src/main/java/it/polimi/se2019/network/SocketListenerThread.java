package it.polimi.se2019.network;

import java.net.Socket;

public class SocketListenerThread extends Thread {
    protected Socket socket;

    public SocketListenerThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {

    }
}

