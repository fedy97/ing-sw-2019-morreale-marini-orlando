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
 * RailGun test
 *
 * @author Gabriel Raul Marini
 */
public class TestRailGun extends TestWeaponFather {
    private RailGun railGun;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        railGun = new RailGun("railgun", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        railGun.getEffects().get(0).setupTargets();
        Player target = game.getPlayer(Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        railGun.activateEffect(0, targets);

        //test damages of the first effect
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(3, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        railGun.getEffects().get(1).setupTargets();

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);
        targets.add(Character.VIOLET);

        railGun.activateEffect(1, targets);

        //test damages for the targets of the second effect
        assertTrue(game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertTrue(game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        assertEquals(2, game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().size());
    }

    @After
    public void finishTest() {
        super.finishTest();
        railGun.reload();
    }
}
