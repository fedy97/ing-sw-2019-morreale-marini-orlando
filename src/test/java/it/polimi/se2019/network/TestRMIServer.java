package it.polimi.se2019.network;

import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.server.RMIServer;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class that verify if RMI server works properly in correct client behaviour scenario
 *
 * @author Gabriel Raul Marini
 */
public class TestRMIServer {
    RMIClient client;

    /**
     * Initialize the test server to test the client behaviour
     */
    @Before
    public void initTest() {
        RMIServer testServer = new RMIServer(null, 1352);
        testServer.start();
        client = new RMIClient(null, 1232);
    }

    /**
     * Verify if the client is able to connect to the test server
     */
    @Test
    public void testConnect() {
        try {
            client.connect(InetAddress.getLocalHost().getHostAddress(), 1352);
        } catch (UnknownHostException e) {
            fail();
        }

        assertTrue(client.isConnected());
    }


}
