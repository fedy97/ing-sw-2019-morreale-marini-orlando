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
     * @return
     */
    public Deck<WeaponCard> getWeaponsDeck() {
        return weaponsDeck;
    }

    /**
     * @return
     */
    public Deck<PowerUpCard> getPowerUpDeck() {
        return powerUpDeck;
    }

    /**
     * @return
     */
    public Deck<PowerUpCard> getGarbageDeck() {
        return garbageDeck;
    }

    /**
     * @return
     */
    public Deck<AmmoCard> getAmmoDeck() {
        return ammoDeck;
    }

    /**
     * @return
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
     * @param character
     */
    public void setPlayerCharacter(Player player, Character character) {
        characterPlayers.put(character, player);
    }

    /**
     * @param weapons
     */
    public void setWeaponDeck(Deck<WeaponCard> weapons) {
        weaponsDeck = weapons;
    }

    /**
     * @param powerUps
     */
    public void setPowerUpDeck(Deck<PowerUpCard> powerUps) {
        powerUpDeck = powerUps;
    }

    /**
     * @param ammos
     */
    public void setAmmoDeck(Deck<AmmoCard> ammos) {
        ammoDeck = ammos;
    }

}
