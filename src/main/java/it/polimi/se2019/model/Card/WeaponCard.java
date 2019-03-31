package it.polimi.se2019.model.Card;

import java.util.*;

/**
 * 
 */
public abstract class WeaponCard extends Card {

    /**
     * Default constructor
     */
    public WeaponCard() {
    }

    /**
     * 
     */
    private AmmoCube paidCost;

    /**
     * 
     */
    private AmmoCube extraCost;

    /**
     * 
     */
    private boolean loaded;

    /**
     * 
     */
    public void reload() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void getPaidCost() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean isLoaded() {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    public void activateBasicEffect() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void getExtraCost() {
        // TODO implement here
        return null;
    }

}