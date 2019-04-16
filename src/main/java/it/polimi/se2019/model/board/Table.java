package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.card.weapons.WeaponCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Table is the representation of all the objects in the game.
 *
 * @author Federico Morreale
 */
public class Table {

    private Map<Platform, ArrayList<WeaponCard>> availableWeapons;
    private WeaponCard[] initWeapons;
    private SkullsBoard skullsBoard;
    private GameField gameField;
    private ScoreBoard scoreBoard;

    /**
     * @param initWeapons an array of weapons ready to be grabbed, each weapon will be linked to its platform
     * @param skullsBoard
     * @param gameField
     * @param scoreBoard
     * @throws InvalidDeckException if availableWeapons deck is not full, or a platform is null in the availableWeapons Map
     *                              or there are not exactly 3 platforms in the Map
     */
    public Table(WeaponCard[] initWeapons,
                 SkullsBoard skullsBoard, GameField gameField, ScoreBoard scoreBoard) throws InvalidDeckException {
        if (!areValidWeapons())
            throw new InvalidDeckException("something went wrong while building available weapons, please check again your arguments");
        this.initWeapons = initWeapons;
        //create the Map of weapons in each generation spot
        availableWeapons = new HashMap<>();
        int index = 0;
        for (int i = 0; i < gameField.getField().length; i++) {
            for (int j = 0; j < gameField.getField()[0].length; j++) {
                Platform p = gameField.getField()[i][j];
                if (p.isGenerationSpot()) {
                    ArrayList<WeaponCard> arrWeapons = new ArrayList<>();
                    arrWeapons.add(initWeapons[index++]);
                    arrWeapons.add(initWeapons[index++]);
                    arrWeapons.add(initWeapons[index++]);
                    availableWeapons.put(p, arrWeapons);
                }
            }
        }
        this.skullsBoard = skullsBoard;
        this.gameField = gameField;
        this.scoreBoard = scoreBoard;

    }

    /**
     * @return true if the array of 9 weapons is valid
     */
    private boolean areValidWeapons() {
        if (initWeapons.length != 9) {
            return false;
        }
        for (int i = 0; i < initWeapons.length; i++) {
            if (initWeapons[i] == null) return false;
        }
        return true;
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
     * @throws InvalidGenerationSpotException
     */
    public ArrayList<WeaponCard> getPlatformAvailableWeapons(Platform generationSpot) throws InvalidGenerationSpotException {
        if (!generationSpot.isGenerationSpot())
            throw new InvalidGenerationSpotException();
        return availableWeapons.get(generationSpot);
    }

    /**
     * @param generationSpot where the weapons are located
     * @param weaponsToSet   to be added to the current available weapons that are less than 3
     * @throws InvalidGenerationSpotException
     * @throws InvalidDeckException
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

}