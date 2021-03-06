package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Thor test
 *
 * @author Gabriel Raul Marini
 */
public class TestThor extends TestWeaponFather {
    private THOR thor;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        thor = new THOR("thor", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
        currPlayer.setCurrentPlatform(game.getGameField().getPlatforms().get(3));
    }

    @Test
    public void testEffect1() {
        thor.getEffects().get(0).setupTargets();
        assertNotEquals(currPlayer.getCharacter(), Character.BANSHEE);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        //testing the first effect damages
        thor.activateEffect(0, targets);
        assertTrue(game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        game.getPlayer(Character.BANSHEE).getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        game.getPlayer(Character.BANSHEE).setCurrentPlatform(game.getGameField().getPlatforms().get(7));
        thor.getEffects().get(1).getLastEffectTargets().add(Character.BANSHEE);
        thor.getEffects().get(1).setupTargets();

        List<Character> targets = new ArrayList<>();
        targets.add(Character.VIOLET);

        //testing the additional damages of the second effect
        thor.activateEffect(1, targets);
        assertTrue(game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().size());
        game.getPlayer(Character.VIOLET).getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect3() {
        game.getPlayer(Character.BANSHEE).setCurrentPlatform(game.getGameField().getPlatforms().get(7));
        thor.getEffects().get(2).getLastEffectTargets().add(Character.VIOLET);
        thor.getEffects().get(2).getLastEffectTargets().add(Character.BANSHEE);
        thor.getEffects().get(2).setupTargets();

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        //testing the additional damages of the third effect
        thor.activateEffect(2, targets);
        assertTrue(game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        game.getPlayer(Character.BANSHEE).getPlayerBoard().resetDamageLine();
    }

    @After
    public void finishTest() {
        thor.reload();
        super.finishTest();
    }
}
