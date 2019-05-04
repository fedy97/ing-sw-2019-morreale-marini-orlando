package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.utils.HandyFunctions;


/**
 * @author Simone Orlando
 */
public class CliPrinter {

    public static void welcomeMessage() {

        HandyFunctions.printConsole("\n" +
                "    ___    ____  ____  _____ _   __ __    __    ___ _   __ _____ \n" +
                "   /   |  / __ \\/ __ \\/ ____/ | / /   |  / /   /  _/ | / / ____/\n" +
                "  / /| | / / / / /_/ / __/ /  |/ / /| | / /    / //  |/ / __/   \n" +
                " / ___ |/ /_/ / _, _/ /___/ /|  / ___ |/ /____/ // /|  / /___   \n" +
                "/_/  |_/_____/_/ |_/_____/_/ |_/_/  |_/_____/___/_/ |_/_____/  ");
    }

    public static void errorMessage(String msg) {
        //TODO
    }

    public static void successMessage(String msg) {
        //TODO
    }

    public static void waitMessage(String msg) {
        //TODO
    }
}
