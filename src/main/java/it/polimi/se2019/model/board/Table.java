package it.polimi.se2019.model.board;

import it.polimi.se2019.model.card.weapons.WeaponCard;

/**
 * Table is the rappresentation of all the objects in the game.
 *
 * @author Federico Morreale
 */
public class Table {

    private WeaponCard[][] weaponsAvailable;
    private SkullsBoard skullsBoard;
    private GameField gameField;
    private ScoreBoard scoreBoard;

    public Table() {

    }

    /**
     * @return a matrix [3][3] of available weapons(3 max) in the 3 generation spots
     */
    public WeaponCard[][] getWeaponsAvailable() {
        return weaponsAvailable;
    }

    /**
     * @return the skullBoard
     */
    public SkullsBoard getSkullsBoard() {
        return skullsBoard;
    }

    /**
     * @return the gameField
     */
    public GameField getGameField() {
        return gameField;
    }

    /**
     * @return the scoreBoard
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

}