package it.polimi.se2019.controller;

import it.polimi.se2019.Action;
import it.polimi.se2019.controller.validator.Frenzy1Validator;
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
public class TestFrenzy1Validator extends TestControllerChild {
    private Validator validator;
    private Player currPlayer;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        validator = new Frenzy1Validator(c);
        currPlayer = c.getPlayerManager().getCurrentPlayer();
        currPlayer.setCurrentPlatform(c.getGame().getGameField().getPlatforms().get(0));
    }

    @Test
    public void getValidMoves() {
        try {
            assertTrue(!validator.getValidMoves(Action.GRAB).isEmpty());
            assertTrue(!validator.getValidMoves(Action.MOVE).isEmpty());
            assertTrue(!validator.getValidMoves(Action.SHOOT).isEmpty());
        } catch (Exception e) {
            fail();

        }
    }

    @After
    public void finisTest() {
        currPlayer.getWeaponCards().clear();
        currPlayer.getPowerUpCards().clear();
    }
}
