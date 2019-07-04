package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.exceptions.NoInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Class used to read terminal input in the CLI
 * @author Simone Orlando
 */
public class CliReader {


    private int seconds;
    private Scanner scanner;
    private boolean stop;

    /**
     * @param seconds of waiting input for timer readings
     */
    CliReader(int seconds) {
        this.seconds = seconds;
        scanner = new Scanner(System.in);
        stop = false;
    }

    /**
     * Reads a string, if it is not entered within a certain time it stops waiting for input
     * @return the string read
     * @throws IOException in case of reading problems
     * @throws NoInputException if the input is not entered in the time provided
     */
    public String getTimedString() throws IOException, NoInputException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < seconds * 1000 && !in.ready()) {

        }
        if (in.ready()) {
            return in.readLine();
        }
        else {
            throw new NoInputException("Time out for input!");
        }
    }

    /**
     * Reads a int, if it is not entered within a certain time it stops waiting for input
     * @return the int read
     * @throws IOException in case of reading problems
     * @throws NoInputException if the input is not entered in the time provided
     */
    int getTimedInt() throws IOException, NoInputException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < seconds * 1000 && !in.ready() && !stop) {

        }
        if (in.ready()) {
            String temp = in.readLine();
            return Integer.parseInt(temp);
        }
        else {
            stopReader();
            throw new NoInputException("Time out for input!");
        }
    }

    /**
     * Read a string from standard input
     * @return the string read
     */
    public String getString() {
        return scanner.next();
    }

    /**
     * Read a int from standard input
     * @return the int read
     */
    public int getInt() {
        return scanner.nextInt();
    }

    /**
     * Sets the seconds to wait for input
     * @param seconds of waiting
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Stop waiting for input forcefully
     */
    private void stopReader() {
        this.stop = true;
    }

}
