package it.polimi.se2019.model.card;

import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.NoCardException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A generic class representing a deck of cards
 *
 * @author Gabriel Raul Marini
 */

public class Deck<Card> extends ArrayList<Card> {

    private final int initialSize;

    /**
     * Instantiate a deck with a maximum size allowed
     *
     * @param initSize maximum size allowed according to the type of deck
     */
    public Deck(int initSize) {
        super(initSize);
        initialSize = initSize;
    }

    /**
     * @return the top card of the deck
     */
    public Card drawCard() throws NoCardException {
        if (size() == 0)
            throw new NoCardException();
        return remove(size() - 1);
        //now here we have to notify the virtual view
    }

    /**
     * @param index specifies the index of the card returned
     * @return the card at the index specified
     * @throws ArrayIndexOutOfBoundsException when index is negative or exceed the size of the arraylist
     */
    public Card getCard(int index) {
        return remove(index);
    }

    /**
     * @param card element to be added to the deck
     * @throws InvalidDeckException thrown if trying to add a card to a full deck
     */
    public void addCard(Card card) throws InvalidDeckException {
        if (size() + 1 > initialSize)
            throw new InvalidDeckException("Deck is already full, you're doing something wrong!");
        this.add(card);
    }

    /**
     * @param cards a collection of cards assumed != null
     * @throws NullPointerException if cards are null
     * @throws InvalidDeckException if collection reference is null or has no elements
     */
    public void addCards(List<Card> cards) throws InvalidDeckException {
        if (cards.size() == 0 || (cards.size() + size()) > initialSize)
            throw new InvalidDeckException("Collection of cards cannot be added to the deck, check the maximum allowed " +
                    "size of the deck or if collection is null or empty!");
        addAll(cards);
    }

    /**
     * Mix the cards of the deck using the static method "shuffle" of Collections
     */
    public void mix() {
        Collections.shuffle(this);
    }

}