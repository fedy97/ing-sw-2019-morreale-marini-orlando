package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.model.card.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;

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