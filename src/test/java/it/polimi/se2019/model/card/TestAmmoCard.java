package it.polimi.se2019.model.card;


import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Test;

import static org.junit.Assert.*;

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
            JsonParser parser = new JsonParser("/json/ammocards.json");
            Deck<AmmoCard> deck;
            //now ammocards has 36 ammos cards according to json
            deck = parser.buildAmmoCards();

            assertTrue(deck.drawCard().hasPowerUp());
            AmmoCard card = deck.drawCard();
            assertEquals("yb", card.toString());

        } catch (InvalidCardException ex) {
            fail();
        }
    }
}
