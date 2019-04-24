package it.polimi.se2019.controller;

import it.polimi.se2019.model.player.Player;


/**
 * @author Federico Morreale
 */
public abstract class PointsCounter {

    /**
     *
     */
    public PointsCounter() {
    }

    /**
     * @return The player who won the game
     */
    public Player getWinner() {
        return null;
    }

    public void calculatePoints() {

    }

    /**
     * @return
     */
    public Player getActualRanking() {
        return null;
    }

    /**
     * @return
     */
    public Player getFinalRanking() {
        return null;
    }

}