package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;

public class ResponseToPingMessage extends ToServerMessage {
    public ResponseToPingMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String user = (String) payload;
        Controller c = Controller.getInstance();
        String pingChar = c.getGame().getPlayer(user).getCharacter().toString();
        c.getPingsList().add(pingChar);
    }
}
