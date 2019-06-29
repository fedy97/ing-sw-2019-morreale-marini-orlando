package it.polimi.se2019.controller;

import it.polimi.se2019.Action;
import it.polimi.se2019.controller.validator.HealthyValidator;
import it.polimi.se2019.controller.validator.UserValidActions;
import it.polimi.se2019.controller.validator.Validator;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.PointsCounter;
import it.polimi.se2019.model.board.*;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.powerups.TagbackGrenade;
import it.polimi.se2019.model.card.weapons.Effect;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.model.rep.AmmoRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.network.message.to_client.*;
import it.polimi.se2019.network.message.to_server.ToServerMessage;
import it.polimi.se2019.utils.*;
import it.polimi.se2019.view.server.VirtualView;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Gabriel Raul Marini
 */
public class Controller implements Observer, Serializable {

    private static final long serialVersionUID = 1625882819211300529L;
    private static Controller instance = null;
    private Game game;
    private DecksManager decksManager;
    private PlayerManager playerManager;
    private Validator validator;
    private TurnController turnController;
    private Map<String, VirtualView> userView;
    private ControllerState state; //the state is set to processing power up or weapon when a specific message from the client (ActivateCardMessage) is received
    private boolean debug;

    private BlockingDeque<Character> currentTargets;
    private BlockingDeque<WeaponCard> chosenWeapons;
    private BlockingDeque<PowerUpCard> chosenPowerUps;
    private BlockingDeque<Platform> chosenDestination;
    private BlockingDeque<Integer> chosenEffect;
    private BlockingDeque<Boolean> chosenBinaryOption;
    private BlockingDeque<String> chosenAmmo;

    private int timerSetup;
    private boolean timerStarted = false;
    private Map<Integer, Integer> mapChosen;
    private List<String> pingsList;
    private List<String> pingsWaitingList;
    private List<String> alreadyNotified = new ArrayList<>();
    private int configMap;
    private boolean waitingToPing = true;
    private boolean gameIsActive = true;
    private boolean frenzyModeOn = false;
    private boolean wasRecharged;
    private boolean serverReloaded;
    private boolean[] validActions;

    private Controller() {
        game = Game.getInstance();
        validator = new HealthyValidator(this);

        currentTargets = new LinkedBlockingDeque<>();
        chosenWeapons = new LinkedBlockingDeque<>();
        chosenPowerUps = new LinkedBlockingDeque<>();
        chosenBinaryOption = new LinkedBlockingDeque<>();
        chosenDestination = new LinkedBlockingDeque<>();
        chosenEffect = new LinkedBlockingDeque<>();
        chosenAmmo = new LinkedBlockingDeque<>();

        playerManager = new PlayerManager(this);
        turnController = new TurnController();
        userView = new HashMap<>();
        state = ControllerState.SETUP;
        timerSetup = HandyFunctions.parserSettings.getTimerSetup();
        pingsList = new ArrayList<>();
        pingsWaitingList = new ArrayList<>();
        serverReloaded = false;
        debug = false;

        validActions = UserValidActions.NO_SHOOT.getActions().clone();
    }

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

    public static void setInstance(Controller instance) {
        Controller.instance = instance;
    }

    public void startWaitingLobbyPing() {
        Set<String> users = userView.keySet();

        new Thread(() -> {
            try {
                while (waitingToPing) {

                    pingsWaitingList.clear();
                    notifyAll(new PingWaitingClientsMessage(null));
                    Thread.sleep(1500);
                    List<String> toRemove = new ArrayList<>();

                    boolean toReset = false;
                    for (String user : users) {
                        if (!pingsWaitingList.contains(user)) {
                            toRemove.add(user);
                            turnController.removeUser(user);
                            if (turnController.getUsers().size() < HandyFunctions.parserSettings.getMinimumPlayers() && timerStarted) {
                                timerStarted = false;
                                toReset = true;
                            }
                        }
                    }

                    for (String string : toRemove)
                        userView.remove(string);

                    if (toReset && !timerStarted) notifyAll(new ResetTimerMessage(null));
                    notifyAll(new NewConnectionMessage(pingsWaitingList));
                }
            } catch (Exception ex) {
                CustomLogger.logException(this.getClass().getName(), ex);
            }
        }).start();
    }

