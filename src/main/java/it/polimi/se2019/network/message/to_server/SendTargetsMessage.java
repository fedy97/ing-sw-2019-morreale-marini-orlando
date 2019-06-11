package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.ArrayList;
import java.util.List;

public class SendTargetsMessage extends ToServerMessage {
    public SendTargetsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller c = Controller.getInstance();
        List<String> targets = (ArrayList<String>) payload;
        HandyFunctions.printList(targets);
        for (String target : targets)
            c.getCurrentTargets().add(Character.valueOf(target));
    }
}
