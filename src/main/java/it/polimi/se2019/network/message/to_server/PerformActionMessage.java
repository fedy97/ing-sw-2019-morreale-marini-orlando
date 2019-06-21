package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;


public class PerformActionMessage extends ToServerMessage {

    public PerformActionMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller c = Controller.getInstance();
        c.processAction((String) payload);
    }
}
