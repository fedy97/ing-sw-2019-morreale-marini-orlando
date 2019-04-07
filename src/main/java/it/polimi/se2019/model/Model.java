package it.polimi.se2019.model;

import it.polimi.se2019.model.board.Table;
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
public class Model extends Observable {

    private Table table;
    private Player players;
    private Deck<WeaponCard> weaponsDeck;
    private Deck<PowerUpCard> powerUpDeck;
    private Deck<PowerUpCard> garbageDeck;
    private Deck<AmmoCard> ammoDeck;
    private Map<Character, Player> characterPlayers;
    
    public Model() {
    }
    
    public Table getTable() {
 
        return null;
    }

    /**
     * @return the players
     */
    public Player getPlayers() {
        return null;
    }

    /**
     * @return
     */
    public Deck<WeaponCard> getWeaponsDeck() {
        return null;
    }

    /**
     * @return
     */
    public Deck<PowerUpCard> getPowerUpDeck() {
        return null;
    }

    /**
     * @return
     */
    public Deck<PowerUpCard> getGarbageDeck() {
        return null;
    }

    /**
     * @return
     */
    public Deck<AmmoCard> getAmmoDeck() {
        return null;
    }

    /**
     * @return
     */
    public Map<Character,Player> getCharacterPlayers() {
        return null;
    }

    /**
     * @param player to be added to the match
     */
    public void addPlayer(Player player) {
    }

    /**
     * @param player
     * @param character
     */
    public void setPlayerCharacter(Player player, Character character) {
    }

    /**
     * @param weapons
     */
    public void setWeaponDeck(Deck<WeaponCard> weapons) {
    }

    /**
     * @param powerUps
     */
    public void setPowerUpDeck(Deck<PowerUpCard> powerUps) {
    }

    /**
     * @param ammos
     */
    public void setAmmoDeck(Deck<AmmoCard> ammos) {
    }

}
