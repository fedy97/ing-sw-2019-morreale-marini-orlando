package it.polimi.se2019.model.board;

import java.util.*;

import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;

/**
 * Platform containing info about doors, walls, players, generation spot and ammo card
 *
 * @author Federico Morreale
 */
public class Platform {

    private int[] platformPosition;
    private boolean isGenerationSpot;
    private boolean hasDoor;
    private ArrayList<Orientation> doorLocation;
    private boolean hasAmmoCard;
    private AmmoCard platformAmmoCard;
    private ArrayList<Character> playersOnThePlatform;
    private ArrayList<Orientation> wallLocation;

    public Platform(int[] platformPosition, boolean isGenerationSpot,
                    ArrayList<Orientation> doorLocation, AmmoCard platformAmmoCard,
                    ArrayList<Character> playersOnThePlatform, ArrayList<Orientation> wallLocation) {
        this.platformPosition = platformPosition;
        this.isGenerationSpot = isGenerationSpot;
        this.doorLocation = doorLocation;
        this.platformAmmoCard = platformAmmoCard;
        this.playersOnThePlatform = playersOnThePlatform;
        this.wallLocation = wallLocation;
        this.hasDoor = doorLocation.isEmpty() ? false : true;
        this.hasAmmoCard = platformAmmoCard == null ? false : true;

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
    public AmmoCard getPlatformAmmoCard() {
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
    public void setPlatformAmmoCard(AmmoCard platformAmmoCard) {
        this.platformAmmoCard = platformAmmoCard;
    }

    /**
     * @param character after a move, a new character is set on the platform
     */
    public void setPlayerOnPlatform(Character character) {

    }
}