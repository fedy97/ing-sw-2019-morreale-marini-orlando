package it.polimi.se2019.network.server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.network.message.to_server.ToServerMessage;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.server.VirtualView;

import java.io.EOFException;
import java.io.IOException;
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
    private VirtualView specificVirtualView;

    public SpecificSocketServer(SocketServer ss, Socket socket, ObjectInputStream input, ObjectOutputStream output, VirtualView vv) {
        this.socket = socket;
        this.input = input;
        this.output = output;
        this.ss = ss;
        this.specificVirtualView = vv;
        this.user = vv.getUser();

    }

    @Override
    public void run() {
        while (!socket.isClosed())  {
            try {
                ToServerMessage msg;
                msg = (ToServerMessage) input.readObject();

                if (msg != null)
                    ss.interpretMessage(msg);

                Thread.sleep(100);
            } catch (EOFException e) {
                try {
                    input.close();
                    output.close();
                    socket.close();
                    //Controller.getInstance().getTurnController().removeUser(user);
                } catch (IOException ex) {
                    HandyFunctions.LOGGER.log(Level.INFO, user + " stream of " + user + " already closed");
                }
                HandyFunctions.LOGGER.log(Level.FINE, user + " closed the connection");
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