    /**
     * Called when the VirtualView notify changes
     */
    @Override
    public void update(Observable virtualView, Object message) {
        if (state == ControllerState.SETUP) {
            if (message.equals("we are at least 2")) {
                TimerLobby t = new TimerLobby(timerSetup);
                t.start();
            } else if (message.equals("we are 5")) {
                waitingToPing = false;
                notifyAll(new ShowChooseMapMessage(null));
                TimerMap t = new TimerMap(timerSetup);
                t.start();
            } else {
                ((ToServerMessage) message).performAction();
            }
        } else {
            new Thread(() ->
                    ((ToServerMessage) message).performAction()).start();
        }
    }

    public void updateValidActions(boolean[] newValidActions) {
        for (int i = 0; i < validActions.length; i++) {
            validActions[i] = validActions[i] && newValidActions[i];
        }
    }

    /**
     * Manage new action request
     *
     * @param action to be performed
     */
    public void processAction(String action) throws InterruptedException {
        callView(new EnablePlayerActionsMessage(UserValidActions.NONE.getActions()), playerManager.getCurrentPlayer().getName());

        if (!action.equals("action4") && !action.equals("action5")) {
            if (isFrenzyModeOn())
                processFrenzyAction(action);
            else
                processNormalAction(action);
            playerManager.useAction();
        }

        if (action.equals("action4")) {
            askFor(getValidator().getReloadableWeapons(), "recharge");
            updateValidActions(UserValidActions.ONLY_END.getActions());
            playerManager.clearActionLeft();

        }

        if (action.equals("action5")) {
            askFor(getValidator().getUsablePowerUps(), "powerups");
            while (getState() == ControllerState.PROCESSING_POWERUP)
                Thread.sleep(200);
        }

        if (playerManager.getActionsLeft() == 0) {
            updateValidActions(UserValidActions.NO_BASIC.getActions());
            sendMessage("You've finished your basic action! Now you can use your powerups, reload or pass the turn",
                    playerManager.getCurrentPlayer().getName());
        }

        callView(new EnablePlayerActionsMessage(validActions), playerManager.getCurrentPlayer().getName());
        setState(ControllerState.IDLE);
    }

