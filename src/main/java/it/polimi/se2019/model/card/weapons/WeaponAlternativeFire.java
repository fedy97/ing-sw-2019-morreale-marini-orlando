package it.polimi.se2019.model.card.weapons;


public abstract class WeaponAlternativeFire extends WeaponCard {
    private Effect alternativeEffect;

    /**
     * @return the alternative effect of the weapon
     */
    public Effect getAlternativeEffect() {
        return alternativeEffect;
    }

}