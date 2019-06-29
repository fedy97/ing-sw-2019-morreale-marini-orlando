package it.polimi.se2019.controller;

import it.polimi.se2019.controller.validator.Validator;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.powerups.TagbackGrenade;
import it.polimi.se2019.model.card.powerups.TargettingScope;
import it.polimi.se2019.model.card.powerups.Teleporter;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class used to verify if the DecksManager works properly
 *
 * @author Gabriel Raul Marini
 */
public class TestValidator extends TestControllerChild {
    private Validator validator;
    private Player currPlayer;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        validator = c.getValidator();
        currPlayer = c.getPlayerManager().getCurrentPlayer();
        try {
            c.getPlayerManager().move(c.getGame().getGameField().getPlatforms().get(3));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getGrabaleWeapons() {
        try {
            List<WeaponCard> grabable = validator.getGrabableWeapons();
            assertEquals(0, grabable.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getReloadableWeapons() {
        assertTrue(currPlayer.getWeaponCards().isEmpty());
        AmmoBox box = currPlayer.getPlayerBoard().getAmmoBox();
        box.clear();

        try {
            WeaponCard weaponCard1 = new WeaponCard("name1", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{AmmoCube.YELLOW});
            WeaponCard weaponCard2 = new WeaponCard("name2", "desc", "img", AmmoCube.RED, new AmmoCube[]{AmmoCube.BLUE});
            WeaponCard weaponCard3 = new WeaponCard("name3", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{AmmoCube.BLUE, AmmoCube.YELLOW});
            weaponCard2.discard();
            weaponCard3.discard();

            currPlayer.addWeaponCard(weaponCard1);
            currPlayer.addWeaponCard(weaponCard2);
            currPlayer.addWeaponCard(weaponCard3);

            box.addAmmos(weaponCard1.getTotalCost());
            box.addAmmos(weaponCard3.getTotalCost());

            List<WeaponCard> res = validator.getReloadableWeapons();
            assertEquals(1, res.size());
            assertTrue(res.contains(weaponCard3));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getUsablePowerUps() {
        currPlayer.getPowerUpCards().clear();

        try {
            PowerUpCard p1 = new TagbackGrenade(AmmoCube.BLUE,"granata venom", "desc", "img");
            PowerUpCard p2 = new Teleporter(AmmoCube.BLUE,"teletrasporto", "desc", "img");
            PowerUpCard p3 = new TargettingScope(AmmoCube.BLUE,"raggio cinetico", "desc", "img");


            currPlayer.addPowerUpCard(p1);
            currPlayer.addPowerUpCard(p2);
            currPlayer.addPowerUpCard(p3);

            List<PowerUpCard> res = validator.getUsablePowerUps();
            assertEquals(2, res.size());;
        } catch (Exception e) {
            fail();
        }
    }

    @After
    public void finisTest(){
        currPlayer.getWeaponCards().clear();
        currPlayer.getPowerUpCards().clear();
    }
}
