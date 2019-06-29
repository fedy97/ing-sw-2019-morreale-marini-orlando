package it.polimi.se2019.model.rep;

import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A test class that validates the behaviour of the AmmoCard
 *
 * @author Gabriel Raul Marini
 */
public class TestCardRep {

    /**
     * Test if InvalidCardException is returned when trying to create an invalid
     * AmmoCard
     */
    @Test
    public void testCardRep() {
        try {
            CardRep rep = new CardRep(new WeaponCard("test", "desc", "path", AmmoCube.BLUE, new AmmoCube[]{AmmoCube.BLUE}));
            assertEquals("test", rep.getTitle());
            assertEquals("path", rep.getPath());
            assertEquals("desc", rep.getDescription());
            rep.setLoaded(true);
            assertTrue(rep.isLoaded());
        } catch (Exception e) {
            fail();
        }
    }
}
