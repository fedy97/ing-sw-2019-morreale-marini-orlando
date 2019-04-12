package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidNumOfRoomsException;
import it.polimi.se2019.exceptions.InvalidPositionException;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.*;

/**
 * GameField is a matrix of Platform (3x4) in which characters can move.
 * It contains also the information about the rooms in an array.
 *
 * @author Federico Morreale
 */
public class GameField {

    private ArrayList<Room> rooms;
    private Platform[][] field;

    public GameField(ArrayList<Room> rooms, Platform[][] field) throws InvalidNumOfRoomsException {
        if (rooms.size() < 5)
            throw new InvalidNumOfRoomsException();
        this.rooms = rooms;
        this.field = field;
    }

    /**
     * @return rooms in the gameField
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * @return the gameField
     */
    public Platform[][] getField() {
        return field;
    }

    /**
     * @param position of one platform in the room
     * @return the Room in which the platform is located
     */
    public Room getRoom(int[] position) throws InvalidPositionException {
        if (!HandyFunctions.isValidPosition(position))
            throw new InvalidPositionException();
        Platform p = field[position[0]][position[1]];
        for (Room r : rooms) {
            if (r.getPlatformsInRoom().contains(p)) return r;
        }
        return null;
    }

    /**
     * @param position of the platform
     * @return the Platform object having that position
     */
    public Platform getPlatform(int[] position) throws InvalidPositionException {
        if (!HandyFunctions.isValidPosition(position))
            throw new InvalidPositionException();
        return field[position[0]][position[1]];
    }

}