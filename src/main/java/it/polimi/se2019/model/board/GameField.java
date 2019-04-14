package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidAdjacentPlatformsException;
import it.polimi.se2019.exceptions.InvalidFieldException;
import it.polimi.se2019.exceptions.InvalidNumOfRoomsException;
import it.polimi.se2019.exceptions.InvalidPositionException;
import it.polimi.se2019.model.enumeration.Orientation;
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

    public GameField(ArrayList<Room> rooms, Platform[][] field) throws InvalidNumOfRoomsException, InvalidFieldException,
            InvalidAdjacentPlatformsException {
        if (rooms.size() < 5)
            throw new InvalidNumOfRoomsException();
        this.rooms = rooms;
        if (field.length != 4 || field[0].length != 3 || !hasMoreThan2Nulls())
            throw new InvalidFieldException();
        this.field = field;
        //build the adjacency list of every platform in the field
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                buildPlatformAdjMap(p, i, j);
            }
        }

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

    /**
     * @return true if the field has more than 2 nulls, if so, the field is not valid
     */
    private boolean hasMoreThan2Nulls() {
        int counter = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                if (p == null) counter++;
            }
        }
        return counter <= 1 ? false : true;
    }

    /**
     * @param platform to which build the adjacency list
     * @param row      of the current platform
     * @param column   of the current platform
     * @throws InvalidAdjacentPlatformsException is there are more than one nulls in the adjacency list
     *                                           build the adjacency list of each platform
     */
    private void buildPlatformAdjMap(Platform platform, int row, int column) throws InvalidAdjacentPlatformsException {
        EnumMap<Orientation, Platform> adjMap = new EnumMap<>(Orientation.class);
        if (row - 1 < 0) adjMap.put(Orientation.UP, null);
        else adjMap.put(Orientation.UP, field[row - 1][column]);
        if (row + 1 >= field.length) adjMap.put(Orientation.DOWN, null);
        else adjMap.put(Orientation.UP, field[row + 1][column]);
        if (column - 1 < 0) adjMap.put(Orientation.LEFT, null);
        else adjMap.put(Orientation.UP, field[row][column - 1]);
        if (column + 1 >= field[0].length) adjMap.put(Orientation.RIGHT, null);
        else adjMap.put(Orientation.UP, field[row][column + 1]);
        //check if it's a valid adjacency list
        int numOfNull = 0;
        for (Platform p : adjMap.values()) {
            if (p == null) numOfNull++;
        }
        if (numOfNull >= 2)
            throw new InvalidAdjacentPlatformsException();

        platform.setAdjacentPlatforms(adjMap);
    }


}