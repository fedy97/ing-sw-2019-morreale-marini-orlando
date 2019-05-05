package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.utils.HandyFunctions;

/**
 * @author Simone Orlando
 */
public class CliSetUp {

    private static final String ESC = "\033[";
    private static final String CLEAR = "2J";
    private static final String CURSORHOME = "H";

    public static void clear() {
        HandyFunctions.printConsole(ESC+CLEAR);
    }

    public static void cursorToHome() {
        HandyFunctions.printConsole(ESC+CURSORHOME);
    }
}
