package it.polimi.se2019.model.player;

import it.polimi.se2019.exceptions.MaxWeaponException;
import it.polimi.se2019.exceptions.MissingCardException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.enumeration.Character;

import java.util.ArrayList;


/**
 * Class that manages all the information about the player during the game
 * such as his character, his position, his score, the cards he holds,
 * the number of deaths, the damage and the marks taken and his ammunition.
 *
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
     *
     * @param character Character chosen by the player at the start of the game
     * @throws NullPointerException if character or startPlatform are null
     */
    public Player(Character character, Platform startPlatform) throws NullPointerException {
        if (character == null || startPlatform == null)
            throw new NullPointerException("Character and startPlatform can not be null!");
        this.character = character;
        this.currentPlatform = startPlatform;
        currentScore = 0;
        playerBoard = new PlayerBoard();
        numOfDeaths = 0;
        powerUpCards = new ArrayList<>();
        weaponCards = new ArrayList<>();
        frenzyModeType = 0;
    }

    /**
     * Get the player character
     *
     * @return Character chosen by the player at the start of the game
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Get the platform where the player is located(his position)
     *
     * @return Current platform where the player is located
     */
    public Platform getCurrentPlatform() {
        return currentPlatform;
    }

    /**
     * Set the position of the player
     * @param platform the new position of the player
     */
    public void setCurrentPlatform(Platform platform) {
        currentPlatform = platform;
    }

    /**
     * @return
     */
    public int getFrenzyModeType() {
        return frenzyModeType;
    }

    /**
     * Get the player's current score
     *
     * @return Player's current score
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Get the player's board
     *
     * @return Player's board
     */
    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Get the power up cards owned by the player
     *
     * @return List containing the player's power up cards
     */
    public ArrayList<PowerUpCard> getPowerUpCards() {
        ArrayList<PowerUpCard> temp = new ArrayList<>();
        temp.addAll(powerUpCards);
        return temp;
    }

    /**
     * Get the weapon cards owned by the player
     *
     * @return List containing the player's weapon cards
     */
    public ArrayList<WeaponCard> getWeaponCards() {
        ArrayList<WeaponCard> temp = new ArrayList<>();
        temp.addAll(weaponCards);
        return temp;
    }

    /**
     * Get the current number of times the player died during the game
     *
     * @return Current number of deaths
     */
    public int getNumOfDeaths() {
        return numOfDeaths;
    }

    /**
     * Change the player's position by moving him to a new platform
     *
     * @param newPlatform The new platform where the player wants to go
     * @throws NullPointerException if newPlatform reference is null
     */
    public void changePlatform(Platform newPlatform) throws NullPointerException {
        if (newPlatform == null)
            throw new NullPointerException("NewPlatform can not be null!");
        currentPlatform = newPlatform;
    }

    /**
     * Add points to those already scored by the player
     *
     * @param point Points to be added
     * @throws IllegalArgumentException if point is less than zero
     */
    public void addPoint(int point) throws IllegalArgumentException {
        if (point < 0)
            throw new IllegalArgumentException("Point should be greater than zero!");
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
     *
     * @param card The card drawn
     * @throws NullPointerException if card reference is null
     */
    public void addPowerUpCard(PowerUpCard card) throws NullPointerException {
        if (card == null)
            throw new NullPointerException("Card can not be null!");
        if (powerUpCards.size() < 3)
            powerUpCards.add(card);
    }

    /**
     * Eliminates a power up card from those owned by the player
     *
     * @param card The power up card that must be eliminated
     * @throws NullPointerException if card reference is null
     * @throws MissingCardException if powerUpCards does not contain card
     */
    public void removePowerUpCard(PowerUpCard card) throws NullPointerException, MissingCardException {
        if (card == null)
            throw new NullPointerException("Card can not be null!");
        if (!powerUpCards.contains(card))
            throw new MissingCardException("PowerUpCards does not contain this card!");
        powerUpCards.remove(card);
    }

    /**
     * Add a weapon card to those already owned by the player
     *
     * @param card The card drawn
     * @throws NullPointerException if card reference is null
     * @throws MaxWeaponException when the player has already three weapons
     */
    public void addWeaponCard(WeaponCard card) throws NullPointerException, MaxWeaponException {
        if (card == null)
            throw new NullPointerException("Card can not be null!");
        if (weaponCards.size() < 3)
            weaponCards.add(card);
        else
            throw new MaxWeaponException("The player has already three weapons!");
    }

    /**
     * Eliminates a weapon card from those owned by the player
     *
     * @param card The weapon card that must be eliminated
     * @throws NullPointerException if card reference is null
     * @throws MissingCardException if weaponCards does not contain card
     */
    public void removeWeaponCard(WeaponCard card) throws NullPointerException, MissingCardException {
        if (card == null)
            throw new NullPointerException("Card can not be null!");
        if (!weaponCards.contains(card))
            throw new MissingCardException("WeaponCards does not contain this card!");
        weaponCards.remove(card);
    }

    /**
     * @param type
     */
    public void setFrenzyModeType(int type) {
        frenzyModeType = type;
    }

}
