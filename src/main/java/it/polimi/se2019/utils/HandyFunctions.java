package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.view.server.VirtualView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

//add here some recurrent functions that can be helpful
public class HandyFunctions {
    public static final Logger LOGGER = Logger.getLogger(Class.class.getName());
    public static final JsonParser parserSettings = new JsonParser("settingsServer.json");
    public static final JsonParser parserClientSettings = new JsonParser("settingsClient.json");

    private static Random random = new Random();

    public static void printConsole(String message) {
        System.out.print(message);
    }

    public static void printConsole(int value) {
        System.out.print(value);
    }

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
     * @return true if the position is in the 3x4 matrix
     */
    public static boolean isValidPosition(int[] position) {
        boolean isOk = true;
        if (position.length != 2 || position[0] > 2 || position[0] < 0 || position[1] > 3 || position[1] < 0)
            isOk = false;
        return isOk;
    }

    /**
     * @param elements to be converted
     * @return a light version of the initial elements which can be easily sent through the network
     */
    public static <T> List<String> getLightCollection(List<T> elements) {
        List<String> lightVersion = new ArrayList<>();

        for (T obj : elements)
            lightVersion.add(obj.toString());

        return lightVersion;
    }

    public static void checkForAtLeast2Players(VirtualView virtualView) {
        if (Controller.getInstance().getTurnController().getUsers().size() == parserSettings.getMinimumPlayers() && !Controller.getInstance().isTimerStarted()) {
            Controller.getInstance().setTimerStarted(true);
            virtualView.viewSetChanged();
            virtualView.notifyObservers("we are at least 2");
        }
        if (Controller.getInstance().getTurnController().getUsers().size() == 5) {
            virtualView.viewSetChanged();
            virtualView.notifyObservers("we are 5");
        }
    }

    /**
     * return a random number between first and second inclusive
     *
     * @param first  int
     * @param second int
     * @return random int
     */
    public static int randomIntegerBetWeen(int first, int second) {
        if (first >= second) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt((second - first) + 1) + first;
    }

    /**
     * @param o object to be addressed
     * @return the system address of the selected object
     */
    public static int getSystemAddress(Object o) {
        return System.identityHashCode(o);
    }

    public static Color stringToColor(String color) throws NoSuchFieldException, IllegalAccessException {
        return (Color) Color.class.getField(color).get(null);
    }

    /**
     * @param list to be printed
     * @param <T>  is the generic type
     */
    public static <T> void printList(List<T> list) {
        for (T el : list) {
            printConsole(el.toString());
        }
    }


}
