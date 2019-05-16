package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.player.Player;

import java.util.List;

/**
 * Class providing service methods to match the server objects' reference with the corresponding
 * remote version
 */
public final class Deserializer {

    /**
     * @param light remote id of the platform
     * @return the reference to the target platform
     */
    public static Platform getPlatform(String light) {
        return Controller.getInstance().getGame().getGameField().getPlatform(light);
    }

    /**
     *
     */
    public static WeaponCard getWeapon(String light) {
        List<WeaponCard> weapons = Controller.getInstance().getPlayerManager().getCurrentPlayer().getWeaponCards();
        for(WeaponCard weaponCard: weapons){
            if(Integer.toString(weaponCard.hashCode()).equals(light))
                return weaponCard;
        }
        return null;
    }

    /**
     *
     */
    public static PowerUpCard getPowerUp(String light, String owner) {
        Player player = Controller.getInstance().getGame().getPlayer(owner);
        for(PowerUpCard powerUpCard: player.getPowerUpCards()){
            if(Integer.toString(powerUpCard.hashCode()).equals(light))
                return powerUpCard;
        }
        return null;
    }


}
