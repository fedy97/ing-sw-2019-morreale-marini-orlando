package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.toclient.ToClientMessage;
import it.polimi.se2019.network.message.toclient.UsernameAlreadyInUseMessage;
import it.polimi.se2019.network.message.toserver.ToServerMessage;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.view.client.RemoteView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

/**
 * RMI implementation of remote client
 *
 * @author Gabriel Raul Marini
 */
public class RMIClient implements Client, Observer {
    private Server stub;
    private RemoteView actor;
    private boolean connected;
    private int port;
    private Registry registry;
    private String user;

    public RMIClient(RemoteView actor, int port, String user) {
        this.actor = actor;
        this.port = port;
        this.user = user;

        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }

        connected = false;
    }

    /**
     * Connect to the remote object on the server and register itself as skeleton calling
     * remote method registerClient() of the RMIServer
     *
     * @param host address of the server
     * @param port to bind in order to retrieve the stub
     */
    public void connect(String host, int port) {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(host, port);
            stub = (Server) remoteRegistry.lookup("FakeView");
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }

        exportRemoteObject();

        try {
            stub.registerClient(getLocalIp(), this.port, user);
            if (!stub.isUsed()) {
                connected = true;
                CustomLogger.logInfo(this.getClass().getName(), "Client is connected!");
            } else {
                stub.setUsed(false);
                interpretMessage(new UsernameAlreadyInUseMessage(user));
            }
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    /**
     * Export the remote reference of RemoteView used to call his methods
     */
    private void exportRemoteObject() {
        try {
            Client skeleton = (Client) UnicastRemoteObject.exportObject(this, port);
            registry.bind("RemoteView", skeleton);
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    /**
     * Disconnect from RMI server, unbind the local registry
     */
    public void disconnect() {
        stub = null;
        try {
            registry.unbind("RemoteView");
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
        connected = false;
    }

    /**
     * @return if the client is connected to the server stub
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * @param msg to be interpreted
     */
    @Override
    public void interpretMessage(ToClientMessage msg) {
        try {
            msg.performAction(actor);
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    /**
     * @param msg containing the set of action to be performed on the corresponding
     *            server side virtual view
     */
    private void callServer(ToServerMessage msg) {
        try {
            stub.interpretMessage(msg);
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    /**
     * Useful method created because InetAddress always return  127.0.0.1
     *
     * @return the local ip address
     */
    private String getLocalIp() {
        String rightIp = "";

        try {
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            while (n.hasMoreElements()) {
                NetworkInterface e = n.nextElement();
                Enumeration<InetAddress> a = e.getInetAddresses();
                while (a.hasMoreElements()) {
                    InetAddress addr = a.nextElement();
                    if (addr.getHostAddress().contains("192.168.") || addr.getHostAddress().contains("10."))
                        rightIp = addr.getHostAddress();
                }
            }
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
        return rightIp;
    }

    /**
     * it recives the updates from GUI or CLI
     *
     * @param o   GUI/CLI
     * @param arg the message sent from gui or cli
     */
    @Override
    public void update(Observable o, Object arg) {
        callServer((ToServerMessage) arg);
    }
}
