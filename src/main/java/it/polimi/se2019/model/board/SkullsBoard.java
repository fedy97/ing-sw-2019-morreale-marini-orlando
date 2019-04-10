package it.polimi.se2019.model.board;

import it.polimi.se2019.model.enumeration.Character;

import java.util.*;

/**
 * SkullsBoard keeps track of the remaining skulls and all the marks
 * a player gets when someone is killed.
 *
 * @author Federico Morreale
 */
public class SkullsBoard {

    private int currentSkulls;
    private int totalSkulls;
    private Map<Character, Integer> killMarks;

    /**
     * @param totalSkulls is the number set at the beginning of a game,
     *        depending on how much the players want to game to last,
     *        it has to be in the [1,8] range
     */
    public SkullsBoard(int totalSkulls) {
        this.killMarks = new EnumMap<>(Character.class);
        this.totalSkulls = totalSkulls;
        this.currentSkulls = totalSkulls;
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
    public Map<Character,Integer> getKillMarks() {
        return killMarks;
    }

    /**
     * @param character to get the killmarks
     * @return the number of killmarks of the selected character
     */
    public Integer getKillMarksPlayer(Character character) {
        return killMarks.get(character);
    }

    /**
     * @param character that killed someone
     * @param quantity  depending on the kind of death(either kill 1 or overkill 2)
     * @return either old value of the character's kill marks or null if the player does not exist
     */
    public Integer addKillMarks(Character character, int quantity) {
        return killMarks.replace(character, killMarks.get(character) + quantity);
    }
}