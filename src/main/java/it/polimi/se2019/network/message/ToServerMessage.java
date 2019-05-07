package it.polimi.se2019.network.message;

import it.polimi.se2019.exceptions.InvalidPayloadException;
import it.polimi.se2019.view.server.VirtualView;

public abstract class ToServerMessage extends Message {

    public ToServerMessage(Object payload) {
        super(payload);
    }

    public abstract void performAction(VirtualView virtualView);
}