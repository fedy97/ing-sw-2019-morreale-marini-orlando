package it.polimi.se2019.model.board;

import java.util.*;

/**
 * Room containing all the platform in it plus the info about generation spot
 *
 * @author Federico Morreale
 */
public class Room {

    private ArrayList<Platform> platformsInRoom;
    private boolean hasGenerationSpot;

    public Room(ArrayList<Platform> platformsInRoom) {
        this.platformsInRoom = platformsInRoom;
        this.hasGenerationSpot = false;
        for (Platform p : platformsInRoom) {
            if (p.isGenerationSpot()) this.hasGenerationSpot = true;
        }
    }

    /**
     * @return the list of platforms in the room
     */
    public ArrayList<Platform> getPlatformsInRoom() {
        return platformsInRoom;
    }

    /**
     * @return true if the room contains a platform with a generation spot
     */
    public boolean hasGenerationSpot() {
        return hasGenerationSpot;
    }

}