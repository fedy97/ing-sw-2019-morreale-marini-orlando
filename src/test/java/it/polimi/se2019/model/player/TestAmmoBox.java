package it.polimi.se2019.model.player;


import it.polimi.se2019.model.enumeration.AmmoCube;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Simone Orlando
 */
public class TestAmmoBox {

    @Test
    public void testAddAmmos() {

        AmmoBox myAmmoBox = new AmmoBox();
        int redNum = 2;
        int yellowNum = 1;
        int blueNum = 1;
        int startValueRed = myAmmoBox.getRedAmmos();
        int startValueYellow = myAmmoBox.getYellowAmmos();
        int startValueBlue = myAmmoBox.getBlueAmmos();

        /*
            Test of the standard addition
         */
        try {
            myAmmoBox.addAmmos(AmmoCube.RED, redNum);
            myAmmoBox.addAmmos(AmmoCube.YELLOW, yellowNum);
            myAmmoBox.addAmmos(AmmoCube.BLUE, blueNum);
        } catch (IllegalArgumentException e) {
            fail();
        } catch (NullPointerException e) {
            fail();
        }

        int red = myAmmoBox.getRedAmmos();
        int yellow = myAmmoBox.getYellowAmmos();
        int blue = myAmmoBox.getBlueAmmos();

        assertEquals(red, redNum + startValueRed);
        assertEquals(yellow, yellowNum + startValueYellow);
        assertEquals(blue, blueNum + startValueBlue);

        /*
            Test of addition with saturation
         */
        int redToAdd = 2;
        int yellowToAdd = 4;
        int blueToAdd = 1;

        try {
            myAmmoBox.addAmmos(AmmoCube.RED, redToAdd);
            myAmmoBox.addAmmos(AmmoCube.YELLOW, yellowToAdd);
            myAmmoBox.addAmmos(AmmoCube.BLUE, blueToAdd);
        } catch (IllegalArgumentException e) {
            fail();
        } catch (NullPointerException e) {
            fail();
        }

        red = myAmmoBox.getRedAmmos();
        yellow = myAmmoBox.getYellowAmmos();
        blue = myAmmoBox.getBlueAmmos();

        assertEquals(red, 3);
        assertEquals(yellow, 3);
        assertEquals(blue, 3);

        /*
            Test of the impossibility of having a null AmmoCube
         */
        try {
            myAmmoBox.addAmmos(null, 1);
            fail();
        } catch (NullPointerException e) {

        }

        /*
            Test of the impossibility of adding a number of negative ammunition
         */
        try {
            myAmmoBox.addAmmos(AmmoCube.BLUE, -4);
            fail();
        } catch (IllegalArgumentException e) {

        }

    }

    @Test
    public void testRemoveAmmos() {

        AmmoBox myAmmoBox = new AmmoBox();

        /*
            Test of the standard ammunition removal
         */
        try {
            myAmmoBox.removeAmmos(AmmoCube.RED, 1);
            myAmmoBox.removeAmmos(AmmoCube.YELLOW, 1);
            myAmmoBox.removeAmmos(AmmoCube.BLUE, 1);
        } catch (NullPointerException e) {
            fail();
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertEquals(myAmmoBox.getRedAmmos(), 0);
        assertEquals(myAmmoBox.getYellowAmmos(), 0);
        assertEquals(myAmmoBox.getBlueAmmos(), 0);

        /*
            Test when num is greater than the current ammunition number
         */
        myAmmoBox.addAmmos(AmmoCube.RED, 2);
        myAmmoBox.addAmmos(AmmoCube.YELLOW, 1);
        myAmmoBox.addAmmos(AmmoCube.BLUE, 3);
        try {
            myAmmoBox.removeAmmos(AmmoCube.RED, 4);
            myAmmoBox.removeAmmos(AmmoCube.YELLOW, 3);
            myAmmoBox.removeAmmos(AmmoCube.BLUE, 6);
        } catch (NullPointerException e) {
            fail();
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertEquals(myAmmoBox.getRedAmmos(), 0);
        assertEquals(myAmmoBox.getYellowAmmos(), 0);
        assertEquals(myAmmoBox.getBlueAmmos(), 0);

        /*
            Test of the impossibility of having a null AmmoCube
         */
        try {
            myAmmoBox.removeAmmos(null, 1);
            fail();
        } catch (NullPointerException e) {

        }

        /*
            Test of the impossibility of removing a number of negative ammunition
         */
        try {
            myAmmoBox.removeAmmos(AmmoCube.BLUE, -3);
            fail();
        } catch (IllegalArgumentException e) {

        }

    }

    /**
     * @author Gabriel Raul Marini
     */
    @Test
    public void testCheckAmmos() {
        AmmoBox ammoBox = new AmmoBox();
        AmmoCube[] ammoCubes = {AmmoCube.BLUE, AmmoCube.BLUE, AmmoCube.YELLOW};

        /*
            The ammoBox has enough cubes, expected value to be true
         */
        ammoBox.addAmmos(AmmoCube.BLUE, 2);

        assertTrue(ammoBox.hasAmmos(ammoCubes));

        /*
         *  The ammoBox hasn't enough BLUE cubes, expected value is false
         */
        ammoBox.removeAmmos(AmmoCube.BLUE, 2);

        assertFalse(ammoBox.hasAmmos(ammoCubes));
    }


}
