package it.polimi.se2019.model;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.board.ScoreBoard;
import it.polimi.se2019.model.board.SkullsBoard;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.*;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.JsonParser;
import it.polimi.se2019.utils.TimerCharacter;
import it.polimi.se2019.utils.TimerMap;

import java.util.*;
import java.util.logging.Level;

/**
 * @author Federico Morreale
 */
public class Game extends Observable {

    private GameField gameField;
    private ArrayList<Player> players;
    private Deck<WeaponCard> weaponsDeck;
    private Deck<PowerUpCard> powerUpDeck;
    private Deck<PowerUpCard> garbageDeck;
    private Deck<AmmoCard> ammoDeck;
    private int secondsLeft;
    private Map<Character, Player> characterPlayers;
    private boolean timerStarted = false;
    private Map<Integer, Integer> mapChosen;
    private static Game instance = null;

    /**
     * Game singleton constructor
     *
     * @return instance
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.players = new ArrayList<>();
            instance.mapChosen = new HashMap<>();
            instance.characterPlayers = new EnumMap<>(Character.class);
            instance.characterPlayers.put(Character.BANSHEE, null);
            instance.characterPlayers.put(Character.VIOLET, null);
            instance.characterPlayers.put(Character.SPROG, null);
            instance.characterPlayers.put(Character.DISTRUCTOR, null);
            instance.characterPlayers.put(Character.DOZER, null);
            instance.mapChosen.put(1, 0);
            instance.mapChosen.put(2, 0);
            instance.mapChosen.put(3, 0);
            instance.mapChosen.put(4, 0);
        }
        return instance;
    }

    /**
     * @param gameField
     * @param weaponsDeck
     * @param powerUpDeck
     * @param ammoDeck
     */
    private void initGame(GameField gameField, Deck<WeaponCard> weaponsDeck, Deck<PowerUpCard> powerUpDeck, Deck<AmmoCard> ammoDeck) {
        this.gameField = gameField;
        this.powerUpDeck = powerUpDeck;
        this.weaponsDeck = weaponsDeck;
        this.ammoDeck = ammoDeck;
    }

    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setVoteMapChosen(int voteMapChosen) {
        mapChosen.put(voteMapChosen, mapChosen.get(voteMapChosen) + 1);
        setChanged();
        //the virtual view will be notified by running the update() method
        notifyObservers(new UpdateVotesMapChosenMessage(mapChosen));
    }

    public void setCharacterChosen(String name, String characterChosen) throws InvalidCharacterException, InvalidPositionException {
        Player player = new Player(name, Character.valueOf(characterChosen), null);
        players.add(player);
        characterPlayers.put(Character.valueOf(characterChosen), player);
        setChanged();
        notifyObservers(new UpdateVotesCharacterChosenMessage(characterChosen));
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
     * @param powerUps The deck containing the power up cards
     */
    public void setPowerUpDeck(Deck<PowerUpCard> powerUps) {
        powerUpDeck = powerUps;
    }

    /**
     * Method used to notify that something changed in the model
     */
    public void notifyChanges() {
        setChanged();
        notifyObservers(getLightVersion());
    }


    public LightGameVersion getLightVersion() {
        //set the skulls
        LightGameVersion lightVersion = new LightGameVersion(null);
        lightVersion.setSkullsNum(getGameField().getSkullsBoard().getCurrentSkulls());

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


            for (PowerUpCard powerUp : player.getPowerUpCards())
                powerUps.add(new CardRep(HandyFunctions.getSystemAddress(powerUp), powerUp.getName(), powerUp.getDescription(),
                        powerUp.getImgPath()));

            for (WeaponCard weaponCard : player.getWeaponCards())
                weapons.add(new CardRep(HandyFunctions.getSystemAddress(weaponCard), weaponCard.getName(), weaponCard.getDescription(),
                        weaponCard.getImgPath()));

            for(Character c : player.getPlayerBoard().getDamageLine())
                damages.add(c.name());
            for(Character c: player.getPlayerBoard().getRevengeMarks())
                marks.add(c.name());

            playerPowerups.put(player.getCharacter().name(), powerUps);
            playerWeapons.put(player.getCharacter().name(), weapons);
            playerBoardRep.put(player.getCharacter().name(), new BoardRep(damages, marks));
        }

        lightVersion.setPlayerPlatform(playerPlatform);
        lightVersion.setPlayerPowerups(playerPowerups);
        lightVersion.setPlatformWeapons(playerWeapons);
        lightVersion.setPlayerBoardRep(playerBoardRep);

        //associate the platform with the ammo tile and the weapons
        Map<String, AmmoRep> platformAmmoTile = new HashMap<>();
        Map<String, List<CardRep>> platformWeapons = new HashMap<>();

        for (Platform platform : gameField.getPlatforms()) {
            List<CardRep> weapons = new ArrayList<>();
            if (platform.hasAmmoCard()) {
                AmmoCard ammoCard = platform.getPlatformAmmoCard();
                platformAmmoTile.put(gameField.getPlatformPosLight(platform),
                        new AmmoRep(HandyFunctions.getSystemAddress(ammoCard), ammoCard.toString()));
            }

            try {
                for (WeaponCard weaponCard : platform.getWeapons())
                    weapons.add(new CardRep(HandyFunctions.getSystemAddress(weaponCard), weaponCard.getName(), weaponCard.getDescription(),
                            weaponCard.getImgPath()));
                platformWeapons.put(gameField.getPlatformPosLight(platform), weapons);
            } catch (InvalidGenerationSpotException e) {
                //go on
            }
        }
        lightVersion.setPlatformAmmoTile(platformAmmoTile);
        lightVersion.setPlatformWeapons(platformWeapons);
        return lightVersion;
    }

