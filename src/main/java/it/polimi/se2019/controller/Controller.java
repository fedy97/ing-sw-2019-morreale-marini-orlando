package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.exceptions.InvalidPositionException;
import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.board.ScoreBoard;
import it.polimi.se2019.model.board.SkullsBoard;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.*;
import it.polimi.se2019.network.message.to_server.ToServerMessage;
import it.polimi.se2019.utils.*;
import it.polimi.se2019.view.server.VirtualView;
import sun.security.util.DisabledAlgorithmConstraints;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;

/**
 * @author Gabriel Raul Marini
 */
public class Controller implements Observer {
    private Game game;
    private DecksManager decksManager;
    private GameManager gameManager;
    private PlayerManager playerManager;
    private Validator validator;
    private TurnController turnController;
    private Map<String, VirtualView> userView;
    private List<String> validActions;
    private ControllerState state; //the state is set to processing power up or weapon when a specific message from the client (ActivateCardMessage) is received
    private BlockingDeque<Player> currentTargets;
    private BlockingDeque<WeaponCard> chosenWeapons;
    private BlockingDeque<Boolean> wantToDiscard;
    private List<Integer> processingStages;
    private PowerUpCard processingPowerUp;
    private WeaponCard processingWeaponCard;
    private boolean lock;
    private int secondsLeft;
    private boolean timerStarted = false;
    private Map<Integer, Integer> mapChosen;
    private static Controller instance = null;


    /**
     * Controller singleton constructor
     *
     * @return instance
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
            instance.mapChosen = new HashMap<>();
            instance.mapChosen.put(1, 0);
            instance.mapChosen.put(2, 0);
            instance.mapChosen.put(3, 0);
            instance.mapChosen.put(4, 0);
        }
        return instance;
    }

    private Controller() {
        game = Game.getInstance();
        validator = new HealthyValidator(this);
        validActions = new ArrayList<>();
        currentTargets = new LinkedBlockingDeque<>();
        chosenWeapons = new LinkedBlockingDeque<>();
        wantToDiscard = new LinkedBlockingDeque<>();
        playerManager = new PlayerManager(this);
        turnController = new TurnController();
        userView = new HashMap<>();
        state = ControllerState.SETUP;
        lock = false;
    }

    /**
     * now we need the managers,
     * this method is thrown
     * after we set all parameters to the Game,
     * when the game starts
     */
    public void setManagers() {
        this.game = Game.getInstance();
        this.decksManager = new DecksManager(game.getPowerUpDeck(), game.getAmmoDeck());
        this.gameManager = new GameManager();

    }


    @Override
    /**
     * Called when the VirtualView notify changes
     */
    public void update(Observable virtualView, Object message) {
        if (state == ControllerState.SETUP) {
            if (message.equals("new client connected")) {
                //notify all clients connected
                for (String user : turnController.getUsers()) {
                    callView(new NewConnectionMessage(turnController.getUsers()), user);
                }
            } else if (message.equals("we are at least 2")) {
                //this timer will modify the model(Game) where the seconds integer is hold
                TimerLobby t = new TimerLobby(2);
                t.start();
            } else {
                ((ToServerMessage) message).performAction();
            }
        } else {
            new Thread(() -> {
                ((ToServerMessage) message).performAction();
                //TODO change this
                if (state == ControllerState.PROCESSING_POWERUP) {
                    processingStages.remove(0);
                    processPowerUp(processingPowerUp);
                } else if (state == ControllerState.PROCESSING_WEAPON) {
                    processingStages.remove(0);
                    processWeaponCard(processingWeaponCard);
                }
            }).start();
        }
        if (playerManager != null) {
            if (playerManager.getActionsLeft() == 0) {
                DisableActionButtonMessage msg = new DisableActionButtonMessage(null);
            }
        }
    }

    /**
     * @param powerUp composed of different stages in order to perform the final effect
     */
    public void processPowerUp(PowerUpCard powerUp) {
        powerUp.activate(processingStages.get(0));
        if (processingStages.isEmpty()) {
            state = ControllerState.IDLE;
            decksManager.addToGarbage(processingPowerUp);
            processingPowerUp = null;
        }
    }

    /**
     * @param weapon composed of different stages in order to perform the final effect
     */
    public void processWeaponCard(WeaponCard weapon) {
        //weaponCard.activate(processingStages.get(0));
        if (processingStages.isEmpty()) {
            state = ControllerState.IDLE;
            processingWeaponCard = null;
        }
    }

    /**
     * @return The current game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return The manager of all decks used in the game
     */
    public DecksManager getDecksManager() {
        return decksManager;
    }

