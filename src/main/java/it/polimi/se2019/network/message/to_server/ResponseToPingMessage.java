package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;

public class ResponseToPingMessage extends ToServerMessage {
    public ResponseToPingMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String pingChar = (String) payload;
        Controller c = Controller.getInstance();
        c.getPingsList().add(pingChar);
    }
}
