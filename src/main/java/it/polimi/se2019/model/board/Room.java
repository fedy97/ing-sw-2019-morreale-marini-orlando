package it.polimi.se2019.model.board;

import java.util.*;
import it.polimi.se2019.model.enumeration.*;

/**
 * 
 */
public class Room {

    /**
     * Default constructor
     */
    public Room() {
    }

    /**
     * 
     */
    private int idRoom;

    /**
     * 
     */
    private Color roomColor;

    /**
     * 
     */
    private ArrayList<Integer> platformsInRoom;

    /**
     * 
     */
    private ArrayList<Integer> playersInRoom;

    /**
     * 
     */
    private boolean hasGenerationSpot;



    /**
     * @return
     */
    public int getIdRoom() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public Color getRoomColor() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Integer> getPlatformsInRoom() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Integer> getPlayersInRoom() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean checkIfHasGenerationSpot() {
        // TODO implement here
        return true;
    }

    /**
     * @param int 
     * @return
     */
    public Platform getPlatform(int idPlatform) {
        // TODO implement here
        return null;
    }

    /**
     * @param int
     */
    public void setPlayerInRoom(int idPlayer) {
        // TODO implement here
    }

}