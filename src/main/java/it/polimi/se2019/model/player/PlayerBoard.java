package it.polimi.se2019.model.player;

import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.Character;
import java.util.ArrayList;

/**
 * Class that manages the damages and the marks suffered by the player and
 * the points obtainable at the player's next death
 * @author Simone Orlando
 */
public class PlayerBoard {

    private ArrayList<Integer> pointsObtainable;
    private ArrayList<Character> damageLine;
    private ArrayList<Character> revengeMarks;
    private AmmoBox ammoBox;

    /**
     * Class constructor that initializes the data structures it uses
     */
    public PlayerBoard() {

    }

    /**
     * Get the player's ammo container
     * @return Player's ammo container
     */
    public AmmoBox getAmmoBox() {
        return ammoBox;
    }

    /**
     * Gets the marks that the player has received from other players
     * @return
     */
    public ArrayList<Character> getRevengeMarks() {
        return revengeMarks;
    }

    /**
     * Get the damage suffered by the player
     * @return
     */
    public ArrayList<Character> getDamageLine() {
        return damageLine;
    }

    /**
     * Get a list containing the points that will be distributed among the players once the player is killed
     * @return
     */
    public ArrayList<Integer> getPointsObtainable() {
        return pointsObtainable;
    }

    /**
     * Add damage to the player
     * @param character The character of player who deals the damage
     * @param value The number of damages to be inflicted
     */
    public void addDamage(Character character, int value) {

    }

    /**
     * Change the points obtainable at the death of the player
     * @param pointsObtainable New list of points obtainable
     */
    public void setPointsObtainable(ArrayList<Integer> pointsObtainable) {

    }

    /**
     * Remove all damage suffered by the player
     */
    public void resetDamageLine() {

    }

    /**
     * Add marks to the player
     * @param character The character of player who marks
     * @param value The number of marks to be added
     */
    public void addRevengeMark(Character character, int value) {

    }

    /**
     * Remove all marks received from a player
     * @param character The character of the player whose marks are to be removed
     */
    public void removeRevengeMarks(Character character) {

    }

}
