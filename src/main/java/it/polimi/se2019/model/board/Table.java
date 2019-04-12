package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.card.weapons.WeaponCard;

import java.util.ArrayList;
import java.util.Map;

/**
 * Table is the rappresentation of all the objects in the game.
 *
 * @author Federico Morreale
 */
public class Table {

    private Map<Platform, ArrayList<WeaponCard>> availableWeapons;
    private SkullsBoard skullsBoard;
    private GameField gameField;
    private ScoreBoard scoreBoard;

    public Table(Map<Platform, ArrayList<WeaponCard>> availableWeapons,
                 SkullsBoard skullsBoard, GameField gameField, ScoreBoard scoreBoard) throws InvalidDeckException {
        if (availableWeapons.keySet().size() != 3 || availableWeapons.keySet().contains(null) || !isAvailableWeaponsFull())
            throw new InvalidDeckException("something went wrong while building available weapons, please check again your arguments");
        this.availableWeapons = availableWeapons;
        this.skullsBoard = skullsBoard;
        this.gameField = gameField;
        this.scoreBoard = scoreBoard;

    }

    /**
     * @return a map of available weapons(3 max) in each generation spot
     */
    public Map<Platform, ArrayList<WeaponCard>> getAllAvailableWeapons() {
        return availableWeapons;
    }

    /**
     * @param generationSpot in which the weapons can be grabbed
     * @return an arraylist of weapon cards in a specified generation spot
     */
    public ArrayList<WeaponCard> getPlatformAvailableWeapons(Platform generationSpot) throws InvalidGenerationSpotException {
        if (!generationSpot.isGenerationSpot())
            throw new InvalidGenerationSpotException();
        return availableWeapons.get(generationSpot);
    }

    /**
     * @param generationSpot where the weapons are located
     * @param weaponsToSet   to be added to the current available weapons that are less than 3
     */
    public void setPlatformAvailableWeapons(Platform generationSpot, ArrayList<WeaponCard> weaponsToSet) throws InvalidGenerationSpotException,
            InvalidDeckException {
        if (!generationSpot.isGenerationSpot())
            throw new InvalidGenerationSpotException();
        if (weaponsToSet.size() > 3)
            throw new InvalidDeckException("you cannot set more than 3 weapons");
        if (weaponsToSet.isEmpty())
            throw new InvalidDeckException("you cannot set an empty collection of weapon cards");
        for (int i = 0; i < 3 - availableWeapons.get(generationSpot).size(); i++) {
            availableWeapons.get(generationSpot).add(weaponsToSet.get(i));
        }
    }

    public SkullsBoard getSkullsBoard() {
        return skullsBoard;
    }

    public GameField getGameField() {
        return gameField;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    /**
     * @return true if every platform that holds weapons has 3! weapons ready to be grabbed
     */
    private boolean isAvailableWeaponsFull() {
        boolean isFull = true;
        for (ArrayList<WeaponCard> arrWep : availableWeapons.values()) {
            if (arrWep.size() != 3) isFull = false;
        }
        return isFull;
    }

}