package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.Cyberblade;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TestPlatform {
    Deck<AmmoCard> deckAmmos;

    @Before
    public void initTest() throws InvalidCardException, InvalidImageException,InvalidDeckException, IOException {
        JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
        deckAmmos = parserAmmos.buildAmmoCards();
    }

    @Test
    public void testSetPlatformRoom() throws NoCardException{
        ArrayList<Platform> platforms = new ArrayList<>();
        AmmoCube[] ammoCubes = new AmmoCube[2];
        ammoCubes[0] = AmmoCube.BLUE;
        ammoCubes[1] = AmmoCube.YELLOW;
        int[] pos = new int[2];
        pos[0] = 0;
        pos[1] = 0;
        try {
            AmmoCard ammoCard = deckAmmos.drawCard();
            Platform p = new Platform(pos, false, ammoCard, null, new ArrayList<>());
            platforms.add(p);
            /*
                setPlatformRoom() is called by Room constructor
             */
            Room room = new Room(platforms, platforms.get(0).getPlatformColor());
            assertEquals(platforms.get(0).getPlatformRoom(), room);
        } catch (InvalidCardException ex) {
            fail();
        } catch (InvalidRoomException ex) {
            fail();
        } catch (NullPointerException ex) {
        }
        try {
            Room room = new Room(new ArrayList<>(), null);
            fail();
        } catch (InvalidRoomException ex) {

        }

    }

    @Test
    public void testSetPlatformAmmoCard() throws NullPointerException,NoCardException {
        try {
            Platform p = new Platform(new int[2], false, null, null, new ArrayList<>());
            fail();
        } catch (InvalidCardException ex) {

        }
        try {
            AmmoCard ammoC = deckAmmos.drawCard();
            Platform p3 = new Platform(new int[2], false, ammoC, null, new ArrayList<>());
            AmmoCard ammoC2 = deckAmmos.drawCard();
            if (!p3.hasAmmoCard() && !p3.isGenerationSpot())
                p3.setPlatformAmmoCard(ammoC2);
        } catch (InvalidCardException ex) {

        }
        try {
            AmmoCard ammoC = deckAmmos.drawCard();
            Platform p4 = new Platform(new int[2], false, ammoC, null, new ArrayList<>());
            p4.setPlatformAmmoCard(null);
            fail();
        } catch (InvalidCardException ex) {
        }
        try {
            Platform p4 = new Platform(new int[2], true, null, null, new ArrayList<>());
            p4.setPlatformAmmoCard(deckAmmos.drawCard());
            fail();
        } catch (InvalidCardException ex) {
        }
    }

    @Test
    public void testSetPlayerOnPlatform() {
        Character c1 = Character.BANSHEE;

        try {

            Platform p1 = new Platform(new int[2], true, null, null, new ArrayList<>());
            p1.setPlayerOnPlatform(c1);
        } catch (InvalidCardException ex) {
        } catch (InvalidCharacterException ex) {
            CustomLogger.logException(this.getClass().getName(), ex);
        } catch (NullPointerException ex) {
            CustomLogger.logException(this.getClass().getName(), ex);
        }
    }

    @Test
    public void testGetWeapons(){

        try {
            AmmoCube[] cubes = new AmmoCube[3];
            cubes[0] = AmmoCube.BLUE;
            cubes[1] = AmmoCube.RED;
            cubes[2] = AmmoCube.YELLOW;
            AmmoCard card = new AmmoCard(cubes, false,"test");
            Platform p1 = new Platform(new int[2], false, card, null, new ArrayList<>());
            try {
                p1.getWeapons();
                fail();            }
            catch (InvalidGenerationSpotException e) {

            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
        try {
            Platform p1 = new Platform(new int[2], true, null, null, new ArrayList<>());
            WeaponCard cardW = new WeaponCard();
            p1.addWeaponCard(cardW);
            assertEquals(cardW, p1.getWeapons().get(0));
        }
        catch (InvalidCardException|NullPointerException|InvalidGenerationSpotException e) {
            fail();
        }
    }

    @Test
    public void testSetAdjacentPlatforms() {
        try {
            Platform p1 = new Platform(new int[2], true, null, null, new ArrayList<>());
            try {
                p1.setAdjacentPlatforms(null);
                fail();
            }
            catch (InvalidAdjacentPlatformsException e) {

            }
            Map<Orientation, Platform> adjacentPlatforms = new HashMap<>();
            try {
                p1.setAdjacentPlatforms(adjacentPlatforms);
                assertEquals(adjacentPlatforms, p1.getAdjacentPlatforms());
            }
            catch (InvalidAdjacentPlatformsException e) {
                fail();
            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
    }


    @Test
    public void testSetWeapons() {
        try {
            AmmoCube[] cubes = new AmmoCube[3];
            cubes[0] = AmmoCube.BLUE;
            cubes[1] = AmmoCube.RED;
            cubes[2] = AmmoCube.YELLOW;
            AmmoCard card = new AmmoCard(cubes, false,"test");
            Platform p1 = new Platform(new int[2], false, card, null, new ArrayList<>());
            try {
                p1.setWeapons(null);
                fail();
            }
            catch (InvalidGenerationSpotException e) {

            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
        try {
            Platform p1 = new Platform(new int[2], true, null, null, new ArrayList<>());
            try {
                List<WeaponCard> weapons = new ArrayList<>();
                p1.setWeapons(weapons);
                assertEquals(weapons, p1.getWeapons());
            }
            catch (InvalidGenerationSpotException e) {
                fail();
            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
    }

    @Test
    public void testAddWeaponCard() {
        try {
            AmmoCube[] cubes = new AmmoCube[3];
            cubes[0] = AmmoCube.BLUE;
            cubes[1] = AmmoCube.RED;
            cubes[2] = AmmoCube.YELLOW;
            AmmoCard card = new AmmoCard(cubes, false,"test");
            Platform p1 = new Platform(new int[2], false, card, null, new ArrayList<>());
            try {
                p1.addWeaponCard(null);
                fail();
            }
            catch (InvalidGenerationSpotException e) {

            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
        try {
            Platform p1 = new Platform(new int[2], true, null, null, new ArrayList<>());
            try {
                WeaponCard card = new WeaponCard();
                p1.addWeaponCard(card);
                assertEquals(card, p1.getWeapons().get(0));
            }
            catch (InvalidGenerationSpotException e) {
                fail();
            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
    }

    @Test
    public void testRemoveWeaponCard() {
        try {
            AmmoCube[] cubes = new AmmoCube[3];
            cubes[0] = AmmoCube.BLUE;
            cubes[1] = AmmoCube.RED;
            cubes[2] = AmmoCube.YELLOW;
            AmmoCard card = new AmmoCard(cubes, false,"test");
            Platform p1 = new Platform(new int[2], false, card, null, new ArrayList<>());
            try {
                p1.removeWeaponCard(new WeaponCard());
                fail();
            }
            catch (InvalidGenerationSpotException e) {

            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
        try {
            Platform p1 = new Platform(new int[2], true, null, null, new ArrayList<>());
            try {
                WeaponCard card = new WeaponCard();
                p1.addWeaponCard(card);
                p1.removeWeaponCard(card);
                assertEquals(0, p1.getWeapons().size());
            }
            catch (InvalidGenerationSpotException e) {
                fail();
            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }
    }

    @Test
    public void testGrabAmmoCard() {
        try {
            Platform p1 = new Platform(new int[2], true, null, null, new ArrayList<>());
            try {
                p1.grabAmmoCard();
                fail();
            }
            catch (InvalidCardException e) {

            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }

        try {
            AmmoCube[] cubes = new AmmoCube[3];
            cubes[0] = AmmoCube.BLUE;
            cubes[1] = AmmoCube.RED;
            cubes[2] = AmmoCube.YELLOW;
            AmmoCard card = new AmmoCard(cubes, false,"test");
            Platform p1 = new Platform(new int[2], false, card, null, new ArrayList<>());
            try {
                assertEquals(card, p1.grabAmmoCard());
            }
            catch (InvalidCardException e) {
                fail();
            }
        }
        catch (InvalidCardException|NullPointerException e) {
            fail();
        }

    }




}