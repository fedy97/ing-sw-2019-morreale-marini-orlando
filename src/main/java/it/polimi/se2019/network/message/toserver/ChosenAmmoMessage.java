package it.polimi.se2019.network.message.toserver;

import it.polimi.se2019.controller.Controller;

public class ChosenAmmoMessage extends ToServerMessage {

    public ChosenAmmoMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String color = (String) payload;
        Controller.getInstance().getChosenAmmo().add(color);
    }
}
