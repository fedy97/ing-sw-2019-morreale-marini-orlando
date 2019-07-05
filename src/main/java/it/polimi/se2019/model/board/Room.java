package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidRoomException;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Room containing all the platform in it plus the info about generation spot
 *
 * @author Federico Morreale
 */
public class Room implements Serializable {

    private ArrayList<Platform> platformsInRoom;
    private boolean hasGenerationSpot;
    private Color roomColor;
    private Platform genSpot;

    /**
     * @param platformsInRoom All the platforms that make up the room
     * @param roomColor awt
     * @throws InvalidRoomException if there is no platform in the room
     */
    public Room(List<Platform> platformsInRoom, Color roomColor) throws InvalidRoomException {
        if (platformsInRoom.isEmpty())
            throw new InvalidRoomException();
        this.platformsInRoom = (ArrayList<Platform>) platformsInRoom;
        this.hasGenerationSpot = false;
        this.genSpot = null;
        for (Platform p : platformsInRoom) {
            if (p.isGenerationSpot()) {this.hasGenerationSpot = true;
            this.genSpot=p;}
        }
        //set the reference of the room to each platform in the arraylist
        for (Platform p : platformsInRoom) {
            p.setPlatformRoom(this);
        }
        this.roomColor=roomColor;
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

    public Color getRoomColor() {
        return roomColor;
    }

    public Platform getGenSpot() {
        return genSpot;
    }
}