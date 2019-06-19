package it.polimi.se2019.model;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

/**
 * @author Federico Morreale
 */
public class Game extends Observable implements Serializable {

    private static final long serialVersionUID = -8611008619219526728L;
    private static Game instance = null;
    private GameField gameField;
    private ArrayList<Player> players;
    private Deck<WeaponCard> weaponsDeck;
    private Deck<PowerUpCard> powerUpDeck;
    private Deck<PowerUpCard> garbageDeck;
    private Deck<AmmoCard> ammoDeck;
    private Map<Character, Player> characterPlayers;

    /**
     * Game singleton constructor
     *
     * @return instance
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.players = new ArrayList<>();

            instance.characterPlayers = new EnumMap<>(Character.class);
            instance.characterPlayers.put(Character.BANSHEE, null);
            instance.characterPlayers.put(Character.VIOLET, null);
            instance.characterPlayers.put(Character.SPROG, null);
            instance.characterPlayers.put(Character.DISTRUCTOR, null);
            instance.characterPlayers.put(Character.DOZER, null);
        }
        return instance;
    }

    public static void setInstance(Game instance) {
        Game.instance = instance;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    /**
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return The deck containing the weapon cards
     */
    public Deck<WeaponCard> getWeaponsDeck() {
        return weaponsDeck;
    }

    /**
     * @return The deck containing the power up cards
     */
    public Deck<PowerUpCard> getPowerUpDeck() {
        return powerUpDeck;
    }

    /**
     * @param powerUps The deck containing the power up cards
     */
    public void setPowerUpDeck(Deck<PowerUpCard> powerUps) {
        powerUpDeck = powerUps;
    }

    /**
     * @return The power up cards already used
     */
    public Deck<PowerUpCard> getGarbageDeck() {
        return garbageDeck;
    }

    /**
     * @return The deck containing the ammo card
     */
    public Deck<AmmoCard> getAmmoDeck() {
        return ammoDeck;
    }

    public void setAmmoDeck(Deck<AmmoCard> ammoDeck) {
        this.ammoDeck = ammoDeck;
    }

    /**
     * @return The map that connects player with character
     */
    public Map<Character, Player> getCharacterPlayers() {
        return characterPlayers;
    }

    /**
     * @param player to be added to the match
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * @param player    to link to the character
     * @param character to assign to the player
     */
    public void setPlayerCharacter(Player player, Character character) {
        characterPlayers.put(character, player);
    }

    /**
     * @param weapons The deck containing the power up cards
     */
    public void setWeaponDeck(Deck<WeaponCard> weapons) {
        weaponsDeck = weapons;
    }

