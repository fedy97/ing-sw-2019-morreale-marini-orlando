package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
        c.setDebug(true);
        c.getTurnController().addUser("user1");
        c.getTurnController().addUser("user2");
        c.getTurnController().addUser("user3");
        c.setVoteMapChosen(1);
        try {
            c.setCharacterChosen("user1", "DISTRUCTOR");
            c.setCharacterChosen("user2", "VIOLET");
            c.setCharacterChosen("user3", "BANSHEE");
        } catch (InvalidCharacterException ex) {
            CustomLogger.logException(this.getClass().getName(),ex);
        }


        c.setSecondsLeftLobby(0);
        c.setSecondsLeftMap(0);
        c.setSecondsLeftCharacter(0);

    }

    @Test
    public void test1() {
        //test
    }
}


