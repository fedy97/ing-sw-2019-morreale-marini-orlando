package it.polimi.se2019.utils;

import it.polimi.se2019.model.enumeration.Character;

import java.util.logging.Logger;

//add here some recurrent functions that can be helpful
public class HandyFunctions {
    public static final Logger LOGGER = Logger.getLogger(Class.class.getName());
    /**
     * @param character to check
     * @return true if character is in the enum Character
     */
    public static boolean characterExists(Character character) {
        boolean canBeAdded = false;
        for (Character c : Character.values()) {
            if (c.equals(character)) canBeAdded = true;
        }
        return canBeAdded;
    }

    /**
     * @param position of the platform
     * @return true if the posiiton is in the 3x4 matrix
     */
    public static boolean isValidPosition(int[] position) {
        boolean isOk = true;
        if (position.length != 2 || position[0] > 2 || position[0] < 0 || position[1] > 3 || position[1] < 0)
            isOk = false;
        return isOk;
    }


}
