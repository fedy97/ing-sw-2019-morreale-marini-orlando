package it.polimi.se2019.model.board;

import java.util.*;

/**
 * GameField is a matrix of Platform (3x4) in which characters can move.
 * It contains also the information about the rooms in a array.
 *
 * @author Federico Morreale
 */
public class GameField {

    private Room[] rooms;
    private Platform[][] field;

    public GameField(Room[] rooms, Platform[][] field) {
        this.rooms = rooms;
        this.field = field;
    }

    /**
     * @return rooms in the gameField
     */
    public Room[] getRooms() {
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
    public Room getRoom(int[] position) {
        return null;
    }

    /**
     * @param position of the platform
     * @return the Platform object having that position
     */
    public Platform getPlatform(int[] position) {
        return null;
    }

}