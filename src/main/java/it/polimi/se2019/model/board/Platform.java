package it.polimi.se2019.model.board;

import java.util.*;

import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.enumeration.Orientation;

/**
 * @author Federico Morreale
 */
public class Platform {

    private int[] platformPosition;
    private boolean isGenerationSpot;
    private boolean hasDoor;
    private ArrayList<Orientation> doorLocation;
    private boolean hasAmmoCard;
    private Card platformAmmoCard;
    private ArrayList<Character> playersOnThePlatform;
    private ArrayList<Orientation> wallLocation;

    public Platform() {
    }

    /**
     * @return the position x,y of the platform in the gameField
     */
    public int[] getPlatformPosition() {
        return platformPosition;
    }

    /**
     * @return true if the platform is a generation spot
     */
    public boolean isGenerationSpot() {
        return isGenerationSpot;
    }

    /**
     * @return true if the platform has a door
     */
    public boolean HasDoor() {
        return hasDoor;
    }

    /**
     * @return the Orientations where the doors are located
     */
    public ArrayList<Orientation> getDoorLocation() {
        return doorLocation;
    }

    /**
     * @return true if the platform has the AmmoCard
     */
    public boolean HasAmmoCard() {
        return hasAmmoCard;
    }

    /**
     * @return the AmmoCard on the platform
     */
    public Card getPlatformAmmoCard() {
        return platformAmmoCard;
    }

    /**
     * @return players standing on (this) platform
     */
    public ArrayList<Character> getPlayersOnThePlatform() {
        return playersOnThePlatform;
    }

    /**
     * @return the Orientations where the wall is located
     */
    public ArrayList<Orientation> getWallLocation() {
        return wallLocation;
    }

    /**
     * @param platformAmmoCard when a AmmoCard is grabbed, a new one is set on the platform
     */
    public void setPlatformAmmoCard(Card platformAmmoCard) {
        this.platformAmmoCard = platformAmmoCard;
    }

    /**
     * @param character after a move, a new character is set on the platform
     */
    public void setPlayerOnPlatform(Character character) {

    }
}