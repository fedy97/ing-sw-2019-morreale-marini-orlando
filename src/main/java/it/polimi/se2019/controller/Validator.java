package it.polimi.se2019.controller;

import it.polimi.se2019.Action;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.Effect;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * @author Gabriel Raul Marini
 */
public abstract class Validator implements Serializable {
    protected Controller father;

    /**
     * Instantiate a validator according to the match state
     */
    public Validator(Controller father) {
        this.father = father;
    }

    /**
     * @param c command received by the player
     * @return the list of platform destination the player can move to
     * @throws InvalidActionException if the player cannot move in the current health state with the
     *                                selected action
     */
    public abstract List<Platform> getValidMoves(Action c) throws InvalidActionException;

    /**
     * @param weapon chosen by the player to perform a damage action
     * @return the list of player that can be the targets of the weapon according to its effect
     */
    public List<Player> getValidTargets(WeaponCard weapon) {
        //TODO
        return new ArrayList<>();
    }

    /**
     * @return the list of weapons that can be grabbed by the player in his current position
     * according to the ammo qty in his AmmoBox
     */
    public List<WeaponCard> getGrabableWeapons() throws InvalidGenerationSpotException {
        List<WeaponCard> res = new ArrayList<>();
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();
        Platform p = currPlayer.getCurrentPlatform();

        if (!p.isGenerationSpot())
            throw new InvalidGenerationSpotException();

        for (WeaponCard weapon : p.getWeapons()) {
            if (ammoBox.hasAmmos(weapon.getExtraCost()))
                res.add(weapon);
        }

        return res;
    }

    /**
     * @param p generation spot selected
     * @return the list of weapons that can be grabbed by the player in the specified platform
     */
    public List<WeaponCard> getGrabableWeapons(Platform p) throws InvalidGenerationSpotException {
        List<WeaponCard> res = new ArrayList<>();
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();

        if (!p.isGenerationSpot())
            throw new InvalidGenerationSpotException();

        for (WeaponCard weapon : p.getWeapons()) {
            if (ammoBox.hasAmmos(weapon.getExtraCost()))
                res.add(weapon);
        }

        return res;
    }

    /**
     * @return the list of weapons the current player can reload according to his ammos
     */
    public List<WeaponCard> getReloadableWeapons() {
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        List<WeaponCard> res = currPlayer.getWeaponCards();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();
        List<WeaponCard> toRemove = new ArrayList<>();

        for (WeaponCard weapon : res) {
            if (!ammoBox.hasAmmos(weapon.getTotalCost()) || weapon.isLoaded())
                toRemove.add(weapon);
        }

        res.removeAll(toRemove);
        return res;
    }

    /**
     * @return the list of PowerUp cards the player can use in the actual state of the game
     */
    public List<PowerUpCard> getUsablePowerUps() {
        List<PowerUpCard> res = new ArrayList<>();
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();

        for (PowerUpCard powerUp : currPlayer.getPowerUpCards())
            if (powerUp.isUsable(currPlayer) && (powerUp.getPossibleTargets() == null || !powerUp.getPossibleTargets().isEmpty()))
                res.add(powerUp);
        return res;
    }

    /**
     * @return the list of PowerUp cards the player can use to buy or reload weapons
     */
    public Map<PowerUpCard, Boolean> getUsablePowerUps(WeaponCard weaponCard, boolean reload) {
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        List<PowerUpCard> res = new ArrayList<>();
        AmmoCube[] cost;

        if (reload)
            cost = weaponCard.getTotalCost();
        else
            cost = weaponCard.getExtraCost();

        for (PowerUpCard powerUp : currPlayer.getPowerUpCards())
            if (reload)

                if (powerUp.isUsable(currPlayer))
                    res.add(powerUp);
        return null; //TODO

    }

    /**
     * @return the weapons the player can use according to the current state of the game
     */
    public List<WeaponCard> getUsableWeapons() {
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        List<WeaponCard> res = currPlayer.getWeaponCards();
        List<WeaponCard> toRemove = new ArrayList<>();

        for (WeaponCard weapon : res) {
            if (!weapon.isLoaded())
                toRemove.add(weapon);
            else {
                boolean usable = false;
                boolean[] usableEffect = weapon.getUsableEffects();

                for (int i = 0; i < usableEffect.length; i++) {
                    if (usableEffect[i]) {
                        Effect effect = weapon.getEffects().get(i);
                        effect.setupTargets();
                        if (effect.getPossibleTargets() == null || !effect.getPossibleTargets().isEmpty())
                            usable = true;
                    }
                }

                if (!usable)
                    toRemove.add(weapon);
            }
        }
        res.removeAll(toRemove);
        HandyFunctions.printList(res);
        return res;
    }

    /**
     * @return a list of the platform where the player can grab an ammo
     */
    public List<Platform> getGrabableAmmos() {
        List<Platform> res = null;

        try {
            res = getValidMoves(Action.GRAB);
        } catch (InvalidActionException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        return res;
    }
}