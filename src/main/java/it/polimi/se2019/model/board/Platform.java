package it.polimi.se2019.model.board;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.utils.HandyFunctions;

/**
 * Platform containing info about doors, walls, players, generation spot and ammos card
 *
 * @author Federico Morreale
 */
public class Platform implements Serializable {

    private Room platformRoom;
    private Color platformColor;
    private Map<Orientation, Platform> adjacentPlatforms;
    private int[] platformPosition;
    private boolean isGenerationSpot;
    private boolean hasDoor;
    private ArrayList<Orientation> doorLocation;
    private boolean hasAmmoCard;
    private AmmoCard platformAmmoCard;
    private ArrayList<Character> playersOnThePlatform;
    private ArrayList<WeaponCard> weapons;

    /**
     * @param platformPosition an array of length = 2, containing x and y coordinates
     * @param isGenerationSpot indicates if the platform is a generation spot
     * @param platformAmmoCard the ammunition card located on the platform
     * @param platformColor    this four params will be parsed from json
     * @throws InvalidCardException if the starting ammos card of the platform is null
     */
    public Platform(int[] platformPosition, boolean isGenerationSpot, AmmoCard platformAmmoCard, Color platformColor, List<Orientation> doors)
            throws InvalidCardException {
        this.doorLocation = (ArrayList<Orientation>) doors;
        this.platformPosition = platformPosition;
        this.isGenerationSpot = isGenerationSpot;
        this.platformColor = platformColor;
        if (platformAmmoCard == null && !isGenerationSpot)
            throw new InvalidCardException("starting ammo cards cannot be null");
        this.platformAmmoCard = platformAmmoCard;
        this.playersOnThePlatform = new ArrayList<>();
        //build the arraylist of doors of the platform, knowing the adjacent platforms
        this.hasAmmoCard = platformAmmoCard != null;
        this.hasDoor = !doors.isEmpty();
        weapons = new ArrayList<>();
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

    public Color getPlatformColor() {
        return platformColor;
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

    /**
     * @param direction of the adjacent platform to be returned
     * @return the platform in orientation dir, null if doesn't exist
     */
    public Platform getAdjacentPlatform(Orientation direction) {
        return adjacentPlatforms.get(direction);
    }

    public List<WeaponCard> getWeapons() throws InvalidGenerationSpotException {
        if (!this.isGenerationSpot())
            throw new InvalidGenerationSpotException();
        return weapons;
    }

    /**
     * @param platformRoom to link to this platform
     * @throws InvalidRoomException if the room is null
     */
    public void setPlatformRoom(Room platformRoom) throws InvalidRoomException {
        if (platformRoom == null)
            throw new InvalidRoomException();
        this.platformRoom = platformRoom;
    }

    /**
     * @param platformAmmoCard to be set on the platform, when a AmmoCard is grabbed
     * @throws InvalidCardException if a ammocard is set to a generation spot or the ammocard is null
     */
    public void setPlatformAmmoCard(AmmoCard platformAmmoCard) throws InvalidCardException {
        if (platformAmmoCard == null)
            throw new InvalidCardException("the ammocard to be set cannot be null");
        if (isGenerationSpot)
            throw new InvalidCardException("cannot set a ammos card on a generation spot");
        if (hasAmmoCard())
            throw new InvalidCardException("this platform already got an ammocard");
        this.platformAmmoCard = platformAmmoCard;
        this.hasAmmoCard = true;
    }

    /**
     * @param character to be set on the platform after his move
     * @throws InvalidCharacterException if the character does not exist
     */
    public void setPlayerOnPlatform(Character character) throws InvalidCharacterException {
        if (!HandyFunctions.characterExists(character))
            throw new InvalidCharacterException("Character selected does not exists");
        playersOnThePlatform.add(character);
    }

    public void removePlayerOnPlatform(Character character) {
        playersOnThePlatform.remove(character);
    }

    /**
     * @param adjacentPlatforms to link to this platform
     * @throws InvalidAdjacentPlatformsException if adjacentPlatforms is null
     */
    public void setAdjacentPlatforms(Map<Orientation, Platform> adjacentPlatforms) throws InvalidAdjacentPlatformsException {
        if (adjacentPlatforms == null)
            throw new InvalidAdjacentPlatformsException();
        this.adjacentPlatforms = adjacentPlatforms;
    }

    /**
     * @param weapons to set to the generation spot
     * @throws InvalidGenerationSpotException if the current platform is not a generation spot
     */
    public void setWeapons(List<WeaponCard> weapons) throws InvalidGenerationSpotException {
        if (!this.isGenerationSpot())
            throw new InvalidGenerationSpotException();
        this.weapons = (ArrayList<WeaponCard>) weapons;
    }

    /**
     *
     */
    public void addWeaponCard(WeaponCard weaponCard) throws InvalidGenerationSpotException {
        if (!this.isGenerationSpot())
            throw new InvalidGenerationSpotException();
        weapons.add(weaponCard);
    }

    /**
     * @param weaponCard the weapon card to be removed from the platform
     * @throws InvalidGenerationSpotException if the current platform is not a generation spot
     */
    public void removeWeaponCard(WeaponCard weaponCard) throws InvalidGenerationSpotException {
        if (!this.isGenerationSpot())
            throw new InvalidGenerationSpotException();
        weapons.remove(weaponCard);
    }

    public AmmoCard grabAmmoCard() throws InvalidCardException {
        if (hasAmmoCard) {
            AmmoCard res = platformAmmoCard;
            hasAmmoCard = false;
            platformAmmoCard = null;
            return res;
        } else
            throw new InvalidCardException("there is no ammos card set in this platform");
    }

    @Override
    public String toString() {
        return getPlatformPosition()[0] + "," + getPlatformPosition()[1];
    }

}