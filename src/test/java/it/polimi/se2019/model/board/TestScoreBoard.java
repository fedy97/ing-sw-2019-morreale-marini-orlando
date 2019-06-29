package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.exceptions.InvalidQuantityException;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestScoreBoard {

  /**  @Test
    public void testSetScoreToPlayer() {
        ScoreBoard board = new ScoreBoard();
        try {
            board.setScoreToPlayer(Character.BANSHEE, 2);
            assertEquals(2, board.getScorePlayer(Character.BANSHEE).intValue());
        } catch (InvalidQuantityException | InvalidCharacterException e) {
            fail();
        }
        try {
            board.setScoreToPlayer(Character.DOZER, 0);
            fail();
        } catch (InvalidQuantityException | InvalidCharacterException e) {

        }
    }*/
}
