package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;

import java.util.List;

/**
 * Weapon with alternative effects
 *
 * @author Gabriel Raul Marini
 */
public class WeaponAlternativeFire extends WeaponCard {

    public WeaponAlternativeFire(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        usableEffects = new boolean[]{true, true};
    }

    @Override
    public void activateEffect(int index, List<Character> targets){
        super.activateEffect(index, targets);
        usableEffects[0] = false;
        usableEffects[1] = false;
    }

    @Override
    public void reload() {
        cleanCache();
        usableEffects[0] = true;
        usableEffects[1] = true;
        loaded = true;
    }
}