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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestZX2 extends TestWeaponFather {
    private ZX2 zx2;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        zx2 = new ZX2("zx2", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        zx2.getEffects().get(0).setupTargets();
        Player target = game.getPlayer(Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        zx2.activateEffect(0, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        target.getPlayerBoard().getDamageLine().size();
        target.getPlayerBoard().getRevengeMarks().size();
        target.getPlayerBoard().resetDamageLine();
        target.getPlayerBoard().clearMarks();
    }

    @Test
    public void testEffect2() {
        zx2.getEffects().get(1).setupTargets();
        Player target1 = game.getPlayer(Character.VIOLET);
        Player target2 = game.getPlayer(Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.VIOLET);
        targets.add(Character.BANSHEE);

        zx2.activateEffect(1, targets);
        assertTrue(target1.getPlayerBoard().getRevengeMarks().contains(currPlayer.getCharacter()));
        assertTrue(target2.getPlayerBoard().getRevengeMarks().contains(currPlayer.getCharacter()));
        assertEquals(1, target1.getPlayerBoard().getRevengeMarks().size());
        assertEquals(1, target2.getPlayerBoard().getRevengeMarks().size());
    }

    @After
    public void finishTest() {
        zx2.reload();
        super.finishTest();
    }
}
