package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidRoomException;

import java.util.*;

/**
 * Room containing all the platform in it plus the info about generation spot
 *
 * @author Federico Morreale
 */
public class Room {

    private ArrayList<Platform> platformsInRoom;
    private boolean hasGenerationSpot;

    public Room(ArrayList<Platform> platformsInRoom) throws InvalidRoomException {
        if (platformsInRoom.isEmpty())
            throw new InvalidRoomException();
        this.platformsInRoom = platformsInRoom;
        this.hasGenerationSpot = false;
        for (Platform p : platformsInRoom) {
            if (p.isGenerationSpot()) this.hasGenerationSpot = true;
        }
        //set the reference of the room to each platform in the arraylist
        for (Platform p : platformsInRoom) {
            p.setPlatformRoom(this);
        }
    }

    /**
     * @return the list of platforms in the room
     */
    public List<Platform> getPlatformsInRoom() {
        return platformsInRoom;
    }

    /**
     * @return true if the room contains a platform with a generation spot
     */
    public boolean hasGenerationSpot() {
        return hasGenerationSpot;
    }

}