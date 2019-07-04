package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;

/**
 * Weapon with one adding effect
 *
 * @author Gabriel Raul Marini
 */
public class WeaponOneAddingEffect extends WeaponCard {

    public WeaponOneAddingEffect(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        usableEffects = new boolean[2];
    }
}