package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidImageException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;

import java.awt.*;
import java.util.List;

/**
 * A class representing the PowerUp card of the game
 *
 * @author Gabriel Raul Marini
 */
public abstract class PowerUpCard extends Card {

    private AmmoCube value;

    /**
     * Instantiate an anonymous PowerUp card
     *
     * @param ammoCube the type of cube the player can use instead of using the
     *                 PowerUp effect
     */
    public PowerUpCard(AmmoCube ammoCube, String name, String description, Image img) throws InvalidImageException, InvalidNameException, InvalidCubeException {
        super(name, description, img);
        if (ammoCube == null)
            throw new InvalidCubeException("ammocube cannot be null");
        this.value = ammoCube;
    }


    /**
     * @return the AmmoCube value the player can take instead of using the effect
     * of the PowerUp card
     */
    public AmmoCube getAmmoCube() {
        return value;
    }

    /**
     * Activate the effect of the card
     */
    public abstract void useEffect(Controller c);

    /**
     * Return if the player in his current state can use the power up or not
     */
    public abstract boolean isUsable(Player player);

    /**
     * @return a collection of players that can be the target
     */
    public abstract List<Player> getPossibleTargets(Controller c);

}