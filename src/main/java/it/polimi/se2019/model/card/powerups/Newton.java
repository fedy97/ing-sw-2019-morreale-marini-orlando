package it.polimi.se2019.model.card.powerups;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;

public final class Newton extends PowerUpCard {


    public Newton(AmmoCube ammoCube) {
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
        //TODO
        return false;
    }
}