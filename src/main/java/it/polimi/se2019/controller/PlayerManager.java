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
import it.polimi.se2019.network.message.to_client.SendBinaryOption;
import it.polimi.se2019.utils.CustomLogger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
     * @param weapons that can be grabbed by the player according to his ammos
     */
    public void grab(List<WeaponCard> weapons) {
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
                CustomLogger.logException(this.getClass().getName(), e);
            }
        }
        father.getGame().notifyChanges();
    }

    /**
     * @param targetsMap containing the damage values for each player.
     *                   keys are the characters that have been shot
     */
    public void addDamage(Map<Player, Integer> targetsMap) {
        Iterator hmIterator = targetsMap.entrySet().iterator();
        Player p;

        int i = 0;
        while (!currentPlayer.getPowerUpCards().isEmpty()) {
            PowerUpCard powerUpCard = currentPlayer.getPowerUpCards().get(i);
            if (powerUpCard.getName().equals("mirino"))
                father.callView(new SendBinaryOption("Do you want to use Mirino?"), currentPlayer.getName());

            try {
                if (father.getChosenBinaryOption().take()) {

                    List<PowerUpCard> toShow = new ArrayList<>();
                    tempPlayers = new ArrayList<>(targetsMap.keySet());

                    for (PowerUpCard mir : currentPlayer.getPowerUpCards()) {
                        if (mir.getName().equals("mirino")) {
                            toShow.add(mir);
                        }
                    }

                    father.askFor(toShow, "powerups");
                    father.setState(ControllerState.PROCESSING_POWERUP);
                    while(father.getState() == ControllerState.PROCESSING_POWERUP)
                        Thread.sleep(200);
                }
            } catch (Exception e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }

        }

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
            } catch (InvalidCharacterException e) {
                CustomLogger.logException(this.getClass().getName(), e);
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
    public void buyWeapon(WeaponCard weapon) throws InsufficientAmmoException, MaxWeaponException, InvalidGenerationSpotException {
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
     * @param powerUp chosen by the player from the list of those allowed by the validator
     * @throws InvalidCardException if the card doesn't belong to the current player
     */
    public void usePowerUp(PowerUpCard powerUp) throws InvalidCardException {
        if (!currentPlayer.getPowerUpCards().contains(powerUp))
            throw new InvalidCardException("The PowerUp doesn't belong to the current player, something went wrong!");

        currentPlayer.removePowerUpCard(powerUp);
        father.getDecksManager().addToGarbage(powerUp);
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

    public void setTempPlayers(List<Player> tempPlayers) {
        this.tempPlayers = tempPlayers;
    }
}