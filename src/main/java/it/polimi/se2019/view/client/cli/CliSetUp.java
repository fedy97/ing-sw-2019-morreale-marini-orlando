package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.utils.HandyFunctions;

/**
 * Class used to manage the position of the cursor in the terminal.
 *
 * @author Simone Orlando
 */
public final class CliSetUp {

    private static final String ESC = "\033[";
    private static final String CLEAR = "2J";
    private static final String CURSORHOME = "H";
    private static final String SAVE = "s";
    private static final String RESTORE = "u";

    CliSetUp() {
        //Not necessary configuration
    }
    /**
     * Cleans the terminal eliminating all the writings
     */
    public static void clear() {
        HandyFunctions.printConsole(ESC+CLEAR);
    }

    /**
     * Move the cursor to the upper left corner of the terminal
     */
    public static void cursorToHome() {
        HandyFunctions.printConsole(ESC+CURSORHOME);
    }

    /**
     * Stores the current cursor's position in the terminal
     */
    static void savePosition() {
        HandyFunctions.printConsole(ESC+SAVE);
    }

    /**
     * Place the cursor in the last saved position
     */
    static void restorePosition() {
        HandyFunctions.printConsole(ESC+RESTORE);
    }

    /**
     * Move the cursor down
     * @param numOfLine the number of lines to move the cursor
     */
    static void cursorDown(int numOfLine)
    {
        HandyFunctions.printConsole(ESC + numOfLine + "B");
    }

    /**
     * Move the cursor up
     * @param numOfLine the number of lines to move the cursor
     */
    static void cursorUp(int numOfLine)
    {
        HandyFunctions.printConsole(ESC + numOfLine + "A");
    }

    /**
     * Move the cursor right
     * @param numOfCol the number of columns to move the cursor
     */
    static void cursorRight(int numOfCol)
    {
        HandyFunctions.printConsole(ESC + numOfCol + "C");
    }

    /**
     * Move the cursor left
     * @param numOfCol the number of columns to move the cursor
     */
    static void cursorLeft(int numOfCol)
    {
        HandyFunctions.printConsole(ESC + numOfCol + "D");
    }
}
