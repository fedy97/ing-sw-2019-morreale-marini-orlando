package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.enumeration.*;

/**
 * A class representing the weapons of the game
 *
 * @author Gabriel Raul Marini
 */

public class WeaponCard extends Card {
    private AmmoCube paidCost;
    private AmmoCube[] extraCost;
    private Effect basicEffect;
    private boolean loaded;

    /**
     * Creates an anonymous weapon
     */
    public WeaponCard() {
    }

    /**
     * Creates a loaded weapon with the costs specified
     *
     * @param paidCost  consisting of an AmmoCube the player don't have to pay when he grabs the weapon
     * @param extraCost consisting of one or two AmmoCubes the player pay when he grabs the weapon
     */
    public WeaponCard(AmmoCube paidCost, AmmoCube[] extraCost) {

        this.paidCost = paidCost;
        this.extraCost = extraCost;
        loaded = true;
    }

    /**
     * constructor for json parser
     * @param name
     * @param descr
     * @param img
     * @param paidCost
     * @param extraCost
     * @throws InvalidNameException
     */
    public WeaponCard(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name,descr,img);
        this.paidCost = paidCost;
        this.extraCost = extraCost;
        loaded = true;
    }

    /**
     * Reload the weapon
     */
    public void reload() {
        loaded = true;
    }

    /**
     * @retun the paid cost of the weapon
     */
    public AmmoCube getPaidCost() {
        return paidCost;
    }

    /**
     * @return the extra cost of the weapon
     */
    public AmmoCube[] getExtraCost() {
        return extraCost;
    }

    /**
     * @return the total cost of the weapon (used to calculate the reload cost of the weapon)
     */
    public AmmoCube[] getTotalCost() {
        AmmoCube[] ammoCubes = new AmmoCube[extraCost.length + 1];
        ammoCubes[0] = paidCost;
        System.arraycopy(extraCost, 0, ammoCubes, 1, extraCost.length);
        return ammoCubes;
    }

    /**
     * @return if the weapon is loaded and able to fire
     */
    public boolean isLoaded() {
        return loaded;
    }


    public void activateBasicEffect() {
        // TODO
    }

}