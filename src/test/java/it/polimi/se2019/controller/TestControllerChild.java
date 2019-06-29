package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Super test class used to verify controller children's behaviour
 *
 * @author Gabriel Raul Marini
 */
public class TestControllerChild {
    protected Controller c;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        c = Controller.getInstance();
        //da qui?
    }
}


