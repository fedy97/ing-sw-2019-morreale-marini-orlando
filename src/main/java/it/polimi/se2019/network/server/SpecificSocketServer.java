package it.polimi.se2019.network.server;

import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.network.message.ToServerMessage;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

public class SpecificSocketServer extends Thread {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String user;
    private SocketServer ss;

    public SpecificSocketServer(SocketServer ss, Socket socket, ObjectInputStream input, ObjectOutputStream output,String user) {
        this.socket = socket;
        this.input = input;
        this.output = output;
        this.user = user;
        this.ss = ss;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                ToServerMessage msg;
                msg = (ToServerMessage) input.readObject();

                if (msg != null)
                    ss.interpretMessage(msg, user);

                Thread.sleep(100);
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.SEVERE, e.toString());
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public ObjectInputStream getInput() {
        return input;
    }
}
