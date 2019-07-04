package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * PowerGlove test
 *
 * @author Gabriel Raul Marini
 */
public class TestPowerGlove extends TestWeaponFather {
    private PowerGlove powerGlove;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        powerGlove = new PowerGlove("powerGlove", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        powerGlove.getEffects().get(0).setupTargets();
        Player target = game.getPlayer(Character.BANSHEE);
        target.setCurrentPlatform(game.getGameField().getPlatforms().get(6));

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        powerGlove.activateEffect(0, targets);

        //verify if damages and marks are added to the targets
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertTrue(target.getPlayerBoard().getRevengeMarks().contains(currPlayer.getCharacter()));
        assertEquals(1, target.getPlayerBoard().getDamageLine().size());
        assertEquals(2, target.getPlayerBoard().getRevengeMarks().size());
        assertEquals(currPlayer.getCurrentPlatform(), target.getCurrentPlatform());
        target.getPlayerBoard().resetDamageLine();
        target.getPlayerBoard().clearMarks();
    }

    @Test
    public void testEffect2() {
        powerGlove.getEffects().get(1).setupTargets();
        Player target = game.getPlayer(Character.BANSHEE);
        target.setCurrentPlatform(game.getGameField().getPlatforms().get(6));

        c.getChosenBinaryOption().add(true);
        c.getCurrentTargets().add(Character.VIOLET);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        powerGlove.activateEffect(1, targets);

        //verify additional damage
        assertEquals(2, target.getPlayerBoard().getDamageLine().size());
    }

    @After
    public void finishTest() {
        super.finishTest();
        powerGlove.reload();
    }
}
