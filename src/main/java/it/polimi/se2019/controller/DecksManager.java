package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * A class that manages the refill and the draw operations on the deck
 *
 * @author Gabriel Raul Marini
 */
public class DecksManager{

    private Deck<PowerUpCard> powerUps;
    private List<PowerUpCard> powerUpGarbageDeck;
    private List<AmmoCard> ammoGarbageDeck;
    private Deck<AmmoCard> ammoDeck;
    private List<Platform> toFill;

    public DecksManager(Deck<PowerUpCard> powerUps, Deck<AmmoCard> ammoDeck) {
        this.powerUps = powerUps;
        this.ammoDeck = ammoDeck;
        powerUpGarbageDeck = new ArrayList<>();
        ammoGarbageDeck = new ArrayList<>();
        toFill = new ArrayList<>();
    }

    /**
     * Draw a card from the PowerUp deck and refill it in case it's empty
     *
     * @return a PowerUpCard from the deck
     */
    public PowerUpCard drawPowerUp() {
        PowerUpCard powerUp;
        try {
            powerUp = powerUps.drawCard();
        } catch (NoCardException e) {
            refillDeck();
            powerUp = powerUps.remove(powerUps.size() - 1);
        }
        return powerUp;
    }

    /**
     * This method is called when a player pick an AmmoCard from a Platform in order
     * to put a new one on the board
     *
     * @param oldAmmoCard picked by the player on the platform
     * @throws NoCardException if previous operations were wrong
     */
    public AmmoCard getNewAmmoCard(AmmoCard oldAmmoCard) throws NoCardException {
        ammoDeck.add(oldAmmoCard);
        ammoDeck.mix();
        return ammoDeck.drawCard();
    }

    /**
     * Check the deck status before drawing a card and refill the PowerUp deck with new
     * cards id necessary
     */
    private void refillDeck() {
        try {
            powerUps.addCards(powerUpGarbageDeck);
            powerUpGarbageDeck.clear();
            powerUps.mix();
        } catch (InvalidDeckException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, "Something went wrong when refilling the deck");
        }
    }

    /**
     * @param powerUp used by player and discarded
     */
    public void addToGarbage(PowerUpCard powerUp) {
        powerUpGarbageDeck.add(powerUp);
    }

    /**
     * @param ammoCard tile collected
     */
    public void discardAmmo(AmmoCard ammoCard){
        ammoGarbageDeck.add(ammoCard);
    }

    /**
     * @param platform from which ammo tile was removed
     */
    public void addToFill(Platform platform){
        toFill.add(platform);
    }

    public List<Platform> getToFill() {
        return toFill;
    }

    public List<AmmoCard> getAmmoGarbageDeck() {
        return ammoGarbageDeck;
    }
}