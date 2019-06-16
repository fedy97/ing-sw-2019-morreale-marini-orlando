package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.List;

public final class Newton extends PowerUpCard {


    public Newton(AmmoCube ammoCube, String name, String description, String img) throws InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);
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
    @Override
    public List<Character> getPossibleTargets() {
        List<Character> targets = new ArrayList<>();
        for (Player player : c.getGame().getPlayers())
            targets.add(player.getCharacter());
        return targets;
    }

    @Override
    public void activate(Character target) {
        Player playerTarget = game.getPlayer(target);
        c.sendMessage("Where do you want to move your target?", c.getPlayerManager().getCurrentPlayer().getName());
        c.askFor(game.getGameField().getPlatformDir(playerTarget.getCurrentPlatform()), "position");

        try {
            playerTarget.setCurrentPlatform(c.getChosenDestination().take());
            c.broadcastMessage(c.getPlayerManager().getCurrentPlayer().getName() + " moved " + playerTarget.getName());
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }
}