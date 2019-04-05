package it.polimi.se2019.model.player;

import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.WeaponCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.enumeration.Character;
import java.util.ArrayList;


/**
 * Class that manages all the information about the player during the game
 * such as his character, his position, his score, the cards he holds,
 * the number of deaths, the damage and the marks taken and his ammunition.
 * @author Simone Orlando
 */
public class Player {

    private Character character;
    private Platform currentPlatform;
    private int currentScore;
    private PlayerBoard playerBoard;
    private int numOfDeaths;
    private ArrayList<PowerUpCard> powerUpCards;
    private ArrayList<WeaponCard> weaponCards;
    private int frenzyModeType;


    /**
     * Class constructor that initializes data structures that will be used
     * to store all the related game information concerning the player
     * @param character Character chosen by the player at the start of the game
     */
    public Player(Character character) {

    }

    /**
     * Get the player character
     * @return Character chosen by the player at the start of the game
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Get the platform where the player is located(his position)
     * @return Current platform where the player is located
     */
    public Platform getCurrentPlatform() {
        return currentPlatform;
    }

    /**
     *
     * @return
     */
    public int getFrenzyModeType() {
        return frenzyModeType;
    }

    /**
     * Get the player's current score
     * @return Player's current score
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Get the player's board
     * @return Player's board
     */
    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Get the power up cards owned by the player
     * @return List containing the player's power up cards
     */
    public ArrayList<PowerUpCard> getPowerUpCards() {
        return powerUpCards;
    }

    /**
     * Get the weapon cards owned by the player
     * @return List containing the player's weapon cards
     */
    public ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    /**
     * Get the current number of times the player died during the game
     * @return Current number of deaths
     */
    public int getNumOfDeaths() {
        return numOfDeaths;
    }

    /**
     * Change the player's position by moving him to a new platform
     * @param newPlatform The new platform where the player wants to go
     */
    public void changePlatform(Platform newPlatform) {
        currentPlatform = newPlatform;
    }

    /**
     * Add points to those already scored by the player
     * @param point Points to add
     *
     */
    public void addPoint(int point) {
        currentScore = currentScore + point;
    }

    /**
     * Increases by one the number of times the player died during the game
     */
    public void addDeath() {
        numOfDeaths = numOfDeaths + 1;
    }

    /**
     * Set the number of times the player has died to zero
     */
    public void resetDeaths() {
        numOfDeaths = 0;
    }

    /**
     * Add a power up card to those already owned by the player
     * @param card The card drawn
     */
    public void addPowerUpCard(PowerUpCard card) {

    }

    /**
     * Eliminates a power up card from those owned by the player
     * @param card The power up card that must be eliminated
     */
    public void removePowerUpCard(PowerUpCard card) {

    }

    /**
     * Add a weapon card to those already owned by the player
     * @param card The card drawn
     */
    public void addWeaponCard(WeaponCard card) {

    }

    /**
     * Eliminates a weapon card from those owned by the player
     * @param card The weapon card that must be eliminated
     */
    public void removeWeaponCard(WeaponCard card) {

    }

    /**
     *
     * @param type
     */
    public void setFrenzyModeType(int type) {
        frenzyModeType = type;
    }

}
