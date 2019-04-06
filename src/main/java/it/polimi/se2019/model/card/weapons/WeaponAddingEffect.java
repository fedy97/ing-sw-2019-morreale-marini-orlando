package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.model.enumeration.AmmoCube;


public abstract class WeaponAddingEffect extends WeaponCard {


    public WeaponAddingEffect() {
    }


    private AmmoCube addingCostFirst;


    private AmmoCube addingCostSecond;


    public void activateFirstEffect() {
        // TODO
    }


    public void ActivateSecondEffect() {
        // TODO
    }

    /*
     * @return
     */
    public AmmoCube getAddingCostFirst() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public AmmoCube getAddingCostSecond() {
        // TODO
        return null;
    }

}