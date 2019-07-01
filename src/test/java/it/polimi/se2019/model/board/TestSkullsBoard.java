package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.exceptions.InvalidQuantityException;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestSkullsBoard {

    @Test
    public void testAddKillMarks() {

        try {
            SkullsBoard board = new SkullsBoard(8);
            board.addKillMarks(Character.DOZER, 2);
            assertEquals(2, board.getKillMarksPlayer(Character.DOZER).intValue());
        } catch (InvalidCharacterException | InvalidQuantityException e) {
            fail();
        }

        try {
            SkullsBoard board = new SkullsBoard(8);
            board.addKillMarks(Character.DOZER, 4);
            fail();
        } catch (InvalidCharacterException | InvalidQuantityException e) {

        }
    }
}
