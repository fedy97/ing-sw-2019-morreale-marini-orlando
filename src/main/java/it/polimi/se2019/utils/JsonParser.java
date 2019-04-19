package it.polimi.se2019.utils;

import it.polimi.se2019.exceptions.InvalidCardException;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Orientation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
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

    /**
     * @param numConfig there are 4 configurations of the field in json file
     * @param deck      of the ammocards, it has to be full
     * @return the matrix 3x4 of the platforms
     * @throws NoCardException
     * @throws InvalidCardException
     */
    public Platform[][] buildField(int numConfig, Deck<AmmoCard> deck) throws NoCardException, InvalidCardException,
            NoSuchFieldException, IllegalAccessException {
        if (path.equals("/json/field.json")) {
            Platform[][] field = new Platform[3][4];
            int[] pos;
            boolean isGenSpot;
            Color platCol;
            AmmoCard ammoC;
            ArrayList<Orientation> arrOr;
            JSONArray configObj = jsonObj.getJSONArray("config" + numConfig);

            for (int i = 0; i < configObj.length(); i++) {
                arrOr = new ArrayList<>();
                JSONObject currPlatformObj = configObj.getJSONObject(i);
                JSONArray jarr = currPlatformObj.getJSONArray("orientation");
                pos = new int[2];
                pos[1] = currPlatformObj.getInt("x");
                pos[0] = currPlatformObj.getInt("y");
                if (!currPlatformObj.getString("platformColor").isEmpty()) {
                    for (int j = 0; j < jarr.length(); j++)
                        if (!jarr.get(j).toString().isEmpty()) arrOr.add(Orientation.valueOf(jarr.get(j).toString()));
                    isGenSpot = currPlatformObj.getBoolean("isGenerationSpot");
                    String nameCol = currPlatformObj.getString("platformColor");
                    platCol = (Color) Color.class.getField(nameCol).get(null);
                    ammoC = deck.drawCard();
                    Platform p = new Platform(pos, isGenSpot, ammoC, platCol, arrOr);
                    field[pos[0]][pos[1]] = p;
                } else
                    field[pos[0]][pos[1]] = null;
            }
            return field;
        }
        return null;
    }
}
