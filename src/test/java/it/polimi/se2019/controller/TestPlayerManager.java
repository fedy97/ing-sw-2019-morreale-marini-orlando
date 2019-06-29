package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.model.player.PlayerBoard;
import it.polimi.se2019.utils.HandyFunctions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPlayerManager extends TestControllerChild {
    PlayerManager playerManager;

    @Before
    public void initTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        playerManager = c.getPlayerManager();
    }

    @Test
    public void testActionsLeft() {
        assertEquals(2, playerManager.getActionsLeft());
        playerManager.useAction();
        assertEquals(1, playerManager.getActionsLeft());
        playerManager.resetActionLeft();
        assertEquals(2, playerManager.getActionsLeft());
        playerManager.clearActionLeft();
        assertEquals(0, playerManager.getActionsLeft());
    }

    @Test
    public void testMark() {
        playerManager.mark(c.getGame().getPlayer("user2"), 3);
        PlayerBoard board = c.getGame().getPlayer("user2").getPlayerBoard();
        assertEquals(3, board.getRevengeMarks().size());

        for (Character c : board.getRevengeMarks()) {
            assertEquals(c, playerManager.getCurrentPlayer().getCharacter());
        }

        board.getRevengeMarks().clear();
    }

    @Test
    public void testMove() {
        Platform destination = c.getGame().getGameField().getPlatforms().get(0);
        playerManager.move(destination);
        assertEquals(destination, playerManager.getCurrentPlayer().getCurrentPlatform());
    }


    @Test
    public void testGrabAmmoCard() {
        Platform destination = null;
        for (Platform p : c.getGame().getGameField().getPlatforms())
            if (p.hasAmmoCard())
                destination = p;
        playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().clear();

        try {
            AmmoCard card = destination.grabAmmoCard();
            playerManager.move(destination);
            playerManager.grabAmmoCard();
            assertTrue(playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().hasAmmos(card.getAmmoCubes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAddDamage() {
        Map<Player, Integer> damages = new HashMap<>();
        damages.put(c.getGame().getPlayer("user2"), 2);
        playerManager.addDamage(damages);

        PlayerBoard damagedBoard = c.getGame().getPlayer("user2").getPlayerBoard();
        assertEquals(2, damagedBoard.getDamageLine().size());

        for (Character character : damagedBoard.getDamageLine())
            assertEquals(playerManager.getCurrentPlayer().getCharacter(), character);
    }

    @After
    public void finshTest() {

    }
}
