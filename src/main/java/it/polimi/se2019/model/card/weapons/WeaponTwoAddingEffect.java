package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;

/**
 * @author Gabriel Raul Marini
 */
public class WeaponTwoAddingEffect extends WeaponCard {

    public WeaponTwoAddingEffect(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        usableEffects = new boolean[3];
    }
}