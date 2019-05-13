package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.utils.Deserializer;

public class MovePlayerMessage extends ToServerMessage {

    public MovePlayerMessage(Object payload){
        super(payload);
    }

    /**
     * Move the previously selected player to the selected destination
     */
    public void performAction(){
        Controller c = Controller.getInstance();
        Platform destination = Deserializer.getPlatform((String) payload);
        c.getCurrentTargets().get(0).setCurrentPlatform(destination);
    }
}
