package it.polimi.se2019.model.card;

import java.util.*;

/**
 * A generic class representing a deck of cards
 *
 * @author Gabriel Raul Marini
 */

public class Deck {

    private ArrayList<Card> elements;

    /**
     * Instantiate an empty deck
     */
    public Deck() {
        elements = new ArrayList<Card>();
    }

    /**
     * @return a random card from a generic deck
     */
    public Card drawCard() {
        // TODO
        return null;
    }

    /**
     * @param index specifies the index of the card returned
     * @return the card at the index specified
     * @throws ArrayIndexOutOfBoundsException when index is negative or exceed the size of the arraylist
     */
    public Card getCard(int index) throws ArrayIndexOutOfBoundsException {
        return elements.get(index);
    }

    /**
     * @return an ArrayList containing all the cards of the deck
     */
    public ArrayList<Card> getElements() {
        ArrayList<Card> cardsCopy = new ArrayList<Card>();
        cardsCopy.addAll(elements);
        return cardsCopy;
    }

}