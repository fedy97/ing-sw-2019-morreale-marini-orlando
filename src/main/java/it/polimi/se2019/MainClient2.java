package it.polimi.se2019;


import it.polimi.se2019.utils.HandyFunctions;
//import it.polimi.se2019.utils.Track;
import it.polimi.se2019.view.client.RemoteView;
import it.polimi.se2019.view.client.cli.CLI;
import it.polimi.se2019.view.client.cli.CliPrinter;
import it.polimi.se2019.view.client.cli.CliSetUp;
import it.polimi.se2019.view.client.gui.LoginPage;


import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
//I duplicated this one to run 2 different clients on intellij
public class MainClient2 {

    public static void main(String[] args) {

        //Path path = Paths.get(URI.create(ClassLoader.getSystemResource("soundtrack.wav").toString()));

        //Thread track = new Thread(new Track(path.toString()));
        /*
        la lascio commentata per ora cosi non parte sempre
        track.start();
         */

        int choice;
        Scanner in = new Scanner(System.in);
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxInterfaceMessage();
        choice = in.nextInt();
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        if (choice == 1) {
            RemoteView myView = new CLI();
            myView.start();
        }
        else {
            LoginPage.startLoginPage();
        }


    }
}