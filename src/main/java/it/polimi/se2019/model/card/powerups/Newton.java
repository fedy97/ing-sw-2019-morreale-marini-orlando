package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidImageException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Newton extends PowerUpCard {


    public Newton(AmmoCube ammoCube, String name, String description, Image img) throws InvalidImageException, InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);
    }

    @Override
    /**
     * Activate the effect of the card
     */
    public void useEffect() {
        Controller c = Controller.getInstance();
        c.getLock();
        c.askFor(c.getValidator().getValidTargets(this), "targets");
        c.waitForResponse();
        Player target = c.getCurrentTargets().get(0);
        c.askFor(c.getGame().getGameField().getPlatformDir(target.getCurrentPlatform()), "positionForOther");
        c.waitForResponse();
        c.releaseLock();
    }

    /**
     * Return if the player in his current state can use the power up or not
     */
    public boolean isUsable(Player player) {
        return true;
    }

    /**
     * @return a collection of players that can be the target
     */
    public List<Player> getPossibleTargets(Controller c) {
        //TODO
        return new ArrayList<>();
    }
}