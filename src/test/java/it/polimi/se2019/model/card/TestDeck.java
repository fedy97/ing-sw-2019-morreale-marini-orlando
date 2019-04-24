package it.polimi.se2019.model.card;


import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Gabriel Raul Marini
 */
public class TestDeck {
    Deck<PowerUpCard> deck;

    @Before
    public void initTest() throws InvalidCubeException, InvalidNameException, InvalidImageException,InvalidDeckException, IOException {
        JsonParser parser = new JsonParser("/json/powerups.json");
        deck = parser.buildPowerupCards();
        deck.mix();
    }


    /**
     * Test the behavior of drawCard() verifying if the drawn card is the expected one
     *
     * @throws NoCardException when the deck is empty
     */

    @Test(expected = NoCardException.class)
    public void testDrawCard() throws NoCardException {
        Deck deck2 = new Deck(2);
        PowerUpCard p = null;

        PowerUpCard t1 = deck.drawCard();
        PowerUpCard t2 = deck.drawCard();

        try {
            deck2.addCard(t1);
            deck2.addCard(t2);
        } catch (InvalidDeckException e) {
            fail();
        }
        /*
            Test if the drawn card is the expected one
         */
        try {
            p = (PowerUpCard) deck2.drawCard();
        } catch (NoCardException e) {
            fail();
        }

        assertEquals(p, t2);

        /*
            Test if NoCardException is thrown when the deck is empty
         */
        deck2.drawCard();
        deck2.drawCard();

    }

    /**
     * Test the behavior of addCard() verifying if the card is correctly added to the deck
     *
     * @throws InvalidDeckException id trying to add cards to a full deck
     */
    @Test(expected = InvalidDeckException.class)
    public void testAddCard() throws InvalidDeckException,NoCardException {
        Deck deck2 = new Deck(1);
        PowerUpCard p = null;

        PowerUpCard t1 = deck.drawCard();
        PowerUpCard t2 = deck.drawCard();

        try {
            deck2.addCard(t1);
        } catch (InvalidDeckException e) {
            fail();
        }

        /*
            Test if the drawn card is the expected one
         */
        try {
            p = (PowerUpCard) deck2.drawCard();
        } catch (NoCardException e) {
            fail();
        }

        assertEquals(p, t1);

        /*
            Test if InvalidDeckException is thrown when the deck is empty
         */
        deck2.addCard(t1);
        deck2.addCard(t2);

    }

    /**
     * Test the behavior of addCards() verifying if the set of cards is properly
     * added to the deck
     *
     * @throws InvalidDeckException if trying to add cards that exceed the maximum size of the deck
     *                              or if the adding deck is empty
     */
    @Test(expected = InvalidDeckException.class)
    public void testAddCards() throws InvalidDeckException,NoCardException {
        Deck deck2 = new Deck(5);
        PowerUpCard p = null;

        PowerUpCard t1 = deck.drawCard();
        PowerUpCard t2 = deck.drawCard();

        try {
            deck2.addCard(t1);
            deck2.addCard(t2);
        } catch (InvalidDeckException e) {
            fail();
        }

        ArrayList<PowerUpCard> cards = new ArrayList<>();
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());

        deck2.addCards(cards);
        assertTrue(deck2.containsAll(cards));

        /*
            Test if InvalidDeckException is thrown when trying to add more cards than
            expected
         */
        try {
            deck2.addCards(cards);
            fail();
        } catch (InvalidDeckException e) {}

        /*
            Test if InvalidDeckException is thrown when trying to add no cards
         */
       deck2.addCards(new ArrayList<>());
    }

    /**
     * Test the behavior of mix() verifying if the deck is mixed properly
     */
    /*@Test
    public void testMixDeck() {
        Deck deck = new Deck(4);

        Teleporter t1 = new Teleporter(AmmoCube.YELLOW);
        Teleporter t2 = new Teleporter(AmmoCube.YELLOW);
        Teleporter t3 = new Teleporter(AmmoCube.YELLOW);
        Teleporter t4 = new Teleporter(AmmoCube.YELLOW);

        try {
            deck.addCard(t1);
            deck.addCard(t2);
            deck.addCard(t3);
            deck.addCard(t4);
        } catch (InvalidDeckException e) {
            fail();
        }

        deck.mix();

        try {
            assertNotEquals(t4, deck.drawCard());
        } catch (NoCardException e) {
            fail();
        }
    }*/

}
