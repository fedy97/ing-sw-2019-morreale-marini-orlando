package it.polimi.se2019.model.Card.Weapons;

import it.polimi.se2019.model.Card.WeaponCard;
import it.polimi.se2019.model.Enum.AmmoCube;

import java.util.*;

/**
 * 
 */
public abstract class WeaponAddingEffect extends WeaponCard {

    /**
     * Default constructor
     */
    public WeaponAddingEffect() {
    }

    /**
     * 
     */
    private AmmoCube addingCostFirst;

    /**
     * 
     */
    private AmmoCube addingCostSecond;

    /**
     * 
     */
    public void activateFirstEffect() {
        // TODO implement here
    }

    /**
     * 
     */
    public void ActivateSecondEffect() {
        // TODO implement here
    }

    /**
     * @return
     */
    public AmmoCube getAddingCostFirst() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public AmmoCube getAddingCostSecond() {
        // TODO implement here
        return null;
    }

}