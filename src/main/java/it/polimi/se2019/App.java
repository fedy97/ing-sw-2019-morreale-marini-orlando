package it.polimi.se2019;

import it.polimi.se2019.exceptions.NoInputException;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.cli.CliReader;

import java.io.IOException;

/*
 * Hello world!
 */
public class App {

    public static void main(String[] arg) throws IOException {
        CliReader reader = new CliReader(10);
        HandyFunctions.printConsole("input: ");
        try {
            int in = reader.getTimedInt();
            HandyFunctions.printConsole(in);
        }
        catch (NoInputException e){
            HandyFunctions.printConsole("Time out!\n");
        }
    }

}