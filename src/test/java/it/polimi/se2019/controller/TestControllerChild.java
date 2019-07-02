package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.JsonParser;
import it.polimi.se2019.view.server.VirtualView;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        c.getGame().getCharacters(c.getGame().getPlayers());
        Deck deck = c.getGame().getGarbageDeck();
        c.getGame().saveServer();
        c.getGame().getPlayer("lol");
        Map<Character,Integer> map = new HashMap<>();
        map.put(Character.DISTRUCTOR, 1);
        c.getGame().setScore(map);
        c.getGame().getFinalScore();
    }

    @Test
    public void test2() {
        HandyFunctions.parserSettings.getMinimumPlayers();
        HandyFunctions.parserSettings.getSocketServerPort();
        HandyFunctions.parserSettings.getRmiServerPort();
        VirtualView virtualView = new VirtualView("user1");
        HandyFunctions.checkForAtLeast2Players(virtualView);
    }
}


