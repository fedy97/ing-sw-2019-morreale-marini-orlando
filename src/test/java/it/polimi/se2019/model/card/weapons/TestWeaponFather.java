package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.controller.TestControllerChild;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.player.Player;
import org.junit.Before;

import java.io.IOException;

public class TestWeaponFather extends TestControllerChild {
    protected Game game;
    protected Player currPlayer;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        currPlayer = c.getPlayerManager().getCurrentPlayer();
        game = Game.getInstance();
    }

    protected void finishTest() {
        try {
            for (Player player : game.getPlayers()) {
                player.getPlayerBoard().resetDamageLine();
                player.getPlayerBoard().clearMarks();
                player.setCurrentPlatform(game.getGameField().getPlatform(new int[]{0, 0}));
            }
        } catch (Exception e) {
        }
    }
}
