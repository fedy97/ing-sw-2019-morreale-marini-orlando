package it.polimi.se2019.controller;

import it.polimi.se2019.model.PointsCounter;
import it.polimi.se2019.model.board.SkullsBoard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test class used to verify if the PointCounter works properly
 *
 * @author Gabriel Raul Marini
 */
public class TestPointsCounter {
    private Player player;
    private SkullsBoard board;


    @Before
    public void initTest() {
        try {
            board = new SkullsBoard(3);
            board.addKillMarks(Character.SPROG, 2);
            board.addKillMarks(Character.BANSHEE, 2);
            board.addKillMarks(Character.VIOLET, 1);
            player = new Player("name", Character.SPROG, null);
            player.getPlayerBoard().addDamage(Character.BANSHEE, 3);
            player.getPlayerBoard().addDamage(Character.VIOLET, 3);
            player.getPlayerBoard().addDamage(Character.DISTRUCTOR, 5);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test the drawPowerUp method verifying if it's refilled when it's empty
     */
    @Test
    public void testGetPoints() {
        boolean passed = true;
        Map<Character, Integer> res = PointsCounter.getPoints(player);

        assertEquals(3, res.keySet().size());
        if (res.get(Character.BANSHEE) != 7 || res.get(Character.VIOLET) != 4 || res.get(Character.DISTRUCTOR) != 8)
            passed = false;
        assertTrue(passed);
    }

    /**
     * Test the drawPowerUp method verifying if it's refilled when it's empty
     */
    @Test
    public void testGetFinalPoints() {
        boolean passed = true;
        Map<Character, Integer> res = PointsCounter.getFinalScore(board);

        assertEquals(3, res.keySet().size());
        if (res.get(Character.SPROG) != 8 || res.get(Character.VIOLET) != 4 || res.get(Character.BANSHEE) != 6)
            passed = false;
        assertTrue(passed);
    }

}
