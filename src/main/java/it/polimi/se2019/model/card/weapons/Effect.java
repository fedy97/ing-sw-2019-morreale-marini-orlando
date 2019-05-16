package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Visibility;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;

/**
 * A class used to describe the weapons effects
 *
 * @author Gabriel Raul Marini
 */
public class Effect {
    private ArrayList<Visibility> possibleTargets;
    private ArrayList<BasicEffect> basicEffects;
    private AmmoCube cost;

    public Effect() {

    }

    public ArrayList<Visibility> getPossibleTargets() {
        return possibleTargets;
    }

    /**
     * Build with basic effects the final effect of the card
     *
     * @param targets players to be damaged, marked or moved by the weapon
     */
    public void activateEffect(ArrayList<Player> targets) {
        for (Player player : targets) {
            for (BasicEffect effect : basicEffects) {
                //     effect.applyTo(player);
            }
        }
    }

}
