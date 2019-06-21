package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.exceptions.InvalidQuantityException;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.Serializable;
import java.util.*;

/**
 * SkullsBoard keeps track of the remaining skulls and all the marks
 * a player gets when someone is killed.
 *
 * @author Federico Morreale
 */
public class SkullsBoard implements Serializable {

    private int currentSkulls;
    private int totalSkulls;
    private Map<Character, Integer> killMarks;
    private ArrayList<Integer> marksInPos;
    private ArrayList<Character> characterThatKilledInPos;

    /**
     * @param totalSkulls is the number set at the beginning of a game,
     *                    depending on how much the players want to game to last,
     *                    it has to be in the [1,8] range
     * @throws InvalidQuantityException if the skulls set is < 1 or > 8
     */
    public SkullsBoard(int totalSkulls) throws InvalidQuantityException {
        this.killMarks = new EnumMap<>(Character.class);
        if (totalSkulls > 8 || totalSkulls < 1)
            throw new InvalidQuantityException("number of skulls must be between 1 and 8");
        this.totalSkulls = totalSkulls;
        this.currentSkulls = totalSkulls;
        marksInPos = new ArrayList<>();
        characterThatKilledInPos = new ArrayList<>();
    }

    /**
     * @return remaining number of skulls in the game
     */
    public int getCurrentSkulls() {
        return currentSkulls;
    }

    /**
     * @return the total number of skulls in the game
     */
    public int getTotalSkulls() {
        return totalSkulls;
    }

    /**
     * @return a map of killmarks per character
     */
    public Map<Character, Integer> getKillMarks() {
        return killMarks;
    }

    public List<Integer> getMarksInPos() {
        return marksInPos;
    }

    public List<Character> getCharacterThatKilledInPos() {
        return characterThatKilledInPos;
    }

    /**
     * @param character to get the killmarks
     * @return the number of killmarks of the selected character
     * @throws InvalidCharacterException if the character does not exist
     */
    public Integer getKillMarksPlayer(Character character) throws InvalidCharacterException {
        if (!HandyFunctions.characterExists(character))
            throw new InvalidCharacterException("Invalid character!");
        return killMarks.get(character);
    }

    /**
     * @param character that killed someone
     * @param quantity  depending on the kind of death(either kill 1 or overkill 2)
     * @throws InvalidCharacterException if the character does not exist
     * @throws InvalidQuantityException  if trying to add more than 2 or less than 1 skulls
     */
    public void addKillMarks(Character character, int quantity) throws InvalidCharacterException, InvalidQuantityException {
        if (!HandyFunctions.characterExists(character))
            throw new InvalidCharacterException("Invalid character!");
        if (quantity > 2 || quantity < 1)
            throw new InvalidQuantityException("you cannot add more than 2 or less than 1 marks");
        if(killMarks.get(character) != null)
            killMarks.replace(character, killMarks.get(character) + quantity);
        else
            killMarks.put(character, quantity);
        marksInPos.add(quantity);
        characterThatKilledInPos.add(character);
        currentSkulls--;
    }
}