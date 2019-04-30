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

        myAmmoBox.addAmmos(AmmoCube.RED, redNum);
        myAmmoBox.addAmmos(AmmoCube.YELLOW, yellowNum);
        myAmmoBox.addAmmos(AmmoCube.BLUE, blueNum);


        int red = myAmmoBox.getRedAmmos();
        int yellow = myAmmoBox.getYellowAmmos();
        int blue = myAmmoBox.getBlueAmmos();

        assertEquals(red, redNum + startValueRed);
        assertEquals(yellow, yellowNum + startValueYellow);
        assertEquals(blue, blueNum + startValueBlue);


    }

    @Test
    public void testRemoveAmmos() {

        AmmoBox myAmmoBox = new AmmoBox();

        /*
            Test of the standard ammunition removal
         */

        myAmmoBox.removeAmmos(AmmoCube.RED, 1);
        myAmmoBox.removeAmmos(AmmoCube.YELLOW, 1);
        myAmmoBox.removeAmmos(AmmoCube.BLUE, 1);

        assertEquals(0,myAmmoBox.getRedAmmos());
        assertEquals(0,myAmmoBox.getYellowAmmos());
        assertEquals(0,myAmmoBox.getBlueAmmos());


    }

    /**
     * @author Gabriel Raul Marini
     */
    @Test
    public void testCheckAmmos() {
        /*
         * By default a new AmmoBox has 1 cube of each color
         */
        AmmoBox ammoBox = new AmmoBox();
        AmmoCube[] ammoCubes = {AmmoCube.BLUE, AmmoCube.BLUE, AmmoCube.YELLOW};

        /*
         * The ammoBox has enough cubes, expected value to be true
         */
        ammoBox.addAmmos(AmmoCube.BLUE, 2);

        assertTrue(ammoBox.hasAmmos(ammoCubes));
        assertTrue(ammoBox.hasAmmo(AmmoCube.RED));

        /*
         *  The ammoBox hasn't enough BLUE cubes, expected value is false
         */
        ammoBox.removeAmmos(AmmoCube.BLUE, 2);
        ammoBox.removeAmmos(AmmoCube.RED, 1);

        assertFalse(ammoBox.hasAmmos(ammoCubes));
        assertFalse(ammoBox.hasAmmo(AmmoCube.RED));

        ammoBox.removeAmmos(AmmoCube.BLUE, 1);
        ammoBox.removeAmmos(AmmoCube.YELLOW, 1);

        /*
         *  The AmmoBox is now empty, true value expected by isEmpty() method
         */
        assertTrue(ammoBox.isEmpty());
    }


}
