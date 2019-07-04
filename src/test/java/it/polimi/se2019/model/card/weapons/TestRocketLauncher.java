package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * RocketLauncher test
 *
 * @author Gabriel Raul Marini
 */
public class TestRocketLauncher extends TestWeaponFather {
    private RocketLauncher rocketLauncher;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        rocketLauncher = new RocketLauncher("rocketLauncher", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        rocketLauncher.getEffects().get(0).setupTargets();
        Platform destination = game.getGameField().getPlatforms().get(7);
        Player target = game.getPlayer(Character.BANSHEE);
        target.setCurrentPlatform(game.getGameField().getPlatforms().get(6));
        c.getChosenBinaryOption().add(true);
        c.getChosenDestination().add(destination);


        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        //test damages of the first effect and verify if target was moved
        rocketLauncher.activateEffect(0, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, target.getPlayerBoard().getDamageLine().size());
        assertEquals(destination, target.getCurrentPlatform());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        rocketLauncher.getEffects().get(1).setupTargets();
        assertNull(rocketLauncher.getEffects().get(1).getPossibleTargets());
        Platform destination = game.getGameField().getPlatforms().get(3);
        c.getChosenDestination().add(destination);

        //Verify if the current player moved
        rocketLauncher.activateEffect(1, null);
        assertTrue(currPlayer.getCurrentPlatform().equals(destination));
    }

    @Test
    public void testEffect3() {
        rocketLauncher.getEffects().get(2).setupTargets();
        assertNull(rocketLauncher.getEffects().get(2).getPossibleTargets());
        rocketLauncher.getEffects().get(2).getLastEffectTargets().add(Character.BANSHEE);

        //test damages of additional effect
        rocketLauncher.activateEffect(2, null);
    }

    @After
    public void finishTest() {
        rocketLauncher.reload();
        super.finishTest();
    }
}
