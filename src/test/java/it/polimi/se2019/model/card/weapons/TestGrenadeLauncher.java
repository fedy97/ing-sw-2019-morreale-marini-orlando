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

public class TestGrenadeLauncher extends TestWeaponFather {
    private GrenadeLauncher grenadeLauncher;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        grenadeLauncher = new GrenadeLauncher("grenadeLauncher", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        grenadeLauncher.getEffects().get(0).setupTargets();
        Player target = game.getPlayer(Character.BANSHEE);
        Platform destination = game.getGameField().getPlatforms().get(5);
        target.setCurrentPlatform(game.getGameField().getPlatforms().get(4));

        c.getChosenBinaryOption().add(true);
        c.getChosenDestination().add(destination);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);
        grenadeLauncher.activateEffect(0, targets);
        assertEquals(target.getCurrentPlatform(), destination);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        grenadeLauncher.getEffects().get(1).setupTargets();
        assertNull(grenadeLauncher.getEffects().get(1).getPossibleTargets());
        Platform toBurn = game.getGameField().getPlatforms().get(3);
        c.getChosenDestination().add(toBurn);

        for (Player target : game.getPlayers())
            target.setCurrentPlatform(toBurn);

        grenadeLauncher.activateEffect(1, null);
        assertTrue(currPlayer.getPlayerBoard().getDamageLine().isEmpty());
    }

    @After
    public void finishTest() {
        super.finishTest();
        grenadeLauncher.reload();
    }
}
