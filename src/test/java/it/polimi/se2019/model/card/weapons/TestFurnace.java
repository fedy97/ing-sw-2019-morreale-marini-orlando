package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestFurnace extends TestWeaponFather {
    private Furnace furnace;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        furnace = new Furnace("furnace", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
        for (Player player : game.getPlayers()) {
            player.setCurrentPlatform(game.getGameField().getPlatforms().get(2));
            player.getPlayerBoard().resetDamageLine();
        }
    }

    @Test
    public void testEffect1() {
        c.getChosenDestination().add(game.getGameField().getPlatforms().get(2));
        assertNull(furnace.getEffects().get(0).getPossibleTargets());
        furnace.activateEffect(0, null);
        for (Player player : game.getPlayers()) {
            if (player != currPlayer) {
                try {
                    player.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter());
                } catch (Exception e) {
                    fail();
                }
            }
        }
    }

    @Test
    public void testEffect2() {
        c.getChosenDestination().add(game.getGameField().getPlatforms().get(2));
        assertNull(furnace.getEffects().get(1).getPossibleTargets());
        furnace.activateEffect(1, null);
        for (Player player : game.getPlayers()) {
            if (player != currPlayer) {
                try {
                   player.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter());
                } catch (Exception e) {
                    fail();
                }
            }
        }
    }

    @After
    public void finishTest(){
        super.finishTest();
    }

}
