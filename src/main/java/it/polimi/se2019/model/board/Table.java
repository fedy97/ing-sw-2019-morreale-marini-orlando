package it.polimi.se2019.model.board;

import it.polimi.se2019.model.card.weapons.WeaponCard;

import java.util.Map;

/**
 * Table is the rappresentation of all the objects in the game.
 *
 * @author Federico Morreale
 */
public class Table {

    private Map<Platform, WeaponCard[]> weaponsAvailable;
    private SkullsBoard skullsBoard;
    private GameField field;
    private ScoreBoard scoreBoard;

    public Table(Map<Platform, WeaponCard[]> weaponsAvailable,
                 SkullsBoard skullsBoard, GameField field, ScoreBoard scoreBoard) {
        this.weaponsAvailable = weaponsAvailable;
        this.skullsBoard = skullsBoard;
        this.field = field;
        this.scoreBoard = scoreBoard;

    }

    /**
     * @return a map of available weapons(3 max) in each generation spot
     */
    public Map<Platform, WeaponCard[]> getAllAvailableWeapons() {
        return weaponsAvailable;
    }

    /**
     * @param generationSpot in which the weapons can be grabbed
     * @return a array of weapon cards in a specified generation spot
     */
    public WeaponCard[] getPlatformAvailableWeapons(Platform generationSpot) {
        return weaponsAvailable.get(generationSpot);
    }

    /**
     * @param generationSpot where the weapons are located
     * @param weaponsToSet to be added to the current available weapons that are less than 3
     * @return old available weapons, null if wrong generation spot
     */
    public WeaponCard[] setPlatformAvailableWeapons(Platform generationSpot, WeaponCard[] weaponsToSet) {
        WeaponCard[] newWeapons = new WeaponCard[weaponsAvailable.get(generationSpot).length + weaponsToSet.length];
        for (int i = 0; i < weaponsAvailable.get(generationSpot).length; i++) {
            newWeapons[i] = weaponsAvailable.get(generationSpot)[i];
        }
        int j = 0;
        for (int i = weaponsAvailable.get(generationSpot).length; i < newWeapons.length; i++) {
            newWeapons[i] = weaponsToSet[j++];
        }
        return weaponsAvailable.replace(generationSpot, newWeapons);
    }

    public SkullsBoard getSkullsBoard() {
        return skullsBoard;
    }

    public GameField getGameField() {
        return field;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

}