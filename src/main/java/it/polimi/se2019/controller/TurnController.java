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
     * @return
     */
    public Player getPlayerTurn() {
        return playerTurn;
    }

    /**
     * @return
     */
    public List<Player> getTurningOrder() {
        return turningOrder;
    }

    /**
     * @return
     */
    public Timer getTurnTimer() {
        return turnTimer;
    }

    private void stopTimer() {
        //TODO
    }

}