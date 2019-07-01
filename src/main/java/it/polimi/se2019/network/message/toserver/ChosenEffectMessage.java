package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;

public class ChosenEffectMessage extends ToServerMessage {

    public ChosenEffectMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller.getInstance().getChosenEffect().add((Integer) payload);
    }
}
