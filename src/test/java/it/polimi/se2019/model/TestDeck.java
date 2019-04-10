package it.polimi.se2019.model;


import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Gabriel Raul Marini
 */
public class TestDeck {

    /**
     * Test the behavior of drawCard() verifying if the drawn card is the expected one
     *
     * @throws NoCardException when the deck is empty
     */
    @Test(expected = NoCardException.class)
    public void testDrawCard() throws NoCardException {
        Deck deck = new Deck(2);
        PowerUpCard p = null;

        Teleporter t1 = new Teleporter(AmmoCube.YELLOW);
        Teleporter t2 = new Teleporter(AmmoCube.YELLOW);

        try {
            deck.addCard(t1);
            deck.addCard(t2);
        } catch (InvalidDeckException e) {
            fail();
        }

        /*
            Test if the drawn card is the expected one
         */
        try {
            p = (PowerUpCard) deck.drawCard();
        } catch (NoCardException e) {
            fail();
        }

        assertEquals(p, t2);

        /*
            Test if NoCardException is thrown when the deck is empty
         */
        deck.drawCard();
        deck.drawCard();

    }

    /**
     * Test the behavior of addCard() verifying if it is correctly added to the deck
     *
     * @throws InvalidDeckException id trying to add cards to a full deck
     */
    @Test(expected = InvalidDeckException.class)
    public void testAddCard() throws InvalidDeckException {
        Deck deck = new Deck(1);
        PowerUpCard p = null;

        Teleporter t1 = new Teleporter(AmmoCube.YELLOW);
        Teleporter t2 = new Teleporter(AmmoCube.YELLOW);

        try {
            deck.addCard(t1);
        } catch (InvalidDeckException e) {
            fail();
        }

        /*
            Test if the drawn card is the expected one
         */
        try {
            p = (PowerUpCard) deck.drawCard();
        } catch (NoCardException e) {
            fail();
        }

        assertEquals(p, t1);

        /*
            Test if InvalidDeckException is thrown when the deck is empty
         */
        deck.addCard(t1);
        deck.addCard(t2);

    }

    /**
     * Test the behavior of mix() verifying if the deck is mixed properly
     */
    @Test
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
    }

}
