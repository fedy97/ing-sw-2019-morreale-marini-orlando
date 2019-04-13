package it.polimi.se2019.model.board;

import java.util.*;

import it.polimi.se2019.exceptions.InvalidAdjcentPlatformsException;
import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.exceptions.InvalidRoomException;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.utils.HandyFunctions;

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

    public Platform(int[] platformPosition, boolean isGenerationSpot, AmmoCard platformAmmoCard, Map<Orientation, Platform> adjacentPlatforms)
            throws InvalidCardException, InvalidAdjcentPlatformsException {
        this.platformPosition = platformPosition;
        this.isGenerationSpot = isGenerationSpot;
        if (platformAmmoCard == null && !isGenerationSpot)
            throw new InvalidCardException("starting ammo card cannot be null");
        this.platformAmmoCard = platformAmmoCard;
        this.playersOnThePlatform = new ArrayList<>();
        //check if a platform has more than 2 nulls in its adjency list
        int numOfNull = 0;
        for (Platform p : adjacentPlatforms.values()) {
            if (p == null) numOfNull++;
        }
        if (numOfNull >= 2) throw new InvalidAdjcentPlatformsException();
        this.adjacentPlatforms = adjacentPlatforms;
        //build the arraylist of doors of the platform, knowing the adjacent platforms
        this.doorLocation = new ArrayList<>();
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
    public List<Orientation> getDoorLocation() {
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
    public List<Character> getPlayersOnThePlatform() {
        return playersOnThePlatform;
    }

    public void setPlatformRoom(Room platformRoom) {
        this.platformRoom = platformRoom;
    }

    /**
     * @param platformAmmoCard to be set on the platform, when a AmmoCard is grabbed
     */
    public void setPlatformAmmoCard(AmmoCard platformAmmoCard) throws InvalidCardException {
        if (platformAmmoCard == null)
            throw new InvalidCardException("The ammoCard to be set cannot be null");
        if (isGenerationSpot)
            throw new InvalidCardException("cannot set a ammo card on a generation spot");
        this.platformAmmoCard = platformAmmoCard;
    }

    /**
     * @param character to be set on the platform after his move
     */
    public void setPlayerOnPlatform(Character character) throws InvalidCharacterException {
        if (!HandyFunctions.characterExist(character))
            throw new InvalidCharacterException();
        playersOnThePlatform.add(character);
    }

    /**
     * @param dir the direction of the adjacent platform to be returned
     * @return the platform in orientation dir, null if doesn't exist
     */
    public Platform getAdjacentPlatform(Orientation dir) {
        return adjacentPlatforms.get(dir);
    }

}