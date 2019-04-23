package it.polimi.se2019.controller;

import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Platform;
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
     * @param game
     * @param decksManager
     * @param gameManager
     * @param playerManager
     */
    public Controller(Game game, DecksManager decksManager, GameManager gameManager, PlayerManager playerManager) {
        this.game = game;
        this.decksManager = decksManager;
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    /**
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return
     */
    public DecksManager getDecksManager() {
        return decksManager;
    }

    /**
     * @param deckManager
     */
    public void setDecksManager(DecksManager deckManager) {
        this.decksManager = deckManager;
    }

    /**
     * @return
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * @param gameManager
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * @return
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * @param playerManager
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
