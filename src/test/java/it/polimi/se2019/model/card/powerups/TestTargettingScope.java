package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.TestControllerChild;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestTargettingScope extends TestControllerChild {
    private TargettingScope targettingScope;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        targettingScope = new TargettingScope(AmmoCube.RED, "mirino", "desc", "path");
        c.getChosenAmmo().add("RED");
        c.getGame().getPlayer(Character.VIOLET).getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testActivate(){
        targettingScope.activate(Character.VIOLET);
        assertTrue(c.getGame().getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(c.getPlayerManager().getCurrentPlayer().getCharacter()));
    }

    @After
    public void finishTest(){
        c.getGame().getPlayer(Character.VIOLET).getPlayerBoard().resetDamageLine();
    }
}
