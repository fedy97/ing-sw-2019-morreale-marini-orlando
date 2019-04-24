package it.polimi.se2019.network.client;

import java.io.IOException;

/**
 * Interface of the remote client to be implemented with Socket or RMI
 *
 * @author Gabriel Raul Marini
 */
public interface Client {

    void disconnect() throws IOException;
}
