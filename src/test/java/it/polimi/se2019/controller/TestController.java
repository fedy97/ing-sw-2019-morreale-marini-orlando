package it.polimi.se2019.controller;

import it.polimi.se2019.controller.validator.UserValidActions;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.network.message.to_client.SendBinaryOption;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class TestController extends TestControllerChild {

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
    }

    @Test
    public void testUpdateValidAction() {
        c.updateValidActions(UserValidActions.NO_BASIC.getActions());
        boolean[] expected = new boolean[]{false, false, false, true, true, true, true};
        for (int i = 0; i < c.getValidActions().length; i++)
            assertEquals(expected[i], c.getValidActions()[i]);
    }

    @Test
    public void testResetValidAction() {
        c.resetValidActions();
        boolean[] expected = new boolean[]{true, true, false, true, true, true, true};
        for (int i = 0; i < c.getValidActions().length; i++)
            assertEquals(expected[i], c.getValidActions()[i]);
    }

    @Test
    public void testActivaFrenzyMode() {
        assertFalse(c.isFrenzyModeOn());
        c.activateFrenzyMode();
        assertTrue(c.isFrenzyModeOn());
        c.setFrenzyMode(false);
    }

    @Test
    public void testNotifyAll() {
        try {
            c.notifyAll(new SendBinaryOption("test"));

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFindCharactersInGame() {
        List<String> characterList = c.findCharactersInGame();
        for (String charName : characterList) {
            assertNotNull(c.getGame().getPlayer(Character.valueOf(charName)));
        }
    }
}
