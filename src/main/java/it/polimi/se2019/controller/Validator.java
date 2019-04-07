package it.polimi.se2019.controller;

import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Federico Morreale
 */
public interface Validator {

    /**
     * @param dir 
     * @return
     */
    public boolean isValidMove(ArrayList<Orientation> dir);

    /**
     * @param target 
     * @return
     */
    public boolean isValidTarget(Player target);

    /**
     * @param position 
     * @return
     */
    public boolean canGrab(Platform position);

}