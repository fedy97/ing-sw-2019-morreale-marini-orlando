package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.TestControllerChild;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestNewton extends TestControllerChild {
    private Newton newton;
    private Player target;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        newton = new Newton(AmmoCube.RED, "raggio cinetico", "desc", "path");
        target = c.getGame().getPlayer(Character.VIOLET);
        target.setCurrentPlatform(c.getGame().getGameField().getPlatforms().get(2));
        c.getChosenDestination().add(c.getGame().getGameField().getPlatforms().get(4));
    }

    @Test
    public void testActivate() {
        newton.activate(Character.VIOLET);
        assertEquals(c.getGame().getPlayer(Character.VIOLET).getCurrentPlatform(), c.getGame().getGameField().getPlatforms().get(4));
    }

    @Test
    public void testGetPossibleTargets() {
        assertTrue(newton.getPossibleTargets().size() == (c.getGame().getPlayers().size() - 1));
    }

    @After
    public void finishTest() {
        target.setCurrentPlatform(c.getGame().getGameField().getPlatforms().get(2));
    }
}
