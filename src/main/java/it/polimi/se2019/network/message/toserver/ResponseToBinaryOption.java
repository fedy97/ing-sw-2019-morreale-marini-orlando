package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;


public class ResponseToBinaryOption extends ToServerMessage {
    public ResponseToBinaryOption(Object payload) {
        super(payload);
    }

    @Override
    /**
     */
    public void performAction(){
        Controller actor = Controller.getInstance();
        Boolean option = (Boolean) payload;
        actor.getChosenBinaryOption().add(option);
    }
}
