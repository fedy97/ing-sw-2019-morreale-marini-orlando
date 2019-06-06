package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;

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
    private List<Character> possibleTargets;
    private int maxTargets;

    public Effect(AmmoCube[] cost) {
        this.cost = cost;
        stages = new ArrayList<>();
        basicEffects = new ArrayList<>();
    }

    /**
     * @param stage
     */
    public void activateEffect(int stage) {
        basicEffects.get(stage).activate(Controller.getInstance());
    }

    /**
     *
     * @param effect
     */
    public void addBasicEffect(BasicEffect effect) {
        basicEffects.add(effect);
    }

    public void addStage(int stage) {
        stages.add(stage);
    }

    public ArrayList<BasicEffect> getBasicEffects() {
        return basicEffects;
    }

    public void setBasicEffects(ArrayList<BasicEffect> basicEffects) {
        this.basicEffects = basicEffects;
    }

    public List<Character> getPossibleTargets() {
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
}
