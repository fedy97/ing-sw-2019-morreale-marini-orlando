package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TestGrenadeLauncher extends TestWeaponFather {
    private GrenadeLauncher grenadeLauncher;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        grenadeLauncher = new GrenadeLauncher("grenadeLauncher", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {

    }

    @Test
    public void testEffect2() {

    }

    @After
    public void finishTest(){
        super.finishTest();
    }
}
