package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Test class used to verify if the DecksManager works properly
 *
 * @author Gabriel Raul Marini
 */
public class TestDecksManager {
    DecksManager decksManager;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        JsonParser parser = new JsonParser("/json/powerups.json");
        Deck<PowerUpCard> powerUpCardDeck = parser.buildPowerupCards();
        JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
        Deck<AmmoCard> ammoCardDeck = parserAmmos.buildAmmoCards();
        decksManager = new DecksManager(powerUpCardDeck, ammoCardDeck);
    }

    /**
     * Test the drawPowerUp method verifying if it's refilled when it's empty
     */
    @Test
    public void testDrawPowerUp() {
        //the deck is composed only of 24 cards!
        for (int i = 0; i < 32; i++) {
            decksManager.addToGarbage(decksManager.drawPowerUp());
        }
        //no errors, ok!
    }

    @Test
    public void testGetNewAmmoCard() {
        try {
            decksManager.getNewAmmoCard(new AmmoCard(new AmmoCube[]{AmmoCube.YELLOW, AmmoCube.BLUE}, true, ""));
        } catch (InvalidCardException e) {
            fail();
        } catch (
                NoCardException e) {
            fail();
        }
    }
}
