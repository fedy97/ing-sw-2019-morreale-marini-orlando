package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.Action;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.controller.Validator;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.network.message.to_client.AskToDiscardMessage;
import it.polimi.se2019.utils.CustomLogger;
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
        if (c.getPlayerManager().getCurrentPlayer().getName().equals(sender)) {
            try {
                if (choice.equals("action1")) {
                    c.setState(ControllerState.PROCESSING_ACTION_1);
                    destinations = v.getValidMoves(Action.MOVE);
                    c.askFor(destinations, "position");
                    c.getPlayerManager().move(c.getChosenDestination().take());
                } else if (choice.equals("action2")) {
                    c.setState(ControllerState.PROCESSING_ACTION_2);
                    destinations = v.getValidMoves(Action.GRAB);
                    c.askFor(destinations, "position");
                    Platform dest = c.getChosenDestination().take();
                    c.getPlayerManager().move(dest);

                    if (dest.isGenerationSpot()) {
                        try {
                            if (c.getPlayerManager().getCurrentPlayer().getWeaponCards().size() == 3) {
                                c.callView(new AskToDiscardMessage(null), sender);
                                WeaponCard card = c.getChosenWeapons().take();

                                if (card.getName().equals("null")) {
                                    c.setState(ControllerState.IDLE);
                                    return;
                                } else
                                    c.getPlayerManager().getCurrentPlayer().removeWeaponCard(card);
                            }

                            c.askFor(c.getValidator().getGrabableWeapons(), "weapons");
                            WeaponCard chosen = c.getChosenWeapons().take();

                            c.getPlayerManager().buyWeapon(chosen);
                        } catch (Exception e) {
                            HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        c.getPlayerManager().grabAmmoCard();
                    }

                } else if (choice.equals("action3")) {
                    c.setState(ControllerState.PROCESSING_ACTION_3);

                    try {
                        destinations = v.getValidMoves(Action.SHOOT);
                        c.askFor(destinations, "position");
                        c.getPlayerManager().move(c.getChosenDestination().take());
                    }catch (InvalidActionException e){
                        CustomLogger.logInfo(this.getClass().getName(), "You cannot move before shooting!");
                    }

                    c.askFor(c.getValidator().getReloadableWeapons(), "weapons");

                    while(c.getState() == ControllerState.PROCESSING_ACTION_3)
                        Thread.sleep(200);
                } else if (choice.equals("action4")) {
                    HandyFunctions.printList(c.getValidator().getReloadableWeapons());
                    c.askFor(c.getValidator().getReloadableWeapons(), "recharge");
                }
            } catch (Exception e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }
        }
        c.setState(ControllerState.IDLE);
    }
}
