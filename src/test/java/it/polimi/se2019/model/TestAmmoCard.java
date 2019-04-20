package it.polimi.se2019.model;


import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
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
    public void TestAmmoCard() throws InvalidDeckException, NoCardException {
        try {
            new AmmoCard(new AmmoCube[3], true);
            fail();
        } catch (InvalidCardException e) {}

        try {
            new AmmoCard(new AmmoCube[2], false);
            fail();
        } catch (InvalidCardException e) {}
        try {
            JsonParser parser = new JsonParser("/json/ammocards.json");
            Deck<AmmoCard> deck;
            //now ammocards has 36 ammo cards according to json
            deck = parser.buildAmmoCards();
            assertEquals(deck.drawCard().hasPowerUp(), true);
        } catch (InvalidCardException ex) {}
    }
}
