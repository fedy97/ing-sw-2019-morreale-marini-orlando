package it.polimi.se2019.network;

import it.polimi.se2019.utils.CustomLogger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public final class IpFinder {

    private IpFinder() {
    }

    /**
     * Useful method created because InetAddress always return  127.0.0.1
     *
     * @return the local ip address
     */
    public static String getLocalIp() {
        String rightIp = "";

        try {
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            while (n.hasMoreElements()) {
                NetworkInterface e = n.nextElement();
                Enumeration<InetAddress> a = e.getInetAddresses();
                while (a.hasMoreElements()) {
                    InetAddress addr = a.nextElement();
                    if (addr.getHostAddress().contains("192.168."))
                        rightIp = addr.getHostAddress();
                }
            }
        } catch (Exception e) {
            CustomLogger.logException(IpFinder.class.getName(), e);
        }

        System.out.println("Right ip: "+ rightIp);
        return rightIp;
    }
}
