package it.polimi.se2019.network.message;

import java.io.Serializable;

/**
 * messages used to comunicate between clients and server
 * each message has a payload
 */
public abstract class Message implements Serializable {
    protected Object payload;

    public Message(Object payload) {
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }

}
