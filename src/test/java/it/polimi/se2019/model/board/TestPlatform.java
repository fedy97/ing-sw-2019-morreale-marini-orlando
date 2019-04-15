package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.exceptions.InvalidRoomException;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestPlatform {

    @Test
    public void testSetPlatformRoom() {
        ArrayList<Platform> platforms = new ArrayList<>();
        AmmoCube[] ammoCubes = new AmmoCube[2];
        ammoCubes[0] = AmmoCube.BLUE;
        ammoCubes[1] = AmmoCube.YELLOW;
        int[] pos = new int[2];
        pos[0] = 0;
        pos[1] = 0;
        try {
            AmmoCard ammoCard = new AmmoCard(ammoCubes, true);
            Platform p = new Platform(pos, false, ammoCard);
            platforms.add(p);
            /*
                setPlatformRoom() is called by Room constructor
             */
            Room room = new Room(platforms);
            assertEquals(platforms.get(0).getPlatformRoom(), room);
        } catch (InvalidCardException ex) {
            fail();
        } catch (InvalidRoomException ex) {
            fail();
        } catch (NullPointerException ex) {
        }
        try {
            Room room = new Room(new ArrayList<>());
            fail();
        } catch (InvalidRoomException ex) {

        }

    }

    @Test
    public void testSetPlatformAmmoCard() {
        try {
            Platform p = new Platform(new int[2], false, null);
            fail();
        } catch (InvalidCardException ex) {

        }
        try {
            AmmoCard ammoC = new AmmoCard(new AmmoCube[2], true);
            Platform p3 = new Platform(new int[2], true, ammoC);
            AmmoCard ammoC2 = new AmmoCard(new AmmoCube[3], false);
            p3.setPlatformAmmoCard(ammoC2);
        } catch (InvalidCardException ex) {

        } catch (NullPointerException ex) {
        }
        try {
            AmmoCard ammoC = new AmmoCard(new AmmoCube[2], true);
            Platform p4 = new Platform(new int[2], false, ammoC);
            p4.setPlatformAmmoCard(null);
            fail();
        } catch (InvalidCardException ex) {
        } catch (NullPointerException ex) {
        }
        try {
            Platform p4 = new Platform(new int[2], true, null);
            p4.setPlatformAmmoCard(new AmmoCard(new AmmoCube[2], true));
            fail();
        } catch (InvalidCardException ex) {
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testSetPlayerOnPlatform() {
        Character c1 = Character.BANSHEE;

        try {
            c1.setPlayerName("morre");
            Platform p1 = new Platform(new int[2], true, null);
            p1.setPlayerOnPlatform(c1);
        } catch (InvalidCardException ex) {
        } catch (InvalidCharacterException ex) {
        } catch (NullPointerException ex) {
        } catch (InvalidNameException ex) {
        }

    }


}