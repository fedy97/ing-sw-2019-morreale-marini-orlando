package it.polimi.se2019.network;

import it.polimi.se2019.utils.Loggable;

import java.io.Serializable;
import java.util.logging.Level;

public class Message extends Loggable implements Serializable {
    public Message(){
        super();
        LOGGER.log(Level.INFO, "This is a loggable class messagge!");
    }
}
