package it.polimi.se2019.model.player;


import it.polimi.se2019.exceptions.*;

import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.Newton;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * A test class verifying that player behaviour is correct with the game guidelines
 *
 * @author Simone Orlando
 */
public class TestPlayer {
    Deck<PowerUpCard> deck;
    Deck<AmmoCard> deckAmmos;
    @Before
    public void initTest() throws InvalidCardException,InvalidCubeException, InvalidNameException, InvalidImageException,InvalidDeckException, IOException {
        JsonParser parser = new JsonParser("/json/powerups.json");
        deck = parser.buildPowerupCards();
        JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
        deckAmmos = parserAmmos.buildAmmoCards();
    }

    @Test
    public void testChangePlatform() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int pos[] = {1, 0};

        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        Platform newPlat = new Platform(pos, true, card, Color.BLUE, orient);

        player.changePlatform(newPlat);
        assertEquals(player.getCurrentPlatform(), newPlat);

        try {
            player.changePlatform(null);
            fail();
        } catch (InvalidPositionException e) {

        }
    }

    @Test
    public void testAddPoint() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int pos[] = {1, 0};
        AmmoCube cube[] = {AmmoCube.RED, AmmoCube.YELLOW, AmmoCube.YELLOW};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);
        try {
            player.addPoint(5);
        } catch (NegativeNumberException e) {
            fail();
        }
        assertEquals(player.getCurrentScore(), 5);

        try {
            player.addPoint(-3);
            fail();
        } catch (NegativeNumberException e) {

        }
    }

    @Test
    public void testAddDeath() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int pos[] = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        player.addDeath();
        assertEquals(player.getNumOfDeaths(), 1);
    }

    @Test
    public void testResetDeaths() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int pos[] = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        player.addDeath();
        assertEquals(player.getNumOfDeaths(), 1);
        player.resetDeaths();
        assertEquals(player.getNumOfDeaths(), 0);
    }

    @Test
    public void testAddPowerUpCard() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int pos[] = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        PowerUpCard pCard = deck.drawCard();

        player.addPowerUpCard(pCard);

        assertEquals(player.getPowerUpCards().contains(pCard), true);
        try {
            player.addPowerUpCard(null);
            fail();
        } catch (InvalidCardException e) {

        }
    }

    @Test
    public void testRemovePowerUpCard() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int pos[] = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        PowerUpCard pCard = deck.drawCard();
        player.addPowerUpCard(pCard);
        assertEquals(player.getPowerUpCards().contains(pCard), true);
        try {
            player.removePowerUpCard(pCard);
        } catch (InvalidCardException e) {
            fail();
        }
        assertEquals(player.getPowerUpCards().contains(pCard), false);

        try {
            player.removePowerUpCard(null);
            fail();
        } catch (InvalidCardException e) {

        }
    }

    @Test
    public void testAddWeaponCard() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int pos[] = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        WeaponCard wCard = new WeaponCard();
        try {
            player.addWeaponCard(wCard);
        } catch (MaxWeaponException e) {
            fail();
        }
        assertEquals(player.getWeaponCards().contains(wCard), true);

        try {
            player.addWeaponCard(wCard);
            player.addWeaponCard(wCard);
        } catch (MaxWeaponException e) {
            fail();
        }

        try {
            player.addWeaponCard(wCard);
            fail();
        } catch (MaxWeaponException e) {

        }
    }

    @Test
    public void testRemoveWeaponCard() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException, MaxWeaponException {
        int pos[] = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        WeaponCard wCard = new WeaponCard();
        player.addWeaponCard(wCard);
        assertEquals(player.getWeaponCards().contains(wCard), true);


        player.removeWeaponCard(wCard);

        assertEquals(player.getWeaponCards().contains(wCard), false);

    }

    @Test
    public void testSetFrenzyModeType() throws NoCardException,InvalidCardException, InvalidPositionException, InvalidCharacterException, MaxWeaponException {
        int pos[] = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player(Character.BANSHEE, start);

        player.setFrenzyModeType(2);
        assertEquals(player.getFrenzyModeType(), 2);
    }

}
