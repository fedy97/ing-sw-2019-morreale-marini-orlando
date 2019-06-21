package it.polimi.se2019.model;

import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.model.player.PlayerBoard;

import java.util.EnumMap;
import java.util.Map;


/**
 * @author Gabriel Raul Marini
 */
public final class PointsCounter {

    private PointsCounter(){}

    /**
     * @param player killed during the turn
     * @return a map containing the points assigned to each player
     */
    public static Map<Character, Integer> getPoints(Player player) {
        PlayerBoard board = player.getPlayerBoard();
        Map<Character, Integer> res = new EnumMap<>(Character.class);
        Map<Character, Integer> maxDamage = new EnumMap<>(Character.class);
        Character firstDamage = board.getDamageLine().get(0);
        res.put(firstDamage, 1);

        for (Character character : board.getDamageLine()) {
            if (maxDamage.containsKey(character))
                maxDamage.replace(character, maxDamage.get(character) + 1);
            else
                maxDamage.put(character, 1);
        }

        int i = 0;

        while (!maxDamage.isEmpty()) {
            Character currCharacter = null;
            int max = 0;

            for (Character key : maxDamage.keySet()) {
                if (maxDamage.get(key) > max) {
                    currCharacter = key;
                    max = maxDamage.get(key);
                }
            }

            if (res.containsKey(currCharacter))
                res.replace(currCharacter, res.get(currCharacter) + PointsBattery.getPointsValue(player.getNumOfDeaths())[i]);
            else
                res.put(currCharacter, PointsBattery.getPointsValue(player.getNumOfDeaths())[i]);

            maxDamage.remove(currCharacter);
            i++;
        }

        return res;
    }
}