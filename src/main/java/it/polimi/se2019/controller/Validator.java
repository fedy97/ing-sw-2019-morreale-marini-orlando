package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Gabriel Raul Marini
 */
public abstract class Validator {
    private PlayerManager playerManager;

    /**
     * Instantiate a validator according to the match state
     */
    public Validator(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * @return the platform the player can reach according to the actual health state and game mode
     */
    public abstract ArrayList<Platform> getValidMoves();

    /**
     * @param weapon chosen by the player to perform a damage action
     * @return the list of player that can be the targets of the weapon according to its effect
     */
    public ArrayList<Player> getValidTargets(WeaponCard weapon) {
        //TODO
        return null;
    }

    /**
     * @return the list of weapons that can be grabbed by the player in his current position
     * according to the ammo qty in his AmmoBox
     */
    public ArrayList<WeaponCard> getGrabableWeapons() throws InvalidGenerationSpotException {
        ArrayList<WeaponCard> res = new ArrayList<>();
        Player currPlayer = playerManager.getCurrentPlayer();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();
        Platform p = currPlayer.getCurrentPlatform();

        if (!p.isGenerationSpot())
            throw new InvalidGenerationSpotException();

        res.addAll(p.getWeapons());

        for (WeaponCard weapon : res) {
            if (!ammoBox.hasAmmos(weapon.getExtraCost()))
                res.remove(weapon);
        }

        return res;
    }

    /**
     * @return the list of weapons the current player can reload according to his ammos
     */
    public ArrayList<WeaponCard> getReloadableWeapons() {
        Player currPlayer = playerManager.getCurrentPlayer();
        ArrayList<WeaponCard> res = currPlayer.getWeaponCards();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();

        for (WeaponCard weapon : res) {
            if (!ammoBox.hasAmmos(weapon.getTotalCost()))
                res.remove(weapon);
        }

        return res;
    }

}