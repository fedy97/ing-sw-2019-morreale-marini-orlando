package it.polimi.se2019.controller;

import it.polimi.se2019.Action;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.*;
import java.util.logging.Level;

/**
 * @author Gabriel Raul Marini
 */
public abstract class Validator{
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
     * @param powerUpCard chosen by the player to perform an action
     * @return the list of player that can be the targets of the powerUp according to its effect
     */
    public List<Player> getValidTargets(PowerUpCard powerUpCard) {
        return powerUpCard.getPossibleTargets(father);
    }

    /**
     * @return the list of weapons that can be grabbed by the player in his current position
     * according to the ammo qty in his AmmoBox
     */
    public List<WeaponCard> getGrabableWeapons() throws InvalidGenerationSpotException {
        List<WeaponCard> res;
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();
        Platform p = currPlayer.getCurrentPlatform();

        if (!p.isGenerationSpot())
            throw new InvalidGenerationSpotException();

        res = new ArrayList<>(p.getWeapons());

        for (WeaponCard weapon : res) {
            if (!ammoBox.hasAmmos(weapon.getExtraCost()))
                res.remove(weapon);
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

        for (WeaponCard weapon : res) {
            if (!ammoBox.hasAmmos(weapon.getTotalCost()))
                res.remove(weapon);
        }

        return res;
    }

    /**
     * @return the list of PowerUp cards the player can use in the actual state of the game
     */
    public List<PowerUpCard> getUsablePowerUps() {
        List<PowerUpCard> res = new ArrayList<>();
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();

        for (PowerUpCard powerUp : currPlayer.getPowerUpCards())
            if (powerUp.isUsable(currPlayer))
                res.add(powerUp);

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