package it.polimi.se2019.controller;

import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Federico Morreale
 */
public class PlayerManager {

    private Player currentPlayer;
    private int actionsLeft;
    private Validator validator;

    public PlayerManager() {
    }

    /**
     * @param newValidator
     */
    public void changeValidator(Validator newValidator) {

    }

    /**
     * @return
     */
    public int getActionLeft() {

        return 0;
    }

    public void useAction() {

    }

    public void resetActionLeft() {

    }

    /**
     * @param target
     * @param amount
     */
    public void mark(Player target, int amount) {

    }

    /**
     * @param dir
     */
    public void move(ArrayList<Orientation> dir) {

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
     * @param weapons
     */
    public void reload(ArrayList<WeaponCard> weapons) {

    }

    /**
     * @param player
     */
    public void setCurrentPlayer(Player player) {

    }

}