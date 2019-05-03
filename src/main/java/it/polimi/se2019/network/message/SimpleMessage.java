package it.polimi.se2019.network.message;

import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.View;

import java.util.logging.Level;

public class SimpleMessage extends Message {

    public SimpleMessage(Object payload){
        super(payload);
    }

    @Override
    public void performAction(View view){
        HandyFunctions.LOGGER.log(Level.INFO, "This is a simple message!");
    }
}
