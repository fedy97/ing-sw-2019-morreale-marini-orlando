package it.polimi.se2019.utils;

import it.polimi.se2019.controller.TestInitializer;
import it.polimi.se2019.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * @author Gabriel Raul Marini
 */
public class TestDeserializer extends TestInitializer {

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
    }

    /**
     * Test if platform of the light version is the same of the field in the game
     */
    @Test
    public void testGetPlatform() {
        try {
            assertEquals(c.getGame().getGameField().getPlatform(new int[]{0, 0}), Deserializer.getPlatform("0,0"));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the weapon of the light version is the same of the field in the game
     */
    @Test
    public void testGetWeapon() {
        try {
            Deserializer.getWeapon(c.getGame().getWeaponsDeck().drawCard().toString());
            assertEquals("null", Deserializer.getWeapon("null").getName());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test if the weapon of the light version is the same of the field in the game
     */
    @Test
    public void testPowerUp() {
        try {
            Deserializer.getPowerUp(System.identityHashCode(c.getGame().getPlayer("user1").getPowerUpCards().get(0)), "user1");
        } catch (Exception e) {
            fail();
        }
    }

}
