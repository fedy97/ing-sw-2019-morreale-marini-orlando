package it.polimi.se2019.model.enumeration;

import java.awt.*;

/**
 * All possible characters that a player can choose to play a game
 *
 * @author Simone Orlando
 * @author Federico Morreale
 */
public enum Character {
    DOZER(Color.darkGray),
    DISTRUCTOR(Color.yellow),
    SPROG(Color.red),
    BANSHEE(Color.green),
    VIOLET(Color.pink);

    private String playerName;
    private final Color color;

    /**
     * @param color of the character, once is set won't change anymore
     */
    Character(final Color color) {
        this.color = color;
    }

    /**
     * @return the name of the player associated to the character
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return the color associated to the character
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param playerName to link to the character
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
