package it.polimi.se2019.model.board;

import java.util.*;

import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.WeaponCard;
import it.polimi.se2019.model.player.*;
/**
 * 
 */
public class Table {

    /**
     * Default constructor
     */
    public Table() {
    }

    /**
     * 
     */
    private ArrayList<WeaponCard> weaponsAvailable;

    /**
     * 
     */
    private Deck powerupsDeck;

    /**
     * 
     */
    private Deck ammosDeck;

    /**
     * 
     */
    private Deck weaponsDeck;

    /**
     * 
     */
    private SkullsBoard skullsBoard;

    /**
     * 
     */
    private GameField field;

    /**
     * 
     */
    private ScoreBoard scoreBoard;

    /**
     * 
     */
    private int winner;

    /**
     * 
     */
    private ArrayList<Integer> players;







    /**
     * @return
     */
    public ScoreBoard getScoreBoard() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public GameField getGameField() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void getAvailableWeapons() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void getPowerupsDeck() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void getAmmosDeck() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void getWeaponsDeck() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public SkullsBoard getSkullsBoard() {
        // TODO implement here
        return null;
    }

    /**
     * @param int
     */
    public void setWinner(int idPlayer) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getWinner() {
        // TODO implement here
        return 0;
    }

    /**
     * @param int 
     * @return
     */
    public Player getPlayer(int idPlayer) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Integer> getPlayers() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void setNewPlayer() {
        // TODO implement here
    }

}