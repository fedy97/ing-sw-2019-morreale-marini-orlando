package it.polimi.se2019.controller;

import it.polimi.se2019.Action;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Gabriel Raul Marini
 */
public class HealthyValidator extends Validator {

    public HealthyValidator(Controller father) {
        super(father);
    }

    /**
     * @param c command received by the player
     * @return the list of platform destination the player can move to
     * @throws InvalidActionException if shoot action is passed, cannot move before shooting
     */
    public List<Platform> getValidMoves(Action c) throws InvalidActionException{
        GameField gameField = father.getGame().getGameField();
        Player currentPlayer = father.getPlayerManager().getCurrentPlayer();
        List<Platform> res;

        if (c == Action.MOVE)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 3);
        else if (c == Action.GRAB)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 1);
        else
            throw new InvalidActionException("Cannot move the player in this mode with the action passed!");

        return res;
    }

}