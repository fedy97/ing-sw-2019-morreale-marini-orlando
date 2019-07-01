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

public class TestTagbackGrenade extends TestControllerChild {
    private TagbackGrenade tagback;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        tagback = new TagbackGrenade(AmmoCube.RED, "mirino", "desc", "path");
        tagback.setUserTarget("user2");
        c.getPlayerManager().getCurrentPlayer().getPlayerBoard().clearMarks();
    }

    @Test
    public void testActivate(){
        tagback.activate(Character.VIOLET);
        assertTrue(c.getPlayerManager().getCurrentPlayer().getPlayerBoard().getRevengeMarks().contains(c.getGame().getPlayer("user2").getCharacter()));
    }

    @After
    public void finishTest(){
        c.getPlayerManager().getCurrentPlayer().getPlayerBoard().clearMarks();
    }
}
