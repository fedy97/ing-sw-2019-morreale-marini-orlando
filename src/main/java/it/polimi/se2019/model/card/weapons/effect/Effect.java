package it.polimi.se2019.model.card.weapons.effect;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to describe the weapons effects
 *
 * @author Gabriel Raul Marini
 */
public abstract class Effect implements Serializable {
    protected Controller c;
    private AmmoCube[] cost;
    private List<Character> possibleTargets;
    private List<Character> lastEffectTargets;
    private int maxTargets;

    public Effect(AmmoCube[] cost) {
        this.cost = cost;
        lastEffectTargets = new ArrayList<>();
        c = Controller.getInstance();
    }

    /**
     * @param targets selected targets to apply the effect
     * @param card to activate
     */
    public abstract void activateEffect(List<Character> targets, WeaponCard card);

    /**
     * @return the possible targets of the effect in the current state of the game
     */
    public List<Character> getPossibleTargets() {
        if (possibleTargets != null) {
            Character character = Controller.getInstance().getPlayerManager().getCurrentPlayer().getCharacter();
            while (possibleTargets.contains(character))
                possibleTargets.remove(character);
        }
        return possibleTargets;
    }

    public void setPossibleTargets(List<Character> possibleTargets) {
        this.possibleTargets = possibleTargets;
    }

    public int getMaxTargets() {
        return maxTargets;
    }

    public void setMaxTargets(int maxTargets) {
        this.maxTargets = maxTargets;
    }

    public AmmoCube[] getCost() {
        return cost;
    }

    public abstract void setupTargets();

    public List<Character> getLastEffectTargets() {
        return lastEffectTargets;
    }
}
