package it.polimi.se2019.model.board;

import java.util.*;

/**
 * @author Federico Morreale
 */
public class GameField {

    private Room[] rooms;
    private Platform[][] gameField;

    public GameField() {
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
    public Platform[][] getGameField() {
        return gameField;
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