    private void processFrenzyAction(String action) {
        List<Platform> destinations;

        try {

            if (action.equals("action1")) {

                setState(ControllerState.PROCESSING_ACTION_1);
                destinations = validator.getValidMoves(Action.SHOOT);

                //send the possible destinations
                askFor(destinations, "position");
                playerManager.move(getChosenDestination().take());

                if (!validator.getReloadableWeapons().isEmpty()) {
                    callView(new SendBinaryOption("Do you want to recharge?"), playerManager.getCurrentPlayer().getName());
                    if (chosenBinaryOption.take()) {
                        askFor(validator.getReloadableWeapons(), "recharge");
                        while (!wasRecharged)
                            Thread.sleep(500);
                        wasRecharged = false;
                    }
                }

                if (validator.getUsableWeapons().isEmpty()) {
                    sendMessage("***** You have no usable weapons actually! ***", playerManager.getCurrentPlayer().getName());
                    setState(ControllerState.IDLE);
                    return;
                }
                askFor(getValidator().getUsableWeapons(), "weaponsToUse");

                while (getState() == ControllerState.PROCESSING_ACTION_1)
                    Thread.sleep(200);
            }

            if ((action.equals("action2") && playerManager.getCurrentPlayer().getFrenzyModeType() == 2) ||
                    (action.equals("action3") && playerManager.getCurrentPlayer().getFrenzyModeType() == 1)) {
                grabRoutine();
            }

            if (action.equals("action2") && playerManager.getCurrentPlayer().getFrenzyModeType() == 1) {
                setState(ControllerState.PROCESSING_ACTION_3);

                destinations = validator.getValidMoves(Action.MOVE);
                //send the possible destinations
                askFor(destinations, "position");
                playerManager.move(getChosenDestination().take());
            }

            if (action.equals("action3") && playerManager.getCurrentPlayer().getFrenzyModeType() == 2)
                sendMessage("*** You cannot perform this action!!!! ***", playerManager.getCurrentPlayer().getName());
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    private void processNormalAction(String action) {
        List<Platform> destinations;
        try {

            if (action.equals("action1")) {
                setState(ControllerState.PROCESSING_ACTION_1);
                destinations = validator.getValidMoves(Action.MOVE);

                //send the possible destinations
                askFor(destinations, "position");
                playerManager.move(getChosenDestination().take());

            } else if (action.equals("action2")) {
                grabRoutine();
            } else if (action.equals("action3")) {

                if (validator.getUsableWeapons().isEmpty()) {
                    sendMessage("***** You have no usable weapons actually! ***", playerManager.getCurrentPlayer().getName());
                    return;
                }

                setState(ControllerState.PROCESSING_ACTION_3);

                try {
                    destinations = validator.getValidMoves(Action.SHOOT);
                    askFor(destinations, "position");
                    getPlayerManager().move(getChosenDestination().take());
                } catch (InvalidActionException e) {
                    CustomLogger.logInfo(this.getClass().getName(), "You cannot move before shooting!");
                }

                askFor(getValidator().getUsableWeapons(), "weaponsToUse");
                while (getState() == ControllerState.PROCESSING_ACTION_3)
                    Thread.sleep(200);

            }
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }

    private void grabRoutine() throws InvalidActionException, InterruptedException {
        setState(ControllerState.PROCESSING_ACTION_2);
        List<Platform> destinations = validator.getValidMoves(Action.GRAB);

        //ask where to move before grabbing
        askFor(destinations, "position");
        Platform dest = getChosenDestination().take();
        getPlayerManager().move(dest);

        if (dest.isGenerationSpot()) {
            try {
                if (playerManager.getCurrentPlayer().getWeaponCards().size() == 3) {
                    callView(new AskToDiscardMessage(null), playerManager.getCurrentPlayer().getName());
                    WeaponCard card = getChosenWeapons().take();

                    if (card.getName().equals("null")) {
                        setState(ControllerState.IDLE);
                        return;
                    } else
                        getPlayerManager().getCurrentPlayer().removeWeaponCard(card);
                }

                askFor(getValidator().getGrabableWeapons(), "weapons");
                WeaponCard chosen = getChosenWeapons().take();

                playerManager.buyWeapon(chosen);
            } catch (Exception e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }
        } else {
            getPlayerManager().grabAmmoCard();
        }
    }

    /**
     * Launch a power up execution
     *
     * @param powerUp chosen by the player
     */
    public void processPowerUp(PowerUpCard powerUp) {
        try {
            if (powerUp.getPossibleTargets() != null) {
                askFor(powerUp.getPossibleTargets(), "targets");
                powerUp.activate(currentTargets.take());
            } else {
                powerUp.activate(null);
            }

            if (powerUp.getName().equals("granata venom")) {
                TagbackGrenade tagbackGrenade = (TagbackGrenade) powerUp;
                game.getPlayer(tagbackGrenade.getUserTarget()).removePowerUpCard(powerUp);
            } else
                playerManager.getCurrentPlayer().removePowerUpCard(powerUp);
            decksManager.addToGarbage(powerUp);
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
        game.notifyChanges();
    }

    /**
     * Launch the weapon execution
     *
     * @param weapon chosen by the player to perform shooting action
     */
    public void processWeaponCard(WeaponCard weapon) {
        List<Integer> usableEffectsIndex = new ArrayList<>();
        int usedEffects = 0;

        while (usedEffects < 3) {
            boolean[] usableEffects = weapon.getUsableEffects();

            for (int i = 0; i < usableEffects.length; i++) {

                if (usableEffects[i]) {
                    Effect effect = weapon.getEffects().get(i);
                    effect.setupTargets();

                    if ((effect.getCost().length == 0 ||
                            playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().hasAmmos(effect.getCost()))
                            && (effect.getPossibleTargets() == null ||
                            !effect.getPossibleTargets().isEmpty()))
                        usableEffectsIndex.add(i);

                }
            }

            if (usableEffectsIndex.isEmpty()) {
                setState(ControllerState.IDLE);
                break;
            }

            try {

                if (usedEffects > 0) {
                    callView(new SendBinaryOption("Do you want to use another effect?"), playerManager.getCurrentPlayer().getName());
                    if (!chosenBinaryOption.take()) {
                        setState(ControllerState.IDLE);
                        break;
                    }
                }

                callView(new ShowUsableEffectsMessage(usableEffectsIndex), playerManager.getCurrentPlayer().getName());
                usableEffectsIndex.clear();

                int effectIndex = chosenEffect.take();
                Effect currEffect = weapon.getEffects().get(effectIndex);

                if (currEffect.getPossibleTargets() != null) {
                    askFor(currEffect.getPossibleTargets(), "targets");
                    List<Character> targets = new ArrayList<>();

                    for (int i = 0; i < currEffect.getMaxTargets(); i++) {
                        targets.add(currentTargets.take());
                        if (currentTargets.isEmpty())
                            break;
                    }
                    weapon.activateEffect(effectIndex, targets);
                    CustomLogger.logInfo(getClass().getName(), "Action performed!");
                } else {
                    weapon.activateEffect(effectIndex, null);
                    CustomLogger.logInfo(getClass().getName(), "Action performed with null!");
                }

            } catch (Exception e) {
                CustomLogger.logException(getClass().getName(), e);
            }

            usedEffects++;
        }

        setState(ControllerState.IDLE);
        weapon.discard();
        game.notifyChanges();
    }

    /**
     * Start the game
     */
    public void startGame() {
        state = ControllerState.IDLE;
    }

    /**
     * Called when the game is finished or there are no more players to continue
     */
    public void endGame() {
        //in order to interrupt the pinging
        gameIsActive = false;
        game.saveServer();
        game.setScore(PointsCounter.getFinalScore(game.getGameField().getSkullsBoard()));
        notifyAll(new ShowScoreboardMessage(game.getFinalScore()));
    }

    /**
     * @return if the timer is started
     */
    public boolean isTimerStarted() {
        return timerStarted;
    }

    /**
     * @param timerStarted true id started, false otherwise
     */
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
        ToClientMessage msg;
        List<String> lightVersion = HandyFunctions.getLightCollection(possibleChoices);
        Player currPlayer = playerManager.getCurrentPlayer();

        switch (choice) {
            case "weapons":
                msg = new ShowWeaponsMessage(lightVersion);
                break;
            case "weaponsToUse":
                msg = new ShowUsableWeaponsMessage(lightVersion);
                break;
            case "position":
                msg = new ShowPlatformMessage(lightVersion);
                break;
            case "targets":
                msg = new ShowPossibleTargetsMessage(lightVersion);
                break;
            case "discard":
                msg = new AskForCubeMessage(lightVersion);
                break;
            case "cube":
                msg = new AskForCubeMessage(lightVersion);
                break;
            case "recharge":
                msg = new ShowReloadableWeaponsMessage(lightVersion);
                break;
            case "powerups":
                msg = new ShowUsablePowerupsMessage(lightVersion);
                break;
            default:
                msg = null; // OTHER options
                break;
        }
        callView(msg, currPlayer.getName());
    }

    /**
     * Common method across RMI and Socket to send requests to client
     *
     * @param msg to the destination client
     */
    public void callView(ToClientMessage msg, String user) {
        if (game.getPlayers().isEmpty() || (game.getPlayer(user) != null && game.getPlayer(user).isConnected()) || game.getPlayer(user) == null)
            userView.get(user).callView(msg);
    }

    public void setVoteMapChosen(int voteMapChosen) {
        mapChosen.put(voteMapChosen, mapChosen.get(voteMapChosen) + 1);
        notifyAll(new UpdateVotesMapChosenMessage(mapChosen));
    }

    public void setCharacterChosen(String name, String characterChosen) throws
            InvalidCharacterException {
        Player player = new Player(name, Character.valueOf(characterChosen), null);
        game.getPlayers().add(player);
        game.getCharacterPlayers().put(Character.valueOf(characterChosen), player);
        notifyAll(new UpdateVotesCharacterChosenMessage(characterChosen));
    }

    public void resetValidActions() {
        if (turnController.isFirstTurn() || playerManager.getCurrentPlayer().getFrenzyModeType() == 2)
            validActions = UserValidActions.NO_SHOOT.getActions().clone();
        else
            validActions = UserValidActions.ALL.getActions().clone();
    }

    /**
     * every time ths method is called by the timer, (this) will notify the virtual view
     *
     * @param secondsLeft to the chooseMap page
     */
    public synchronized void setSecondsLeftLobby(int secondsLeft) {
        if (timerStarted) {
            notifyAll(new UpdateTimerLobbyMessage(secondsLeft));
            if (secondsLeft == 0) {
                waitingToPing = false;
                notifyAll(new ShowChooseMapMessage(null));
                //starts the other timer, this timer even if is in Model , is a controller feature
                //in fact TimerMap will modify the model by calling setSecondsLeftMap
                TimerMap t = new TimerMap(timerSetup);
                t.start();

            }
        }
    }

    /**
     * @param secondsLeft to the choose character page
     */
    public synchronized void setSecondsLeftMap(int secondsLeft) {
        notifyAll(new UpdateTimerMapMessage(secondsLeft));
        if (secondsLeft == 0) {
            try {
                int config = findWhichMapWon();
                createAssets(config);
                notifyAll(new ShowChooseCharacterMessage(Integer.toString(config)));
                TimerCharacter t = new TimerCharacter(timerSetup);
                t.start();
            } catch (Exception e) {
                CustomLogger.logException(getClass().getName(), e);
            }
        }
    }

    /**
     * @param secondsLeft to the game field page
     */
    public synchronized void setSecondsLeftCharacter(int secondsLeft) {
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

                PowerUpCard p1 = decksManager.drawPowerUp();
                PowerUpCard p2 = decksManager.drawPowerUp();
                playerManager.getCurrentPlayer().addPowerUpCard(p1);
                playerManager.getCurrentPlayer().addPowerUpCard(p2);
                cardReps.add(new CardRep(p1));
                cardReps.add(new CardRep(p2));

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
                if (!debug) {
                    turnController.notifyFirst();
                    startPinging();
                }

            } catch (Exception e) {
                CustomLogger.logException(getClass().getName(), e);
            }
        }
    }

    /**
     * Start to check periodically if a client disconnect itself from the game
     */
    public void startPinging() {
        List<String> chars = new ArrayList<>();

        //Collect all the characters in game
        for (Player p : game.getPlayers())
            chars.add(p.getCharacter().name());

        Thread t = new Thread(() -> {

            try {

                while (gameIsActive) {
                    pingsList.clear();
                    notifyAll(new PingClientsMessage(null));
                    Thread.sleep(3000);
                    if (pingsList.size() == 2 && serverReloaded)
                        setServerReloaded(false);
                    if (pingsList.size() < 2 && !serverReloaded)
                        endGame();
                    else {
                        for (String charCurr : chars) {

                            if (!pingsList.contains(charCurr)) {
                                game.deleteObserver(userView.get(game.getPlayer(Character.valueOf(charCurr)).getName()));
                                Player toDisconnect = game.getPlayer(Character.valueOf(charCurr));
                                toDisconnect.setConnected(false);

                                if (!alreadyNotified.contains(charCurr)) {
                                    alreadyNotified.add(charCurr);
                                    broadcastMessage(toDisconnect.getName() + " disconnected!");

                                    if (toDisconnect.getCurrentPlatform() == null) {
                                        PowerUpCard p = decksManager.drawPowerUp();
                                        toDisconnect.addPowerUpCard(p);

                                        Color powerupColor = HandyFunctions.stringToColor(p.getAmmoCube().name());
                                        for (Room r : Game.getInstance().getGameField().getRooms()) {
                                            if (r.hasGenerationSpot() && r.getGenSpot().getPlatformColor().equals(powerupColor))
                                                toDisconnect.setCurrentPlatform(r.getGenSpot());
                                        }
                                    }
                                }
                                if (turnController.getTurnUser().equals(toDisconnect.getName()))
                                    turnController.endTurn();
                            }
                        }
                    }
                    Thread.sleep(500);
                }
            } catch (Exception ex) {
                CustomLogger.logException(this.getClass().getName(), ex);
            }

        });
        t.start();
    }

    public List<String> findCharactersInGame() {
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
                CustomLogger.logException(getClass().getName(), e);
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
            JsonParser parserWeapons = new JsonParser("/json/weaponsDebug.json");
            game.setWeaponDeck(parserWeapons.buildWeaponCards());
            WeaponCard[] weaponCards = new WeaponCard[9];
            for (int i = 0; i < 9; i++)
                weaponCards[i] = game.getWeaponsDeck().drawCard();
            game.setGameField(new GameField(field, weaponCards, new SkullsBoard(HandyFunctions.parserSettings.numOfSkulls()), new ScoreBoard()));
            this.decksManager = new DecksManager(game.getPowerUpDeck(), game.getAmmoDeck());
        } catch (Exception e) {
            CustomLogger.logException(getClass().getName(), e);
        }

    }

