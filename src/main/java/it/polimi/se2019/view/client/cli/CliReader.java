package it.polimi.se2019.view.client.cli;

import java.util.Scanner;
import java.util.Timer;

/**
 * @author Simone Orlando
 */
public class CliReader {

    Scanner in;
    Timer timer;

    public CliReader() {
        in = new Scanner(System.in);
    }

    public String getString() {

        return in.next();
    }

    public int getInt() {
        return in.nextInt();
    }

    private void startTimer() {
        //TODO
    }

    private void checkTimer() {
        //TODO
    }
}
