package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.EnablePlayerMessage;
import it.polimi.se2019.network.message.to_client.ShowChoosePowerUpMessage;
import it.polimi.se2019.network.message.to_client.ShowMessage;
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
        Controller c = Controller.getInstance();
        c.getPlayerManager().setCurrentPlayer(Controller.getInstance().getGame().getPlayer(first));

    }

    public void notifyFirst() {
        Controller c = Controller.getInstance();
        c.callView(new EnablePlayerMessage(true), c.getPlayerManager().getCurrentPlayer().getName());
        c.callView(new ShowMessage("It's your turn!"), c.getPlayerManager().getCurrentPlayer().getName());
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
                    c.getGame().notifyChanges();
                } catch (Exception e) {
                    HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
                }
            }
            c.getDecksManager().getToFill().clear();
            c.getDecksManager().getAmmoGarbageDeck().clear();
        } else
            curr = turningOrder.get(currIndex + 1);
        c.getPlayerManager().setCurrentPlayer(c.getGame().getPlayer(curr));
        Player currPlayer = c.getPlayerManager().getCurrentPlayer();
        for (Player player : c.getGame().getPlayers()) {
            if (player.equals(currPlayer)) {
                c.callView(new EnablePlayerMessage(true), player.getName());
                c.callView(new ShowMessage("It's your turn!"), player.getName());
                if (currPlayer.getCurrentPlatform() == null) {
                    try {
                        List<CardRep> cardReps = new ArrayList<>();
                        PowerUpCard p1 = c.getDecksManager().drawPowerUp();
                        PowerUpCard p2 = c.getDecksManager().drawPowerUp();
                        c.getPlayerManager().getCurrentPlayer().addPowerUpCard(p1);
                        c.getPlayerManager().getCurrentPlayer().addPowerUpCard(p2);
                        cardReps.add(new CardRep(HandyFunctions.getSystemAddress(p1), p1.getName(), p1.getDescription(), p1.getImgPath()));
                        cardReps.add(new CardRep(HandyFunctions.getSystemAddress(p2), p2.getName(), p2.getDescription(), p2.getImgPath()));
                        ShowChoosePowerUpMessage message = new ShowChoosePowerUpMessage(cardReps);
                        c.callView(message, currPlayer.getName());
                    } catch (InvalidCardException ex) {
                        HandyFunctions.LOGGER.log(Level.SEVERE, ex.getMessage());
                    }

                }
            } else {
                c.callView(new EnablePlayerMessage(false), player.getName());
                c.callView(new ShowMessage("It's ".concat(c.getPlayerManager().getCurrentPlayer().getName()).concat(" turn!")), player.getName());
            }
        }
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