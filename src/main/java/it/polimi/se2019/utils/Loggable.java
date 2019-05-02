package it.polimi.se2019.utils;

import java.util.logging.Logger;

public class Loggable {
    protected final Logger LOGGER;

    public Loggable(){
        LOGGER = Logger.getLogger(Class.class.getName());
    }
}
