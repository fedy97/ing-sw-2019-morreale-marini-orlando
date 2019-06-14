package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;

public abstract class WeaponAlternativeFire extends WeaponCard {

    public WeaponAlternativeFire(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        usableEffects = new boolean[]{true, true};
    }

    @Override
    public void reload() {
        cleanCache();
        usableEffects[0] = true;
        usableEffects[1] = true;
        loaded = true;
    }
}