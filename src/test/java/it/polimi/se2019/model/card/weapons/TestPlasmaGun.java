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

public class TestPlasmaGun extends TestWeaponFather {
    private PlasmaGun plasmaGun;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        plasmaGun = new PlasmaGun("machinegun", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        plasmaGun.getEffects().get(0).setupTargets();
        assertNotEquals(currPlayer.getCharacter(), Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        plasmaGun.activateEffect(0, targets);
        assertTrue(game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        game.getPlayer(Character.BANSHEE).getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        plasmaGun.getEffects().get(1).setupTargets();
        assertNull(plasmaGun.getEffects().get(1).getPossibleTargets());
        Platform destination = game.getGameField().getPlatforms().get(3);
        c.getChosenDestination().add(destination);

        plasmaGun.activateEffect(1, null);
        assertTrue(currPlayer.getCurrentPlatform().equals(destination));
    }

    @Test
    public void testEffect3() {
        plasmaGun.getEffects().get(2).setupTargets();
        assertNull(plasmaGun.getEffects().get(2).getPossibleTargets());
        plasmaGun.getEffects().get(2).getLastEffectTargets().add(Character.BANSHEE);

        plasmaGun.activateEffect(2, null);
        assertEquals(1, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        game.getPlayer(Character.BANSHEE).getPlayerBoard().resetDamageLine();
    }

    @After
    public void finishTest() {
        plasmaGun.reload();
        super.finishTest();
    }
}
