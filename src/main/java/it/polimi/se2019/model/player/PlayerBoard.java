package it.polimi.se2019.model.player;

import it.polimi.se2019.model.enumeration.Character;
import java.util.ArrayList;

/**
 * Class that manages the damages and the marks suffered by the player and
 * the points obtainable at the player's next death
 * @author Simone Orlando
 */
public class PlayerBoard {

    private ArrayList<Character> damageLine;
    private ArrayList<Character> revengeMarks;
    private AmmoBox ammoBox;

    /**
     * Class constructor that initializes the data structures it uses
     */
    public PlayerBoard() {
        damageLine = new ArrayList<>();
        revengeMarks = new ArrayList<>();
        ammoBox = new AmmoBox();
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
     * Add damage to the player
     * @param character The character of player who deals the damage
     * @param value The number of damages to be inflicted
     * @throws IllegalArgumentException if value is less than zero
     * @throws NullPointerException if character reference is null
     */
    public void addDamage(Character character, int value) throws IllegalArgumentException, NullPointerException{
        if (value < 0)
            throw new IllegalArgumentException("Value should be greater than zero!");
        if (character == null)
            throw new NullPointerException("Character cannot be null!");
        int oldSize = damageLine.size();
        if (oldSize + value > 12) {
            for (int i = 0; i < 12 - oldSize; i++) {
                damageLine.add(character);
            }
        }
        else {
            for (int i = 0; i < value; i++) {
                damageLine.add(character);
            }
        }
    }


    /**
     * Remove all damage suffered by the player
     */
    public void resetDamageLine() {
        damageLine.clear();
    }

    /**
     * Add marks to the player
     * @param character The character of player who marks
     * @param value The number of marks to be added
     * @throws IllegalArgumentException if value is less than zero
     * @throws NullPointerException if character reference is null
     */
    public void addRevengeMark(Character character, int value) throws IllegalArgumentException, NullPointerException {
        if (value < 0)
            throw new IllegalArgumentException("Value should be greater than zero!");
        if(character == null)
            throw new NullPointerException("Character cannot be null!");
        int counter = 0;
        for (Character c: revengeMarks) {
            if (c == character)
                counter++;
        }

        if (counter + value < 3) {
            for (int i = 0; i < value ; i++) {
                revengeMarks.add(character);
            }
        }
        else {
            for (int i = 0; i < 3 - counter ; i++) {
                revengeMarks.add(character);
            }
        }
    }

    /**
     * Remove all marks received from a player
     * @param character The character of the player whose marks are to be removed
     * @throws NullPointerException if character reference is null
     */
    public void removeRevengeMarks(Character character) throws NullPointerException{
        if (character == null)
            throw new NullPointerException("Character cannot be null!");
        while (revengeMarks.contains(character)) {
            revengeMarks.remove(character);
        }
    }

}
