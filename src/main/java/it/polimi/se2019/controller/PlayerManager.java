package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InsufficientAmmoException;
import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.exceptions.MaxWeaponException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
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
    private List<Player> tempPlayers;
    private int actionsLeft;
    private Controller father;

    public PlayerManager(Player player, Controller father) {
        actionsLeft = 2;
        currentPlayer = player;
        this.father = father;
    }

    /**
     * @return the actions of the player
     */
    public int getActionsLeft() {
        return actionsLeft;
    }

    public void useAction() {
        actionsLeft--;
    }

    /**
     * Reset the actions the current player can perform
     */
    public void resetActionLeft() {
        actionsLeft = 2;
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
    public void move(List<Orientation> dirs) {
        Platform platform = currentPlayer.getCurrentPlatform();
        for (Orientation dir : dirs) {
            platform = platform.getAdjacentPlatform(dir);
        }
        currentPlayer.setCurrentPlatform(platform);
    }

    /**
     * @param dir
     */
    public void grab(List<Orientation> dir) {
        //TODO
    }

    /**
     * @param targetsMap
     */
    public void addDamage(Map<Player, Integer> targetsMap) {
        //TODO
    }

    /**
     * @param weapon to be bought
     * @throws InsufficientAmmoException when the player hasn't enough ammos to buy the weapon
     * @throws MaxWeaponException        when the player has already three weapons, he can choose to discard one
     *                                   or not buying the current one
     */
    public void buyWeapon(WeaponCard weapon) throws InsufficientAmmoException, MaxWeaponException {
        AmmoBox box = currentPlayer.getPlayerBoard().getAmmoBox();
        if (box.hasAmmos(weapon.getTotalCost())) {
            currentPlayer.addWeaponCard(weapon);
        } else
            throw new InsufficientAmmoException("Player hasn't enough ammos to buy the weapon");
    }

    /**
     * @param powerUp chosen by the player from the list of those allowed by the validator
     * @throws InvalidCardException if the card doesn't belong to the current player
     */
    public void usePowerUp(PowerUpCard powerUp) throws InvalidCardException {
        if (!currentPlayer.getPowerUpCards().contains(powerUp))
            throw new InvalidCardException("The PowerUp doesn't belong to the current player, something went wrong!");

        powerUp.useEffect(father);
        currentPlayer.removePowerUpCard(powerUp);
        father.getDecksManager().addToGarbage(powerUp);
    }

    /**
     * @param weapons collection of weapons to be recharged
     * @throws InsufficientAmmoException if the player hasn't enough ammos to pay the reload cost
     *                                   of the weapon
     */
    public void reload(List<WeaponCard> weapons) throws InsufficientAmmoException {
        AmmoBox box = currentPlayer.getPlayerBoard().getAmmoBox();
        for (WeaponCard weaponCard : weapons) {
            if (box.hasAmmos(weaponCard.getTotalCost()))
                weaponCard.reload();
            else
                throw new InsufficientAmmoException("CurrentPlayer hasn't enough ammos to recharge the weapons");
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param player to be managed by the class
     */
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public List<Player> getTempPlayers() {
        return tempPlayers;
    }

    public void setTempPlayers(List<Player> tempPlayers) {
        this.tempPlayers = tempPlayers;
    }
}