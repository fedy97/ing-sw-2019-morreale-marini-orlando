package it.polimi.se2019.model.board;

import java.util.*;
import it.polimi.se2019.model.enumeration.*;


public class Room {


    public Room() {
    }


    private int idRoom;


    private Color roomColor;


    private ArrayList<Integer> platformsInRoom;


    private ArrayList<Integer> playersInRoom;


    private boolean hasGenerationSpot;



    /*
     * @return
     */
    public int getIdRoom() {
        // TODO
        return 0;
    }

    /*
     * @return
     */
    public Color getRoomColor() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public ArrayList<Integer> getPlatformsInRoom() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public ArrayList<Integer> getPlayersInRoom() {
        // TODO
        return null;
    }

    /*
     * @return
     */
    public boolean checkIfHasGenerationSpot() {
        // TODO
        return true;
    }

    /*
     * @param int 
     * @return
     */
    public Platform getPlatform(int idPlatform) {
        // TODO
        return null;
    }

    /*
     * @param int
     */
    public void setPlayerInRoom(int idPlayer) {
        // TODO
    }

}