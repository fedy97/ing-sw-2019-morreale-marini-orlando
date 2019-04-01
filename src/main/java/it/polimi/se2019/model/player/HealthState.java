package it.polimi.se2019.model.player;

import java.util.*;
import it.polimi.se2019.model.enumeration.*;


public interface HealthState {


    /*
     * @param dist 
     * @param dirs
     */
    void move(int dist, ArrayList<Orientation> dirs);

    /*
     * @param dist 
     * @param dirs
     */
    void grab(int dist, ArrayList<Orientation> dirs);

    /*
     * @param dist 
     * @param dirs 
     * @param Player
     */
    void shoot(int dist, ArrayList<Orientation> dirs, Player player);

}