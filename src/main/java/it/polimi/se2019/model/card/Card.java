package it.polimi.se2019.model.card;

import it.polimi.se2019.exceptions.InvalidImageException;
import it.polimi.se2019.exceptions.InvalidNameException;

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
    protected String imgPath;

    /***
     * Instantiate an anonymous card
     *
     */
    public Card() {
    }

    /**
     * Costructor used by AmmoCard, it only needs the image, not name or description
     *
     * @param img of the ammocard
     */
    public Card(Image img) throws InvalidImageException {
        if (img == null)
            throw new InvalidImageException();
        this.bgImage = img;
    }

    /**
     * Instantiate a card with a specified name and a given background image
     */
    public Card(String name, String description, Image img) throws InvalidNameException, InvalidImageException {
        if (name.equals("") || description.equals(""))
            throw new InvalidNameException();
        if (img == null)
            throw new InvalidImageException();
        this.description = description;
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
     * @return the reference to the background image of the card
     */
    public Image getBgImage() {
        return bgImage;
    }

    /**
     * @return the description of the card
     */
    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }

}