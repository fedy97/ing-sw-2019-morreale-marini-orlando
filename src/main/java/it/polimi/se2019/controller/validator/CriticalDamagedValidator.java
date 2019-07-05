package it.polimi.se2019.controller.validator;

import it.polimi.se2019.model.enumeration.Action;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * Validate actions in "critical damaged" mode
 */
public class CriticalDamagedValidator extends Validator {

    public CriticalDamagedValidator(Controller father) {
        super(father);
    }


    /**
     * @param c command received by the player
     * @return the list of platform destination the player can move to
     */
    @Override
    public List<Platform> getValidMoves(Action c) {
        List<Platform> res = null;
        GameField gameField = father.getGame().getGameField();
        Player currentPlayer = father.getPlayerManager().getCurrentPlayer();

        if (c == Action.SHOOT)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 1);
        if (c == Action.MOVE)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 3);
        if (c == Action.GRAB)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 2);

        return res;
    }

}