package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.TestInitializer;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test Newton powerup
 *
 * @author Gabriel Raul Marini
 */
public class TestNewton extends TestInitializer {
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

    /**
     * Verify if target player was moved
     */
    @Test
    public void testActivate() {
        newton.activate(Character.VIOLET);
        assertEquals(c.getGame().getPlayer(Character.VIOLET).getCurrentPlatform(), c.getGame().getGameField().getPlatforms().get(4));
    }

    /**
     * Returns all the players except the current one
     */
    @Test
    public void testGetPossibleTargets() {
        assertTrue(newton.getPossibleTargets().size() == (c.getGame().getPlayers().size() - 1));
    }

    @After
    public void finishTest() {
        target.setCurrentPlatform(c.getGame().getGameField().getPlatforms().get(2));
    }
}
