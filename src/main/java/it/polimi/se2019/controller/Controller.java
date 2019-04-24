package it.polimi.se2019.controller;

import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Raul Marini
 */
public class Controller {
    private Game game;
    private DecksManager decksManager;
    private GameManager gameManager;
    private PlayerManager playerManager;
    private Validator validator;


    public Controller() {
    }


    /**
     * Instatiate a new controller class
     *
     * @param game The game to menage
     * @param decksManager The manager of all decks used in the game
     * @param gameManager The game manager
     * @param playerManager The manager of the players in the game
     */
    public Controller(Game game, DecksManager decksManager, GameManager gameManager, PlayerManager playerManager) {
        this.game = game;
        this.decksManager = decksManager;
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    /**
     * @return The current game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game The game to manage
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return The manager of all decks used in the game
     */
    public DecksManager getDecksManager() {
        return decksManager;
    }

    /**
     * @param deckManager The manager of all decks used in the game
     */
    public void setDecksManager(DecksManager deckManager) {
        this.decksManager = deckManager;
    }

    /**
     * @return The game manager
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * @param gameManager The game manager
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * @return The manager of the players in the game
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * @param playerManager The manager of the players in the game
     */
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     *
     */
    public void checkState() {
        //TODO
    }

    /**
     *
     */
    public void startGame() {
        //TODO
    }

    /**
     *
     */
    public void endGame() {
        //TODO
    }

    /**
     * @param possibleChoices list of weapons returned by the validator
     * @return the weapons the player want to grab
     */
    public List<WeaponCard> askForWeapons(List<WeaponCard> possibleChoices){
        //TODO
        return new ArrayList<>();
    }

    /**
     * @param discardables weapons owned by the current player in the actual state of the game
     * @return the weapon the player want to discard in order to grab another one
     */
    public WeaponCard askForDiscard(List<WeaponCard> discardables){
        //TODO
        return null;
    }

    /**
     *
     * @return the targets chosen by the player from those returned by the Validator
     */
    public List<Player> askForTargets(List<Player> possibleTargets){
        //TODO
        return new ArrayList<>();
    }

    /**
     * @param possibleDestination the list of possible platform destination
     * @return the destination platform chosen by the player
     */
    public Platform askForPosition(List<Platform> possibleDestination){
        //TODO
        return null;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * @return the cube chosen by the player in order to perform an action
     */
    public AmmoCube askForTribute(){
        //TODO
        return null;
    }
}
