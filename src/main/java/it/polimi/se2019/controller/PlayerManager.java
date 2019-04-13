package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InsufficientAmmoException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * A manager class that controls the player behaviour
 *
 * @author Gabriel Raul Marini
 */
public class PlayerManager {

    private Player currentPlayer;
    private int actionsLeft;
    private Validator validator;

    public PlayerManager(Player player, Validator initialValidator) {
        actionsLeft = 0;
        currentPlayer = player;
        validator = initialValidator;
    }

    /**
     * Method called when game mode change and different actions are permitted
     *
     * @param newValidator specifying the legal actions in the new game mode
     */
    public void changeValidator(Validator newValidator) {
        validator = newValidator;
    }

    /**
     * @return the actions of the player
     */
    public int getActionsLeft() {
        return actionsLeft;
    }

    public void useAction() {

    }

    public void resetActionLeft() {
    }

    /**
     * Mark the target player with the mark of the current player
     *
     * @param target the player to be marked
     * @param amount of mark value
     */
    public void mark(Player target, int amount) {
        target.getPlayerBoard().addRevengeMark(target.getCharacter(), amount);
    }

    /**
     * Move the player using the current validator to verify that actions are allowed
     *
     * @param dirs the set of directions to follow to move the players
     */
    public void move(ArrayList<Orientation> dirs) {
        if (validator.isValidMove(dirs)) {
            Platform platform = currentPlayer.getCurrentPlatform();
            for (Orientation dir : dirs) {
                platform = platform.getAdjacentPlatform(dir);
            }
            currentPlayer.setCurrentPlatform(platform);
        }
    }

    /**
     * @param dir
     */
    public void grab(ArrayList<Orientation> dir) {

    }

    /**
     * @param dir
     * @param reload
     * @param targets
     * @param weapon
     */
    public void shoot(ArrayList<Orientation> dir, boolean reload, ArrayList<Player> targets, WeaponCard weapon) {

    }

    /**
     * @param weapon
     */
    public void buyWeapon(WeaponCard weapon) {

    }

    /**
     * @param powerUp
     */
    public void usePowerUp(PowerUpCard powerUp) {

    }

    /**
     * @param weapons collection of weapons to be recharged
     * @throws InsufficientAmmoException if the player hasn't enough ammos to pay the reload cost
     *                                   of the weapon
     */
    public void reload(ArrayList<WeaponCard> weapons) throws InsufficientAmmoException {
        AmmoBox box = currentPlayer.getPlayerBoard().getAmmoBox();
        for (WeaponCard weaponCard : weapons) {
            if(box.hasAmmos(new AmmoCube[]{weaponCard.getPaidCost()}) && box.hasAmmos(weaponCard.getExtraCost()))
                weaponCard.reload();
            else
                throw new InsufficientAmmoException("CurrentPlayer hasn't enough ammos to recharhe the weapons");
        }
    }

    /**
     * @param player
     */
    public void setCurrentPlayer(Player player) {

    }

}