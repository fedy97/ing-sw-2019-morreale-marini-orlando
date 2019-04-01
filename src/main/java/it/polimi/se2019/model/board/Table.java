package it.polimi.se2019.model.board;

import java.util.*;

import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.WeaponCard;
import it.polimi.se2019.model.player.*;

public class Table {


    public Table() {
    }


    private ArrayList<WeaponCard> weaponsAvailable;


    private Deck powerupsDeck;


    private Deck ammosDeck;


    private Deck weaponsDeck;


    private SkullsBoard skullsBoard;


    private GameField field;


    private ScoreBoard scoreBoard;


    private int winner;


    private ArrayList<Integer> players;







    /*
     * @return
     */
    public ScoreBoard getScoreBoard() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public GameField getGameField() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public void getAvailableWeapons() {
        // TODO
        return;
    }

    /*
     * @return
     */
    public void getPowerupsDeck() {
        // TODO
        return;
    }

    /*
     * @return
     */
    public void getAmmosDeck() {
        // TODO
        return;
    }

    /*
     * @return
     */
    public void getWeaponsDeck() {
        // TODO
        return;
    }

    /*
     * @return
     */
    public SkullsBoard getSkullsBoard() {
        // TODO
        return null;
    }

    /*
     * @param int
     */
    public void setWinner(int idPlayer) {
        // TODO
    }

    /*
     * @return
     */
    public int getWinner() {
        // TODO
        return 0;
    }

    /*
     * @param int 
     * @return
     */
    public Player getPlayer(int idPlayer) {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public ArrayList<Integer> getPlayers() {
        // TODO
        return null;
    }


    public void setNewPlayer() {
        // TODO
    }

}