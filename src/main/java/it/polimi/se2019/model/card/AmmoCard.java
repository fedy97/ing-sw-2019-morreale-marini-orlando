package it.polimi.se2019.model.card;

import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.model.enumeration.AmmoCube;

import java.io.Serializable;


/**
 * A class representing an AmmoCard the player can use to grab cubes and PowerUp cards
 */
public final class AmmoCard extends Card implements Serializable {

    private AmmoCube[] ammoCubes;
    private boolean hasPowerUp;

    /**
     * Instantiate an Ammocard with two cubes and one power up or three cubes
     *
     * @param cubes      the number of cubes the player can grab
     * @param hasPowerUp true if the player can draw a PowerUp card, false otherwise
     */
    public AmmoCard(AmmoCube[] cubes, boolean hasPowerUp, String img) throws InvalidCardException {
        super(img);
        this.hasPowerUp = hasPowerUp;
        if ((cubes.length != 3 && !hasPowerUp) || (hasPowerUp && cubes.length != 2))
            throw new InvalidCardException("Cannot create this type of AmmoCard, please check the rules!");

        ammoCubes = cubes;
    }

    /**
     * @return an array containing the cubes the player can grab
     */
    public AmmoCube[] getAmmoCubes() {
        return ammoCubes;
    }

    /**
     * @return true if the card permit to draw a power up, false otherwise
     */
    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    //TODO test
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (AmmoCube ammoCube : ammoCubes) {
            if (ammoCube == AmmoCube.RED)
                result.append("r");
            else if (ammoCube == AmmoCube.BLUE)
                result.append("b");
            else
                result.append("y");

        }
        return result.toString();
    }

}
