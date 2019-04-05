package it.polimi.se2019.model.board;

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

    public SkullsBoard() {
    }

    /**
     * @return remaining number of skulls in the game
     */
    public int getCurrentSkulls() {
        return 0;
    }

    /**
     * @return the total number of skulls in the game
     */
    public int getTotalSkulls() {
        return 0;
    }

    /**
     * @return a map of kills per character
     */
    public Map<Character, Integer> getKillMarks() {
        return killMarks;
    }

    /**
     * @param character just killed
     * @param quantity  depending on the kind of death(either kill 1 or overkill 2)
     */
    public void addKillMarks(Character character, int quantity) {
        this.currentSkulls--;
        return;
    }
}