    /**
     * Send general info to the selected player
     *
     * @param msg       content of the message (String)
     * @param recipient destination of the message
     */
    public void sendMessage(String msg, String recipient) {
        callView(new ShowMessage(msg), recipient);
    }

    /**
     * Send general info to all the players
     *
     * @param msg content of the communication
     */
    public void broadcastMessage(String msg) {
        notifyAll(new ShowMessage(msg));
    }

    /**
     * if there is a draw, the first between them will be chosen.
     *
     * @return the config of the map that won the votations.
     */
    private int findWhichMapWon() {
        int max = -1;
        int config = -1;
        for (Map.Entry<Integer, Integer> entry : mapChosen.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                config = entry.getKey();
            }
        }
        configMap = config;
        return config;
    }

    /**
     * Send a broadcast message
     *
     * @param msg to be sent to all the players
     */
    protected void notifyAll(ToClientMessage msg) {
        for (String user : userView.keySet()) {
            if (game.getGameField() == null || game.getPlayers().isEmpty() || (game.getPlayer(user) != null && game.getPlayer(user).isConnected()) || game.getPlayer(user) == null) {
                callView(msg, user);
            }
        }
    }

    /**
     * @param virtualView fake instance of the remote view of the player
     * @param user        of the remote view
     */
    public void addVirtualView(VirtualView virtualView, String user) {
        userView.put(user, virtualView);
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

    public TurnController getTurnController() {
        return turnController;
    }

    public BlockingDeque<Character> getCurrentTargets() {
        return currentTargets;
    }

    public BlockingDeque<WeaponCard> getChosenWeapons() {
        return chosenWeapons;
    }

    public BlockingDeque<Platform> getChosenDestination() {
        return chosenDestination;
    }

    public BlockingDeque<Boolean> getChosenBinaryOption() {
        return chosenBinaryOption;
    }

    public BlockingDeque<String> getChosenAmmo() {
        return chosenAmmo;
    }

    public BlockingDeque<Integer> getChosenEffect() {
        return chosenEffect;
    }

    public ControllerState getState() {
        return state;
    }

    /**
     * Set the new state of the controller
     *
     * @param newState
     */
    public void setState(ControllerState newState) {
        state = newState;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public int getConfigMap() {
        return configMap;
    }

    public Map<String, VirtualView> getUserView() {
        return userView;
    }

    public List<String> getPingsList() {
        return pingsList;
    }

    public List<String> getAlreadyNotified() {
        return alreadyNotified;
    }

    public List<String> getPingsWaitingList() {
        return pingsWaitingList;
    }

    /**
     * Activate the final frenzy mode
     */
    public void activateFrenzyMode() {
        for (Player player : game.getPlayers())
            if (player.hasNoDamage())
                player.getPlayerBoard().setReverted(true);
        frenzyModeOn = true;
    }

    /**
     * @return if frenzy mode is on
     */
    public boolean isFrenzyModeOn() {
        return frenzyModeOn;
    }

    public boolean wasRecharged() {
        return wasRecharged;
    }

    public void setWasRecharged(boolean wasRecharged) {
        this.wasRecharged = wasRecharged;
    }

    public void setServerReloaded(boolean serverReloaded) {
        this.serverReloaded = serverReloaded;
    }

    public boolean isGameIsActive() {
        return gameIsActive;
    }

    public void setGameIsActive(boolean gameIsActive) {
        this.gameIsActive = gameIsActive;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
