package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.network.message.Message;

public abstract class ToServerMessage extends Message {
    protected String sender;

    public ToServerMessage(Object payload) {
        super(payload);
    }

    public abstract void performAction();

    /**
     * @param sender username of the client sending the message
     */
    public void setSender(String sender){
        this.sender = sender;
    }

    /**
     * @return the username of the sender client
     */
    public String getSender(){
        return sender;
    }
}