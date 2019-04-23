package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public final class Newton extends PowerUpCard {


    public Newton(AmmoCube ammoCube) {
        super(ammoCube);
    }

    @Override
    /**
     * Activate the effect of the card
     */
    public void useEffect(Controller c) {
        Player currPlayer = c.getPlayerManager().getCurrentPlayer();
        List<Orientation> dir = new ArrayList<>();
        dir.add(Orientation.ALL);
        Player target = c.askForTargets(c.getValidator().getValidTargets(this)).get(0);
        Platform destination =  c.askForPosition(c.getGame().getGameField().getPlatformDir(target.getCurrentPlatform(),
                dir));
        target.setCurrentPlatform(destination);
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