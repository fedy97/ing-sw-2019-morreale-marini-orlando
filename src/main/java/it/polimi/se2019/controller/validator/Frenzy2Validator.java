package it.polimi.se2019.controller.validator;

import it.polimi.se2019.Action;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Gabriel Raul Marini
 */
public class Frenzy2Validator extends Validator {

    public Frenzy2Validator(Controller father) {
        super(father);
    }

    /**
     * @param c command received by the player
     * @throws InvalidActionException if move action is passed, cannot move in this mode
     * @return the list of platform destination the player can move to
     */
    @Override
    public List<Platform> getValidMoves(Action c) throws InvalidActionException{
        List<Platform> res;
        GameField gameField = father.getGame().getGameField();
        Player currentPlayer = father.getPlayerManager().getCurrentPlayer();

        if (c == Action.SHOOT)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 2);
        else if (c == Action.GRAB)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 3);
        else
            throw new InvalidActionException("You are in frenzy mode 2, you cannot just move!");

        return res;
    }

}