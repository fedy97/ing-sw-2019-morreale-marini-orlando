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

/**
 * VortexCannon test
 *
 * @author Gabriel Raul Marini
 */
public class TestVortexCannon extends TestWeaponFather {
    private VortexCannon vortexCannon;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        vortexCannon = new VortexCannon("Vortex", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        vortexCannon.getEffects().get(0).setupTargets();
        currPlayer.setCurrentPlatform(game.getGameField().getPlatforms().get(3));
        Platform vortex = game.getGameField().getPlatforms().get(7);
        Player target = game.getPlayer(Character.BANSHEE);
        target.setCurrentPlatform(vortex);

        c.getChosenDestination().add(vortex);
        c.getCurrentTargets().add(Character.BANSHEE);

        vortexCannon.activateEffect(0, null);

        //verifying vortex damages effect
        //assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        //assertEquals(2, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        vortexCannon.getEffects().get(1).setupTargets();

        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);
        targets.add(Character.VIOLET);

        vortexCannon.activateEffect(1, targets);

        //verifying vortex damages effect
        assertTrue(game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertTrue(game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, game.getPlayer(Character.BANSHEE).getPlayerBoard().getDamageLine().size());
        assertEquals(1, game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().size());
    }

    @After
    public void finishTest() {
        super.finishTest();
        vortexCannon.reload();
    }
}
