package it.polimi.se2019.utils;

import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Federico Morreale
 */

public class JsonParser {

    private String path;
    private JSONObject jsonObj;

    public JsonParser(String path) {
        this.path = path;
        InputStream is = getClass().getResourceAsStream(path);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder ris = new StringBuilder();
        try {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                ris.append(line).append("\n");
            }
            jsonObj = new JSONObject(ris.toString());
        } catch (IOException ex) {

        }
    }

    /**
     * @return an arraylist of 7 different types of ammo cards,
     * ready to be added to a real deck of 36 ammos
     * @throws InvalidCardException
     */
    public ArrayList<AmmoCard> buildAmmoCards() throws InvalidCardException {
        if (path.equals("/json/ammocards.json")) {
            ArrayList<AmmoCard> ammoCards = new ArrayList<>();
            AmmoCube[] arrAmmos;
            JSONArray ammosObj = jsonObj.getJSONArray("ammos");

            for (int i = 0; i < ammosObj.length(); i++) {
                JSONObject currAmmosObj = ammosObj.getJSONObject(i);
                boolean hasPowerup = currAmmosObj.getBoolean("powerup");
                AmmoCube a1 = AmmoCube.valueOf(currAmmosObj.getString("ammocube1"));
                AmmoCube a2 = AmmoCube.valueOf(currAmmosObj.getString("ammocube2"));
                if (!hasPowerup) {
                    AmmoCube a3 = AmmoCube.valueOf(currAmmosObj.getString("ammocube3"));
                    arrAmmos = new AmmoCube[3];
                    arrAmmos[0] = a1;
                    arrAmmos[1] = a2;
                    arrAmmos[2] = a3;
                } else {
                    arrAmmos = new AmmoCube[2];
                    arrAmmos[0] = a1;
                    arrAmmos[1] = a2;
                }
                AmmoCard amm = new AmmoCard(arrAmmos, hasPowerup);
                ammoCards.add(amm);
            }
            return ammoCards;
        }
        return null;
    }

    public Platform[][] buildField() {
        return null;
    }
}
