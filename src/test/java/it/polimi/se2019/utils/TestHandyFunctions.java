package it.polimi.se2019.utils;

import it.polimi.se2019.model.enumeration.Character;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class used to test general functions of HandyFunctions
 *
 * @author Gabriel Raul Marini
 */
public class TestHandyFunctions {

    @Test
    public void testIsValidPosition() {
        assertTrue(HandyFunctions.isValidPosition(new int[]{0, 2}));
        assertFalse(HandyFunctions.isValidPosition(new int[]{2, 5}));
    }

    @Test
    public void testCharacterExists() {
        assertTrue(HandyFunctions.characterExists(Character.BANSHEE));
    }

    @Test
    public void testRandoIntegerBewtween() {
        try {
            HandyFunctions.randomIntegerBetWeen(4, 2);
            fail();
        } catch (Exception e) {
            int num = HandyFunctions.randomIntegerBetWeen(2, 4);
            assertTrue(num >= 2 && num <= 4);
        }
    }
}
