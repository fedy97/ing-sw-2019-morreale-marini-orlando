package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.scene.control.cell.CheckBoxListCell;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Which interface do you want to use?            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <1> CLI                                    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <2> GUI                                    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                        ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxConnectionMessage() {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Choose a connection type:                      ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <1> Socket                                 ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <2> Rmi                                    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                        ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxUsernameMessage() {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Username: ");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                     ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Ip:                                            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxIpMessage(String usename) {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Username: "+usename);
        for (int i=0; i < 37-usename.length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Ip: ");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                           ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    public static void boxWaitingMessage() {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               Waiting for other players...               ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }

    public static void boxLobbyMessage(List<String> users) {
        if (users.size() == 1) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 2) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 3) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 4) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(4) + " joined the game");
            for (int i = 0; i < 40 - (users.get(4)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
    }

    public static void timerMessage(int count) {
        HandyFunctions.printConsole("\r\t\t\t\t\t                  The game will start in: "+count);
    }

    public static void possibleMapsMessage(int timer, int[] vote) {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                      Choose the map                      ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃    <1> 3-4-4(votes: "+vote[0]+")            <3> 3-4-3(votes: "+vote[2]+")    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃    <2> 4-4-3(votes: "+vote[1]+")            <4> 4-4-4(votes: "+vote[3]+")    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter.stamp("            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter.stamp("\t\t\t\t\t  Time left: "+timer);
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
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                   Choose the character                   ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃  <1> Banshee "+cBan+"          <2> Sprog "+cSpr+"         <3> Dozer "+cDoz+"  ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃            <4> Violet "+cVio+"          <5> Distructor "+cDis+"        ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter.stamp("            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter.stamp("\t\t\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
    }

    public static void weaponBox(CliColor color, List<CardRep> powWeapons) {
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
        CliPrinter.stamp("\n\t\t\t\t\t");

        map1.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox();
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
        CliPrinter.stamp("\n\t\t\t\t\t");

        map2.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox();
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
        CliPrinter.stamp("\n\t\t\t\t\t");

        map3.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox();
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
        CliPrinter.stamp("\n\t\t\t\t\t");

        map4.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox();
        CliSetUp.cursorDown(20);
    }


    public static void choosePowerUpMessage(CardRep p1, CardRep p2) {
        CliPrinter.stamp("\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t ┃                                 Choose a powerup:                                          ┃\n");
        CliPrinter.stamp("\t ┃                                                                                            ┃\n");
        CliPrinter.stamp("\t ┃                                      <1> " + p1.getTitle());
        for (int i=0; i < 50 - p1.getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliPrinter.stamp("\t ┃                                                                                            ┃\n");
        CliPrinter.stamp("\t ┃                                      <2> " + p2.getTitle());
        for (int i=0; i < 50 - p2.getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliPrinter.stamp("\t ┃                                                                                            ┃\n");
        CliPrinter.stamp("\t ┃                                 press the <key> followed by enter                          ┃\n");
        CliPrinter.stamp("\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }

    public static void drawPlayersInfoBox() {
        stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(40);
        CliSetUp.cursorDown(1);
        for (int i=0;i<30;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorUp(30);
        CliSetUp.cursorLeft(1);
        for (int i=0;i<30;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        CliSetUp.cursorUp(30);
        CliSetUp.cursorLeft(26);
        CliPrinter.stamp("PLAYERS' INFO");
        CliSetUp.cursorDown(2);
        CliSetUp.cursorLeft(24);
    }

    public static void printPlatformWeapons(LightGameVersion lightGameVersion) {
        CliPrinter.weaponBox(CliColor.TEXTRED, lightGameVersion.getPlatformWeapons().get("1,0"));
        CliPrinter.weaponBox(CliColor.TEXTBLUE,lightGameVersion.getPlatformWeapons().get("0,2"));
        CliPrinter.weaponBox(CliColor.TEXTYELLOW,lightGameVersion.getPlatformWeapons().get("2,3"));
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
