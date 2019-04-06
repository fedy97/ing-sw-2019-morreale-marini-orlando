package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.enumeration.*;


public abstract class WeaponCard extends Card {


    public WeaponCard() {
    }


    private AmmoCube paidCost;


    private AmmoCube extraCost;


    private boolean loaded;


    public void reload() {
        // TODO
    }

    /*
     * @return
     */
    public void getPaidCost() {
        // TODO
        return;
    }

    /*
     * @return
     */
    public boolean isLoaded() {
        // TODO
        return false;
    }


    public void activateBasicEffect() {
        // TODO
    }

    /*
     * @return
     */
    public void getExtraCost() {
        // TODO
        return;
    }

}