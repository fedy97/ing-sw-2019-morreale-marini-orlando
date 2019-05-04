package it.polimi.se2019;


import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;
import it.polimi.se2019.view.client.cli.CLI;
import it.polimi.se2019.view.client.gui.GUI;

import java.util.Scanner;

public class MainClient {

    public static void main(String[] arg) {

        RemoteView myView;
        int choice;
        Scanner in = new Scanner(System.in);

        HandyFunctions.printConsole("Which interface do you want to use?\n<1> CLI\n<2> GUI\n: ");
        choice = in.nextInt();
        if (choice == 1) {
            myView = new CLI();
        }
        else {
            myView = new GUI();
        }

        myView.start();
    }
}
