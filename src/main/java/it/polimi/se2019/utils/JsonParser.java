package it.polimi.se2019.utils;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.*;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Orientation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Deque;

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
     * @return a deck of 36 ammos
     * @throws InvalidCardException
     */
    public Deck<AmmoCard> buildAmmoCards() throws InvalidCardException, InvalidDeckException, IOException {
        if (path.equals("/json/ammocards.json")) {
            ArrayList<AmmoCard> ammoCards = new ArrayList<>();
            AmmoCube[] arrAmmos;
            String pathImg;
            JSONArray ammosObj = jsonObj.getJSONArray("ammos");

            for (int i = 0; i < ammosObj.length(); i++) {
                JSONObject currAmmosObj = ammosObj.getJSONObject(i);
                boolean hasPowerup = currAmmosObj.getBoolean("powerup");
                AmmoCube a1 = AmmoCube.valueOf(currAmmosObj.getString("ammocube1"));
                AmmoCube a2 = AmmoCube.valueOf(currAmmosObj.getString("ammocube2"));
                pathImg = "/assets/ammos/" + currAmmosObj.getString("image") + ".png";

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
                AmmoCard amm = new AmmoCard(arrAmmos, hasPowerup, pathImg);
                ammoCards.add(amm);
            }
            Deck<AmmoCard> d = new Deck<>(36);
            d.addCards(ammoCards);
            d.mix();
            return d;
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
    public Platform[][] buildField(int numConfig, Deck<AmmoCard> deck) throws InvalidCardException,
            NoSuchFieldException, IllegalAccessException, NoCardException {
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
                    platCol = HandyFunctions.stringToColor(nameCol);
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

    /**
     * @return a deck of 24 powerups
     * @throws InvalidDeckException
     * @throws IOException
     * @throws InvalidCubeException
     * @throws InvalidImageException
     * @throws InvalidNameException
     */
    public Deck<PowerUpCard> buildPowerupCards() throws InvalidDeckException, IOException,
            InvalidCubeException, InvalidNameException {
        if (path.equals("/json/powerups.json")) {
            ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
            AmmoCube ammo;
            String name;
            String description;
            String pathImg;
            PowerUpCard powerUpCard;
            JSONArray powerupsObj = jsonObj.getJSONArray("powerups");
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < powerupsObj.length(); i++) {
                    JSONObject currPowerupsObj = powerupsObj.getJSONObject(i);
                    name = currPowerupsObj.getString("name");
                    description = currPowerupsObj.getString("description");
                    JSONArray jArrAmmocube = currPowerupsObj.getJSONArray("ammoCube");
                    JSONArray jArrImage = currPowerupsObj.getJSONArray("image");
                    for (int j = 0; j < jArrAmmocube.length(); j++) {
                        ammo = AmmoCube.valueOf(jArrAmmocube.get(j).toString());
                        pathImg = jArrImage.getString(j) + ".jpg";
                        if (i == 0) powerUpCard = new TargettingScope(ammo, name, description, pathImg);
                        else if (i == 1) powerUpCard = new Newton(ammo, name, description, pathImg);
                        else if (i == 2) powerUpCard = new TagbackGrenade(ammo, name, description, pathImg);
                        else powerUpCard = new Teleporter(ammo, name, description, pathImg);
                        powerUpCards.add(powerUpCard);
                    }
                }
            }
            Deck<PowerUpCard> d = new Deck<>(24);
            d.addCards(powerUpCards);
            d.mix();
            return d;
        }
        return null;
    }

    public Deck<WeaponCard> buildWeaponCards() throws InvalidDeckException, IOException,
            InvalidCubeException, InvalidNameException {
        if (path.equals("/json/weapons.json")) {
            ArrayList<WeaponCard> weaponCards = new ArrayList<>();
            AmmoCube paidCost;
            String name;
            String description;
            String pathImg;
            WeaponCard weaponCard;
            AmmoCube[] extraCost;
            JSONArray weaponsObj = jsonObj.getJSONArray("weapons");
            for (int i = 0; i < weaponsObj.length(); i++) {
                JSONObject currWeaponObj = weaponsObj.getJSONObject(i);
                name = currWeaponObj.getString("name");
                description = currWeaponObj.getString("description");
                pathImg = name.replace(' ','_') + ".png";
                paidCost = AmmoCube.valueOf(currWeaponObj.getString("paidCost"));
                JSONArray jArrExtraCost = currWeaponObj.getJSONArray("extraCost");
                if (!jArrExtraCost.get(0).toString().equals("")) {
                    extraCost = new AmmoCube[jArrExtraCost.length()];
                    for (int j = 0; j < jArrExtraCost.length(); j++)
                        extraCost[j] = AmmoCube.valueOf((String) jArrExtraCost.get(j));
                }
                else extraCost = null;
                weaponCard = new WeaponCard(name,description,pathImg,paidCost,extraCost);
                weaponCards.add(weaponCard);
            }

            Deck<WeaponCard> d = new Deck<>(24);
            d.addCards(weaponCards);
            d.mix();
            return d;
        }
        return null;
    }
}
