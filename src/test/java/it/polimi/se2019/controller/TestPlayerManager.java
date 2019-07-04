package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.model.player.PlayerBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test class used to verify if operations on players work properly
 *
 * @author Gabriel Raul Marini
 */
public class TestPlayerManager extends TestInitializer {
    PlayerManager playerManager;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        playerManager = c.getPlayerManager();
        playerManager.getCurrentPlayer().removeWeapons();
        playerManager.getCurrentPlayer().removePowerUps();
        playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().clear();
        playerManager.getCurrentPlayer().getPlayerBoard().getDamageLine().clear();
        playerManager.getCurrentPlayer().getPlayerBoard().getRevengeMarks().clear();
    }

    /**
     * Verify if actions of the player are decreased and increased
     */
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

    /**
     * Verify if marks are added to the player
     */
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

    /**
     * Verify if player move
     */
    @Test
    public void testMove() {
        Platform destination = c.getGame().getGameField().getPlatforms().get(3);
        playerManager.move(destination);
        assertEquals(destination, playerManager.getCurrentPlayer().getCurrentPlatform());
    }


    /**
     * Verify if the grab operation is correctly done
     */
    @Test
    public void testGrabAmmoCard() {
        Platform destination = null;
        for (Platform p : c.getGame().getGameField().getPlatforms())
            if (p.hasAmmoCard())
                destination = p;

        playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().clear();

        try {
            playerManager.move(destination);
            AmmoCard card = destination.getPlatformAmmoCard();
            playerManager.grabAmmoCard();
            assertTrue(playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().hasAmmos(card.getAmmoCubes()));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Verify if damages are added properly
     */
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

    /**
     * Verify if weapon is added to the player and if ammos are scaled
     */
    @Test
    public void testBuyWeapon() {
        Platform destination = null;
        for (Platform p : c.getGame().getGameField().getPlatforms())
            if (p.isGenerationSpot())
                destination = p;

        try {
            playerManager.move(destination);
            playerManager.buyWeapon(destination.getWeapons().get(0));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Used to convert power up to ammo
     */
    @Test
    public void testConvertPowerUpToAmmo() {
        AmmoBox box = playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox();
        box.clear();
        PowerUpCard powerUpCard = c.getDecksManager().drawPowerUp();
        playerManager.getCurrentPlayer().addPowerUpCard(powerUpCard);
        try {
            assertTrue(box.isEmpty());
            playerManager.convertPowerUpToAmmo(powerUpCard);
            assertTrue(box.hasAmmo(powerUpCard.getAmmoCube()));
        } catch (Exception e) {
        }
    }

    /**
     * Verify if the reload operation works properly
     */
    @Test
    public void testReload() {
        AmmoBox box = playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox();
        box.clear();

        try {
            WeaponCard weaponCard = new WeaponCard("name", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{AmmoCube.BLUE, AmmoCube.YELLOW});
            for (AmmoCube cube : weaponCard.getTotalCost())
                box.addAmmos(cube, 1);
            weaponCard.discard();
            playerManager.reload(weaponCard);
            assertTrue(weaponCard.isLoaded());
        } catch (Exception e) {
            fail();
        }
    }

    @After
    public void finishTest() {
        playerManager.getCurrentPlayer().removePowerUps();
        playerManager.getCurrentPlayer().removeWeapons();
        playerManager.getCurrentPlayer().getPlayerBoard().getRevengeMarks().clear();
        playerManager.getCurrentPlayer().getPlayerBoard().getDamageLine().clear();
        playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().clear();
    }
}
