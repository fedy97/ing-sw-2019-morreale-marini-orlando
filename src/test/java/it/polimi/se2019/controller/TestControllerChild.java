package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.fail;

/**
 * Super test class used to verify controller children's behaviour
 *
 * @author Gabriel Raul Marini
 */
public class TestControllerChild {
    protected Controller c;

    @Before
    public void initTest() throws IllegalAccessException, InvocationTargetException,NoSuchMethodException,InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        c = Controller.getInstance();
        c.setDebug(true);
        c.getTurnController().addUser("user1");
        c.getTurnController().addUser("user2");
        c.getTurnController().addUser("user3");

        c.setSecondsLeftMap(0);
        c.setSecondsLeftCharacter(0);


        Method method = Controller.class.getDeclaredMethod("findWhichMapWon");
        method.setAccessible(true);
        int configMap = (int) method.invoke(c);
        Method method2 = Controller.class.getDeclaredMethod("createAssets", int.class);
        method2.setAccessible(true);
        method2.invoke(c, configMap);
        Method method3 = Controller.class.getDeclaredMethod("findCharactersAvailableAndReplace");
        method3.setAccessible(true);
        method3.invoke(c);
        c.startGame();
        c.getTurnController().start();

    }

    @Test
    public void test1(){
        //this will print user1
//        HandyFunctions.printConsole(c.getTurnController().getTurnUser());
    }
}


