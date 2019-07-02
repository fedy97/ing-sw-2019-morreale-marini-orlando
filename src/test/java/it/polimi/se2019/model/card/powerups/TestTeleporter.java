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

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTeleporter extends TestControllerChild {
    private Teleporter teleporter;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        teleporter = new Teleporter(AmmoCube.RED, "teletrasporto", "desc", "path");
    }

    @Test
    public void testActivate() {
        c.getChosenDestination().add(c.getGame().getGameField().getPlatforms().get(4));
        teleporter.activate(Character.VIOLET);
       // assertEquals(c.getPlayerManager().getCurrentPlayer().getCurrentPlatform(), c.getGame().getGameField().getPlatforms().get(4));
    }

    @Test
    public void testGetPossibleTargets() {
        assertNull(teleporter.getPossibleTargets());
    }

    @After
    public void finishTest() {

    }
}
