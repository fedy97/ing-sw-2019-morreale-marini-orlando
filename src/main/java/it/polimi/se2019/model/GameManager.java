package it.polimi.se2019.model;

import java.util.*;
import it.polimi.se2019.model.board.*;
import it.polimi.se2019.model.player.*;
import it.polimi.se2019.model.enumeration.*;


public class GameManager {

    private Table table;
    private GameState gameState;
    private ArrayList<Player> players;
    private Map<Character, Player> characterPlayer;

    public GameManager() {
    }


    public GameState getGameState() {
        // TODO
        return null;
    }

    public ArrayList<Player> getPlayers() {
        // TODO
        return null;
    }


    public void setupGame() {
        // TODO
    }


    public void startGame() {
        // TODO
    }

    /**
     *
     * @param character
     * @param player
     */
    public void setCharacterToPlayer(Character character, Player player) {
        //TODO
    }


    public void setPlayerToPlatform(Player player, Platform platform) {
        // TODO
    }


    public void endTurn() {
        // TODO
    }


    public void timesUp() {
        // TODO
    }


    public void runningOutTime() {
        // TODO
    }

    /**
     * @return the player that has the right to perform the actions
     */
    public Player getCurrentPlayer() {
        // TODO
        return null;
    }



    /**
     * @param player the player to be suspended
     */
    public void setSuspendedPlayer(Player player) {
        // TODO
    }
}