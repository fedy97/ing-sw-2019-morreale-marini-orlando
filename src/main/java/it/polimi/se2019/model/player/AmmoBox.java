package it.polimi.se2019.model.player;

import it.polimi.se2019.model.enumeration.AmmoCube;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages the ammunition owned by the player
 *
 * @author Simone Orlando
 */
public class AmmoBox {

    private int redAmmos;
    private int blueAmmos;
    private int yellowAmmos;
    private List<AmmoCube> optionals;

    /**
     * Class constructor that initializes the number of ammunition to two for each color
     */
    public AmmoBox() {
        redAmmos = 1;
        blueAmmos = 1;
        yellowAmmos = 1;
        optionals = new ArrayList<>();
    }

    /**
     * Get the current number of red ammunition the player has
     *
     * @return The number of red ammunition
     */
    public int getRedAmmos() {
        return redAmmos;
    }

    /**
     * Get the current number of blue ammunition the player has
     *
     * @return The number of blue ammunition
     */
    public int getBlueAmmos() {
        return blueAmmos;
    }

    /**
     * Get the current number of yellow ammunition the player has
     *
     * @return The number of yellow ammunition
     */
    public int getYellowAmmos() {
        return yellowAmmos;
    }

    /**
     * @return the total number of AmmoCubes of the box
     */
    public int getTotal() {
        return redAmmos + blueAmmos + yellowAmmos;
    }

    /**
     * Add ammunition to those already owned by the player
     *
     * @param type The color of the ammunition to add
     * @param num  The number of ammunition to add
     */
    public void addAmmos(AmmoCube type, int num) {
        if (type == AmmoCube.RED) {
            if (redAmmos + num > 3)
                redAmmos = 3;
            else
                redAmmos = redAmmos + num;
        } else if (type == AmmoCube.BLUE) {
            if (blueAmmos + num > 3)
                blueAmmos = 3;
            else
                blueAmmos = blueAmmos + num;
        } else {
            if (yellowAmmos + num > 3)
                yellowAmmos = 3;
            else
                yellowAmmos = yellowAmmos + num;
        }
    }

    /**
     * Remove ammo from the player's box
     *
     * @param type The color of the ammunition to remove
     * @param num  The number of ammunition to remove
     */
    public void removeAmmos(AmmoCube type, int num) {
        List<AmmoCube> toRemove = new ArrayList<>();

        for (AmmoCube cube : optionals)
            if (cube == type) {
                toRemove.add(cube);
                num--;
            }
        optionals.removeAll(toRemove);

        if (type == AmmoCube.RED) {
            if (redAmmos - num < 0)
                redAmmos = 0;
            else
                redAmmos = redAmmos - num;
        } else if (type == AmmoCube.BLUE) {
            if (blueAmmos - num < 0)
                blueAmmos = 0;
            else
                blueAmmos = blueAmmos - num;
        } else {
            if (yellowAmmos - num < 0)
                yellowAmmos = 0;
            else
                yellowAmmos = yellowAmmos - num;
        }
    }

    /**
     * @return if the AmmoBox of the player is totally empty
     */
    public boolean isEmpty() {
        return blueAmmos == 0 && redAmmos == 0 && yellowAmmos == 0;
    }

    /**
     * @param ammo to be checked
     * @return if the AmmoBox has the AmmoCube specified by the parameters
     */
    public boolean hasAmmo(AmmoCube ammo) {
        if (ammo == AmmoCube.RED)
            return redAmmos > 0;
        if (ammo == AmmoCube.BLUE)
            return blueAmmos > 0;
        if (ammo == AmmoCube.YELLOW)
            return yellowAmmos > 0;
        return false;
    }

    /**
     * Method used to check if the player has enough ammos to perform actions
     *
     * @param ammos to be checked
     * @return if the AmmoBox has enough ammos
     */
    public boolean hasAmmos(AmmoCube[] ammos) {
        if (ammos == null) return true;
        int red = 0;
        int blue = 0;
        int yellow = 0;
        int optRed = 0;
        int optBlue = 0;
        int optYellow = 0;

        for (AmmoCube cube : ammos) {
            if (cube == AmmoCube.YELLOW)
                yellow++;
            if (cube == AmmoCube.BLUE)
                blue++;
            if (cube == AmmoCube.RED)
                red++;
        }

        for (AmmoCube optional : optionals) {
            if (optional == AmmoCube.BLUE)
                optBlue++;
            if (optional == AmmoCube.RED)
                optRed++;
            if (optional == AmmoCube.YELLOW)
                optYellow++;
        }


        return (blueAmmos + optBlue) >= blue && (redAmmos + optRed) >= red && (yellowAmmos + optYellow) >= yellow;
    }


    /**
     * @return the list of cubes of the AmmoBox
     */
    public List<AmmoCube> getCubes() {
        List<AmmoCube> cubes = new ArrayList<>();

        for (int i = 0; i < redAmmos; i++)
            cubes.add(AmmoCube.RED);
        for (int i = 0; i < blueAmmos; i++)
            cubes.add(AmmoCube.BLUE);
        for (int i = 0; i < yellowAmmos; i++)
            cubes.add(AmmoCube.YELLOW);

        return cubes;
    }

    public void addOptionalAmmo(AmmoCube ammoCube) {
        optionals.add(ammoCube);
    }

    public void removeOptionalAmmo(AmmoCube ammoCube) {
        optionals.remove(ammoCube);
    }
}
