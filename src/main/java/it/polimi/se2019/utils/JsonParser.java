package it.polimi.se2019.utils;

import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    public ArrayList<AmmoCard> buildAmmoCards() {
        return null;
    }

    public Platform[][] buildField() {
        return null;
    }
}
