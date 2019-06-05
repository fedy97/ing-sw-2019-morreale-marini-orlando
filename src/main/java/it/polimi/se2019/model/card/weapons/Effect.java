package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A class used to describe the weapons effects
 *
 * @author Gabriel Raul Marini
 */
public class Effect {
    private ArrayList<BasicEffect> basicEffects;
    private AmmoCube[] cost;
    private List<Integer> stages;
    private List<Player> possibleTargets;
    private int maxTargets;

    public Effect(AmmoCube[] cost) {
        this.cost = cost;
        stages = new ArrayList<>();
        basicEffects = new ArrayList<>();
    }

    public void activateEffect(int stage) {
        basicEffects.get(stage).activate(Controller.getInstance());
    }


    public void addBasicEffect(BasicEffect effect) {
        basicEffects.add(effect);
    }

    public void addStage(int stage) {
        stages.add(stage);
    }

}
