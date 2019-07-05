package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.toclient.SendBinaryOption;
import it.polimi.se2019.network.message.toclient.ShowUsablePowerupsMessage;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.Serializable;
import java.util.*;

/**
 * A manager class that controls the player behaviour
 *
 * @author Gabriel Raul Marini
 */
public class PlayerManager implements Serializable {

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
     * Clear the number of action left
     */
    public void clearActionLeft() {
        actionsLeft = 0;
    }

    /**
     * Mark the target player with the mark of the current player
     *
     * @param target the player to be marked
     * @param amount of mark value
     */
    public void mark(Player target, int amount) {
        target.getPlayerBoard().addRevengeMark(currentPlayer.getCharacter(), amount);
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
                PowerUpCard powerUp = father.getDecksManager().drawPowerUp();
                if (currentPlayer.getPowerUpCards().size() < 3)
                    currentPlayer.addPowerUpCard(powerUp);
                else
                    father.getDecksManager().addToGarbage(powerUp);
            }

            AmmoCube[] ammoCubes = ammo.getAmmoCubes();
            for (int i = 0; i < ammoCubes.length; i++)
                currentPlayer.getPlayerBoard().getAmmoBox().addAmmos(ammoCubes[i], 1);

            father.getDecksManager().discardAmmo(ammo);
            father.getGame().notifyChanges();
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    /**
     * @param targetsMap containing the damage values for each player.
     *                   keys are the characters that have been shot
     */
    public synchronized void addDamage(Map<Player, Integer> targetsMap) {
        Iterator hmIterator = targetsMap.entrySet().iterator();

        if (!father.isDebug()) {
            checkTargettingScope(targetsMap.keySet());
            checkTagbackGrenade(targetsMap.keySet());
        }

        Player p;
        StringBuilder targetsDamaged = new StringBuilder();
        while (hmIterator.hasNext()) {

            Map.Entry mapElement = (Map.Entry) hmIterator.next();
            int damage = ((int) mapElement.getValue());
            p = (Player) mapElement.getKey();
            List<Character> marks = p.getPlayerBoard().getRevengeMarks();

            try {
                if (marks.contains(currentPlayer.getCharacter())) {
                    for (Character mark : marks)
                        if (mark == currentPlayer.getCharacter())
                            p.getPlayerBoard().addDamage(currentPlayer.getCharacter(), 1);
                    p.getPlayerBoard().removeRevengeMarks(currentPlayer.getCharacter());
                }

                p.getPlayerBoard().addDamage(currentPlayer.getCharacter(), damage);
                targetsDamaged.append(p.getName());
                targetsDamaged.append(", ");
            } catch (InvalidCharacterException e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }
        }
        targetsDamaged.append("damaged");
        father.broadcastMessage(targetsDamaged.toString());
        father.getGame().notifyChanges();
    }

    /**
     * Check if the player can use TargettingScope
     *
     * @param targets that are receiving damage
     */
    private void checkTargettingScope(Set<Player> targets) {
        List<PowerUpCard> targettingScope = new ArrayList<>();

        for (PowerUpCard powerUp : currentPlayer.getPowerUpCards())
            if (powerUp.getName().equals("mirino"))
                targettingScope.add(powerUp);

        int i = 0;
        while (i < targettingScope.size()) {
            father.callView(new SendBinaryOption("Do you want to use Mirino?"), currentPlayer.getName());

            try {
                if (father.getChosenBinaryOption().take()) {
                    tempPlayers = new ArrayList<>(targets);

                    father.askFor(targettingScope, "powerups");
                    father.setState(ControllerState.PROCESSING_POWERUP);
                    while (father.getState() == ControllerState.PROCESSING_POWERUP)
                        Thread.sleep(200);
                } else
                    break;
            } catch (Exception e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }
            i++;
        }
    }

    /**
     * Check if the targets can use TagBackGrenade
     *
     * @param targets that are receiving damage
     */
    private void checkTagbackGrenade(Set<Player> targets) {

        for (Player player : targets) {
            List<PowerUpCard> tagbackGrenade = new ArrayList<>();
            for (PowerUpCard powerUp : player.getPowerUpCards()) {
                if (powerUp.getName().equals("granata venom"))
                    tagbackGrenade.add(powerUp);
            }

            if (!tagbackGrenade.isEmpty()) {
                List<String> lightVersion = HandyFunctions.getLightCollection(tagbackGrenade);
                father.callView(new ShowUsablePowerupsMessage(lightVersion), player.getName());
            }
        }
    }


    /**
     * @param weapon to be bought
     * @throws MaxWeaponException when the player has already three weapons, he can choose to discard one
     *                            or not buying the current one
     * @throws InvalidGenerationSpotException .
     */
    public synchronized void buyWeapon(WeaponCard weapon) throws MaxWeaponException, InvalidGenerationSpotException {
        AmmoBox box = currentPlayer.getPlayerBoard().getAmmoBox();
        currentPlayer.addWeaponCard(weapon);
        currentPlayer.getCurrentPlatform().removeWeaponCard(weapon);
        AmmoCube[] cost = weapon.getExtraCost();
        if (cost != null) {
            for (int i = 0; i < cost.length; i++)
                box.removeAmmos(cost[i], 1);
        }
        father.getGame().notifyChanges();
    }

    /**
     * @param powerUpCard to be converted into the corresponding ammo cube
     * @throws InvalidCardException if the card doesn't belong to the current player
     */
    public void convertPowerUpToAmmo(PowerUpCard powerUpCard) throws InvalidCardException {
        if (!currentPlayer.getPowerUpCards().contains(powerUpCard))
            throw new InvalidCardException("The PowerUp doesn't belong to the current player, something went wrong!");

        currentPlayer.getPlayerBoard().getAmmoBox().addOptionalAmmo(powerUpCard.getAmmoCube());
        currentPlayer.removePowerUpCard(powerUpCard);
        father.getDecksManager().addToGarbage(powerUpCard);
        father.getGame().notifyChanges();
    }

    /**
     * @param weaponCard to be recharged
     * @throws InsufficientAmmoException if the player hasn't enough ammos to pay the reload cost
     *                                   of the weapon
     */
    public void reload(WeaponCard weaponCard) throws InsufficientAmmoException {
        AmmoBox box = currentPlayer.getPlayerBoard().getAmmoBox();
        if (box.hasAmmos(weaponCard.getTotalCost())) {
            weaponCard.reload();
            box.removeAmmos(weaponCard.getTotalCost());
        } else
            throw new InsufficientAmmoException("CurrentPlayer hasn't enough ammos to recharge the weapons");
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
        resetActionLeft();
    }

    public List<Player> getTempPlayers() {
        return tempPlayers;
    }
}