    /**
     * @param deckManager The manager of all decks used in the game
     */
    public void setDecksManager(DecksManager deckManager) {
        this.decksManager = deckManager;
    }

    /**
     * @return the game manager
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * @param gameManager The game manager
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * @return the manager of the players in the game
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * @param playerManager The manager of the players in the game
     */
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     *
     */
    public void checkState() {
        //TODO
    }

    /**
     *
     */
    public void startGame() {
        state = ControllerState.IDLE;
    }

    /**
     *
     */
    public void endGame() {
        //TODO
    }
    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }

    /**
     * @param possibleChoices collection of elements to show to the user
     * @param choice          type of info required by the controller to manage the current
     *                        state of the game
     * @param <T>             type of object to show to the client
     */
    public <T> void askFor(List<T> possibleChoices, String choice) {
        ToClientMessage msg = null;
        List<String> lightVersion = HandyFunctions.getLightCollection(possibleChoices);
        Player currPlayer = playerManager.getCurrentPlayer();

        if (choice.equals("weapons"))
            msg = new ShowWeaponsMessage(lightVersion);
        else if (choice.equals("position"))
            msg = new ShowPlatformMessage(lightVersion);
        else if (choice.equals("positionForOther"))
            msg = new ShowPlatformMessageForOther(lightVersion);
        else if (choice.equals("targets"))
            msg = new ShowPossibleTargetsMessage(lightVersion);
        else if (choice.equals("discard"))
            msg = new AskForCubeMessage(lightVersion);
        else if (choice.equals("cube"))
            msg = new AskForCubeMessage(lightVersion);
        else
            msg = null; // OTHER options

        callView(msg, currPlayer.getName());
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * @return the cube chosen by the player in order to perform an action
     */
    public AmmoCube askForTribute() {
        //TODO
        return null;
    }

    /**
     * Common method across RMI and Socket to send requests to client
     *
     * @param msg to the destination client
     */
    public void callView(ToClientMessage msg, String user) {
        userView.get(user).callView(msg);
    }

    /**
     * @param virtualView fake instance of the remote view of the player
     * @param user        of the remote view
     */
    public void addVirtualView(VirtualView virtualView, String user) {
        userView.put(user, virtualView);
    }

    /**
     * @param action that can be validated by the controller once received a command message
     *               from the client
     */
    public void addValidAction(String action) {
        validActions.add(action);
    }

    /**
     * @param action to be removed
     */
    public void removeValidAction(String action) {
        validActions.remove(action);
    }

    /**
     * @param action to be validated
     * @return if the action is valid in the current state of the game
     */
    public boolean isValidAction(String action) {
        return validActions.contains(action);
    }


    public TurnController getTurnController() {
        return turnController;
    }

    public boolean getLock() {
        if (lock)
            return false;
        lock = true;
        return true;
    }

    public void releaseLock() {
        lock = false;
    }

    public void waitForResponse() {
        //BOH TODO
    }

    public BlockingDeque<Player> getCurrentTargets() {
        return currentTargets;
    }

    public BlockingDeque<WeaponCard> getChosenWeapons() {
        return chosenWeapons;
    }

    public BlockingDeque<Boolean> getWantToDiscard() {
        return wantToDiscard;
    }

    public void setState(ControllerState state) {
        this.state = state;
    }

    public ControllerState getState() {
        return state;
    }

    public void addStages(List<Integer> stages) {
        processingStages.addAll(stages);
    }


    public void setVoteMapChosen(int voteMapChosen) {
        mapChosen.put(voteMapChosen, mapChosen.get(voteMapChosen) + 1);
        notifyAll(new UpdateVotesMapChosenMessage(mapChosen));
    }

    public void setCharacterChosen(String name, String characterChosen) throws InvalidCharacterException, InvalidPositionException {
        Player player = new Player(name, Character.valueOf(characterChosen), null);
        game.getPlayers().add(player);
        game.getCharacterPlayers().put(Character.valueOf(characterChosen), player);
        notifyAll(new UpdateVotesCharacterChosenMessage(characterChosen));
    }

    /**
     * every time ths method is called by the timer, (this) will notify the virtual view
     *
     * @param secondsLeft to the chooseMap page
     */
    public synchronized void setSecondsLeftLobby(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        notifyAll(new UpdateTimerLobbyMessage(secondsLeft));
        if (secondsLeft == 0) {
            notifyAll(new ShowChooseMapMessage(null));
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
        notifyAll(new UpdateTimerMapMessage(secondsLeft));
        if (secondsLeft == 0) {
            try {
                int config = findWhichMapWon();
                createAssets(config);
                notifyAll(new ShowChooseCharacterMessage(Integer.toString(config)));
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
        notifyAll(new UpdateTimerCharacterMessage(secondsLeft));
        if (secondsLeft == 0) {
            //if a player did not chose a character, assign a random one
            try {
                findCharactersAvailableAndReplace();
                startGame();
                turnController.start();
                List<CardRep> cardReps = new ArrayList<>();
                String firstUser = turnController.getTurnUser();
                //the first user will recieve 2 reps of powerups

                PowerUpCard p1 = game.getPowerUpDeck().drawCard();
                PowerUpCard p2 = game.getPowerUpDeck().drawCard();
                playerManager.getCurrentPlayer().addPowerUpCard(p1);
                playerManager.getCurrentPlayer().addPowerUpCard(p2);
                cardReps.add(new CardRep(HandyFunctions.getSystemAddress(p1), p1.getName(), p1.getDescription(), p1.getImgPath()));
                cardReps.add(new CardRep(HandyFunctions.getSystemAddress(p2), p2.getName(), p2.getDescription(), p2.getImgPath()));

                List<AmmoRep> ammoReps = new ArrayList<>();
                //set for each platform an ammocard, than add it to the array of ammoreps
                for (int i = 0; i < game.getGameField().getField().length; i++) {
                    for (int j = 0; j < game.getGameField().getField()[0].length; j++) {
                        Platform p = game.getGameField().getField()[i][j];
                        if (p != null && p.hasAmmoCard()) {
                            AmmoCard ammoCard = p.getPlatformAmmoCard();
                            ammoReps.add(new AmmoRep(HandyFunctions.getSystemAddress(ammoCard), ammoCard.toString()));
                        } else ammoReps.add(null);
                    }
                }
                //now I create a list of the characters in game to send to clients in order to display their board
                List<String> arrChars = findCharactersInGame();
                notifyAll(new ShowGameBoardMessage(firstUser, ammoReps, cardReps, game.getLightVersion().getPlatformWeapons(), arrChars));
                turnController.notifyFirst();

            } catch (Exception e) {
                e.printStackTrace();
                HandyFunctions.LOGGER.log(Level.SEVERE, e.toString());
            }
        }
    }

    private List<String> findCharactersInGame() {
        List<String> arrChars = new ArrayList<>();
        for (Map.Entry<Character, Player> entry : game.getCharacterPlayers().entrySet()) {
            if (entry.getValue() != null) {
                String charToAdd = entry.getKey().name();
                arrChars.add(charToAdd);
            }
        }
        return arrChars;
    }

    private void findCharactersAvailableAndReplace() {
        ArrayList<Character> arr = new ArrayList<>();
        for (Map.Entry<Character, Player> entry : game.getCharacterPlayers().entrySet()) {
            if (entry.getValue() == null) arr.add(entry.getKey());
        }
        for (String user : Controller.getInstance().getTurnController().getUsers()) {
            boolean isFound = false;
            for (Player p : Game.getInstance().getPlayers()) {
                if (user.equals(p.getName())) isFound = true;
            }
            try {
                if (!isFound) {
                    Character c = arr.remove(arr.size() - 1);
                    Player p = new Player(user, c, null);
                    game.getPlayers().add(p);
                    game.getCharacterPlayers().put(c, p);
                    SetRandomCharacterMessage message = new SetRandomCharacterMessage(c.name(), user);
                    notifyAll(message);
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
            game.setAmmoDeck(parserAmmos.buildAmmoCards());
            JsonParser parserField = new JsonParser("/json/field.json");
            Platform[][] field = parserField.buildField(config, game.getAmmoDeck());
            JsonParser parser = new JsonParser("/json/powerups.json");
            Deck<PowerUpCard> powerUpCardDeck = parser.buildPowerupCards();
            game.setPowerUpDeck(powerUpCardDeck);
            JsonParser parserWeapons = new JsonParser("/json/weapons.json");
            game.setWeaponDeck(parserWeapons.buildWeaponCards());
            WeaponCard[] weaponCards = new WeaponCard[9];
            for (int i = 0; i < 9; i++)
                weaponCards[i] = game.getWeaponsDeck().drawCard();
            game.setGameField(new GameField(field, weaponCards, new SkullsBoard(8), new ScoreBoard()));
            setManagers();
        } catch (Exception ex) {
            ex.printStackTrace();
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

    private void notifyAll(ToClientMessage msg){
        for (String user : userView.keySet()) {
            callView(msg, user);
        }
    }

}
