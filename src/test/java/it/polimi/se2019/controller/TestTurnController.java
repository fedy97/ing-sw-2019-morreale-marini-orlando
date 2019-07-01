package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.model.player.PlayerBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestTurnController extends TestControllerChild {
    TurnController turnController;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        turnController = c.getTurnController();
        assertTrue(turnController.isFirstTurn());
    }

    @Test
    public void testEndTurn() {
       turnController.endTurn();
       assertEquals("user2", c.getPlayerManager().getCurrentPlayer().getName());
       c.activateFrenzyMode();
       turnController.endTurn();
    }

    @After
    public void finishTest(){
        c.setFrenzyMode(false);
    }

}
