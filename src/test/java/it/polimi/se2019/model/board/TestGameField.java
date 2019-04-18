package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameField {

    @Test
    public void testGetPlatform() throws InvalidCardException, InvalidDeckException, NoCardException,
            InvalidFieldException, InvalidAdjacentPlatformsException, InvalidRoomException, InvalidPositionException,
            NoSuchFieldException, IllegalAccessException {
        JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
        ArrayList<AmmoCard> ammocards = parserAmmos.buildAmmoCards();
        Deck<AmmoCard> deck = new Deck<>(36);
        deck.addCards(ammocards);
        deck.addCards(ammocards); //now we have 14 ammocards in the deck
        JsonParser parserField = new JsonParser("/json/field.json");
        Platform[][] field = parserField.buildField(1, deck);
        GameField gf = new GameField(field);
        Platform p = gf.getPlatform(gf.getField()[0][1].getPlatformPosition());
        assertEquals(p.isGenerationSpot(), true);
    }

}