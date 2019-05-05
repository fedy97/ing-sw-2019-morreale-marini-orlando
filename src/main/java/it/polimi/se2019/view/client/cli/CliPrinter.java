package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.utils.HandyFunctions;


/**
 * @author Simone Orlando
 */
public class CliPrinter {

    private static String RESET = "\u001B[0m";

    public static void stamp(String msg) {
        HandyFunctions.printConsole(msg + RESET);
    }

    public static void stamp(String msg, CliColor textColor) {
        String out;
        out = textColor.getCode() + msg;
        stamp(out);
    }

    public static void stamp(String msg, CliColor textColor, CliColor backColor) {
        String out;
        out = backColor.getCode() + msg;
        stamp(out,textColor);
    }

    public static void welcomeMessage() {

        stamp("\n" +
                "    ___    ____  ____  _____ _   __ __    __    ___ _   __ _____ \n" +
                "   /   |  / __ \\/ __ \\/ ____/ | / /   |  / /   /  _/ | / / ____/\n" +
                "  / /| | / / / / /_/ / __/ /  |/ / /| | / /    / //  |/ / __/   \n" +
                " / ___ |/ /_/ / _, _/ /___/ /|  / ___ |/ /____/ // /|  / /___   \n" +
                "/_/  |_/_____/_/ |_/_____/_/ |_/_/  |_/_____/___/_/ |_/_____/  ", CliColor.TEXTRED);
    }

    public static void errorMessage(String msg) {
        stamp("[ERR] "+msg+"\n");
    }

    public static void successMessage(String msg) {
        stamp("[OK] "+msg+"\n");
    }

    public static void waitMessage(String msg) {
        stamp("[WAIT] "+msg+"\n");
    }

    public static void warningMessage(String msg) {
        stamp("[WARNING] "+msg+"\n");
    }

}
