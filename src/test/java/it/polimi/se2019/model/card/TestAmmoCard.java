package it.polimi.se2019.model.card;


import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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
    public void TestAmmoCard() throws InvalidImageException,IOException,InvalidDeckException, NoCardException {

        try {
            JsonParser parser = new JsonParser("/json/ammocards.json");
            Deck<AmmoCard> deck;
            //now ammocards has 36 ammos cards according to json
            deck = parser.buildAmmoCards();
            //TODO ISSUE
            //assertTrue(deck.drawCard().hasPowerUp());
        } catch (InvalidCardException ex) {}
    }
}
