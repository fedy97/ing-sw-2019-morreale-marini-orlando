package it.polimi.se2019.model.player;

import java.util.*;
import it.polimi.se2019.model.enumeration.*;
import it.polimi.se2019.model.card.powerups.*;
import it.polimi.se2019.model.card.WeaponCard;

/**
 * 
 */
public class Player {

    /**
     * Default constructor
     */
    public Player() {
    }

    /**
     * 
     */
    private int idPlayer;

    /**
     * 
     */
    private int currentScore;

    /**
     * 
     */
    private PlayerBoard playerBoard;

    /**
     * 
     */
    private String playerName;

    /**
     * 
     */
    private int numberOfDeaths;

    /**
     * 
     */
    private ArrayList<PowerUpCard> powerUpCards;

    /**
     * 
     */
    private ArrayList<WeaponCard> weaponCards;

    /**
     * 
     */
    private HealthState healthState;

    /**
     * 
     */
    private Color playerColor;







    /**
     * @return
     */
    public int getIdPlayer() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getCurrentScore() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public String getPlayerName() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public PlayerBoard getPlayerBoard() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public int getNumberOfDeaths() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public ArrayList<PowerUpCard> getPowerUpCards() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<WeaponCard> getWeaponCards() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Color getPlayerColor() {
        // TODO implement here
        return null;
    }

    /**
     * @param point
     */
    public void addPoint(int point) {
        // TODO implement here
    }

    /**
     * 
     */
    public void addDeath() {
        // TODO implement here
    }

    /**
     * @param card
     */
    public void addPowerAppCard(PowerUpCard card) {
        // TODO implement here
    }

    /**
     * @param card
     */
    public void addWeaponCard(WeaponCard card) {
        // TODO implement here
    }

    /**
     * @param newHealthState
     */
    public void changeHealthState(HealthState newHealthState) {
        // TODO implement here
    }

    /**
     * @return
     */
    public HealthState getHealthState() {
        // TODO implement here
        return null;
    }

    /**
     * @param dir
     */
    public void move(Orientation dir) {
        // TODO implement here
    }

    /**
     * @param player 
     * @param val; int
     */
    public void tag(Player player, int val) {
        // TODO implement here
    }

    /**
     * @param player 
     * @param val
     */
    public void damage(Player player, int val) {
        // TODO implement here
    }

    /**
     * 
     */
    public void grab() {
        // TODO implement here
    }

    /**
     * 
     */
    public void shoot() {
        // TODO implement here
    }

}