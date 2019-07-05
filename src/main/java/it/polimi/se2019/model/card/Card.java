package it.polimi.se2019.model.card;

import it.polimi.se2019.exceptions.InvalidNameException;

import java.io.Serializable;

/**
 * A generic class representing a card
 *
 * @author Gabriel Raul Marini
 */


public abstract class Card implements Serializable {

    protected String name;
    protected String description;
    protected String imgPath;

    /***
     * Instantiate an anonymous card
     */
    public Card() {
    }

    /**
     * Costructor used by AmmoCard, it only needs the image, not name or description
     *
     * @param img of the ammocard
     */
    public Card(String img) {
        this.imgPath = img;
    }

    /**
     * Instantiate a card with a specified name and a given background image
     *
     * @param name of the card
     * @param description of the card
     * @param img of the card
     * @throws InvalidNameException .
     */
    public Card(String name, String description, String img) throws InvalidNameException {
        if (name.equals("") || description.equals(""))
            throw new InvalidNameException();
        this.description = description;
        this.name = name;
        this.imgPath = img;
    }

    /**
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description of the card
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    @Override
    public String toString() {
        return Integer.toString(hashCode());
    }
}