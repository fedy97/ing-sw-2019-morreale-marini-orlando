package it.polimi.se2019.view.client.cli.Debug;

import it.polimi.se2019.model.rep.AmmoRep;
import it.polimi.se2019.model.rep.BoardRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.cli.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Simone Orlando
 */
public final class CliPrinter3 {

    private static final String RESET = "\u001B[0m";


    public static void stamp(String msg) {
        HandyFunctions.printConsole(msg + RESET);
    }

    public static void noResetStamp(String msg, CliColor textColor, CliColor backColor) {
        String out;
        out = backColor.getCode() + msg;
        noResetStamp(out,textColor);
    }

    public static void noResetStamp(String msg, CliColor textColor) {
        String out;
        out = textColor.getCode() + msg;
        noResetStamp(out);
    }

    public static void noResetStamp(String msg) {
        HandyFunctions.printConsole(msg);
    }

    public static void reset() {
        HandyFunctions.printConsole(RESET);
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

        stamp("\n" + " \t\t\t\t █████╗ ██████╗ ██████╗ ███████╗███╗   ██╗ █████╗ ██╗     ██╗███╗   ██╗███████╗\n" +
                "\t\t\t\t██╔══██╗██╔══██╗██╔══██╗██╔════╝████╗  ██║██╔══██╗██║     ██║████╗  ██║██╔════╝\n" +
                "\t\t\t\t███████║██║  ██║██████╔╝█████╗  ██╔██╗ ██║███████║██║     ██║██╔██╗ ██║█████╗  \n" +
                "\t\t\t\t██╔══██║██║  ██║██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║██║     ██║██║╚██╗██║██╔══╝  \n" +
                "\t\t\t\t██║  ██║██████╔╝██║  ██║███████╗██║ ╚████║██║  ██║███████╗██║██║ ╚████║███████╗\n" +
                "\t\t\t\t╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝", CliColor.TEXTRED);
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
        CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           Which interface do you want to use?            ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃               <1> CLI                                    ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃               <2> GUI                                    ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter3.stamp("                                        ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxConnectionMessage() {
        CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           Choose a connection type:                      ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃               <1> Socket                                 ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃               <2> Rmi                                    ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter3.stamp("                                        ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxUsernameMessage() {
        CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           Username: ");
        CliSetUp.savePosition();
        CliPrinter3.stamp("                                     ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           Ip:                                            ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxIpMessage(String usename) {
        CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           Username: "+usename);
        for (int i=0; i < 37-usename.length(); i++) {
            CliPrinter3.stamp(" ");
        }
        CliPrinter3.stamp("┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃           Ip: ");
        CliSetUp.savePosition();
        CliPrinter3.stamp("                                           ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxWaitingMessage() {
        CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃               Waiting for other players...               ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }

    public static void boxLobbyMessage(List<String> users) {
        if (users.size() == 1) {
            CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 2) {
            CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 3) {
            CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 4) {
            CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else {
            CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃  " + users.get(4) + " joined the game");
            for (int i = 0; i < 40 - (users.get(4)).length(); i++) {
                CliPrinter3.stamp(" ");
            }
            CliPrinter3.stamp("┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
    }

    public static void timerMessage(int count) {
        HandyFunctions.printConsole("\r\t\t\t\t\t                  The game will start in: "+count);
    }

    public static void possibleMapsMessage(int timer, int[] vote) {
        CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                      Choose the map                      ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃    <1> 3-4-4(votes: "+vote[0]+")            <3> 3-4-3(votes: "+vote[2]+")    ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃    <2> 4-4-3(votes: "+vote[1]+")            <4> 4-4-4(votes: "+vote[3]+")    ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter3.stamp("            ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter3.stamp("\t\t\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
        CliPrinter3.noResetStamp("c",CliColor.TEXTWHITE, CliColor.BACKWHITE);
        CliSetUp.cursorLeft(1);
    }

    public static void standardActionsMessage() {
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                            Choose an action:                                           ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                        <1> move                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                        <2> grab                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                        <3> shoot                                                       ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                        <4> reload                                                      ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                        <5> use power up                                                ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                        <6> end turn                                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                     press the <key> followed by enter                                  ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public static void possibleCharMessage(int timer, ArrayList<String> chosen, int myChoice) {

        char cBan = ' ';
        char cSpr = ' ';
        char cDoz = ' ';
        char cVio = ' ';
        char cDis = ' ';
        if(chosen.contains("BANSHEE")) {
            cBan = '✖';
        }
        if(chosen.contains("SPROG")) {
            cSpr = '✖';
        }
        if(chosen.contains("DOZER")) {
            cDoz = '✖';
        }
        if(chosen.contains("VIOLET")) {
            cVio = '✖';
        }
        if(chosen.contains("DISTRUCTOR")) {
            cDis = '✖';
        }

        if(myChoice == 1) {
            cBan = '✔';
        }
        else if(myChoice ==2) {
            cSpr = '✔';
        }
        else if(myChoice ==3) {
            cDoz = '✔';
        }
        else if(myChoice ==4) {
            cVio = '✔';
        }
        else if(myChoice ==5) {
            cDis = '✔';
        }
        CliPrinter3.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                   Choose the character                   ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃  <1> Banshee "+cBan+"          <2> Sprog "+cSpr+"         <3> Dozer "+cDoz+"  ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃            <4> Violet "+cVio+"          <5> Distructor "+cDis+"        ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter3.stamp("            ┃\n");
        CliPrinter3.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter3.stamp("\t\t\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
        CliPrinter3.noResetStamp("c",CliColor.TEXTWHITE, CliColor.BACKWHITE);
        CliSetUp.cursorLeft(1);
    }

    public static void weaponBox(CliColor color, List<CardRep> powWeapons) {
        CliSetUp.savePosition();
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter3.stamp("┃     "+powWeapons.get(0).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(0).getTitle().length(); i++) {
            CliPrinter3.stamp(" ");
        }
        CliPrinter3.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter3.stamp("┃                             ┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter3.stamp("┃     "+powWeapons.get(1).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(1).getTitle().length(); i++) {
            CliPrinter3.stamp(" ");
        }
        CliPrinter3.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter3.stamp("┃                             ┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter3.stamp("┃     "+powWeapons.get(2).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(2).getTitle().length(); i++) {
            CliPrinter3.stamp(" ");
        }
        CliPrinter3.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(2);

    }

    public static void printMap1(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap1 map1 = new CliMap1();

        map1.plat1.setAmmo(ammoReps.get(0).getType());
        map1.plat2.setAmmo(ammoReps.get(1).getType());
        map1.plat5.setAmmo(ammoReps.get(5).getType());
        map1.plat6.setAmmo(ammoReps.get(6).getType());
        map1.plat7.setAmmo(ammoReps.get(7).getType());
        map1.plat8.setAmmo(ammoReps.get(8).getType());
        map1.plat9.setAmmo(ammoReps.get(9).getType());
        map1.plat10.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter3.stamp("\n\t\t\t\t\t");

        map1.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null);
        CliSetUp.cursorDown(20);
    }

    public static void printMap2(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap2 map2 = new CliMap2();

        map2.plat1.setAmmo(ammoReps.get(0).getType());
        map2.plat2.setAmmo(ammoReps.get(1).getType());
        map2.plat4.setAmmo(ammoReps.get(3).getType());
        map2.plat6.setAmmo(ammoReps.get(5).getType());
        map2.plat7.setAmmo(ammoReps.get(6).getType());
        map2.plat8.setAmmo(ammoReps.get(7).getType());
        map2.plat9.setAmmo(ammoReps.get(9).getType());
        map2.plat10.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter3.stamp("\n\t\t\t\t\t");

        map2.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null);
        CliSetUp.cursorDown(20);
    }

    public static void printMap3(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap3 map3 = new CliMap3();

        map3.plat1.setAmmo(ammoReps.get(0).getType());
        map3.plat2.setAmmo(ammoReps.get(1).getType());
        map3.plat5.setAmmo(ammoReps.get(5).getType());
        map3.plat6.setAmmo(ammoReps.get(6).getType());
        map3.plat7.setAmmo(ammoReps.get(7).getType());
        map3.plat8.setAmmo(ammoReps.get(9).getType());
        map3.plat9.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter3.stamp("\n\t\t\t\t\t");

        map3.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null);
        CliSetUp.cursorDown(20);
    }

    public static void printMap4(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap4 map4 = new CliMap4();

        map4.plat1.setAmmo(ammoReps.get(0).getType());
        map4.plat2.setAmmo(ammoReps.get(1).getType());
        map4.plat4.setAmmo(ammoReps.get(3).getType());
        map4.plat6.setAmmo(ammoReps.get(5).getType());
        map4.plat7.setAmmo(ammoReps.get(6).getType());
        map4.plat8.setAmmo(ammoReps.get(7).getType());
        map4.plat9.setAmmo(ammoReps.get(8).getType());
        map4.plat10.setAmmo(ammoReps.get(9).getType());
        map4.plat11.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter3.stamp("\n\t\t\t\t\t");

        map4.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null);
        CliSetUp.cursorDown(20);
    }


    public static void choosePowerUpMessage(CardRep p1, CardRep p2) {
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                             Choose a powerup:                                          ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                      <1> " + p1.getTitle());
        for (int i=0; i < 62 - p1.getTitle().length(); i++) {
            CliPrinter3.stamp(" ");
        }
        CliPrinter3.stamp("┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                      <2> " + p2.getTitle());
        for (int i=0; i < 62 - p2.getTitle().length(); i++) {
            CliPrinter3.stamp(" ");
        }
        CliPrinter3.stamp("┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┃                                      press the <key> followed by enter                                 ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.cursorUp(1);
    }

    public static void usePowerUpMessage(LightGameVersion lightGameVersion, String myChar, List<String> powerUps) {
        CliSetUp.savePosition();
        CliSetUp.cursorUp(1);
        CliSetUp.cursorRight(9);
        Map<String, List<CardRep>> playerPowerUps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerUps = playerPowerUps.get(myChar);

        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                           Choose a powerUp to use:                                     ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myPowerUps) {
            if (powerUps.contains(Integer.toString(c.getId()))) {
                CliPrinter3.stamp(c.getTitle());
                if (counter < 2) {
                    CliPrinter3.stamp(", ");
                }
            }
            counter++;
        }
        stamp(" <0,1,2>: ");
    }

    public static void boxMessage(String msg) {
        CliSetUp.savePosition();
        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(2);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                 Messages:                                              ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        if (msg != null) {

        }
        CliSetUp.restorePosition();
    }

    public static void drawPlayersInfoBox(LightGameVersion lightGameVersion) {

        int playerCounter = 0;

        Map<String, List<CardRep>> playerPowerups;
        Map<String, List<CardRep>> playerWeapons;
        Map<String, BoardRep> playerBoardRep;

        BoardRep bansheeRep;
        BoardRep sprogRep;
        BoardRep dozerRep;
        BoardRep violetRep;
        BoardRep distructorRep;

        List<String> bansheeDamages;
        List<String> bansheemarks;
        Map<String,Integer> bansheeAmmos;
        List<CardRep> bansheePowerUp;
        List<CardRep> bansheeWeapons;

        List<String> sprogDamages;
        List<String> sprogmarks;
        Map<String,Integer> sprogAmmos;
        List<CardRep> sprogPowerUp;
        List<CardRep> sprogWeapons;

        List<String> dozerDamages;
        List<String> dozermarks;
        Map<String,Integer> dozerAmmos;
        List<CardRep> dozerPowerUp;
        List<CardRep> dozerWeapons;

        List<String> violetDamages;
        List<String> violetmarks;
        Map<String,Integer> violetAmmos;
        List<CardRep> violetPowerUp;
        List<CardRep> violetWeapons;

        List<String> distructorDamages;
        List<String> distructormarks;
        Map<String,Integer> distructorAmmos;
        List<CardRep> distructorPowerUp;
        List<CardRep> distructorWeapons;


        stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(61);
        CliSetUp.cursorDown(1);
        for (int i=0;i<45;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorUp(45);
        CliSetUp.cursorLeft(1);
        for (int i=0;i<45;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        CliSetUp.cursorUp(45);
        CliSetUp.cursorLeft(36);
        CliPrinter3.stamp("PLAYERS' INFO");
        CliSetUp.cursorDown(2);
        CliSetUp.cursorLeft(34);
        CliSetUp.savePosition(); // per poter scrivere sempre all'inizio del box, basta scendere di uno

        if (lightGameVersion != null) {
            playerPowerups = lightGameVersion.getPlayerPowerups();
            playerWeapons = lightGameVersion.getPlayerWeapons();
            playerBoardRep = lightGameVersion.getPlayerBoardRep();

            bansheeRep = playerBoardRep.get("BANSHEE");
            if (bansheeRep != null) {
                playerCounter++;

                CliPrinter3.stamp("BANSHEE:", CliColor.TEXTBLUE);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                bansheeDamages = bansheeRep.getDamages();
                bansheemarks = bansheeRep.getMarks();
                bansheeAmmos = bansheeRep.getColorQtyAmmos();
                bansheePowerUp = playerPowerups.get("BANSHEE");
                bansheeWeapons = playerWeapons.get("BANSHEE");

                int redAmmos = bansheeAmmos.get("RED");
                int blueAmmos = bansheeAmmos.get("BLUE");
                int yellowAmmos = bansheeAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter3.stamp("damages: ");
                for (String d: bansheeDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("marks: ");
                for (String d: bansheemarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("Ammos: ");
                CliPrinter3.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter3.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter3.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("powerups: ");
                for (CardRep c: bansheePowerUp) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("weapons: ");
                for (CardRep c: bansheeWeapons) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(2);
                CliSetUp.savePosition();

            }

            sprogRep = playerBoardRep.get("SPROG");
            if(sprogRep != null) {
                playerCounter++;

                CliPrinter3.stamp("SPROG:", CliColor.TEXTGREEN);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                sprogDamages = sprogRep.getDamages();
                sprogmarks = sprogRep.getMarks();
                sprogAmmos = sprogRep.getColorQtyAmmos();
                sprogPowerUp = playerPowerups.get("SPROG");
                sprogWeapons = playerWeapons.get("SPROG");

                int redAmmos = sprogAmmos.get("RED");
                int blueAmmos = sprogAmmos.get("BLUE");
                int yellowAmmos = sprogAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter3.stamp("damages: ");
                for (String d: sprogDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("marks: ");
                for (String d: sprogmarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("Ammos: ");
                CliPrinter3.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter3.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter3.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("powerups: ");
                for (CardRep c: sprogPowerUp) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("weapons: ");
                for (CardRep c: sprogWeapons) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(2);
                CliSetUp.savePosition();
            }

            dozerRep = playerBoardRep.get("DOZER");
            if (dozerRep != null) {
                playerCounter++;

                CliPrinter3.stamp("DOZER:", CliColor.TEXTWHITE);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                dozerDamages = dozerRep.getDamages();
                dozermarks = dozerRep.getMarks();
                dozerAmmos = dozerRep.getColorQtyAmmos();
                dozerPowerUp = playerPowerups.get("DOZER");
                dozerWeapons = playerWeapons.get("DOZER");

                int redAmmos = dozerAmmos.get("RED");
                int blueAmmos = dozerAmmos.get("BLUE");
                int yellowAmmos = dozerAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter3.stamp("damages: ");
                for (String d: dozerDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("marks: ");
                for (String d: dozermarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("Ammos: ");
                CliPrinter3.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter3.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter3.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("powerups: ");
                for (CardRep c: dozerPowerUp) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("weapons: ");
                for (CardRep c: dozerWeapons) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(2);
                CliSetUp.savePosition();
            }

            violetRep = playerBoardRep.get("VIOLET");
            if (violetRep != null) {
                playerCounter++;

                CliPrinter3.stamp("VIOLET:", CliColor.TEXTPURPLE);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                violetDamages = violetRep.getDamages();
                violetmarks = violetRep.getMarks();
                violetAmmos = violetRep.getColorQtyAmmos();
                violetPowerUp = playerPowerups.get("VIOLET");
                violetWeapons = playerWeapons.get("VIOLET");

                int redAmmos = violetAmmos.get("RED");
                int blueAmmos = violetAmmos.get("BLUE");
                int yellowAmmos = violetAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter3.stamp("damages: ");
                for (String d: violetDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("marks: ");
                for (String d: violetmarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("Ammos: ");
                CliPrinter3.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter3.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter3.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("powerups: ");
                for (CardRep c: violetPowerUp) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("weapons: ");
                for (CardRep c: violetWeapons) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(2);
                CliSetUp.savePosition();
            }

            distructorRep = playerBoardRep.get("DISTRUCTOR");
            if (distructorRep != null) {
                playerCounter++;

                CliPrinter3.stamp("DISTRUCTOR:", CliColor.TEXTYELLOW);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                distructorDamages = distructorRep.getDamages();
                distructormarks = distructorRep.getMarks();
                distructorAmmos = distructorRep.getColorQtyAmmos();
                distructorPowerUp = playerPowerups.get("DISTRUCTOR");
                distructorWeapons = playerWeapons.get("DISTRUCTOR");

                int redAmmos = distructorAmmos.get("RED");
                int blueAmmos = distructorAmmos.get("BLUE");
                int yellowAmmos = distructorAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter3.stamp("damages: ");
                for (String d: distructorDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("marks: ");
                for (String d: distructormarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter3.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter3.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter3.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter3.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter3.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("Ammos: ");
                CliPrinter3.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter3.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter3.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("powerups: ");
                for (CardRep c: distructorPowerUp) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter3.stamp("weapons: ");
                for (CardRep c: distructorWeapons) {
                    CliPrinter3.stamp(c.getTitle() + ", ");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(2);
                CliSetUp.savePosition();
            }

            CliSetUp.cursorUp(7*playerCounter);
        }
    }

    public static void discartWeaponMessage(LightGameVersion lightGameVersion, String myChar) {
        CliSetUp.savePosition();
        Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeapons = playerWeapons.get(myChar);

        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                          Choose a weapon to discart:                                   ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myWeapons) {
            CliPrinter3.stamp(c.getTitle());
            if (counter <2) {
                CliPrinter3.stamp(", ");
            }
            counter++;
        }
        stamp(" <0,1,2>: ");
    }

    public static void reloadWeaponMessage(LightGameVersion lightGameVersion, String myChar, List<String> weapons) {
        CliSetUp.savePosition();
        Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeapons = playerWeapons.get(myChar);
        CliSetUp.cursorRight(9);
        CliSetUp.cursorUp(1);
        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                          Choose a weapon to reload:                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myWeapons) {
            if (weapons.contains(Integer.toString(c.getId()))) {
                CliPrinter3.stamp(c.getTitle());
                if (counter < 2) {
                    CliPrinter3.stamp(", ");
                }
            }
            counter++;
        }
        stamp(" <0,1,2>: ");
    }

    public static void chooseWeaponMessage(LightGameVersion lightGameVersion, String myChar, List<String> weapons) {
        CliSetUp.savePosition();
        Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeapons = playerWeapons.get(myChar);
        CliSetUp.cursorUp(1);
        CliSetUp.cursorRight(9);
        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                               Choose a weapon:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myWeapons) {
            if (weapons.contains(Integer.toString(c.getId()))) {
                CliPrinter3.stamp(c.getTitle());
                if (counter < 2) {
                    CliPrinter3.stamp(", ");
                }
            }
            counter++;
        }
        stamp(" <0,1,2>: ");

    }

    public static void showTargetMessage(LightGameVersion lightGameVersion, List<String> targets) {
        CliSetUp.cursorUp(6);
        CliSetUp.cursorRight(9);
        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                               Choose a target:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (String s: targets) {
            CliPrinter3.stamp(s);
            if (counter < 2) {
                CliPrinter3.stamp(", ");
            }
            counter++;
        }
        stamp(" <0,1,2,3,4>: ");
    }

    public static void enlightenEffectsMessage(List<Integer> effects) {
        CliSetUp.cursorUp(1);
        CliSetUp.cursorRight(9);
        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                              Choose an effect:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (Integer s: effects) {
            HandyFunctions.printConsole(s.intValue());
            if (counter < 2) {
                CliPrinter3.stamp(", ");
            }
            counter++;
        }
        stamp(" : ");

    }

    public static void printPlatformWeapons(LightGameVersion lightGameVersion) {
        CliPrinter3.weaponBox(CliColor.TEXTRED, lightGameVersion.getPlatformWeapons().get("1,0"));
        CliPrinter3.weaponBox(CliColor.TEXTBLUE,lightGameVersion.getPlatformWeapons().get("0,2"));
        CliPrinter3.weaponBox(CliColor.TEXTYELLOW,lightGameVersion.getPlatformWeapons().get("2,3"));
    }

    public static void printPossiblePlatform(List<String> platforms) {
        CliSetUp.cursorRight(2);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                             Choose a platform:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        for (String s: platforms) {
            stamp("("+s+")"+ " ");
        }
        stamp(": ");
    }

    public static Map<Integer, Integer> printPossibleWeapon(LightGameVersion lightGameVersion, List<String> weapons) {
        Map<String, List<CardRep>> platformWeapons = lightGameVersion.getPlatformWeapons();
        List<CardRep> plat1 = platformWeapons.get("0,2");
        List<CardRep> plat2 = platformWeapons.get("1,0");
        List<CardRep> plat3 = platformWeapons.get("2,3");
        Map<Integer, Integer> hashes = new HashMap<>();


        CliSetUp.cursorLeft(8);
        CliSetUp.cursorDown(1);

        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                              Choose a weapon:                                          ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: plat1) {
            for (String w: weapons) {
                if(c.getId() == Integer.parseInt(w)) {
                    CliPrinter3.stamp(c.getTitle());
                    hashes.put(counter,c.getId());
                    if (counter < 2)
                        CliPrinter3.stamp(", ");
                    counter ++;
                }
            }
        }
        for (CardRep c: plat2) {
            for (String w: weapons) {
                if(c.getId() == Integer.parseInt(w)) {
                    CliPrinter3.stamp(c.getTitle());
                    hashes.put(counter,c.getId());
                    if (counter < 2)
                        CliPrinter3.stamp(", ");
                    counter++;
                }
            }
        }
        for (CardRep c: plat3) {
            for (String w: weapons) {
                if(c.getId() == Integer.parseInt(w)) {
                    CliPrinter3.stamp(c.getTitle());
                    hashes.put(counter,c.getId());
                    if (counter < 2)
                        CliPrinter3.stamp(", ");
                    counter++;
                }
            }
        }
        stamp(" <0,1,2>: ");
        return hashes;
    }

    public static void binaryOptionMessage(String msg) {
        CliSetUp.cursorDown(1);
        CliSetUp.cursorLeft(8);
        CliPrinter3.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                 Message:                                               ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter3.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        CliPrinter3.stamp(msg + "<y/n>: ");
    }


    public static void printMap(LightGameVersion lightGameVersion, String choosenBoard) {
        if(choosenBoard.equals("1"))
            printLightMap1(lightGameVersion);
        else if(choosenBoard.equals("2"))
            printLightMap2(lightGameVersion);
        else if(choosenBoard.equals("3"))
            printLightMap3(lightGameVersion);
        else
            printLightMap4(lightGameVersion);
    }

    public static void printLightMap1(LightGameVersion lightGameVersion) {
        CliMap1 map1 = new CliMap1();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map1.plat1.setBansheeInside();
            } else {
                map1.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map1.plat2.setBansheeInside();
            } else {
                map1.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map1.plat3.setBansheeInside();
            } else {
                map1.plat3.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map1.plat4.setBansheeInside();
            } else {
                map1.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map1.plat5.setBansheeInside();
            } else {
                map1.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map1.plat6.setBansheeInside();
            } else {
                map1.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map1.plat7.setBansheeInside();
            } else {
                map1.plat7.noBansheeInside();
            }
            if (bansheePos.equals("2,0")) {
                map1.plat8.setBansheeInside();
            } else {
                map1.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map1.plat9.setBansheeInside();
            } else {
                map1.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map1.plat10.setBansheeInside();
            } else {
                map1.plat10.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map1.plat11.setBansheeInside();
            } else {
                map1.plat11.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map1.plat1.setSprogInside();
            } else {
                map1.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map1.plat2.setSprogInside();
            } else {
                map1.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map1.plat3.setSprogInside();
            } else {
                map1.plat3.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map1.plat4.setSprogInside();
            } else {
                map1.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map1.plat5.setSprogInside();
            } else {
                map1.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map1.plat6.setSprogInside();
            } else {
                map1.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map1.plat7.setSprogInside();
            } else {
                map1.plat7.noSprogInside();
            }
            if (sprogPos.equals("2,0")) {
                map1.plat8.setSprogInside();
            } else {
                map1.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map1.plat9.setSprogInside();
            } else {
                map1.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map1.plat10.setSprogInside();
            } else {
                map1.plat10.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map1.plat11.setSprogInside();
            } else {
                map1.plat11.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map1.plat1.setDozerInside();
            } else {
                map1.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map1.plat2.setDozerInside();
            } else {
                map1.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map1.plat3.setDozerInside();
            } else {
                map1.plat3.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map1.plat4.setDozerInside();
            } else {
                map1.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map1.plat5.setDozerInside();
            } else {
                map1.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map1.plat6.setDozerInside();
            } else {
                map1.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map1.plat7.setDozerInside();
            } else {
                map1.plat7.noDozerInside();
            }
            if (dozerPos.equals("2,0")) {
                map1.plat8.setDozerInside();
            } else {
                map1.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map1.plat9.setDozerInside();
            } else {
                map1.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map1.plat10.setDozerInside();
            } else {
                map1.plat10.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map1.plat11.setDozerInside();
            } else {
                map1.plat11.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map1.plat1.setVioletInside();
            } else {
                map1.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map1.plat2.setVioletInside();
            } else {
                map1.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map1.plat3.setVioletInside();
            } else {
                map1.plat3.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map1.plat4.setVioletInside();
            } else {
                map1.plat4.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map1.plat5.setVioletInside();
            } else {
                map1.plat5.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map1.plat6.setVioletInside();
            } else {
                map1.plat6.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map1.plat7.setVioletInside();
            } else {
                map1.plat7.noVioletInside();
            }
            if (violetPos.equals("2,0")) {
                map1.plat8.setVioletInside();
            } else {
                map1.plat8.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map1.plat9.setVioletInside();
            } else {
                map1.plat9.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map1.plat10.setVioletInside();
            } else {
                map1.plat10.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map1.plat11.setVioletInside();
            } else {
                map1.plat11.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map1.plat1.setDistructorInside();
            } else {
                map1.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map1.plat2.setDistructorInside();
            } else {
                map1.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map1.plat3.setDistructorInside();
            } else {
                map1.plat3.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map1.plat4.setDistructorInside();
            } else {
                map1.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map1.plat5.setDistructorInside();
            } else {
                map1.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map1.plat6.setDistructorInside();
            } else {
                map1.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map1.plat7.setDistructorInside();
            } else {
                map1.plat7.noDistructorInside();
            }
            if (distructorPos.equals("2,0")) {
                map1.plat8.setDistructorInside();
            } else {
                map1.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map1.plat9.setDistructorInside();
            } else {
                map1.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map1.plat10.setDistructorInside();
            } else {
                map1.plat10.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map1.plat11.setDistructorInside();
            } else {
                map1.plat11.noDistructorInside();
            }
        }

        map1.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map1.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map1.plat5.setAmmo(platformAmmoTile.get("1,1").getType());
        map1.plat6.setAmmo(platformAmmoTile.get("1,2").getType());
        map1.plat7.setAmmo(platformAmmoTile.get("1,3").getType());
        map1.plat8.setAmmo(platformAmmoTile.get("2,0").getType());
        map1.plat9.setAmmo(platformAmmoTile.get("2,1").getType());
        map1.plat10.setAmmo(platformAmmoTile.get("2,2").getType());

        map1.stamp();
    }

    public static void printLightMap2(LightGameVersion lightGameVersion) {
        CliMap2 map2 = new CliMap2();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map2.plat1.setBansheeInside();
            } else {
                map2.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map2.plat2.setBansheeInside();
            } else {
                map2.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map2.plat3.setBansheeInside();
            } else {
                map2.plat3.noBansheeInside();
            }
            if (bansheePos.equals("0,3")) {
                map2.plat4.setBansheeInside();
            } else {
                map2.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map2.plat5.setBansheeInside();
            } else {
                map2.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map2.plat6.setBansheeInside();
            } else {
                map2.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map2.plat7.setBansheeInside();
            } else {
                map2.plat7.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map2.plat8.setBansheeInside();
            } else {
                map2.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map2.plat9.setBansheeInside();
            } else {
                map2.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map2.plat10.setBansheeInside();
            } else {
                map2.plat10.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map2.plat11.setBansheeInside();
            } else {
                map2.plat11.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map2.plat1.setSprogInside();
            } else {
                map2.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map2.plat2.setSprogInside();
            } else {
                map2.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map2.plat3.setSprogInside();
            } else {
                map2.plat3.noSprogInside();
            }
            if (sprogPos.equals("0,3")) {
                map2.plat4.setSprogInside();
            } else {
                map2.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map2.plat5.setSprogInside();
            } else {
                map2.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map2.plat6.setSprogInside();
            } else {
                map2.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map2.plat7.setSprogInside();
            } else {
                map2.plat7.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map2.plat8.setSprogInside();
            } else {
                map2.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map2.plat9.setSprogInside();
            } else {
                map2.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map2.plat10.setSprogInside();
            } else {
                map2.plat10.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map2.plat11.setSprogInside();
            } else {
                map2.plat11.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map2.plat1.setDozerInside();
            } else {
                map2.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map2.plat2.setDozerInside();
            } else {
                map2.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map2.plat3.setDozerInside();
            } else {
                map2.plat3.noDozerInside();
            }
            if (dozerPos.equals("0,3")) {
                map2.plat4.setDozerInside();
            } else {
                map2.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map2.plat5.setDozerInside();
            } else {
                map2.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map2.plat6.setDozerInside();
            } else {
                map2.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map2.plat7.setDozerInside();
            } else {
                map2.plat7.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map2.plat8.setDozerInside();
            } else {
                map2.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map2.plat9.setDozerInside();
            } else {
                map2.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map2.plat10.setDozerInside();
            } else {
                map2.plat10.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map2.plat11.setDozerInside();
            } else {
                map2.plat11.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map2.plat1.setVioletInside();
            } else {
                map2.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map2.plat2.setVioletInside();
            } else {
                map2.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map2.plat3.setVioletInside();
            } else {
                map2.plat3.noVioletInside();
            }
            if (violetPos.equals("0,3")) {
                map2.plat4.setVioletInside();
            } else {
                map2.plat4.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map2.plat5.setVioletInside();
            } else {
                map2.plat5.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map2.plat6.setVioletInside();
            } else {
                map2.plat6.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map2.plat7.setVioletInside();
            } else {
                map2.plat7.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map2.plat8.setVioletInside();
            } else {
                map2.plat8.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map2.plat9.setVioletInside();
            } else {
                map2.plat9.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map2.plat10.setVioletInside();
            } else {
                map2.plat10.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map2.plat11.setVioletInside();
            } else {
                map2.plat11.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map2.plat1.setDistructorInside();
            } else {
                map2.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map2.plat2.setDistructorInside();
            } else {
                map2.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map2.plat3.setDistructorInside();
            } else {
                map2.plat3.noDistructorInside();
            }
            if (distructorPos.equals("0,3")) {
                map2.plat4.setDistructorInside();
            } else {
                map2.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map2.plat5.setDistructorInside();
            } else {
                map2.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map2.plat6.setDistructorInside();
            } else {
                map2.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map2.plat7.setDistructorInside();
            } else {
                map2.plat7.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map2.plat8.setDistructorInside();
            } else {
                map2.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map2.plat9.setDistructorInside();
            } else {
                map2.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map2.plat10.setDistructorInside();
            } else {
                map2.plat10.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map2.plat11.setDistructorInside();
            } else {
                map2.plat11.noDistructorInside();
            }
        }

        map2.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map2.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map2.plat4.setAmmo(platformAmmoTile.get("0,3").getType());
        map2.plat6.setAmmo(platformAmmoTile.get("1,1").getType());
        map2.plat7.setAmmo(platformAmmoTile.get("1,2").getType());
        map2.plat8.setAmmo(platformAmmoTile.get("1,3").getType());
        map2.plat9.setAmmo(platformAmmoTile.get("2,1").getType());
        map2.plat10.setAmmo(platformAmmoTile.get("2,2").getType());

        map2.stamp();
    }

    public static void printLightMap3(LightGameVersion lightGameVersion) {
        CliMap3 map3 = new CliMap3();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map3.plat1.setBansheeInside();
            } else {
                map3.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map3.plat2.setBansheeInside();
            } else {
                map3.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map3.plat3.setBansheeInside();
            } else {
                map3.plat3.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map3.plat4.setBansheeInside();
            } else {
                map3.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map3.plat5.setBansheeInside();
            } else {
                map3.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map3.plat6.setBansheeInside();
            } else {
                map3.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map3.plat7.setBansheeInside();
            } else {
                map3.plat7.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map3.plat8.setBansheeInside();
            } else {
                map3.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map3.plat9.setBansheeInside();
            } else {
                map3.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map3.plat10.setBansheeInside();
            } else {
                map3.plat10.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map3.plat1.setSprogInside();
            } else {
                map3.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map3.plat2.setSprogInside();
            } else {
                map3.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map3.plat3.setSprogInside();
            } else {
                map3.plat3.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map3.plat4.setSprogInside();
            } else {
                map3.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map3.plat5.setSprogInside();
            } else {
                map3.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map3.plat6.setSprogInside();
            } else {
                map3.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map3.plat7.setSprogInside();
            } else {
                map3.plat7.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map3.plat8.setSprogInside();
            } else {
                map3.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map3.plat9.setSprogInside();
            } else {
                map3.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map3.plat10.setSprogInside();
            } else {
                map3.plat10.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map3.plat1.setDozerInside();
            } else {
                map3.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map3.plat2.setDozerInside();
            } else {
                map3.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map3.plat3.setDozerInside();
            } else {
                map3.plat3.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map3.plat4.setDozerInside();
            } else {
                map3.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map3.plat5.setDozerInside();
            } else {
                map3.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map3.plat6.setDozerInside();
            } else {
                map3.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map3.plat7.setDozerInside();
            } else {
                map3.plat7.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map3.plat8.setDozerInside();
            } else {
                map3.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map3.plat9.setDozerInside();
            } else {
                map3.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map3.plat10.setDozerInside();
            } else {
                map3.plat10.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map3.plat1.setVioletInside();
            } else {
                map3.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map3.plat2.setVioletInside();
            } else {
                map3.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map3.plat3.setVioletInside();
            } else {
                map3.plat3.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map3.plat4.setVioletInside();
            } else {
                map3.plat4.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map3.plat5.setVioletInside();
            } else {
                map3.plat5.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map3.plat6.setVioletInside();
            } else {
                map3.plat6.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map3.plat7.setVioletInside();
            } else {
                map3.plat7.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map3.plat8.setVioletInside();
            } else {
                map3.plat8.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map3.plat9.setVioletInside();
            } else {
                map3.plat9.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map3.plat10.setVioletInside();
            } else {
                map3.plat10.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map3.plat1.setDistructorInside();
            } else {
                map3.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map3.plat2.setDistructorInside();
            } else {
                map3.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map3.plat3.setDistructorInside();
            } else {
                map3.plat3.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map3.plat4.setDistructorInside();
            } else {
                map3.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map3.plat5.setDistructorInside();
            } else {
                map3.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map3.plat6.setDistructorInside();
            } else {
                map3.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map3.plat7.setDistructorInside();
            } else {
                map3.plat7.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map3.plat8.setDistructorInside();
            } else {
                map3.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map3.plat9.setDistructorInside();
            } else {
                map3.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map3.plat10.setDistructorInside();
            } else {
                map3.plat10.noDistructorInside();
            }
        }

        map3.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map3.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map3.plat5.setAmmo(platformAmmoTile.get("1,1").getType());
        map3.plat6.setAmmo(platformAmmoTile.get("1,2").getType());
        map3.plat7.setAmmo(platformAmmoTile.get("1,3").getType());
        map3.plat8.setAmmo(platformAmmoTile.get("2,1").getType());
        map3.plat9.setAmmo(platformAmmoTile.get("2,2").getType());

        map3.stamp();
    }

    public static void printLightMap4(LightGameVersion lightGameVersion) {
        CliMap4 map4 = new CliMap4();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map4.plat1.setBansheeInside();
            } else {
                map4.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map4.plat2.setBansheeInside();
            } else {
                map4.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map4.plat3.setBansheeInside();
            } else {
                map4.plat3.noBansheeInside();
            }
            if (bansheePos.equals("0,3")) {
                map4.plat4.setBansheeInside();
            } else {
                map4.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map4.plat5.setBansheeInside();
            } else {
                map4.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map4.plat6.setBansheeInside();
            } else {
                map4.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map4.plat7.setBansheeInside();
            } else {
                map4.plat7.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map4.plat8.setBansheeInside();
            } else {
                map4.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,0")) {
                map4.plat9.setBansheeInside();
            } else {
                map4.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map4.plat10.setBansheeInside();
            } else {
                map4.plat10.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map4.plat11.setBansheeInside();
            } else {
                map4.plat11.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map4.plat12.setBansheeInside();
            } else {
                map4.plat12.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map4.plat1.setSprogInside();
            } else {
                map4.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map4.plat2.setSprogInside();
            } else {
                map4.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map4.plat3.setSprogInside();
            } else {
                map4.plat3.noSprogInside();
            }
            if (sprogPos.equals("0,3")) {
                map4.plat4.setSprogInside();
            } else {
                map4.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map4.plat5.setSprogInside();
            } else {
                map4.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map4.plat6.setSprogInside();
            } else {
                map4.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map4.plat7.setSprogInside();
            } else {
                map4.plat7.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map4.plat8.setSprogInside();
            } else {
                map4.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,0")) {
                map4.plat9.setSprogInside();
            } else {
                map4.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map4.plat10.setSprogInside();
            } else {
                map4.plat10.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map4.plat11.setSprogInside();
            } else {
                map4.plat11.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map4.plat12.setSprogInside();
            } else {
                map4.plat12.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map4.plat1.setDozerInside();
            } else {
                map4.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map4.plat2.setDozerInside();
            } else {
                map4.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map4.plat3.setDozerInside();
            } else {
                map4.plat3.noDozerInside();
            }
            if (dozerPos.equals("0,3")) {
                map4.plat4.setDozerInside();
            } else {
                map4.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map4.plat5.setDozerInside();
            } else {
                map4.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map4.plat6.setDozerInside();
            } else {
                map4.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map4.plat7.setDozerInside();
            } else {
                map4.plat7.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map4.plat8.setDozerInside();
            } else {
                map4.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,0")) {
                map4.plat9.setDozerInside();
            } else {
                map4.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map4.plat10.setDozerInside();
            } else {
                map4.plat10.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map4.plat11.setDozerInside();
            } else {
                map4.plat11.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map4.plat12.setDozerInside();
            } else {
                map4.plat12.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map4.plat1.setVioletInside();
            } else {
                map4.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map4.plat2.setVioletInside();
            } else {
                map4.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map4.plat3.setVioletInside();
            } else {
                map4.plat3.noVioletInside();
            }
            if (violetPos.equals("0,3")) {
                map4.plat4.setVioletInside();
            } else {
                map4.plat4.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map4.plat5.setVioletInside();
            } else {
                map4.plat5.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map4.plat6.setVioletInside();
            } else {
                map4.plat6.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map4.plat7.setVioletInside();
            } else {
                map4.plat7.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map4.plat8.setVioletInside();
            } else {
                map4.plat8.noVioletInside();
            }
            if (violetPos.equals("2,0")) {
                map4.plat9.setVioletInside();
            } else {
                map4.plat9.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map4.plat10.setVioletInside();
            } else {
                map4.plat10.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map4.plat11.setVioletInside();
            } else {
                map4.plat11.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map4.plat12.setVioletInside();
            } else {
                map4.plat12.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map4.plat1.setDistructorInside();
            } else {
                map4.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map4.plat2.setDistructorInside();
            } else {
                map4.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map4.plat3.setDistructorInside();
            } else {
                map4.plat3.noDistructorInside();
            }
            if (distructorPos.equals("0,3")) {
                map4.plat4.setDistructorInside();
            } else {
                map4.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map4.plat5.setDistructorInside();
            } else {
                map4.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map4.plat6.setDistructorInside();
            } else {
                map4.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map4.plat7.setDistructorInside();
            } else {
                map4.plat7.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map4.plat8.setDistructorInside();
            } else {
                map4.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,0")) {
                map4.plat9.setDistructorInside();
            } else {
                map4.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map4.plat10.setDistructorInside();
            } else {
                map4.plat10.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map4.plat11.setDistructorInside();
            } else {
                map4.plat11.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map4.plat12.setDistructorInside();
            } else {
                map4.plat12.noDistructorInside();
            }
        }

        map4.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map4.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map4.plat4.setAmmo(platformAmmoTile.get("0,3").getType());
        map4.plat6.setAmmo(platformAmmoTile.get("1,1").getType());
        map4.plat7.setAmmo(platformAmmoTile.get("1,2").getType());
        map4.plat8.setAmmo(platformAmmoTile.get("1,3").getType());
        map4.plat9.setAmmo(platformAmmoTile.get("2,0").getType());
        map4.plat10.setAmmo(platformAmmoTile.get("2,1").getType());
        map4.plat11.setAmmo(platformAmmoTile.get("2,2").getType());

        map4.stamp();
    }










}
