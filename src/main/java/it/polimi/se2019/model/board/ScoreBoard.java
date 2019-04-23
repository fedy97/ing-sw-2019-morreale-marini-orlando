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

    private Map<Character, Integer> ranking;

    /**
     * initialize the scoreboard with zero points
     */
    public ScoreBoard() {
        this.ranking = new EnumMap<>(Character.class);
        for (Character c : this.ranking.keySet()) {
            this.ranking.put(c, 0);
        }
    }

    /**
     * @param character in order to see his current score
     * @return the score of a selected player
     * @throws InvalidCharacterException if the character does not exist
     */
    public Integer getScorePlayer(Character character) throws InvalidCharacterException {
        if (!HandyFunctions.characterExists(character))
            throw new InvalidCharacterException("Invalid character!");
        return ranking.get(character);
    }

    public Map<Character, Integer> getScoreBoard() {
        return ranking;
    }

    /**
     * @param character to set the points
     * @param quantity  of points to set to the player
     * @throws InvalidCharacterException if the character does not exist
     * @throws InvalidQuantityException  if the quantity is < 1
     */
    public void setScoreToPlayer(Character character, int quantity) throws InvalidQuantityException, InvalidCharacterException {
        if (quantity < 1)
            throw new InvalidQuantityException("the score to set to the player must be > 0");
        if (!HandyFunctions.characterExists(character))
            throw new InvalidCharacterException("Invalid character!");
        ranking.replace(character, ranking.get(character) + quantity);
    }

}