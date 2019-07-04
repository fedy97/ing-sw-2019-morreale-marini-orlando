package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTurnController extends TestInitializer {
    TurnController turnController;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        turnController = c.getTurnController();
        assertTrue(turnController.isFirstTurn());
    }

    /**
     * Test if turns are passed in the correct way
     */
    @Test
    public void testEndTurn() {
        turnController.endTurn();
        assertEquals("user2", c.getPlayerManager().getCurrentPlayer().getName());
        c.activateFrenzyMode();
        turnController.endTurn();
    }

    @After
    public void finishTest() {
        c.setFrenzyMode(false);
    }

}
