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

/**
 * LockRifle test
 *
 * @author Gabriel Raul Marini
 */
public class TestLockRifle extends TestWeaponFather {
    private LockRifle lockRifle;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        lockRifle = new LockRifle("lockrifle", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        lockRifle.getEffects().get(0).setupTargets();

        Player target = game.getPlayer(Character.BANSHEE);
        assertNotEquals(currPlayer.getCharacter(), Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(target.getCharacter());

        //verify if damages and marks are added to the target
        lockRifle.activateEffect(0, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, target.getPlayerBoard().getDamageLine().size());
        assertEquals(1, target.getPlayerBoard().getRevengeMarks().size());
        target.getPlayerBoard().resetDamageLine();
        target.getPlayerBoard().clearMarks();
    }

    @Test
    public void testEffect2() {
        lockRifle.getEffects().get(1).getLastEffectTargets().add(Character.BANSHEE);
        lockRifle.getEffects().get(1).setupTargets();

        Player target = game.getPlayer(Character.VIOLET);

        List<Character> targets = new ArrayList<>();
        targets.add(target.getCharacter());

        //verify if mark is added to the target
        lockRifle.activateEffect(1, targets);
        assertEquals(1, target.getPlayerBoard().getRevengeMarks().size());
        target.getPlayerBoard().clearMarks();
    }

    @After
    public void finishTest() {
        super.finishTest();
        lockRifle.reload();
    }
}
