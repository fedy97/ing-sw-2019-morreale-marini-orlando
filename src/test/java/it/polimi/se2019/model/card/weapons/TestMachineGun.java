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

public class TestMachineGun extends TestWeaponFather {
    private MachineGun machineGun;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        machineGun = new MachineGun("machinegun", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        machineGun.getEffects().get(0).setupTargets();
        assertNotEquals(currPlayer.getCharacter(), Character.BANSHEE);
        assertNotEquals(currPlayer.getCharacter(), Character.VIOLET);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);
        targets.add(Character.VIOLET);

        machineGun.activateEffect(0, targets);

        assertTrue(game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertTrue(game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        assertEquals(1, game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().size());
        game.getPlayer(Character.BANSHEE).getPlayerBoard().resetDamageLine();
        game.getPlayer(Character.VIOLET).getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
       machineGun.getEffects().get(1).getLastEffectTargets().add(Character.BANSHEE);
        machineGun.getEffects().get(1).setupTargets();

        Player target = game.getPlayer(Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(target.getCharacter());

        machineGun.activateEffect(1, targets);
        assertEquals(1, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect3() {
        machineGun.getEffects().get(2).getLastEffectTargets().add(Character.VIOLET);
        machineGun.getEffects().get(2).setupTargets();

        Player target = game.getPlayer(Character.VIOLET);

        List<Character> targets = new ArrayList<>();
        targets.add(target.getCharacter());

        machineGun.activateEffect(2, targets);
        assertEquals(1, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @After
    public void finishTest() {
        super.finishTest();
        machineGun.reload();
    }
}
