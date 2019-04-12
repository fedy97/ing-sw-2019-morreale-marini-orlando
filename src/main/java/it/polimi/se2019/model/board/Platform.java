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

    private Room platformRoom;
    private Map<Orientation, Platform> adjacentPlatforms;
    private int[] platformPosition;
    private boolean isGenerationSpot;
    private boolean hasDoor;
    private ArrayList<Orientation> doorLocation;
    private boolean hasAmmoCard;
    private AmmoCard platformAmmoCard;
    private ArrayList<Character> playersOnThePlatform;

    public Platform(int[] platformPosition, boolean isGenerationSpot, AmmoCard platformAmmoCard, Map<Orientation, Platform> adjacentPlatforms,
                    Room platformRoom) {
        this.platformPosition = platformPosition;
        this.isGenerationSpot = isGenerationSpot;
        this.platformAmmoCard = platformAmmoCard;
        this.playersOnThePlatform = new ArrayList<>();
        this.adjacentPlatforms = adjacentPlatforms;
        this.platformRoom = platformRoom;

        for (Map.Entry<Orientation, Platform> entry : adjacentPlatforms.entrySet()) {
            if (entry.getValue().getPlatformRoom() != this.getPlatformRoom())
                this.doorLocation.add(entry.getKey());
        }

        this.hasDoor = doorLocation.isEmpty() ? false : true;
        this.hasAmmoCard = platformAmmoCard == null ? false : true;

    }

    /**
     * @return the position x,y of the platform in the gameField
     */
    public int[] getPlatformPosition() {
        return platformPosition;
    }

    public boolean isGenerationSpot() {
        return isGenerationSpot;
    }

    public boolean hasDoor() {
        return hasDoor;
    }

    /**
     * @return the Orientations where the doors are located
     */
    public ArrayList<Orientation> getDoorLocation() {
        return doorLocation;
    }

    public boolean hasAmmoCard() {
        return hasAmmoCard;
    }

    public AmmoCard getPlatformAmmoCard() {
        return platformAmmoCard;
    }

    public Room getPlatformRoom() {
        return platformRoom;
    }

    /**
     * @return a map of the platforms that are next to (this), null if there's a wall
     */
    public Map<Orientation, Platform> getAdjacentPlatforms() {
        return adjacentPlatforms;
    }

    /**
     * @return players standing on (this) platform
     */
    public ArrayList<Character> getPlayersOnThePlatform() {
        return playersOnThePlatform;
    }

    /**
     * @param platformAmmoCard to be set on the platform, when a AmmoCard is grabbed
     */
    public void setPlatformAmmoCard(AmmoCard platformAmmoCard) {
        this.platformAmmoCard = platformAmmoCard;
    }

    /**
     * @param character to be set on the platform after his move
     */
    public void setPlayerOnPlatform(Character character) {
        playersOnThePlatform.add(character);
    }
}