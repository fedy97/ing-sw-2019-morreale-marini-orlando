package it.polimi.se2019.controller;

import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Federico Morreale
 */
public class TurnController {

    public TurnController() {
        //TODO
    }

    private Player playerTurn;
    private List<Player> turningOrder;
    private Timer turnTimer;

    /**
     * @return The player who has the turn
     */
    public Player getPlayerTurn() {
        return playerTurn;
    }

    /**
     * @return A list indicating the order of players' turns
     */
    public List<Player> getTurningOrder() {
        return turningOrder;
    }

    /**
     * @return The current value of the timer
     */
    public Timer getTurnTimer() {
        return turnTimer;
    }

    private void stopTimer() {
        //TODO
    }

}