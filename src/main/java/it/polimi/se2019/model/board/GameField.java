package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.utils.HandyFunctions;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * GameField is a matrix of Platform (3x4) in which characters can move.
 * It contains also the information about the rooms in an array.
 *
 * @author Federico Morreale
 */
public class GameField {

    private ArrayList<Room> rooms;
    private Platform[][] field;

    /**
     * @param rooms of the field, 5 or 6
     * @param field where the 11 or 12 platforms are located
     * @throws InvalidNumOfRoomsException        if the rooms are not 5 or 6
     * @throws InvalidFieldException             if there is more than 1 platform equal to null or the matrix is not 3x4
     * @throws InvalidAdjacentPlatformsException if the adjacency list has more than 2 nulls
     */
    public GameField(Platform[][] field) throws InvalidFieldException, InvalidRoomException,
            InvalidAdjacentPlatformsException {
        /*
        if (rooms.size() != 5 && rooms.size() != 6)
            throw new InvalidNumOfRoomsException();
        this.rooms = rooms;*/
        if (field.length != 3 || field[0].length != 4 || !hasMoreThan2Nulls())
            throw new InvalidFieldException();
        this.field = field;
        //build the adjacency list of every platform in the field
        int cont = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                if (p != null)
                    buildPlatformAdjMap(p, i, j);

            }
        }
        // we may now build the rooms
        this.rooms = new ArrayList<>();
        buildRooms(field);

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
     * @throws InvalidPositionException if position is out of the matrix
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
     * @throws InvalidPositionException if position is out of the matrix
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

    /**
     * it creates the array list of rooms
     * firstly it creates the arraylists of platform having the same color,
     * then those arraylists will be added to rooms arraylist
     *
     * @param field of the configuration chosen by the players
     */
    private void buildRooms(Platform[][] field) throws InvalidRoomException {
        int numOfRooms;
        List<Color> listOfColors = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                if (p != null) {
                    Color c = p.getPlatformColor();
                    if (!listOfColors.contains(c))
                        listOfColors.add(c);
                }
            }
        }
        numOfRooms = listOfColors.size();
        Color currentColor = null;
        for (int i = 0; i < numOfRooms; i++) {
            ArrayList<Platform> listOfPlat = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 4; k++) {
                    Platform p = field[j][k];
                    if (p != null) {
                        if (listOfPlat.isEmpty() && listOfColors.contains(p.getPlatformColor()))
                            currentColor=p.getPlatformColor();
                        if (listOfColors.contains(p.getPlatformColor()) && p.getPlatformColor() == currentColor) {
                            listOfPlat.add(p);
                        }
                    }
                }
            }
            listOfColors.remove(currentColor);
            Room r = new Room(listOfPlat);
            this.rooms.add(r);
        }

    }

}