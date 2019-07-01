package it.polimi.se2019.model.card.powerups;


import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.utils.CustomLogger;

import java.util.List;

public final class Teleporter extends PowerUpCard {


    public Teleporter(AmmoCube ammoCube, String name, String description, String img) throws InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);
    }

    /**
     * @return a collection of players that can be the target
     */
    @Override
    public List<Character> getPossibleTargets() {
        return null;
    }

    @Override
    public void activate(Character target) {
        c.sendMessage("Where do you want to teleport?", c.getPlayerManager().getCurrentPlayer().getName());
        c.askFor(game.getGameField().getPlatforms(), "position");

        try {
            c.getPlayerManager().getCurrentPlayer().setCurrentPlatform(c.getChosenDestination().take());
            c.broadcastMessage(c.getPlayerManager().getCurrentPlayer().getName() + " teleported!");
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

}