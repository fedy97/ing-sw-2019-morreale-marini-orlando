package it.polimi.se2019.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Custom logger created to simplify logging operation
 *
 * @author Gabriel Raul Marini
 */
public final class CustomLogger {

    public static void logInfo(String className, String msg) {
        Logger logger = Logger.getLogger(className);
        logger.log(Level.INFO, msg);
    }

    public static void logException(String className, Exception e) {
        String msg = "Exception has occured in " + className + ". Message is :" + e.getMessage();
        Logger logger = Logger.getLogger(className);
        logger.log(Level.WARNING, msg);
    }
}
