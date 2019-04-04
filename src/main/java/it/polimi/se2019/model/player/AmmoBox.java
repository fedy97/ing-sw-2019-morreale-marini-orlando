package it.polimi.se2019.model.player;

import it.polimi.se2019.model.enumeration.AmmoCube;

/**
 * Class that manages the ammunition owned by the player
 * @author Simone Orlando
 */
public class AmmoBox {

    private int redAmmos;
    private int blueAmmos;
    private int yellowAmmos;

    /**
     * Class constructor that initializes the number of ammunition to two for each color
     */
    public AmmoBox() {

    }

    /**
     * Get the current number of red ammunition the player has
     * @return The number of red ammunition
     */
    public int getRedAmmos() {
        return redAmmos;
    }

    /**
     * Get the current number of blue ammunition the player has
     * @return The number of blue ammunition
     */
    public int getBlueAmmos() {
        return blueAmmos;
    }

    /**
     * Get the current number of yellow ammunition the player has
     * @return The number of yellow ammunition
     */
    public int getYellowAmmos() {
        return yellowAmmos;
    }

    /**
     * Add ammunition to those already owned by the player
     * @param type The color of the ammunition to add
     * @param num The number of ammunition to add
     */
    public void addAmmos(AmmoCube type, int num) {

    }

    /**
     * Remove ammo from the player's box
     * @param type The color of the ammunition to remove
     * @param num The number of ammunition to remove
     */
    public void removeAmmos(AmmoCube type, int num) {

    }
}
