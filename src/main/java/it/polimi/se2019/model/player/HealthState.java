package it.polimi.se2019.model.player;

import java.util.*;
import it.polimi.se2019.model.enumeration.*;

/**
 * 
 */
public interface HealthState {


    /**
     * @param dist 
     * @param dirs
     */
    public void move(int dist, ArrayList<Orientation> dirs);

    /**
     * @param dist 
     * @param dirs
     */
    public void grab(int dist, ArrayList<Orientation> dirs);

    /**
     * @param dist 
     * @param dirs 
     * @param Player
     */
    public void shoot(int dist, ArrayList<Orientation> dirs, Player player);

}