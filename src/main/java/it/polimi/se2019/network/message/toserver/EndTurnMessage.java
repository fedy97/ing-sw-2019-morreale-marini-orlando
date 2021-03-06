package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;

public class EndTurnMessage extends ToServerMessage {
    public EndTurnMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(){
        Controller.getInstance().getTurnController().endTurn();
    }
}
