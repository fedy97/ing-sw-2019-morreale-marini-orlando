package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.Newton;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.powerups.TagbackGrenade;
import it.polimi.se2019.model.enumeration.AmmoCube;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class used to verify if the DecksManager works properly
 *
 * @author Gabriel Raul Marini
 */
public class TestDecksManager {
    DecksManager decksManager;

    @Before
    public void initTest() {
        Deck<PowerUpCard> powerDeck = new Deck<>(3);
        ArrayList<PowerUpCard> garbage = new ArrayList<>();
        Deck<AmmoCard> ammoDeck = new Deck<>(3);

        powerDeck.add(new Newton(AmmoCube.RED));
        powerDeck.add(new TagbackGrenade(AmmoCube.BLUE));
        powerDeck.add(new Newton(AmmoCube.RED));

        try {
            ammoDeck.add(new AmmoCard(new AmmoCube[]{AmmoCube.RED, AmmoCube.YELLOW}, true));
            ammoDeck.add(new AmmoCard(new AmmoCube[]{AmmoCube.RED, AmmoCube.YELLOW}, true));
            ammoDeck.add(new AmmoCard(new AmmoCube[]{AmmoCube.RED, AmmoCube.YELLOW}, true));
        } catch (InvalidCardException e) {
            fail();
        }

        decksManager = new DecksManager(powerDeck, garbage, ammoDeck);
    }

    /**
     * Test the drawPowerUp method verifying if it's refilled when it's empty
     */
    @Test
    public void testDrawPowerUp() {
        PowerUpCard powerUp = decksManager.drawPowerUp();
        decksManager.addToGarbage(powerUp);
        decksManager.drawPowerUp();
        decksManager.drawPowerUp();

        assertEquals(powerUp, decksManager.drawPowerUp());
    }

    @Test
    public void testGetNewAmmoCard() {
        try {
            decksManager.getNewAmmoCard(new AmmoCard(new AmmoCube[]{AmmoCube.RED, AmmoCube.YELLOW}, true));
        } catch (InvalidCardException e) {
            fail();
        } catch (
                NoCardException e) {
            fail();
        }
    }

}
