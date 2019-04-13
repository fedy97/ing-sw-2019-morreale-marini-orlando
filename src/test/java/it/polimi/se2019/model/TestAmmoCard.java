package it.polimi.se2019.model;


import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * A test class that validates the behaviour of the AmmoCard
 *
 * @author Gabriel Raul Marini
 */
public class TestAmmoCard {

    /**
     * Test if InvalidCardException is returned when trying to create an invalid
     * AmmoCard
     */
    @Test
    public void TestAmmoCard() {
        try {
            new AmmoCard(new AmmoCube[3], true);
            fail();
        } catch (InvalidCardException e) {}

        try {
            new AmmoCard(new AmmoCube[2], false);
            fail();
        } catch (InvalidCardException e) {}
    }
}
