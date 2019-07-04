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
 * Sledgehammer test
 *
 * @author Gabriel Raul Marini
 */
public class TestSledgeHammer extends TestWeaponFather {
    private Sledgehammer sledgehammer;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        sledgehammer = new Sledgehammer("sledgehammer", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        sledgehammer.getEffects().get(0).setupTargets();
        Player target = game.getPlayer(Character.BANSHEE);


        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        //testing damages of th effect
        sledgehammer.activateEffect(0, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(2, target.getPlayerBoard().getDamageLine().size());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        sledgehammer.getEffects().get(1).setupTargets();
        Platform destination = game.getGameField().getPlatforms().get(7);
        Player target = game.getPlayer(Character.BANSHEE);
        target.setCurrentPlatform(game.getGameField().getPlatforms().get(6));
        c.getChosenBinaryOption().add(true);
        c.getChosenDestination().add(destination);


        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        //testing if target was damaged and moved
        sledgehammer.activateEffect(1, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(3, target.getPlayerBoard().getDamageLine().size());
        assertEquals(destination, target.getCurrentPlatform());
    }

    @After
    public void finishTest() {
        sledgehammer.reload();
        super.finishTest();
    }
}
