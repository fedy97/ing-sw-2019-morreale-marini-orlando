package it.polimi.se2019.controller.validator;

import it.polimi.se2019.model.enumeration.Action;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.player.Player;

import java.util.List;

/**
 * @author Gabriel Raul Marini
 */
public class Frenzy1Validator extends Validator {

    public Frenzy1Validator(Controller father) {
        super(father);
    }


    /**
     * @param c command received by the player
     * @return the list of platform destination the player can move to
     */
    @Override
    public List<Platform> getValidMoves(Action c) {
        GameField gameField = father.getGame().getGameField();
        Player currentPlayer = father.getPlayerManager().getCurrentPlayer();
        List<Platform> res = null;

        if (c == Action.MOVE)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 4);
        if (c == Action.SHOOT)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 1);
        if (c == Action.GRAB)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 2);

        return res;
    }

}