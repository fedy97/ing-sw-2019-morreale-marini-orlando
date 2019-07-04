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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Whisper test
 *
 * @author Gabriel Raul Marini
 */
public class TestWhisper extends TestWeaponFather {
    private Whisper whisper;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        whisper = new Whisper("whisper", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
    }

    @Test
    public void testEffect1() {
        whisper.getEffects().get(0).setupTargets();
        List<Character> violet = new ArrayList<>();
        violet.add(Character.VIOLET);
        game.getPlayer(Character.VIOLET).getPlayerBoard().resetDamageLine();
        whisper.activateEffect(0, violet);

        //testing if damages and marks are added
        assertEquals(3, game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().size());
        assertEquals(1, game.getPlayer(Character.VIOLET).getPlayerBoard().getRevengeMarks().size());
        assertTrue(game.getPlayer(Character.VIOLET).getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter()));
    }

    @After
    public void finishTest(){
        super.finishTest();
        whisper.reload();
    }
}
