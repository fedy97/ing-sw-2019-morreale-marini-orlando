package it.polimi.se2019.network.message;

import it.polimi.se2019.exceptions.InvalidPayloadException;
import it.polimi.se2019.view.View;

import java.io.Serializable;

public abstract class Message implements Serializable {
    protected Object payload;

    public Message(Object payload){
        this.payload = payload;
    }
}
