package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.exceptions.InvalidQuantityException;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.*;

/**
 * ScoreBoard keeps track of the scores of the players
 *
 * @author Federico Morreale
 */
public class ScoreBoard {

    private Map<Character, Integer> scoreBoard;

    /**
     * initialize the scoreboard with zero points
     */
    public ScoreBoard() {
        this.scoreBoard = new EnumMap<>(Character.class);
        for (Character c : this.scoreBoard.keySet()) {
            this.scoreBoard.put(c, 0);
        }
    }

    /**
     * @param character in order to see his current score
     * @return the score of a selected player
     */
    public Integer getScorePlayer(Character character) throws InvalidCharacterException {
        if (!HandyFunctions.characterExist(character))
            throw new InvalidCharacterException();
        return scoreBoard.get(character);
    }

    public Map<Character,Integer> getScoreBoard(){
        return scoreBoard;
    }
    /**
     * @param character to set the points
     * @param quantity  of points to set to the player
     */
    public void setScoreToPlayer(Character character, int quantity) throws InvalidQuantityException, InvalidCharacterException {
        if (quantity < 1)
            throw new InvalidQuantityException("the score to set to the player must be > 0");
        if (!HandyFunctions.characterExist(character))
            throw new InvalidCharacterException();
        scoreBoard.replace(character, scoreBoard.get(character) + quantity);
    }

}