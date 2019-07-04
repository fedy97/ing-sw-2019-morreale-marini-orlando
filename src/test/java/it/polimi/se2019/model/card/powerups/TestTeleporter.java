package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.TestInitializer;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test Teleporter powerup
 *
 * @author Gabriel Raul Marini
 */
public class TestTeleporter extends TestInitializer {
    private Teleporter teleporter;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        teleporter = new Teleporter(AmmoCube.RED, "teletrasporto", "desc", "path");
    }

    /**
     * Test if player is correctly moved
     */
    @Test
    public void testActivate() {
         c.getChosenDestination().add(c.getGame().getGameField().getPlatforms().get(4));
        teleporter.activate(null);
        assertEquals(c.getPlayerManager().getCurrentPlayer().getCurrentPlatform().toString(), c.getGame().getGameField().getPlatforms().get(4).toString());
    }

    @Test
    public void testGetPossibleTargets() {
        assertNull(teleporter.getPossibleTargets());
    }

}
