package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestCyberblade extends TestWeaponFather {
    private Cyberblade cyberblade;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        cyberblade = new Cyberblade("machinegun", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        cyberblade.getEffects().get(0).setupTargets();
        assertNotEquals(currPlayer.getCharacter(), Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        cyberblade.activateEffect(0, targets);
        assertTrue(game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        game.getPlayer(Character.BANSHEE).getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        cyberblade.getEffects().get(1).setupTargets();
        assertNull(cyberblade.getEffects().get(1).getPossibleTargets());
        Platform destination = game.getGameField().getPlatforms().get(3);
        c.getChosenDestination().add(destination);

        cyberblade.activateEffect(1, null);
        assertTrue(currPlayer.getCurrentPlatform().equals(destination));
    }

    @Test
    public void testEffect3() {
        cyberblade.getEffects().get(2).setupTargets();

        List<Character> targets = new ArrayList<>();
        targets.add(Character.VIOLET);

        cyberblade.activateEffect(2, targets);
        assertTrue(game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().size());
    }

    @After
    public void finishTest() {
        cyberblade.reload();
        super.finishTest();
    }
}
