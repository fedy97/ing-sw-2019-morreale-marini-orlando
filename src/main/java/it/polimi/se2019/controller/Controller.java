package it.polimi.se2019.controller;

import it.polimi.se2019.model.Game;

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
}