    /**
     * every time ths method is called by the timer, (this) will notify the virtual view
     *
     * @param secondsLeft to the chooseMap page
     */
    public synchronized void setSecondsLeftLobby(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        setChanged();
        notifyObservers(new UpdateTimerLobbyMessage(secondsLeft));
        if (secondsLeft == 0) {
            setChanged();
            notifyObservers(new ShowChooseMapMessage(null));
            //starts the other timer, this timer even if is in Model , is a controller feature
            //in fact TimerMap will modify the model by calling setSecondsLeftMap
            TimerMap t = new TimerMap(2);
            t.start();

        }
    }

    /**
     * @param secondsLeft to the choose character page
     */
    public synchronized void setSecondsLeftMap(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        setChanged();
        notifyObservers(new UpdateTimerMapMessage(secondsLeft));
        if (secondsLeft == 0) {
            try {
                int config = findWhichMapWon();
                createAssets(config);
                setChanged();
                notifyObservers(new ShowChooseCharacterMessage(Integer.toString(config)));
                TimerCharacter t = new TimerCharacter(2);
                t.start();
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.SEVERE, e.toString());
            }
        }
    }

    /**
     * @param secondsLeft to the game field page
     */
    public synchronized void setSecondsLeftCharacter(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        setChanged();
        notifyObservers(new UpdateTimerCharacterMessage(secondsLeft));
        if (secondsLeft == 0) {
            //if a player did not chose a character, assign a random one
            try {
                findCharactersAvailableAndReplace();
                Controller.getInstance().startGame();
                Controller.getInstance().getTurnController().start();
                List<CardRep> cardReps = new ArrayList<>();
                String firstUser = Controller.getInstance().getTurnController().getTurnUser();
                //the first user will recieve 2 reps of powerups
                PowerUpCard p1 = powerUpDeck.drawCard();
                PowerUpCard p2 = powerUpDeck.drawCard();
                Controller.getInstance().getPlayerManager().getCurrentPlayer().addPowerUpCard(p1);
                Controller.getInstance().getPlayerManager().getCurrentPlayer().addPowerUpCard(p2);
                cardReps.add(new CardRep(HandyFunctions.getSystemAddress(p1), p1.getName(), p1.getDescription(), p1.getImgPath()));
                cardReps.add(new CardRep(HandyFunctions.getSystemAddress(p2), p2.getName(), p2.getDescription(), p2.getImgPath()));

                List<AmmoRep> ammoReps = new ArrayList<>();
                //set for each platform an ammocard, than add it to the array of ammoreps
                for (int i = 0; i < gameField.getField().length; i++) {
                    for (int j = 0; j < gameField.getField()[0].length; j++) {
                        Platform p = gameField.getField()[i][j];
                        if (p != null && p.hasAmmoCard()) {
                            AmmoCard ammoCard = p.getPlatformAmmoCard();
                            ammoReps.add(new AmmoRep(HandyFunctions.getSystemAddress(ammoCard), ammoCard.toString()));
                        }
                        else ammoReps.add(null);
                    }
                }
                setChanged();
                notifyObservers(new ShowGameBoardMessage(firstUser, ammoReps, cardReps, getLightVersion().getPlatformWeapons()));
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.SEVERE, e.toString());
            }
        }
    }

    private void findCharactersAvailableAndReplace() {
        ArrayList<Character> arr = new ArrayList<>();
        for (Map.Entry<Character, Player> entry : characterPlayers.entrySet()) {
            if (entry.getValue() == null) arr.add(entry.getKey());
        }
        for (String user : Controller.getInstance().getTurnController().getUsers()) {
            boolean isFound = false;
            for (Player p : players) {
                if (user.equals(p.getName())) isFound = true;
            }
            try {
                if (!isFound) {
                    Character c = arr.remove(arr.size() - 1);
                    Player p = new Player(user, c, null);
                    players.add(p);
                    characterPlayers.put(c, p);
                    SetRandomCharacterMessage message = new SetRandomCharacterMessage(c.name(), user);
                    setChanged();
                    notifyObservers(message);
                }
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.SEVERE, "error in creating random character");
            }
        }
    }

    /**
     * we can now build the field and the decks
     */
    private void createAssets(int config) {
        try {
            JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
            ammoDeck = parserAmmos.buildAmmoCards();
            JsonParser parserField = new JsonParser("/json/field.json");
            Platform[][] field = parserField.buildField(config, ammoDeck);
            JsonParser parser = new JsonParser("/json/powerups.json");
            powerUpDeck = parser.buildPowerupCards();
            JsonParser parserWeapons = new JsonParser("/json/weapons.json");
            weaponsDeck = parserWeapons.buildWeaponCards();
            WeaponCard[] weaponCards = new WeaponCard[9];
            for (int i = 0; i < 9; i++)
                weaponCards[i] = weaponsDeck.drawCard();
            gameField = new GameField(field, weaponCards, new SkullsBoard(8), new ScoreBoard());

            initGame(gameField, weaponsDeck, powerUpDeck, ammoDeck);
            Controller.getInstance().setManagers();
        } catch (Exception ex) {
            HandyFunctions.LOGGER.log(Level.SEVERE, ex.toString());
        }

    }

    /**
     * if there is a draw, the first between them will be chosen.
     *
     * @return the config of the map that won the votations.
     */
    private int findWhichMapWon() {
        int max = -1;
        int config = -1;
        for (int i : mapChosen.keySet()) {
            if (mapChosen.get(i) > max) {
                max = mapChosen.get(i);
                config = i;
            }
        }
        return config;
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
    public Player getPlayer(Character c){
        return characterPlayers.get(c);
    }
}
