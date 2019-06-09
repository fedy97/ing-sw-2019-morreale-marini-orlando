package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the weapons of the game
 *
 * @author Gabriel Raul Marini
 */

public class WeaponCard extends Card {
    protected boolean[] availableEffects;
    protected boolean[] usableEffects;
    protected boolean loaded;
    private AmmoCube paidCost;
    private AmmoCube[] extraCost;
    private List<Effect> effects;

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
     *
     * @param name
     * @param descr
     * @param img
     * @param paidCost
     * @param extraCost
     * @throws InvalidNameException
     */
    public WeaponCard(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img);
        this.paidCost = paidCost;
        this.extraCost = extraCost;
        loaded = true;
        effects = new ArrayList<>();
    }

    /**
     * Reload the weapon
     */

    public void reload() {}

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
        if (extraCost != null) {
            AmmoCube[] ammoCubes = new AmmoCube[extraCost.length + 1];
            ammoCubes[0] = paidCost;
            System.arraycopy(extraCost, 0, ammoCubes, 1, extraCost.length);
            return ammoCubes;
        } else {
            AmmoCube[] ammoCubes = new AmmoCube[1];
            ammoCubes[0] = paidCost;
            return ammoCubes;
        }
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

    @Override
    public String toString() {
        return Integer.toString(hashCode());
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> basicEffect) {
        this.effects = basicEffect;
    }

    public boolean[] getAvailableEffects() {
        return availableEffects;
    }

    public void setAvailableEffects(boolean[] availableEffects) {
        this.availableEffects = availableEffects;
    }

    public boolean[] getUsableEffects() {
        return usableEffects;
    }

    public void setUsableEffects(boolean[] usableEffects) {
        this.usableEffects = usableEffects;
    }

    public void activateEffect(int index, List<Character> targets) {
        effects.get(index).activateEffect(targets, this);
    }

    /**
     * Clean the last action memory once the card was used
     */
    protected void cleanCache() {
        for (Effect effect : effects)
            effect.getLastEffectTargets().clear();
    }
}