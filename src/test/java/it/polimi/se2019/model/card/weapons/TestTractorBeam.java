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

public class TestTractorBeam extends TestWeaponFather {
    private TractorBeam tractorBeam;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        tractorBeam = new TractorBeam("tractorbeam", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        tractorBeam.getEffects().get(0).setupTargets();
        Platform destination = game.getGameField().getPlatforms().get(7);
        Player target = game.getPlayer(Character.BANSHEE);
        target.setCurrentPlatform(game.getGameField().getPlatforms().get(6));
        c.getChosenDestination().add(destination);


        List<Character> targets = new ArrayList<>();
        targets.add(Character.BANSHEE);

        tractorBeam.activateEffect(0, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(1, target.getPlayerBoard().getDamageLine().size());
        assertEquals(destination, target.getCurrentPlatform());
        target.getPlayerBoard().resetDamageLine();
    }

    @Test
    public void testEffect2() {
        tractorBeam.getEffects().get(1).setupTargets();
        Player target = game.getPlayer(Character.VIOLET);

        List<Character> targets = new ArrayList<>();
        targets.add(Character.VIOLET);

        tractorBeam.activateEffect(1, targets);
        assertTrue(target.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
        assertEquals(3, target.getPlayerBoard().getDamageLine().size());
        assertEquals(currPlayer.getCurrentPlatform(), target.getCurrentPlatform());
        target.getPlayerBoard().resetDamageLine();
    }

    @After
    public void finishTest() {
        tractorBeam.reload();
        super.finishTest();
    }
}
