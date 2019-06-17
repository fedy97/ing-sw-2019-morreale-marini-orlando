package it.polimi.se2019.model.player;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.utils.CustomLogger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that manages all the information about the player during the game
 * such as his character, his position, his score, the cards he holds,
 * the number of deaths, the damage and the marks taken and his ammunition.
 *
 * @author Simone Orlando
 */
public class Player implements Serializable {

    private String name;
    private Character character;
    private Platform currentPlatform;
    private int currentScore;
    private PlayerBoard playerBoard;
    private int numOfDeaths;
    private ArrayList<PowerUpCard> powerUpCards;
    private ArrayList<WeaponCard> weaponCards;
    private int frenzyModeType;
    private boolean underAttack;
    private List<Player> currentTargets;
    private boolean attacking;
    private boolean isConnected;

    /**
     * Class constructor that initializes data structures that will be used
     * to store all the related game information concerning the player
     *
     * @param character Character chosen by the player at the start of the game
     * @throws InvalidCharacterException if character is null
     * @throws InvalidPositionException  if startPlatform is null
     */
    public Player(String name, Character character, Platform startPlatform) throws InvalidCharacterException, InvalidPositionException {
        if (character == null)
            throw new InvalidCharacterException("Character can not be null!");
        /*if (startPlatform == null)
            throw new InvalidPositionException("StartPlatform can not be null!");*/
        this.character = character;
        this.currentPlatform = startPlatform;
        this.name = name;
        currentScore = 0;
        playerBoard = new PlayerBoard();
        numOfDeaths = 0;
        powerUpCards = new ArrayList<>();
        weaponCards = new ArrayList<>();
        frenzyModeType = 0;
        underAttack = false;
        attacking = false;
        isConnected = true;
        currentTargets = new ArrayList<>();
    }

    /**
     * Get the player character
     *
     * @return Character chosen by the player at the start of the game
     */
    public Character getCharacter() {
        return character;
    }

    public String getName() {
        return name;
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
     *
     * @param platform the new position of the player
     */
    public void setCurrentPlatform(Platform platform) {
        if (currentPlatform != null)
            currentPlatform.removePlayerOnPlatform(getCharacter());
        currentPlatform = platform;
        try {
            currentPlatform.setPlayerOnPlatform(this.getCharacter());
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    /**
     * @return The player mode
     */
    public int getFrenzyModeType() {
        return frenzyModeType;
    }

    /**
     * @param type The new player mode
     */
    public void setFrenzyModeType(int type) {
        frenzyModeType = type;
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
    public List<PowerUpCard> getPowerUpCards() {
        return new ArrayList<>(powerUpCards);
    }

    /**
     * Get the weapon cards owned by the player
     *
     * @return List containing the player's weapon cards
     */
    public List<WeaponCard> getWeaponCards() {
        return new ArrayList<>(weaponCards);
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
     * @throws InvalidPositionException if newPlatform reference is null
     */
    public void changePlatform(Platform newPlatform) throws InvalidPositionException {
        if (newPlatform == null)
            throw new InvalidPositionException("NewPlatform can not be null!");
        currentPlatform = newPlatform;
    }

    /**
     * Add points to those already scored by the player
     *
     * @param point Points to be added
     * @throws NegativeNumberException if point is less than zero
     */
    public void addPoint(int point) throws NegativeNumberException {
        if (point < 0)
            throw new NegativeNumberException("Point should be greater than zero!");
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
     * @throws InvalidCardException if card reference is null
     */
    public void addPowerUpCard(PowerUpCard card) throws InvalidCardException {
        if (card == null)
            throw new InvalidCardException("Card cannot be null!");
        if (powerUpCards.size() < 3) {
            powerUpCards.add(card);
            //playerBoard.getAmmoBox().addOptionalAmmo(card.getAmmoCube());
        }
    }

    /**
     * Eliminates a power up card from those owned by the player
     *
     * @param card The power up card that must be eliminated
     * @throws InvalidCardException if card reference is null
     */
    public void removePowerUpCard(PowerUpCard card) throws InvalidCardException {
        if (card == null)
            throw new InvalidCardException("Card cannot be null!");
        powerUpCards.remove(card);
        //playerBoard.getAmmoBox().removeOptionalAmmo(card.getAmmoCube());
    }

    /**
     * Add a weapon card to those already owned by the player
     *
     * @param card The card drawn
     * @throws MaxWeaponException when the player has already three weapons
     */
    public void addWeaponCard(WeaponCard card) throws MaxWeaponException {
        if (weaponCards.size() < 3)
            weaponCards.add(card);
        else
            throw new MaxWeaponException("The player has already three weapons!");
    }

    /**
     * Eliminates a weapon card from those owned by the player
     *
     * @param card The weapon card that must be eliminated
     */
    public void removeWeaponCard(WeaponCard card) {
        weaponCards.remove(card);
    }

    //TODO test
    /**
     * @return if the player has more than 3 damage tokens but less than 6
     */
    public boolean isDamaged() {
        if (playerBoard.getDamageLine().size() >= 3 && playerBoard.getDamageLine().size() < 6)
            return true;
        else
            return false;
    }

    //TODO test
    /**
     * @return if the player has more than 6 damage tokens
     */
    public boolean isSeriouslyDamaged() {
        if (playerBoard.getDamageLine().size() >= 6)
            return true;
        else
            return false;
    }

    public void setUnderAttack() {
        underAttack = true;
    }

    public void resetUnderAttack() {
        underAttack = false;
    }

    public boolean isUnderAttack() {
        return underAttack;
    }

    public List<Player> getCurrentTargets() {
        return currentTargets;
    }

    public void addTarget(Player currentTarget) {
        this.currentTargets.add(currentTarget);
    }

    public void beginAttack() {
        attacking = true;
    }

    public void terminateAttack() {
        attacking = false;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
