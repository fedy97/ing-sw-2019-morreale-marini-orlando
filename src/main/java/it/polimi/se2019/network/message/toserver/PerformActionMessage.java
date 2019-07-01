package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.utils.CustomLogger;


public class PerformActionMessage extends ToServerMessage {

    public PerformActionMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller c = Controller.getInstance();
        try {
            c.processAction((String) payload);
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }
}
