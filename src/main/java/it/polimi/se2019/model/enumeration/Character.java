package it.polimi.se2019.model.enumeration;

import java.awt.*;

/**
 * All possible characters that a player can choose to play a game
 *
 * @author Simone Orlando
 * @author Federico Morreale
 */
public enum Character {
    DOZER(Color.gray),
    DISTRUCTOR(Color.yellow),
    SPROG(Color.green),
    BANSHEE(Color.blue),
    VIOLET(Color.pink);

    private final Color color;

    /**
     * @param color of the character, once is set won't change anymore
     */
    Character(final Color color) {
        this.color = color;
    }

    /**
     * @return the color associated to the character
     */
    public Color getColor() {
        return color;
    }

}
