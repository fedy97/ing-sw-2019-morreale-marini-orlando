package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.model.card.Card;
import it.polimi.se2019.model.enumeration.AmmoCube;

/**
 * A class representing the PowerUp card of the game
 *
 * @author Gabriel Raul Marini
 */
public abstract class PowerUpCard extends Card {

    private AmmoCube value;


    /**
     *
     * Instantiate an anonymous PowerUp card
     * @param ammoCube the type of cube the player can use instead of using the
     *                 PowerUp effect
     */
    public PowerUpCard(AmmoCube ammoCube) {
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
    public abstract void useEffect();

}