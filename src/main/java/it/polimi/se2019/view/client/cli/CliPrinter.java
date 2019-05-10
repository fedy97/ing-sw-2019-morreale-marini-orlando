package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.utils.HandyFunctions;

import java.util.List;


/**
 * @author Simone Orlando
 */
public final class CliPrinter {

    private static final String RESET = "\u001B[0m";

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

        stamp("\n" + " \t\t █████╗ ██████╗ ██████╗ ███████╗███╗   ██╗ █████╗ ██╗     ██╗███╗   ██╗███████╗\n" +
                "\t\t██╔══██╗██╔══██╗██╔══██╗██╔════╝████╗  ██║██╔══██╗██║     ██║████╗  ██║██╔════╝\n" +
                "\t\t███████║██║  ██║██████╔╝█████╗  ██╔██╗ ██║███████║██║     ██║██╔██╗ ██║█████╗  \n" +
                "\t\t██╔══██║██║  ██║██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║██║     ██║██║╚██╗██║██╔══╝  \n" +
                "\t\t██║  ██║██████╔╝██║  ██║███████╗██║ ╚████║██║  ██║███████╗██║██║ ╚████║███████╗\n" +
                "\t\t╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝", CliColor.TEXTRED);
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

    public static void boxInterfaceMessage() {
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃           Which interface do you want to use?            ┃\n");
        CliPrinter.stamp("\t\t\t ┃               <1> CLI                                    ┃\n");
        CliPrinter.stamp("\t\t\t ┃               <2> GUI                                    ┃\n");
        CliPrinter.stamp("\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                        ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxConnectionMessage() {
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃           Choose a connection type:                      ┃\n");
        CliPrinter.stamp("\t\t\t ┃               <1> Socket                                 ┃\n");
        CliPrinter.stamp("\t\t\t ┃               <2> Rmi                                    ┃\n");
        CliPrinter.stamp("\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                        ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxUsernameMessage() {
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃           Username: ");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                     ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃           Ip:                                            ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxIpMessage(String usename) {
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃           Username: "+usename);
        for (int i=0; i < 37-usename.length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃           Ip: ");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                           ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxWaitingMessage() {
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃               Waiting for other players...               ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }

    public static void boxLobbyMessage(List<String> users) {
        if (users.size() == 1) {
            CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 2) {
            CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 3) {
            CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 4) {
            CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else {
            CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃  " + users.get(4) + " joined the game");
            for (int i = 0; i < 40 - (users.get(4)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
    }

    public static void timerMessage(int count) {
        HandyFunctions.printConsole("\r\t\t\t                  The game will start in: "+count);
    }

    public static void possibleMapsMessage(int timer, int[] vote) {
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                      Choose the map                      ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃    <1> 3-4-4(votes: "+vote[0]+")            <3> 3-4-3(votes: "+vote[2]+")    ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃    <2> 4-4-3(votes: "+vote[1]+")            <4> 4-4-4(votes: "+vote[3]+")    ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃                      choice: ");
        CliSetUp.savePosition();
        CliPrinter.stamp("                            ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter.stamp("\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
    }

}
