package it.polimi.se2019.controller;

import it.polimi.se2019.model.Model;

/**
 * @author Gabriel Raul Marini
 */
public class Controller {

    public Controller() {
    }

    private Model model;
    public DecksManager decksManager;
    public GameManager gameManager;
    public PlayerManager playerManager;

    /**
     * Instatiate a new controller class
     *
     * @param model
     * @param decksManager
     * @param gameManager
     * @param playerManager
     */
    public Controller(Model model, DecksManager decksManager, GameManager gameManager, PlayerManager playerManager) {
        this.model = model;
        this.decksManager = decksManager;
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    /**
     * @return
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model
     */
    public void setModel(Model model) {
        this.model = model;
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
