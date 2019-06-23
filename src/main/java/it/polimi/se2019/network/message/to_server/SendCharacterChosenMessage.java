package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.utils.CustomLogger;

public class SendCharacterChosenMessage extends ToServerMessage {

    public SendCharacterChosenMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        try {
            Controller.getInstance().setCharacterChosen(this.sender, (String) payload);
        } catch (Exception ex) {
            CustomLogger.logException(this.getClass().getName(), ex);
        }
    }
}
