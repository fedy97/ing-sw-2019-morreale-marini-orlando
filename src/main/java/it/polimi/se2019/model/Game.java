package it.polimi.se2019.model;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.board.ScoreBoard;
import it.polimi.se2019.model.board.SkullsBoard;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.*;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.JsonParser;
import it.polimi.se2019.utils.TimerMap;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

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
    private int secondsLeft;
    private boolean timerStarted = false;
    private Map<Integer, Integer> mapChosen;
    private static Game instance = null;

    /**
     * Game singleton constructor
     *
     * @return instance
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.mapChosen = new HashMap<>();
            instance.mapChosen.put(1, 0);
            instance.mapChosen.put(2, 0);
            instance.mapChosen.put(3, 0);
            instance.mapChosen.put(4, 0);
        }
        return instance;
    }

    //TODO, when the game starts, the method is thrown, every attribute will be set not only gamefield
    private void initGame(GameField gameField, Deck<WeaponCard> weaponsDeck, Deck<PowerUpCard> powerUpDeck, Deck<AmmoCard> ammoDeck) {
        this.gameField = gameField;
        this.powerUpDeck = powerUpDeck;
        this.weaponsDeck = weaponsDeck;
        this.ammoDeck = ammoDeck;
    }

    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setVoteMapChosen(int voteMapChosen) {
        mapChosen.put(voteMapChosen, mapChosen.get(voteMapChosen) + 1);
        setChanged();
        notifyObservers(new UpdateVotesMapChosenMessage(mapChosen));
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
    public Map<Character, Player> getCharacterPlayers() {
        return characterPlayers;
    }

    /**
     * @param player to be added to the match
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * @param player    to link to the character
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
     * Method used to notify that something changed in the model
     */
    public void notifyChanges() {
        setChanged();
        notifyObservers();
    }

    /**
     * every time ths method is called by the timer, (this) will notify the virtual view
     *
     * @param secondsLeft to the chooseMap page
     */
    public synchronized void setSecondsLeftLobby(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        setChanged();
        notifyObservers(new UpdateTimerLobbyMessage(secondsLeft));
        if (secondsLeft == 0) {
            setChanged();
            notifyObservers(new ShowChooseMapMessage(null));
            //starts the other timer
            TimerMap t = new TimerMap(5);
            t.start();

        }
    }

    /**
     * @param secondsLeft to the choose character page
     */
    public synchronized void setSecondsLeftMap(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        setChanged();
        notifyObservers(new UpdateTimerMapMessage(secondsLeft));
        if (secondsLeft == 0) {
            try {
                setChanged();
                notifyObservers(new ShowChooseCharacterMessage(null));
                int config = findWhichMapWon();
                JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
                Deck<AmmoCard> deck = parserAmmos.buildAmmoCards();
                //now we have 36 ammocards in the deck
                deck.mix();
                JsonParser parserField = new JsonParser("/json/field.json");
                Platform[][] field = parserField.buildField(config, deck);
                WeaponCard[] weaponCards = new WeaponCard[9];
                for (int i = 0; i < 9; i++)
                    weaponCards[i] = new WeaponCard();
                JsonParser parser = new JsonParser("/json/powerups.json");
                Deck<PowerUpCard> powerUpCardDeck = parser.buildPowerupCards();

                GameField gf = new GameField(field, weaponCards, new SkullsBoard(8), new ScoreBoard());

                initGame(gf, null, powerUpCardDeck, ammoDeck);

            } catch (Exception ex) {
                HandyFunctions.LOGGER.log(Level.SEVERE, ex.toString());
            }
        }
    }

    private int findWhichMapWon() {
        int max = -1;
        int config = -1;
        for (int i : mapChosen.keySet()) {
            if (mapChosen.get(i) > max) {
                max = mapChosen.get(i);
                config = i;
            }
        }
        return config;
    }
}
