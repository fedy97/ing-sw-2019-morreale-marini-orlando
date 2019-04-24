package it.polimi.se2019.model;

import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Federico Morreale
 */
public class Game extends Observable {

    private GameField gameField;
    private ArrayList<Player> players;
    private Deck<WeaponCard> weaponsDeck;
    private Deck<PowerUpCard> powerUpDeck;
    private Deck<PowerUpCard> garbageDeck;
    private Deck<AmmoCard> ammoDeck;
    private Map<Character, Player> characterPlayers;
    
    public Game() {
        //TODO
    }
    
    public GameField getGameField() {
        return gameField;
    }

    /**
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return The deck containing the weapon cards
     */
    public Deck<WeaponCard> getWeaponsDeck() {
        return weaponsDeck;
    }

    /**
     * @return The deck containing the power up cards
     */
    public Deck<PowerUpCard> getPowerUpDeck() {
        return powerUpDeck;
    }

    /**
     * @return The power up cards already used
     */
    public Deck<PowerUpCard> getGarbageDeck() {
        return garbageDeck;
    }

    /**
     * @return The deck containing the ammo card
     */
    public Deck<AmmoCard> getAmmoDeck() {
        return ammoDeck;
    }

    /**
     * @return The map that connects player with character
     */
    public Map<Character,Player> getCharacterPlayers() {
        return characterPlayers;
    }

    /**
     * @param player to be added to the match
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * @param player to link to the character
     * @param character to assign to the player
     */
    public void setPlayerCharacter(Player player, Character character) {
        characterPlayers.put(character, player);
    }

    /**
     * @param weapons The deck containing the power up cards
     */
    public void setWeaponDeck(Deck<WeaponCard> weapons) {
        weaponsDeck = weapons;
    }

    /**
     * @param powerUps The deck containing the power up cards
     */
    public void setPowerUpDeck(Deck<PowerUpCard> powerUps) {
        powerUpDeck = powerUps;
    }

    /**
     * @param ammos The deck containing the ammo card
     */
    public void setAmmoDeck(Deck<AmmoCard> ammos) {
        ammoDeck = ammos;
    }

    /**
     * Method used to notify that something changed in the model
     */
    public void notifyChanges(){
        setChanged();
        notifyObservers();
    }
}
