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

import static org.junit.Assert.*;

public class TestShockWave extends TestWeaponFather {
    private Shockwave shockwave;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        shockwave = new Shockwave("shockwave", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        shockwave.getEffects().get(0).setupTargets();
        Player target1 = game.getPlayer(Character.BANSHEE);
        Player target2 = game.getPlayer(Character.VIOLET);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);
        targets.add(Character.VIOLET);

        target1.setCurrentPlatform(game.getGameField().getPlatforms().get(6));
        target2.setCurrentPlatform(game.getGameField().getPlatforms().get(7));

        shockwave.activateEffect(0, targets);

        assertTrue(target1.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, target1.getPlayerBoard().getDamageLine().size());
        assertTrue(target2.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, target2.getPlayerBoard().getDamageLine().size());
        target1.getPlayerBoard().resetDamageLine();
        target1.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        shockwave.getEffects().get(1).setupTargets();
        try {
            currPlayer.setCurrentPlatform(game.getGameField().getPlatform(new int[]{2, 3}));
            for (Player player : game.getPlayers())
                if (player != currPlayer)
                    player.setCurrentPlatform(game.getGameField().getPlatform(new int[]{2, 2}));
        } catch (Exception e) {
            fail();
        }

        shockwave.activateEffect(1, null);
    }

    @After
    public void finishTest() {
        super.finishTest();
        shockwave.reload();
    }
}
