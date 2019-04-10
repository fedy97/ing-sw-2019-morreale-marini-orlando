package it.polimi.se2019.model.board;

import it.polimi.se2019.model.enumeration.Character;

import java.util.*;

/**
 * ScoreBoard keeps track of the scores of the players
 *
 * @author Federico Morreale
 */
public class ScoreBoard {

    private Map<Character, Integer> scoreBoard;

    public ScoreBoard() {
        this.scoreBoard = new EnumMap<Character, Integer>(Character.class);
        for (Character c : this.scoreBoard.keySet()) {
            this.scoreBoard.put(c, 0);
        }
    }

    /**
     * @param character in order to see his current score
     * @return the score of a selected player
     */
    public Integer getScorePlayer(Character character) {
        return scoreBoard.get(character);
    }

    public Map<Character,Integer> getScoreBoard(){
        return scoreBoard;
    }
    /**
     * @param character to set the points
     * @param quantity  of points to set to the player
     * @return the old score of the player, null if the player does not exist
     */
    public Integer setScoreToPlayer(Character character, int quantity) {
        return scoreBoard.replace(character, scoreBoard.get(character) + quantity);
    }

}