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
        redAmmos = 1;
        blueAmmos = 1;
        yellowAmmos = 1;
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
     * @throws IllegalArgumentException if num is less than zero
     * @throws NullPointerException if AmmoCube reference is null
     */
    public void addAmmos(AmmoCube type, int num) throws IllegalArgumentException, NullPointerException{
        if (num < 0)
            throw new IllegalArgumentException("Num should be greater than zero!");
        if (type == null)
            throw new NullPointerException("Type cannot be null!");
        if (type == AmmoCube.RED) {
            if (redAmmos + num > 3)
                redAmmos = 3;
            else
                redAmmos = redAmmos + num;
        }
        else if (type == AmmoCube.BLUE) {
            if (blueAmmos + num > 3)
                blueAmmos = 3;
            else
                blueAmmos = blueAmmos + num;
        }
        else {
            if (yellowAmmos + num > 3)
                yellowAmmos = 3;
            else
                yellowAmmos = yellowAmmos + num;
        }
    }

    /**
     * Remove ammo from the player's box
     * @param type The color of the ammunition to remove
     * @param num The number of ammunition to remove
     * @throws IllegalArgumentException if num is less than zero
     * @throws NullPointerException if AmmoCube reference is null
     */
    public void removeAmmos(AmmoCube type, int num) throws IllegalArgumentException, NullPointerException{
        if (num < 0)
            throw new IllegalArgumentException("Num should be greater than zero!");
        if (type == null)
            throw new NullPointerException("Type cannot be null!");
        if (type == AmmoCube.RED) {
            if (redAmmos - num < 0)
                redAmmos = 0;
            else
                redAmmos = redAmmos - num;
        }
        else if (type == AmmoCube.BLUE) {
            if (blueAmmos - num < 0)
                blueAmmos = 0;
            else
                blueAmmos = blueAmmos - num;
        }
        else {
            if (yellowAmmos - num < 0)
                yellowAmmos = 0;
            else
                yellowAmmos = yellowAmmos - num;
        }
    }

    /**
     * @param ammos to be checked
     * @return if the AmmoBox has enough ammos
     */
    public boolean hasAmmos(AmmoCube[] ammos){
        boolean result = true;
        for(int i = 0; i < ammos.length; i++){
            if(ammos[i] == AmmoCube.YELLOW)
                result = yellowAmmos > 0;
            if(ammos[i] == AmmoCube.BLUE);
                result = blueAmmos > 0;
            if(ammos[i] == AmmoCube.RED);
            result = redAmmos > 0;
        }
        return result;
    }
}
