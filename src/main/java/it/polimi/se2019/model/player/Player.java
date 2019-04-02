package it.polimi.se2019.model.player;

import java.util.*;
import it.polimi.se2019.model.enumeration.*;
import it.polimi.se2019.model.card.powerups.*;
import it.polimi.se2019.model.card.WeaponCard;


public class Player {


    private int idPlayer;
    private int currentScore;
    private PlayerBoard playerBoard;
    private String playerName;
    private int numberOfDeaths;
    private ArrayList<PowerUpCard> powerUpCards;
    private ArrayList<WeaponCard> weaponCards;
    private HealthState healthState;
    private Color playerColor;

    public Player() {
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void setPowerUpCards(ArrayList<PowerUpCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    public void setWeaponCards(ArrayList<WeaponCard> weaponCards) {
        this.weaponCards = weaponCards;
    }

    public void setHealthState(HealthState healthState) {
        this.healthState = healthState;
    }

    /*
     * @return
     */
    public int getIdPlayer() {
        // TODO
        return 0;
    }

    /*
     * @return
     */
    public int getCurrentScore() {
        // TODO
        return 0;
    }

    /*
     * @return
     */
    public String getPlayerName() {
        // TODO
        return "";
    }

    /*
     * @return
     */
    public PlayerBoard getPlayerBoard() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public int getNumberOfDeaths() {
        // TODO
        return 0;
    }

    /*
     * @return
     */
    public ArrayList<PowerUpCard> getPowerUpCards() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public ArrayList<WeaponCard> getWeaponCards() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public Color getPlayerColor() {
        // TODO
        return null;
    }

    /*
     * @param point
     */
    public void addPoint(int point) {
        // TODO
    }


    public void addDeath() {
        // TODO
    }

    /*
     * @param card
     */
    public void addPowerAppCard(PowerUpCard card) {
        // TODO
    }

    /*
     * @param card
     */
    public void addWeaponCard(WeaponCard card) {
        // TODO
    }

    /*
     * @param newHealthState
     */
    public void changeHealthState(HealthState newHealthState) {
        // TODO
    }

    /*
     * @return
     */
    public HealthState getHealthState() {
        // TODO
        return null;
    }

    /*
     * @param dir
     */
    public void move(Orientation dir) {
        // TODO
    }

    /*
     * @param player 
     * @param val; int
     */
    public void tag(Player player, int val) {
        // TODO
    }

    /*
     * @param player 
     * @param val
     */
    public void damage(Player player, int val) {
        // TODO
    }


    public void grab() {
        // TODO
    }


    public void shoot() {
        // TODO
    }

}