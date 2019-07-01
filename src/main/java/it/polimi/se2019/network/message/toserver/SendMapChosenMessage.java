package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;

public class SendMapChosenMessage extends ToServerMessage {

    public SendMapChosenMessage(Object payload){ super(payload);}

    @Override
    public void performAction() {
        Controller.getInstance().setVoteMapChosen((int) payload);
    }
}
