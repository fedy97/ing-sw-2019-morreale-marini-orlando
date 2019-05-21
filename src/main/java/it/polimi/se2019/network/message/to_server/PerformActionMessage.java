package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.Action;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.controller.Validator;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.List;
import java.util.logging.Level;


public class PerformActionMessage extends ToServerMessage {

    public PerformActionMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller c = Controller.getInstance();
        Validator v = c.getValidator();
        List<Platform> destinations = null;

        String choice = (String) payload;

        if (choice.equals("action1")) {
            c.setState(ControllerState.PROCESSING_ACTION_1);
            try {
                destinations = v.getValidMoves(Action.MOVE);
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
            }
            c.askFor(destinations, "position");
        } else if (choice.equals("action2")) {
            c.setState(ControllerState.PROCESSING_ACTION_2);
            try {
                destinations = v.getValidMoves(Action.GRAB);
            } catch (Exception e) {
                e.printStackTrace();
                HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
            }
            c.askFor(destinations, "position");
        } else if (choice.equals("action3")) {
            c.setState(ControllerState.PROCESSING_ACTION_3);
            try {
                destinations = v.getValidMoves(Action.SHOOT);
                c.askFor(destinations, "position");
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
            }
            List<WeaponCard> weapons = v.getUsableWeapons();
            c.askFor(weapons, "weapons");
        }

    }
}
