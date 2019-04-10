package it.polimi.se2019.model.board;

import it.polimi.se2019.model.card.weapons.WeaponCard;

import java.util.Map;

/**
 * Table is the rappresentation of all the objects in the game.
 *
 * @author Federico Morreale
 */
public class Table {

    private Map<Platform, WeaponCard[]> availableWeapons;
    private SkullsBoard skullsBoard;
    private GameField gameField;
    private ScoreBoard scoreBoard;

    public Table(Map<Platform, WeaponCard[]> availableWeapons,
                 SkullsBoard skullsBoard, GameField gameField, ScoreBoard scoreBoard) {
        this.availableWeapons = availableWeapons;
        this.skullsBoard = skullsBoard;
        this.gameField = gameField;
        this.scoreBoard = scoreBoard;

    }

    /**
     * @return a map of available weapons(3 max) in each generation spot
     */
    public Map<Platform, WeaponCard[]> getAllAvailableWeapons() {
        return availableWeapons;
    }

    /**
     * @param generationSpot in which the weapons can be grabbed
     * @return a array of weapon cards in a specified generation spot
     */
    public WeaponCard[] getPlatformAvailableWeapons(Platform generationSpot) {
        return availableWeapons.get(generationSpot);
    }

    /**
     * @param generationSpot where the weapons are located
     * @param weaponsToSet to be added to the current available weapons that are less than 3
     * @return old available weapons, null if wrong generation spot
     */
    public WeaponCard[] setPlatformAvailableWeapons(Platform generationSpot, WeaponCard[] weaponsToSet) {
        WeaponCard[] newWeapons = new WeaponCard[availableWeapons.get(generationSpot).length + weaponsToSet.length];
        for (int i = 0; i < availableWeapons.get(generationSpot).length; i++) {
            newWeapons[i] = availableWeapons.get(generationSpot)[i];
        }
        int j = 0;
        for (int i = availableWeapons.get(generationSpot).length; i < newWeapons.length; i++) {
            newWeapons[i] = weaponsToSet[j++];
        }
        return availableWeapons.replace(generationSpot, newWeapons);
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