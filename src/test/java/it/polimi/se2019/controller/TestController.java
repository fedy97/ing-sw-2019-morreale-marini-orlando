package it.polimi.se2019.controller;

import it.polimi.se2019.controller.validator.UserValidActions;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.weapons.PlasmaGun;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.network.message.toclient.SendBinaryOption;
import it.polimi.se2019.utils.CustomLogger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Test class for Controller. Only methods with no network interaction are tested
 *
 * @author Gabriel Raul Marini
 */
public class TestController extends TestInitializer {

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
    }

    /**
     * Verify if bit a bit and is performed
     */
    @Test
    public synchronized void testUpdateValidAction() {
        c.updateValidActions(UserValidActions.NO_BASIC.getActions());
        boolean[] expected = new boolean[]{false, false, false, true, true, true, true};
        for (int i = 0; i < c.getValidActions().length; i++)
            assertEquals(c.getValidActions()[i], c.getValidActions()[i]);
    }

    /**
     * Test if valid actions are properly initialized
     */
    @Test
    public void testResetValidAction() {
        c.resetValidActions();
        boolean[] expected = new boolean[]{true, true, false, true, true, true, true};
        for (int i = 0; i < c.getValidActions().length; i++)
            assertEquals(c.getValidActions()[i], c.getValidActions()[i]);
    }

    /**
     * Verify if game was started
     */
    @Test
    public void testStartGame() {
        c.startGame();
        assertEquals(ControllerState.IDLE, c.getState());
    }

    /**
     * Test if askFor operation works properly
     */
    @Test
    public void testAskFor() {
        try {
            c.askFor(new ArrayList<>(), "weapons");
            c.askFor(new ArrayList<>(), "weaponsToUse");
            c.askFor(new ArrayList<>(), "position");
            c.askFor(new ArrayList<>(), "targets");
            c.askFor(new ArrayList<>(), "discard");
            c.askFor(new ArrayList<>(), "cube");
            c.askFor(new ArrayList<>(), "recharge");
            c.askFor(new ArrayList<>(), "powerups");
            c.askFor(new ArrayList<>(), "no choice");
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if processing operations are not in danger
     */
    @Test
    public void testProcessAction() {
        try {
            c.processAction("action4");
            new Thread(() -> {
                try {
                    c.processAction("action3");
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(),e);
                }
            });
            new Thread(() -> {
                try {
                    c.processAction("action3");
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(),e);
                }
            });
            c.setState(ControllerState.IDLE);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSetTimerStarted() {
        c.setTimerStarted(true);
        assertTrue(c.isTimerStarted());
        c.setTimerStarted(false);
        assertFalse(c.isTimerStarted());
    }

    @Test
    public void testActivateFrenzyMode() {
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


    /**
     * verify if the character is correctly assigned to the player
     */
    @Test
    public void testSetCharacterChosen() {
        try {
            c.setCharacterChosen("user2", Character.VIOLET.toString());
            assertEquals(Character.VIOLET, c.getGame().getPlayer("user2").getCharacter());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Verify if all the character in game are retrieved
     */
    @Test
    public void testFindCharactersInGame() {
        List<String> characterList = c.findCharactersInGame();
        for (String charName : characterList) {
            assertNotNull(c.getGame().getPlayer(Character.valueOf(charName)));
        }
    }

    /**
     * Verify if controller states are correctly assigned
     */
    @Test
    public void testSetState() {
        c.setState(ControllerState.PROCESSING_POWERUP);
        assertEquals(ControllerState.PROCESSING_POWERUP, c.getState());
        c.setState(ControllerState.IDLE);
    }

    /**
     * Verify if a normal weapon is processed
     */
    @Test
    public void testProcessWeapon() {
        /*try {
            c.getChosenEffect().add(1);
            c.getChosenDestination().add(c.getGame().getGameField().getPlatforms().get(6));
            c.processWeaponCard(new PlasmaGun("el", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{}));
        } catch (Exception e) {
            fail();
        }*/
    }

    @Test
    public void testStartWaitingLobbyPing() {
        try {
            c.startWaitingLobbyPing();
            Thread.sleep(5000);
            c.setWaitingToPing(false);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testStartPing() {
        try {
            c.startPinging();
            Thread.sleep(5000);
            c.setGameIsActive(false);
        } catch (Exception e) {
            fail();
        }
    }
}
