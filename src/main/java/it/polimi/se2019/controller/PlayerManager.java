package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.*;
import java.util.logging.Level;

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

    public PlayerManager(Controller father) {
        actionsLeft = 2;
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
        father.getGame().notifyChanges();
    }

    /**
     * Move the player using the current validator to verify that actions are allowed
     *
     * @param platform platform destination
     */
    public void move(Platform platform) {
        currentPlayer.setCurrentPlatform(platform);
        father.getGame().notifyChanges();
    }

    /**
     * Grab the ammo card placed on the current platform
     */
    public void grabAmmoCard() {
        try {
            AmmoCard ammo = currentPlayer.getCurrentPlatform().grabAmmoCard();
            father.getDecksManager().addToFill(currentPlayer.getCurrentPlatform());

            if (ammo.hasPowerUp()) {
                currentPlayer.addPowerUpCard(father.getDecksManager().drawPowerUp());
            }

            AmmoCube[] ammoCubes = ammo.getAmmoCubes();
            for (int i = 0; i < ammoCubes.length; i++)
                currentPlayer.getPlayerBoard().getAmmoBox().addAmmos(ammoCubes[i], 1);

            father.getDecksManager().discardAmmo(ammo);
            father.getGame().notifyChanges();
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    /**
     * @param weapons that can be grabbed by the player according to his ammos
     * @throws MaxWeaponException is something went wrong
     */
    public void grab(List<WeaponCard> weapons) throws MaxWeaponException {
        for (WeaponCard weaponCard : weapons) {

            try {
                currentPlayer.addWeaponCard(weaponCard);
                currentPlayer.getCurrentPlatform().removeWeaponCard(weaponCard);
            } catch (MaxWeaponException e) {
                /** TODO
                 WeaponCard choice = father.askForDiscard(currentPlayer.getWeaponCards());

                 if (choice != null) {
                 currentPlayer.removeWeaponCard(choice);
                 currentPlayer.addWeaponCard(weaponCard);
                 }*/

            } catch (InvalidGenerationSpotException e) {
                HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
            }
        }
        father.getGame().notifyChanges();
    }

    /**
     * @param targetsMap containing the damage values for each player
     */
    public void addDamage(Map<Player, Integer> targetsMap) {
        Iterator hmIterator = targetsMap.entrySet().iterator();
        Player p;

        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();
            int damage = ((int) mapElement.getValue());
            p = (Player) mapElement.getKey();
            try {
                p.getPlayerBoard().addDamage(currentPlayer.getCharacter(), damage);
            } catch (InvalidCharacterException e) {
                HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
            }
        }

        father.getGame().notifyChanges();
    }

    /**
     * @param weapon to be bought
     * @throws InsufficientAmmoException when the player hasn't enough ammos to buy the weapon
     * @throws MaxWeaponException        when the player has already three weapons, he can choose to discard one
     *                                   or not buying the current one
     */
    public void buyWeapon(WeaponCard weapon) throws InsufficientAmmoException, MaxWeaponException {
        AmmoBox box = currentPlayer.getPlayerBoard().getAmmoBox();
        currentPlayer.addWeaponCard(weapon);
        AmmoCube[] cost = weapon.getExtraCost();

        for (int i = 0; i < cost.length; i++)
            box.removeAmmos(cost[i], 1);

        father.getGame().notifyChanges();
    }

    /**
     * @param powerUp chosen by the player from the list of those allowed by the validator
     * @throws InvalidCardException if the card doesn't belong to the current player
     */
    public void usePowerUp(PowerUpCard powerUp) throws InvalidCardException {
        if (!currentPlayer.getPowerUpCards().contains(powerUp))
            throw new InvalidCardException("The PowerUp doesn't belong to the current player, something went wrong!");

        powerUp.activate(0);
        currentPlayer.removePowerUpCard(powerUp);
        father.getDecksManager().addToGarbage(powerUp);
        father.getGame().notifyChanges();
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
        father.getGame().notifyChanges();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param player to be managed by the class
     */
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
        actionsLeft = 2;
    }

    public List<Player> getTempPlayers() {
        return tempPlayers;
    }

    public void setTempPlayers(List<Player> tempPlayers) {
        this.tempPlayers = tempPlayers;
    }
}