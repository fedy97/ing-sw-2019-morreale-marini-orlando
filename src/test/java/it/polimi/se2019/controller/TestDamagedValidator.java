package it.polimi.se2019.controller;

import it.polimi.se2019.Action;
import it.polimi.se2019.controller.validator.DamagedValidator;
import it.polimi.se2019.controller.validator.Validator;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class used to verify if the DamagedValidator works properly
 *
 * @author Gabriel Raul Marini
 */
public class TestDamagedValidator extends TestInitializer {
    private Validator validator;
    private Player currPlayer;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        validator = new DamagedValidator(c);
        currPlayer = c.getPlayerManager().getCurrentPlayer();
        currPlayer.setCurrentPlatform(c.getGame().getGameField().getPlatforms().get(0));
    }

    /**
     * Test the valid moves for the selected action
     */
    @Test
    public void getValidMoves() {
        try {
            assertTrue(!validator.getValidMoves(Action.GRAB).isEmpty());
            assertTrue(!validator.getValidMoves(Action.MOVE).isEmpty());
        } catch (Exception e) {
            fail();

        }

        try {
            validator.getValidMoves(Action.SHOOT);
            fail();
        } catch (Exception e) {
        }
    }

    @After
    public void finisTest() {
        currPlayer.removePowerUps();
        currPlayer.removeWeapons();
    }
}
