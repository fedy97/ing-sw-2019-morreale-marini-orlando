package it.polimi.se2019.model.player;


import com.sun.org.glassfish.external.amx.AMX;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.model.enumeration.AmmoCube;
import org.junit.Test;

import java.util.List;

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

        assertEquals(0, myAmmoBox.getRedAmmos());
        assertEquals(0, myAmmoBox.getYellowAmmos());
        assertEquals(0, myAmmoBox.getBlueAmmos());


    }

    @Test
    public void testRemoveAmmos2() {
        AmmoBox myAmmoBox = new AmmoBox();
        AmmoCube[] list = new AmmoCube[2];
        list[0] = AmmoCube.RED;
        list[1] = AmmoCube.YELLOW;

        myAmmoBox.removeAmmos(list);

        assertEquals(0, myAmmoBox.getRedAmmos());
        assertEquals(0, myAmmoBox.getYellowAmmos());
        assertEquals(1, myAmmoBox.getBlueAmmos());
    }

    @Test
    public void testIsEmpty() {
        AmmoBox myAmmoBox = new AmmoBox();
        AmmoCube[] list = new AmmoCube[3];
        list[0] = AmmoCube.RED;
        list[1] = AmmoCube.YELLOW;
        list[2] = AmmoCube.BLUE;

        myAmmoBox.removeAmmos(list);

        assertTrue(myAmmoBox.isEmpty());

        myAmmoBox.addAmmos(AmmoCube.BLUE,1);
        assertTrue(!myAmmoBox.isEmpty());
    }

    @Test
    public void testHasAmmo() {
        AmmoBox myAmmoBox = new AmmoBox();

        assertTrue(myAmmoBox.hasAmmo(AmmoCube.RED));
        assertTrue(myAmmoBox.hasAmmo(AmmoCube.YELLOW));
        assertTrue(myAmmoBox.hasAmmo(AmmoCube.BLUE));

        myAmmoBox.removeAmmos(AmmoCube.BLUE,1);
        assertTrue(!myAmmoBox.hasAmmo(AmmoCube.BLUE));

        myAmmoBox.removeAmmos(AmmoCube.YELLOW,1);
        assertTrue(!myAmmoBox.hasAmmo(AmmoCube.YELLOW));

        myAmmoBox.removeAmmos(AmmoCube.RED,1);
        assertTrue(!myAmmoBox.hasAmmo(AmmoCube.RED));
    }

    @Test
    public void testHasAmmos() {
        AmmoBox myAmmoBox = new AmmoBox();
        myAmmoBox.addAmmos(AmmoCube.BLUE,3);
        myAmmoBox.addAmmos(AmmoCube.RED,3);
        myAmmoBox.addAmmos(AmmoCube.YELLOW,3);

        AmmoCube[] list = new AmmoCube[4];
        list[0] = AmmoCube.BLUE;
        list[1] = AmmoCube.YELLOW;
        list[2] = AmmoCube.BLUE;
        list[3] = AmmoCube.RED;

        assertTrue(myAmmoBox.hasAmmos(list));

        AmmoCube[] list2 = new AmmoCube[6];
        list2[0] = AmmoCube.BLUE;
        list2[1] = AmmoCube.BLUE;
        list2[2] = AmmoCube.BLUE;
        list2[3] = AmmoCube.BLUE;
        list2[4] = AmmoCube.BLUE;
        list2[5] = AmmoCube.BLUE;

        assertTrue(!myAmmoBox.hasAmmos(list2));
    }

    @Test
    public void testGetCubes() {
        AmmoBox myAmmoBox = new AmmoBox();
        List<AmmoCube> list = myAmmoBox.getCubes();
        assertEquals(AmmoCube.RED,list.get(0));
        assertEquals(AmmoCube.BLUE,list.get(1));
        assertEquals(AmmoCube.YELLOW,list.get(2));
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
