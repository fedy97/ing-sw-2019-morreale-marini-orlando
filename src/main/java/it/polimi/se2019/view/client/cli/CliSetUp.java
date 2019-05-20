package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.utils.HandyFunctions;

/**
 * @author Simone Orlando
 */
public final class CliSetUp {

    private static final String ESC = "\033[";
    private static final String CLEAR = "2J";
    private static final String CURSORHOME = "H";
    private static final String SAVE = "s";
    private static final String RESTORE = "u";

    public static void clear() {
        HandyFunctions.printConsole(ESC+CLEAR);
    }

    public static void cursorToHome() {
        HandyFunctions.printConsole(ESC+CURSORHOME);
    }

    public static void savePosition() {
        HandyFunctions.printConsole(ESC+SAVE);
    }

    public static void restorePosition() {
        HandyFunctions.printConsole(ESC+RESTORE);
    }

    public static void cursorDown(int numOfLine)
    {
        HandyFunctions.printConsole(ESC + numOfLine + "B");
    }
}
