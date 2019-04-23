package it.polimi.se2019.controller;

import java.util.*;

import it.polimi.se2019.model.board.*;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.*;
import it.polimi.se2019.model.enumeration.*;


public class GameManager {

    private GameField gameField;
    private GameState gameState;
    private ArrayList<Player> players;
    private Map<Character, Player> characterPlayer;

    public GameManager() {
        //TODO
    }


    public ArrayList<Player> getPlayers() {
        // TODO
        return players;
    }


    public void setupGame() {
        // TODO
    }


    public void startGame() {
        // TODO
    }

    /**
     * @param character
     * @param player
     */
    public void setCharacterToPlayer(Character character, Player player) {
        //TODO
    }


    public void setPlayerToPlatform(Player player, Platform platform) {
        // TODO
    }


    public void endTurn() {
        // TODO
    }


    public void timesUp() {
        // TODO
    }


    public void runningOutTime() {
        // TODO
    }

    /**
     * @return the player that has the right to perform the actions
     */
    public Player getCurrentPlayer() {
        // TODO
        return null;
    }

    /**
     * @param player the player to be suspended
     */
    public void setSuspendedPlayer(Player player) {
        // TODO
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Map<Character, Player> getCharacterPlayer() {
        return characterPlayer;
    }

    public void setCharacterPlayer(Map<Character, Player> characterPlayer) {
        this.characterPlayer = characterPlayer;
    }
}