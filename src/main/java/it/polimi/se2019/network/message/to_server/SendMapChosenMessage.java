package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;

public class SendMapChosenMessage extends ToServerMessage {

    public SendMapChosenMessage(Object payload){ super(payload);}

    @Override
    public void performAction() {
        Controller.getInstance().setVoteMapChosen((int) payload);
    }
}
