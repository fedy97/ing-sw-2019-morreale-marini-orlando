package it.polimi.se2019.model.card;

import java.awt.*;

/**
 * A generic class representing a card
 *
 * @author Gabriel Raul Marini
 */


public abstract class Card {

    protected String name;
    protected Image bgImage;
    protected String description;

    /***
     * Instantiate an anonymous card
     *
     */
    public Card() {
    }

    /**
     * Instantiate a card with a specified name and a given background image
     */
    public Card(String name, Image img) {
        this.name = name;
        this.bgImage = img;
    }

    /**
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the card
     *
     * @param name of the card
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the bgImage of the card with the image specified
     *
     * @param image background image of the card
     */
    public void setBgImage(Image image) {
        this.bgImage = image;
    }

    /**
     * @return the reference to the background image of the card
     */
    public Image getBgImage() {
        return bgImage;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description of the card
     */
    public String getDescription() {
        return description;
    }

}