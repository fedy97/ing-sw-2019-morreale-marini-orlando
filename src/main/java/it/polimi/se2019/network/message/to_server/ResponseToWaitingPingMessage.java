package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;

public class ResponseToWaitingPingMessage extends ToServerMessage {
    public ResponseToWaitingPingMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String pingUser = (String) payload;
        Controller c = Controller.getInstance();
        c.getPingsWaitingList().add(pingUser);
    }
}
