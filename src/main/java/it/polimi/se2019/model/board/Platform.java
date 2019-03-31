package it.polimi.se2019.model.board;

import java.util.*;
import it.polimi.se2019.model.enumeration.*;

/**
 * 
 */
public class Platform {

    /**
     * Default constructor
     */
    public Platform() {
    }

    /**
     * 
     */
    private int idPlatform;

    /**
     * 
     */
    private Color platformColor;

    /**
     * 
     */
    private boolean isGenerationSpot;

    /**
     * 
     */
    private boolean hasDoor;

    /**
     * 
     */
    private ArrayList<Orientation> doorLocation;

    /**
     * 
     */
    private boolean hasAmmoCard;

    /**
     * 
     */
    private int platformAmmoCard;

    /**
     * 
     */
    private ArrayList<Integer> playersOnThePlatform;

    /**
     * 
     */
    private ArrayList<Orientation> wallLocation;


    /**
     * @return
     */
    public int getIdPlatform() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public Color getPlatformColor() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean checkIfIsGenerationSpot() {
        // TODO implement here
        return true;
    }

    /**
     * @return
     */
    public boolean checkIfHasDoor() {
        // TODO implement here
        return true;
    }

    /**
     * @return
     */
    public ArrayList<Orientation> getDoorLocation() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean checkIfHasAmmoCard() {
        // TODO implement here
        return true;
    }


    /**
     * @return
     */
    public ArrayList<Integer> getPlayersOnThePlatform() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Orientation> getWallLocation() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public int getPlatformAmmoCard() {
        // TODO implement here
        return 0;
    }

    /**
     * @param int
     */
    public void setPlayerInPlatform(int idPlayer) {
        // TODO implement here
    }

}