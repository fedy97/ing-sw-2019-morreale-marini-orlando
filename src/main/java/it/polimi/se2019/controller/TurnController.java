package it.polimi.se2019.controller;

import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.*;
import java.util.logging.Level;

/**
 * @author Gabriel Raul Marini
 */
public class TurnController {
    private String curr;
    private String first;
    private List<String> turningOrder;
    private Timer turnTimer;

    public TurnController() {
        turningOrder = new ArrayList<>();
        turnTimer = new Timer();
    }

    /**
     * Init the game logic
     */
    public void start() {
        curr = first;
        Controller.getInstance().getPlayerManager().setCurrentPlayer(Controller.getInstance().getGame().getPlayer(first));
    }

    /**
     * @param user to be added to the ordered list
     */
    public void addUser(String user) {
        if (turningOrder.isEmpty())
            first = user;
        turningOrder.add(user);
    }

    /**
     * End the turn of the current user and switch to the next
     */
    public void endTurn() {
        Controller c = Controller.getInstance();
        int currIndex = turningOrder.indexOf(curr);
        if ((currIndex + 1) == turningOrder.size()) {
            curr = turningOrder.get(0);

            for (int i = 0; i < c.getDecksManager().getToFill().size(); i++) {
                try {
                    AmmoCard newAmmo = c.getDecksManager().getNewAmmoCard(c.getDecksManager().getAmmoGarbageDeck().get(i));
                    c.getDecksManager().getToFill().get(i).setPlatformAmmoCard(newAmmo);
                } catch (Exception e) {
                    HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
                }
            }
            c.getDecksManager().getToFill().clear();
            c.getDecksManager().getAmmoGarbageDeck().clear();
        } else
            curr = turningOrder.get(currIndex + 1);
        c.getPlayerManager().setCurrentPlayer(c.getGame().getPlayer(curr));
    }

    /**
     * @return The player who has the turn
     */
    public String getTurnUser() {
        return curr;
    }

    public List<String> getUsers() {
        return turningOrder;
    }

    public void removeUser(String user) {
        turningOrder.remove(user);
    }

    /**
     * @return The current value of the timer
     */
    public Timer getTurnTimer() {
        return turnTimer;
    }


}