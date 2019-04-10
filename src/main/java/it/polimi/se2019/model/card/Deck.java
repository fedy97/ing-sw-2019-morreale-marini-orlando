package it.polimi.se2019.model.card;

import it.polimi.se2019.exceptions.InvalidDeckException;

import java.util.*;

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
     * @throws IllegalArgumentException initial size has to be >= 0
     */
    public Deck(int initSize) {
        super(initSize);
        initialSize = initSize;
    }

    /**
     * @return the top card of the deck
     */
    public Card drawCard() {
        return remove(size() - 1);
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
     * @throws NullPointerException if card reference is inconsistent with the definition adopted
     * @throws InvalidDeckException thrown if trying to add a card to a full deck
     */
    public void addCard(Card card) throws NullPointerException, InvalidDeckException {
        if (size() + 1 > initialSize)
            throw new InvalidDeckException("Deck is already full, you're doing something wrong!");
        if (card == null)
            throw new NullPointerException("Card cannot be null!");
        this.add(card);
    }

    /**
     * @param cards a collection of cards assumed != null
     * @throws NullPointerException if cards are null
     * @throws InvalidDeckException if collection reference is null or has no elements
     */
    public void addCards(ArrayList<Card> cards) throws NullPointerException, InvalidDeckException {
        if (cards == null || cards.size() == 0 || (cards.size() + size()) > initialSize)
            throw new InvalidDeckException("Collection of cards cannot be added to the deck, check the maximum allowed " +
                    "size of the deck or if collection is null or empty!");
        for (Card c : cards)
            if (c == null) throw new NullPointerException("Cards cannot be null!");
        addAll(cards);
    }

    /**
     * Mix the cards of the deck using the static method "shuffle" of Collections
     */
    public void mix() {
        Collections.shuffle(this);
    }
}