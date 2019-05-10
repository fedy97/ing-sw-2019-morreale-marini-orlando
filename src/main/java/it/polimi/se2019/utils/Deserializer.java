package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.Platform;

/**
 * Class providing service methods to match the server objects' reference with the corresponding
 * remote version
 */
public final class Deserializer {

    /**
     * @param light remote id of the platform
     * @return the reference to the target platform
     */
    public static Platform getPlatform(String light){
        return Controller.getInstance().getGame().getGameField().getPlatform(light);
    }

}
