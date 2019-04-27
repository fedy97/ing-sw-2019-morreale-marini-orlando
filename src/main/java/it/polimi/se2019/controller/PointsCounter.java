package it.polimi.se2019.controller;

import it.polimi.se2019.model.player.Player;

import java.util.List;


/**
 * @author Federico Morreale
 */
public interface PointsCounter{

    /**
     * @return The player who won the game
     */
    Player getWinner();

    void calculatePoints();

    /**
     * @return
     */
    List<Player> getActualRanking();

    /**
     * @return
     */
    List<Player> getFinalRanking();

}