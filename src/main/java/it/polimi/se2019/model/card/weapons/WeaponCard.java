package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the weapons of the game
 *
 * @author Gabriel Raul Marini
 */

public class WeaponCard extends Card {
    protected boolean[] usableEffects;
    protected boolean loaded;
    protected Controller c;
    protected Game game;
    protected PlayerManager playerManager;
    private AmmoCube paidCost;
    private AmmoCube[] extraCost;
    private List<Effect> effects;

    /**
     * Creates an anonymous weapon
     */
    public WeaponCard() {
    }

    /**
     * constructor for json parser
     *
     * @param name of the weapon
     * @param descr description of the weapon
     * @param img path to the weapon's image
     * @param paidCost in addition to extraCost define the reload cost
     * @param extraCost paid when grabbing the weapon
     * @throws InvalidNameException
     */
    public WeaponCard(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img);
        this.paidCost = paidCost;
        this.extraCost = extraCost;
        loaded = true;
        effects = new ArrayList<>();
        usableEffects = new boolean[]{true};
        c = Controller.getInstance();
        game = c.getGame();
        playerManager = c.getPlayerManager();
    }

    /**
     * Reload the weapon
     */
    public void reload() {
        cleanCache();
        usableEffects[0] = true;
        loaded = true;
    }

    /**
     * @retun the paid cost of the weapon
     */
    public AmmoCube getPaidCost() {
        return paidCost;
    }

    /**
     * @return the extra cost of the weapon
     */
    public AmmoCube[] getExtraCost() {
        return extraCost;
    }

    /**
     * @return the total cost of the weapon (used to calculate the reload cost of the weapon)
     */
    public AmmoCube[] getTotalCost() {
        if (extraCost != null) {
            AmmoCube[] ammoCubes = new AmmoCube[extraCost.length + 1];
            ammoCubes[0] = paidCost;
            System.arraycopy(extraCost, 0, ammoCubes, 1, extraCost.length);
            return ammoCubes;
        } else {
            AmmoCube[] ammoCubes = new AmmoCube[1];
            ammoCubes[0] = paidCost;
            return ammoCubes;
        }
    }

    /**
     * @return if the weapon is loaded and able to fire
     */
    public boolean isLoaded() {
        return loaded;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public boolean[] getUsableEffects() {
        return usableEffects;
    }

    public void setUsableEffects(boolean[] usableEffects) {
        this.usableEffects = usableEffects;
    }

    public void activateEffect(int index, List<Character> targets) {
        effects.get(index).activateEffect(targets, this);
    }

    public void discard() {
        loaded = false;
        cleanCache();
    }

    /**
     * Clean the last action memory once the card was used
     */
    protected void cleanCache() {
        for (Effect effect : effects) {
            if(effect.getPossibleTargets() != null)
                effect.getPossibleTargets().clear();
            effect.getLastEffectTargets().clear();
        }
    }
}