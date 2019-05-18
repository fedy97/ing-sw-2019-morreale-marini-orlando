package it.polimi.se2019.controller;

import it.polimi.se2019.Lobby;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.*;
import it.polimi.se2019.network.message.to_server.ToServerMessage;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.TimerLobby;
import it.polimi.se2019.view.server.VirtualView;

import java.util.*;

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
    private VirtualView currentView;
    private List<String> validActions;
    private ControllerState state; //the state is set to processing power up or weapon when a specific message from the client (ActivateCardMessage) is received
    private List<Player> currentTargets;
    private List<Integer> processingStages;
    private PowerUpCard processingPowerUp;
    private WeaponCard processingWeaponCard;
    private boolean lock;
    private static Controller instance = null;


    /**
     * Controller singleton constructor
     *
     * @return instance
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        validActions = new ArrayList<>();
        currentTargets = new ArrayList<>();
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
        this.playerManager = new PlayerManager(this);
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
                TimerLobby t = new TimerLobby(5);
                t.start();
            } else {
                //set chosen map from client and character
                ((ToServerMessage) message).performAction();
            }
        } else {
            ((ToServerMessage) message).performAction();

            if(state == ControllerState.PROCESSING_POWERUP){
                processingStages.remove(0);
                processPowerUp(processingPowerUp);
            }else if(state == ControllerState.PROCESSING_WEAPON){
                processingStages.remove(0);
                processWeaponCard(processingWeaponCard);
            }

            state = ControllerState.IDLE;
        }
    }

    /**
     * @param powerUp composed of different stages in order to perform the final effect
     */
    public void processPowerUp(PowerUpCard powerUp) {
        // powerUp.activate(processingStages.get(0));
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
        if (processingStages.isEmpty()){
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
     * @param msg  to the destination client
     * @param user recipient of the message
     */
    private void callView(ToClientMessage msg, String user) {
        /*if (Lobby.getRmiServer().isConnected(user))
            Lobby.getRmiServer().sendToClient(msg, user);
        if (Lobby.getSocketServer().isConnected(user))
            Lobby.getSocketServer().sendToClient(msg, user);*/
        userView.get(user).callView(msg,user);
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

    public List<Player> getCurrentTargets() {
        return currentTargets;
    }

    public void setState(ControllerState state) {
        this.state = state;
    }

    public void addStages(List<Integer> stages) {
        processingStages.addAll(stages);
    }
}
