package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.scene.control.cell.CheckBoxListCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        CliPrinter.stamp("\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter.stamp("            ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter.stamp("\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
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
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                   Choose the character                   ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃  <1> Banshee "+cBan+"          <2> Sprog "+cSpr+"         <3> Dozer "+cDoz+"  ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃            <4> Violet "+cVio+"          <5> Distructor "+cDis+"        ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter.stamp("            ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter.stamp("\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
    }

    private static void weaponBox(CliColor color, List<CardRep> powWeapons) {
        CliSetUp.savePosition();
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃     "+powWeapons.get(0).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(0).getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃                             ┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃     "+powWeapons.get(1).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(1).getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃                             ┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃     "+powWeapons.get(2).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(2).getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(2);

    }

    public static void printMap1(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliSetUp.savePosition();
        CliPrinter.stamp("\n");

        CliPrinter.stamp("\t\t\t     ┏━━━━━━━━━━━┓", CliColor.TEXTRED); CliPrinter.stamp("┏━━━━━━━━━━━┓┏━━━━━━━━━━━┓\n",CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃    ", CliColor.TEXTRED); printAmmo(ammoReps.get(0).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTRED); CliPrinter.stamp("┃    ",CliColor.TEXTCYAN); CliPrinter.printAmmo(ammoReps.get(1).getType()); CliPrinter.stamp("         ",CliColor.TEXTCYAN); CliPrinter.printAmmo(ammoReps.get(2).getType()); CliPrinter.stamp("     ┃\n", CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃           ┗", CliColor.TEXTRED); CliPrinter.stamp("┛                        ┃\n",CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃           ┏", CliColor.TEXTRED); CliPrinter.stamp("┓                        ┃\n",CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┗━━━━┓ ┏━━━━┛┗━━━━┓ ┏━━━━┛\n",CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┏━━━━┛ ┗━━━━━━━━━━┛ ┗━━━━┓", CliColor.TEXTPURPLE); CliPrinter.stamp("┏━━━━━━━━━━━┓\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃    ", CliColor.TEXTRED); printAmmo(ammoReps.get(4).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTRED); CliPrinter.stamp("┃   ", CliColor.TEXTPURPLE); printAmmo(ammoReps.get(5).getType()); CliPrinter.stamp("          ", CliColor.TEXTPURPLE); printAmmo(ammoReps.get(6).getType()); CliPrinter.stamp("     ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃    ",CliColor.TEXTYELLOW); printAmmo(ammoReps.get(7).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃                        ┗", CliColor.TEXTPURPLE); CliPrinter.stamp("┛           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃                        ┏", CliColor.TEXTPURPLE); CliPrinter.stamp("┓           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃                        ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┗━━━━┓ ┏━━━━┛", CliColor.TEXTRED); CliPrinter.stamp("┗━━━━┓ ┏━━━━━━━━━━━━━━━━━┛", CliColor.TEXTPURPLE); CliPrinter.stamp("┃           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┏━━━━┛ ┗━━━━━━━━━━┛ ┗━━━━━━━━━━━━━━━━━┓", CliColor.TEXTWHITE); CliPrinter.stamp("┃           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃   ", CliColor.TEXTWHITE); printAmmo(ammoReps.get(8).getType()); CliPrinter.stamp("          ", CliColor.TEXTWHITE); printAmmo(ammoReps.get(9).getType()); CliPrinter.stamp("          ", CliColor.TEXTWHITE); printAmmo(ammoReps.get(10).getType()); CliPrinter.stamp("     ┃", CliColor.TEXTWHITE); CliPrinter.stamp("┃    ",CliColor.TEXTYELLOW); printAmmo(ammoReps.get(11).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                                     ┗", CliColor.TEXTWHITE); CliPrinter.stamp("┛           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                                     ┏", CliColor.TEXTWHITE); CliPrinter.stamp("┓           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                                     ┃", CliColor.TEXTWHITE); CliPrinter.stamp("┃           ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛", CliColor.TEXTWHITE); CliPrinter.stamp("┗━━━━━━━━━━━┛\n",CliColor.TEXTYELLOW);

        CliSetUp.restorePosition();
        CliPrinter.stamp("                                                                                   ");
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
    }

    public static void printMap2(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliSetUp.savePosition();
        CliPrinter.stamp("\n");

        CliPrinter.stamp("\t\t\t     ┏━━━━━━━━━━━┓┏━━━━━━━━━━━┓┏━━━━━━━━━━━┓",CliColor.TEXTCYAN); CliPrinter.stamp("┏━━━━━━━━━━━┓\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃    ",CliColor.TEXTCYAN); printAmmo(ammoReps.get(0).getType()); CliPrinter.stamp("          ", CliColor.TEXTCYAN); CliPrinter.printAmmo(ammoReps.get(1).getType()); CliPrinter.stamp("          ", CliColor.TEXTCYAN); CliPrinter.printAmmo(ammoReps.get(2).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTCYAN); CliPrinter.stamp("┃    ",CliColor.TEXTGREEN); CliPrinter.printAmmo(ammoReps.get(3).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃                                     ┗",CliColor.TEXTCYAN); CliPrinter.stamp("┛           ┃\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃                                     ┏",CliColor.TEXTCYAN); CliPrinter.stamp("┓           ┃\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃                                     ┃",CliColor.TEXTCYAN); CliPrinter.stamp("┃           ┃\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┗━━━━┓ ┏━━━━┛┗━━━━━━━━━━━┛┗━━━━┓ ┏━━━━┛",CliColor.TEXTCYAN); CliPrinter.stamp("┗━━━━┓ ┏━━━━┛\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┏━━━━┛ ┗━━━━┓┏━━━━━━━━━━━┓",CliColor.TEXTRED); CliPrinter.stamp("┏━━━━┛ ┗━━━━━━━━━━┛ ┗━━━━┓\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃    ",CliColor.TEXTRED); printAmmo(ammoReps.get(4).getType()); CliPrinter.stamp("          ",CliColor.TEXTRED); printAmmo(ammoReps.get(5).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTRED); CliPrinter.stamp("┃    ",CliColor.TEXTYELLOW); printAmmo(ammoReps.get(6).getType()); CliPrinter.stamp("          ", CliColor.TEXTYELLOW); printAmmo(ammoReps.get(7).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┃",CliColor.TEXTRED); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┃",CliColor.TEXTRED); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┃",CliColor.TEXTRED); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┗━━━━━━━━━━━┛┗━━━━┓ ┏━━━━┛",CliColor.TEXTRED); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┏━━━━┛ ┗━━━━┓",CliColor.TEXTWHITE); CliPrinter.stamp("┃                        ┃ \n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃    ",CliColor.TEXTWHITE); printAmmo(ammoReps.get(9).getType()); CliPrinter.stamp("    ┃",CliColor.TEXTWHITE); CliPrinter.stamp("┃    ",CliColor.TEXTYELLOW); printAmmo(ammoReps.get(10).getType()); CliPrinter.stamp("          ", CliColor.TEXTYELLOW); printAmmo(ammoReps.get(11).getType()); CliPrinter.stamp("    ┃ \n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃           ┗",CliColor.TEXTWHITE); CliPrinter.stamp("┛                        ┃ \n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃           ┏",CliColor.TEXTWHITE); CliPrinter.stamp("┓                        ┃ \n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃           ┃",CliColor.TEXTWHITE); CliPrinter.stamp("┃                        ┃ \n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┗━━━━━━━━━━━┛",CliColor.TEXTWHITE); CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━┛ \n",CliColor.TEXTYELLOW);

        CliSetUp.restorePosition();
        CliPrinter.stamp("                                                                                   ");
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
    }

    public static void printMap3(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliSetUp.savePosition();
        CliPrinter.stamp("\n");

        CliPrinter.stamp("\t\t\t     ┏━━━━━━━━━━━┓┏━━━━━━━━━━━┓┏━━━━━━━━━━━┓\n", CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃    ", CliColor.TEXTCYAN); printAmmo(ammoReps.get(0).getType()); CliPrinter.stamp("          ", CliColor.TEXTCYAN); printAmmo(ammoReps.get(1).getType()); CliPrinter.stamp("          ", CliColor.TEXTCYAN); printAmmo(ammoReps.get(2).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃                                     ┃\n", CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃                                     ┃\n", CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┃                                     ┃\n", CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┗━━━━┓ ┏━━━━┛┗━━━━━━━━━━━┛┗━━━━┓ ┏━━━━┛\n", CliColor.TEXTCYAN);
        CliPrinter.stamp("\t\t\t     ┏━━━━┛ ┗━━━━┓┏━━━━━━",CliColor.TEXTRED); CliPrinter.stamp("━━━━━┓┏━━━━┛ ┗━━━━┓", CliColor.TEXTPURPLE); CliPrinter.stamp("┏━━━━━━━━━━━┓\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃    ",CliColor.TEXTRED); printAmmo(ammoReps.get(4).getType()); CliPrinter.stamp("          ", CliColor.TEXTRED); printAmmo(ammoReps.get(5).getType()); CliPrinter.stamp("", CliColor.TEXTRED); CliPrinter.stamp("          ", CliColor.TEXTPURPLE); printAmmo(ammoReps.get(6).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃    ", CliColor.TEXTYELLOW); printAmmo(ammoReps.get(7).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                   ",CliColor.TEXTRED); CliPrinter.stamp("                  ┗", CliColor.TEXTPURPLE); CliPrinter.stamp("┛           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                   ",CliColor.TEXTRED); CliPrinter.stamp("                  ┏", CliColor.TEXTPURPLE); CliPrinter.stamp("┓           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                   ",CliColor.TEXTRED); CliPrinter.stamp("                  ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┗━━━━━━━━━━━━━━━━━┓",CliColor.TEXTRED); CliPrinter.stamp(" ┏━━━━━━━━━━━━━━━━━┛", CliColor.TEXTPURPLE); CliPrinter.stamp("┃           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┏━━━━┛ ┗━━━━┓┏━━━━━━━━━━━┓",CliColor.TEXTWHITE); CliPrinter.stamp("┃           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃    ",CliColor.TEXTWHITE); printAmmo(ammoReps.get(9).getType()); CliPrinter.stamp("          ", CliColor.TEXTWHITE); printAmmo(ammoReps.get(10).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTWHITE); CliPrinter.stamp("┃    ", CliColor.TEXTYELLOW); printAmmo(ammoReps.get(11).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃                        ┗",CliColor.TEXTWHITE); CliPrinter.stamp("┛           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃                        ┏",CliColor.TEXTWHITE); CliPrinter.stamp("┓           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┃                        ┃",CliColor.TEXTWHITE); CliPrinter.stamp("┃           ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t                  ┗━━━━━━━━━━━━━━━━━━━━━━━━┛",CliColor.TEXTWHITE); CliPrinter.stamp("┗━━━━━━━━━━━┛\n", CliColor.TEXTYELLOW);

        CliSetUp.restorePosition();
        CliPrinter.stamp("                                                                                   ");
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
    }

    public static void printMap4(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliSetUp.savePosition();
        CliPrinter.stamp("\n");

        CliPrinter.stamp("\t\t\t     ┏━━━━━━━━━━━┓", CliColor.TEXTRED); CliPrinter.stamp("┏━━━━━━━━━━━┓┏━━━━━━━━━━━┓", CliColor.TEXTCYAN); CliPrinter.stamp("┏━━━━━━━━━━━┓\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃    ", CliColor.TEXTRED); printAmmo(ammoReps.get(0).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTRED); CliPrinter.stamp("┃    ", CliColor.TEXTCYAN); printAmmo(ammoReps.get(1).getType()); CliPrinter.stamp("          ", CliColor.TEXTCYAN); printAmmo(ammoReps.get(2).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTCYAN); CliPrinter.stamp("┃    ",CliColor.TEXTGREEN); printAmmo(ammoReps.get(3).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃           ┗", CliColor.TEXTRED); CliPrinter.stamp("┛                        ┗", CliColor.TEXTCYAN); CliPrinter.stamp("┛           ┃\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃           ┏", CliColor.TEXTRED); CliPrinter.stamp("┓                        ┏", CliColor.TEXTCYAN); CliPrinter.stamp("┓           ┃\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃                        ┃", CliColor.TEXTCYAN); CliPrinter.stamp("┃           ┃\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┗━━━━┓ ┏━━━━┛┗━━━━┓ ┏━━━━┛", CliColor.TEXTCYAN); CliPrinter.stamp("┗━━━━┓ ┏━━━━┛\n",CliColor.TEXTGREEN);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┏━━━━┛ ┗━━━━┓", CliColor.TEXTPURPLE); CliPrinter.stamp("┏━━━━┛ ┗━━━━━━━━━━┛ ┗━━━━┓\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃    ", CliColor.TEXTRED); printAmmo(ammoReps.get(4).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTRED); CliPrinter.stamp("┃    ", CliColor.TEXTPURPLE); printAmmo(ammoReps.get(5).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃    ",CliColor.TEXTYELLOW); printAmmo(ammoReps.get(6).getType()); CliPrinter.stamp("          ", CliColor.TEXTYELLOW); printAmmo(ammoReps.get(7).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃           ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃           ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃           ┃", CliColor.TEXTRED); CliPrinter.stamp("┃           ┃", CliColor.TEXTPURPLE); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┗━━━━┓ ┏━━━━┛", CliColor.TEXTRED); CliPrinter.stamp("┗━━━━┓ ┏━━━━┛", CliColor.TEXTPURPLE); CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┏━━━━┛ ┗━━━━━━━━━━┛ ┗━━━━┓", CliColor.TEXTWHITE);  CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃    ", CliColor.TEXTWHITE); printAmmo(ammoReps.get(8).getType()); CliPrinter.stamp("          ", CliColor.TEXTWHITE); printAmmo(ammoReps.get(9).getType()); CliPrinter.stamp("    ┃", CliColor.TEXTWHITE); CliPrinter.stamp("┃    ",CliColor.TEXTYELLOW); printAmmo(ammoReps.get(10).getType()); CliPrinter.stamp("          ", CliColor.TEXTYELLOW); printAmmo(ammoReps.get(11).getType()); CliPrinter.stamp("    ┃\n", CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┃", CliColor.TEXTWHITE) ; CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┗", CliColor.TEXTWHITE) ; CliPrinter.stamp("┛                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┏", CliColor.TEXTWHITE) ; CliPrinter.stamp("┓                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┃", CliColor.TEXTWHITE) ; CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┃                        ┃", CliColor.TEXTWHITE) ; CliPrinter.stamp("┃                        ┃\n",CliColor.TEXTYELLOW);
        CliPrinter.stamp("\t\t\t     ┗━━━━━━━━━━━━━━━━━━━━━━━━┛", CliColor.TEXTWHITE) ; CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━┛\n",CliColor.TEXTYELLOW);

        CliSetUp.restorePosition();
        CliPrinter.stamp("                                                                                   ");
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
    }

    private static void printAmmo(String type) {
        if(type.length() == 2) {
            CliPrinter.stamp("★",CliColor.TEXTWHITE);
            for(int i=0; i<2;i++) {
                if(type.charAt(i) == 'r') {
                    CliPrinter.stamp("■",CliColor.TEXTRED);
                }
                else if (type.charAt(i) == 'b') {
                    CliPrinter.stamp("■",CliColor.TEXTBLUE);
                }
                else {
                    CliPrinter.stamp("■",CliColor.TEXTYELLOW);
                }
            }
        }
        else {
            for(int i=0; i<3;i++) {
                if(type.charAt(i) == 'r') {
                    CliPrinter.stamp("■",CliColor.TEXTRED);
                }
                else if (type.charAt(i) == 'b') {
                    CliPrinter.stamp("■",CliColor.TEXTBLUE);
                }
                else {
                    CliPrinter.stamp("■",CliColor.TEXTYELLOW);
                }
            }
        }

    }

    public static void choosePowerUpMessage(CardRep p1, CardRep p2) {
        CliPrinter.stamp("\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t ┃                    Choose a powerup:                     ┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃         <1> " + p1.getTitle());
        for (int i=0; i < 45 - p1.getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃         <2> " + p2.getTitle());
        for (int i=0; i < 45 - p2.getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliPrinter.stamp("\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t ┃             press the <key> followed by enter            ┃\n");
        CliPrinter.stamp("\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }

}
