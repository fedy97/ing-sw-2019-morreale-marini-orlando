package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.card.weapons.WeaponCard;

import java.util.ArrayList;

/**
 * Table is the representation of all the objects in the game.
 *
 * @author Federico Morreale
 */
public class Table {

    /**
     * initWeapons is an array of length = 9
     */
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
                 SkullsBoard skullsBoard, GameField gameField, ScoreBoard scoreBoard) throws InvalidDeckException, InvalidGenerationSpotException {
        if (!areValidWeapons())
            throw new InvalidDeckException("something went wrong while building available weapons, please check again your arguments");
        this.initWeapons = initWeapons;
        //create the Map of weapons in each generation spot
        int index = 0;
        for (int i = 0; i < gameField.getField().length; i++) {
            for (int j = 0; j < gameField.getField()[0].length; j++) {
                Platform p = gameField.getField()[i][j];
                if (p != null && p.isGenerationSpot()) {
                    ArrayList<WeaponCard> arrWeapons = new ArrayList<>();
                    arrWeapons.add(initWeapons[index++]);
                    arrWeapons.add(initWeapons[index++]);
                    arrWeapons.add(initWeapons[index++]);
                    p.setWeapons(arrWeapons);
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