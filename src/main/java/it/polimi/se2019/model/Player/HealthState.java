package it.polimi.se2019.model.Player;

import java.util.*;

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
    public void shoot(int dist, ArrayList<Orientation> dirs, void Player);

}