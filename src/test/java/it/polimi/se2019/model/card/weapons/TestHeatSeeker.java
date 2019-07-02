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

public class TestHeatSeeker extends TestWeaponFather {
    private HeatSeeker heatSeeker;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        heatSeeker = new HeatSeeker("heatseeker", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
        for (Player player : game.getPlayers()) {
            player.setCurrentPlatform(game.getGameField().getPlatforms().get(2));
            player.getPlayerBoard().resetDamageLine();
        }
    }

    @Test
    public void testEffect1() {
        heatSeeker.getEffects().get(0).setupTargets();
        assertTrue(heatSeeker.getEffects().get(0).getPossibleTargets().isEmpty());
        List<Character> violet = new ArrayList<>();
        violet.add(Character.VIOLET);
        heatSeeker.activateEffect(0, violet);
        assertTrue(game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
    }

    @After
    public void finishTest(){
        super.finishTest();
    }
}
