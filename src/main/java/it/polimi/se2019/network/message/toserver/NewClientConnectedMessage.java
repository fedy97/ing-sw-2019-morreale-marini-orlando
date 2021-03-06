package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.network.message.toclient.NewConnectionMessage;

public class NewClientConnectedMessage extends ToServerMessage {
    public NewClientConnectedMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(){
        String user = (String) payload;
        Controller c = Controller.getInstance();
        c.getPingsWaitingList().add(user);
        for (String userToNotify : c.getTurnController().getUsers()) {
            c.callView(new NewConnectionMessage(c.getTurnController().getUsers()), userToNotify);
        }
    }
}
