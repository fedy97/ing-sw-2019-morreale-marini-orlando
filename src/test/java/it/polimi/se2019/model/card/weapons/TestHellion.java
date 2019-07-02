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

public class TestHellion extends TestWeaponFather {
    private Hellion hellion;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        hellion = new Hellion("hellion", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        hellion.getEffects().get(0).setupTargets();
        Player target = game.getPlayer(Character.BANSHEE);
        target.setCurrentPlatform(currPlayer.getCurrentPlatform());
        assertNotEquals(currPlayer.getCharacter(), Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(target.getCharacter());

        hellion.activateEffect(0, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        hellion.getEffects().get(1).setupTargets();
        Player target = game.getPlayer(Character.VIOLET);
        target.setCurrentPlatform(currPlayer.getCurrentPlatform());
        assertNotEquals(currPlayer.getCharacter(), Character.VIOLET);

        List<Character> targets = new ArrayList<>();
        targets.add(target.getCharacter());

        hellion.activateEffect(1, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @After
    public void finishTest() {
        super.finishTest();
    }
}