    /**
     * Method used to notify that something changed in the model
     */
    public void notifyChanges() {
        setChanged();
        notifyObservers(getLightVersion());
        try {
            FileOutputStream f = new FileOutputStream(new File("server.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(Controller.getInstance());
            o.writeObject(Game.getInstance());
            o.close();
            f.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            CustomLogger.logException(this.getClass().getName(), ex);
        }
    }

    public LightGameVersion getLightVersion() {
        //set the skulls
        LightGameVersion lightVersion = new LightGameVersion(null);
        lightVersion.setSkullsNum(getGameField().getSkullsBoard().getCurrentSkulls());
        lightVersion.setTotalSkulls(getGameField().getSkullsBoard().getTotalSkulls());
        List<String> characterThatKilled = new ArrayList<>();
        List<Integer> quantityOfMarks = new ArrayList<>();

        for (Character c : gameField.getSkullsBoard().getCharacterThatKilledInPos())
            characterThatKilled.add(c.name());

        quantityOfMarks.addAll(gameField.getSkullsBoard().getMarksInPos());

        lightVersion.setCharactersThatKilled(characterThatKilled);
        lightVersion.setQuantityOfMarks(quantityOfMarks);

        Map<String, String> playerPlatform = new HashMap<>();
        Map<String, List<CardRep>> playerPowerups = new HashMap<>();
        Map<String, List<CardRep>> playerWeapons = new HashMap<>();
        Map<String, BoardRep> playerBoardRep = new HashMap<>();

        //associate the players (characters) with their info (platform, powerUps, weapons, boardRep)
        for (Player player : getPlayers()) {
            playerPlatform.put(player.getCharacter().name(), getGameField().getPlatformPosLight(player.getCurrentPlatform()));
            List<CardRep> powerUps = new ArrayList<>();
            List<CardRep> weapons = new ArrayList<>();
            List<String> damages = new ArrayList<>();
            List<String> marks = new ArrayList<>();
            Map<String, Integer> ammos = new HashMap<>();

            AmmoBox box = player.getPlayerBoard().getAmmoBox();

            ammos.put("RED", box.getRedAmmos());
            ammos.put("BLUE", box.getBlueAmmos());
            ammos.put("YELLOW", box.getYellowAmmos());
            //add optional ammos
            for (AmmoCube ammoCube : box.getOptionals())
                ammos.put(ammoCube.name(), ammos.get(ammoCube.name()) + 1);

            for (PowerUpCard powerUp : player.getPowerUpCards())
                powerUps.add(new CardRep(HandyFunctions.getSystemAddress(powerUp), powerUp.getName(), powerUp.getDescription(),
                        powerUp.getImgPath()));

            for (WeaponCard weaponCard : player.getWeaponCards()) {
                CardRep weaponRep = new CardRep(HandyFunctions.getSystemAddress(weaponCard), weaponCard.getName(), weaponCard.getDescription(),
                        weaponCard.getImgPath());
                weaponRep.setLoaded(weaponCard.isLoaded());
                weapons.add(weaponRep);
            }

            for (Character c : player.getPlayerBoard().getDamageLine())
                damages.add(c.name());
            for (Character c : player.getPlayerBoard().getRevengeMarks())
                marks.add(c.name());

            playerPowerups.put(player.getCharacter().name(), powerUps);
            playerWeapons.put(player.getCharacter().name(), weapons);
            playerBoardRep.put(player.getCharacter().name(), new BoardRep(damages, marks, ammos));
        }

        lightVersion.setPlayerWeapons(playerWeapons);
        lightVersion.setPlayerPlatform(playerPlatform);
        lightVersion.setPlayerPowerups(playerPowerups);
        lightVersion.setPlayerBoardRep(playerBoardRep);

        //associate the platform with the ammo tile and the weapons
        Map<String, AmmoRep> platformAmmoTile = new HashMap<>();
        Map<String, List<CardRep>> platformWeapons = new HashMap<>();

        for (Platform platform : gameField.getPlatforms()) {

            if (!platform.isGenerationSpot()) {
                AmmoCard ammoCard = platform.getPlatformAmmoCard();
                if (ammoCard != null)
                    platformAmmoTile.put(gameField.getPlatformPosLight(platform),
                            new AmmoRep(HandyFunctions.getSystemAddress(ammoCard), ammoCard.toString()));
                else
                    platformAmmoTile.put(gameField.getPlatformPosLight(platform),
                            new AmmoRep(0, "null"));
            } else {
                try {
                    List<CardRep> weapons = new ArrayList<>();

                    for (WeaponCard weaponCard : platform.getWeapons())
                        weapons.add(new CardRep(HandyFunctions.getSystemAddress(weaponCard), weaponCard.getName(), weaponCard.getDescription(),
                                weaponCard.getImgPath()));
                    if (platform.getWeapons().size() < 3) {
                        for (int j = platform.getWeapons().size(); j < 3; j++) {
                            weapons.add(new CardRep(0, "", "", ""));
                        }
                    }
                    platformWeapons.put(gameField.getPlatformPosLight(platform), weapons);
                } catch (InvalidGenerationSpotException ex) {
                    HandyFunctions.LOGGER.log(Level.SEVERE, ex.getMessage());
                }
            }
        }
        lightVersion.setPlatformAmmoTile(platformAmmoTile);
        lightVersion.setPlatformWeapons(platformWeapons);
        return lightVersion;
    }

    /**
     * @param name username of the player
     * @return the reference to the object player
     */
    public Player getPlayer(String name) {
        for (Player player : players)
            if (player.getName().equals(name))
                return player;
        return null;
    }

    /**
     * @param c character associated to the player
     * @return the player corresponding to the passed character
     */
    public Player getPlayer(Character c) {
        return characterPlayers.get(c);
    }
}
