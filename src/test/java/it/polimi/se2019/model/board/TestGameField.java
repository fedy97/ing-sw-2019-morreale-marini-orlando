package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameField {
    GameField gf;

    @Before
    public void initTest()throws InvalidCardException, InvalidDeckException, NoCardException,
            InvalidFieldException, InvalidAdjacentPlatformsException, InvalidRoomException, InvalidPositionException,
            NoSuchFieldException, IllegalAccessException {

            JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
            Deck<AmmoCard> deck = parserAmmos.buildAmmoCards();
            //now we have 36 ammocards in the deck
            JsonParser parserField = new JsonParser("/json/field.json");
            Platform[][] field = parserField.buildField(1, deck);
            gf = new GameField(field);
    }


    @Test
    public void testGetPlatform() throws InvalidPositionException {
        Platform p = gf.getPlatform(gf.getField()[0][1].getPlatformPosition());
        assertEquals(p.isGenerationSpot(), true);
    }

    @Test
    public void testGetAvailablePlatforms() throws InvalidPositionException {

        ArrayList<Platform> list = gf.getAvailablePlatforms(gf.getPlatform(new int[]{0,1}), 2);
        /*for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + list.get(i).getPlatformPosition()[0] + " " + list.get(i).getPlatformPosition()[1] + "]");
        }*/
    }

}