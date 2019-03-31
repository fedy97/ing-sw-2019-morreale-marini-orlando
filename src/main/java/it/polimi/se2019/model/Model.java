package it.polimi.se2019.model;

import java.util.*;
import it.polimi.se2019.model.board.*;
import it.polimi.se2019.model.player.*;
import it.polimi.se2019.model.enumeration.*;

/**
 * 
 */
public class Model {

    /**
     * Default constructor
     */
    public Model() {
    }

    /**
     * 
     */
    public Table table;

    /**
     * 
     */
    public GameState gameState;

    /**
     * 
     */
    public ArrayList<Player> players;




    /**
     * 
     */
    public void setupGame() {
        // TODO implement here
    }

    /**
     * 
     */
    public void startGame() {
        // TODO implement here
    }

    /**
     * 
     */
    public void setFigureToPlayer() {
        // TODO implement here
    }

    /**
     * 
     */
    public void setPlayerToPlatform() {
        // TODO implement here
    }

    /**
     * 
     */
    public void endTurn() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Player getCurrentPlayer() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void timesUp() {
        // TODO implement here
    }

    /**
     * 
     */
    public void runningOutTime() {
        // TODO implement here
    }

    /**
     * @param int 
     * @param bool
     */
    public void setSuspendedPlayer(int a, boolean b) {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean checkEnoughWeapons() {
        // TODO implement here
        return true;
    }

    /**
     * @return
     */
    public boolean checkEnoughPowerups() {
        // TODO implement here
        return true;
    }

    /**
     * @return
     */
    public boolean checkEnoughAmmos() {
        // TODO implement here
        return true;
    }

    /**
     * 
     */
    public void checkLegalMove() {
        // TODO implement here
    }

}