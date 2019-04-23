package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameField {
    GameField gf;

    @Before
    public void initTest() throws InvalidCardException, InvalidDeckException, NoCardException,
            InvalidFieldException, InvalidAdjacentPlatformsException, InvalidRoomException, InvalidPositionException,
            NoSuchFieldException, IllegalAccessException, InvalidQuantityException, InvalidGenerationSpotException {

        JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
        Deck<AmmoCard> deck = parserAmmos.buildAmmoCards();
        //now we have 36 ammocards in the deck
        deck.mix();
        JsonParser parserField = new JsonParser("/json/field.json");
        Platform[][] field = parserField.buildField(1, deck);
        WeaponCard[] weaponCards = new WeaponCard[9];
        for (int i = 0; i < 9; i++)
            weaponCards[i] = new WeaponCard();
        gf = new GameField(field, weaponCards, new SkullsBoard(8), new ScoreBoard());
    }

    @Test
    public void testGetPlatform() throws InvalidPositionException {
        Platform p = gf.getPlatform(gf.getField()[0][1].getPlatformPosition());
        assertEquals(p.isGenerationSpot(), true);
    }

    @Test
    public void testGetAvailablePlatforms() throws InvalidPositionException {

        ArrayList<Platform> list = gf.getAvailablePlatforms(gf.getPlatform(new int[]{2, 2}), 2);
        /*for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + list.get(i).getPlatformPosition()[0] + " " + list.get(i).getPlatformPosition()[1] + "]");
        }*/
    }

}