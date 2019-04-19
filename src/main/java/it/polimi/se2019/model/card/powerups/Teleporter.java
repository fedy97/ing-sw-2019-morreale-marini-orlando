package it.polimi.se2019.model.card.powerups;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public final class Teleporter extends PowerUpCard {


    public Teleporter(AmmoCube ammoCube) {
        super(ammoCube);
    }

    @Override
    /**
     * Activate the effect of the card
     */
    public void useEffect(Controller c) {
        //TODO
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