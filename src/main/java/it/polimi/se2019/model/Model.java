package it.polimi.se2019.model;

import java.util.*;
import it.polimi.se2019.model.board.*;
import it.polimi.se2019.model.player.*;
import it.polimi.se2019.model.enumeration.*;


public class Model {

    private Table table;
    private GameState gameState;
    private ArrayList<Player> players;

    public Model() {
    }

    public Table getTable() {
        // TODO
        return null;
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


    public void setFigureToPlayer() {
        // TODO
    }


    public void setPlayerToPlatform() {
        // TODO
    }


    public void endTurn() {
        // TODO
    }

    /*
     * @return
     */
    public Player getCurrentPlayer() {
        // TODO
        return null;
    }


    public void timesUp() {
        // TODO
    }


    public void runningOutTime() {
        // TODO
    }

    /*
     * @param int 
     * @param bool
     */
    public void setSuspendedPlayer(int a, boolean b) {
        // TODO
    }

    /*
     * @return
     */
    public boolean checkEnoughWeapons() {
        // TODO
        return true;
    }

    /*
     * @return
     */
    public boolean checkEnoughPowerups() {
        // TODO
        return true;
    }

    /*
     * @return
     */
    public boolean checkEnoughAmmos() {
        // TODO
        return true;
    }


    public void checkLegalMove() {
        // TODO
    }

}