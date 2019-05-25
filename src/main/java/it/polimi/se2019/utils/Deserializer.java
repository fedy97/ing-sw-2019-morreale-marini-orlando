package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.player.Player;

import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;

/**
 * Class providing service methods to match the server objects' with the corresponding
 * remote version
 */
public final class Deserializer {

    /**
     * @param light remote id of the platform
     * @return the reference to the target platform
     */
    public static Platform getPlatform(String light) {
        try {
            int pos[] = new int[2];
            pos[0] = Integer.parseInt(light.substring(0, 1));
            pos[1] = Integer.parseInt(light.substring(2, 3));
            return Controller.getInstance().getGame().getGameField().getPlatform(pos);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    /**
     * @param light hashcode of the WeaponCard received from the client
     * @return the server reference of the WeaponCard
     */
    public static WeaponCard getWeapon(String light) {
        List<WeaponCard> weapons = Controller.getInstance().getPlayerManager().getCurrentPlayer().getWeaponCards();

        if (light.equals("null")) {
            WeaponCard nullCard = new WeaponCard();
            nullCard.setName("null");
            return nullCard;
        }

        for (WeaponCard weaponCard : weapons) {
            if (weaponCard.toString().equals(light))
                return weaponCard;
        }
        for (Platform platform : Game.getInstance().getGameField().getPlatforms()) {
            try {
                for (WeaponCard weaponCard : platform.getWeapons()) {
                    if (weaponCard.toString().equals(light))
                        return weaponCard;
                }
            } catch (InvalidGenerationSpotException ex) {
            }

        }

        return null;
    }

    /**
     * @param light hashcode of the PowerUp received from the client
     * @param owner of the PowerUp card
     * @return the server reference of the PowerUp
     */
    public static PowerUpCard getPowerUp(int light, String owner) {
        Player player = Controller.getInstance().getGame().getPlayer(owner);
        for (PowerUpCard powerUpCard : player.getPowerUpCards()) {
            if (Integer.toString(powerUpCard.hashCode()).equals(Integer.toString(light)))
                return powerUpCard;
        }
        return null;
    }


}
