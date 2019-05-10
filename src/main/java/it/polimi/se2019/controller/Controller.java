package it.polimi.se2019.controller;

import it.polimi.se2019.Lobby;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.*;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.Timer;
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
    private Map<String, Player> userPlayer;
    private Map<String, VirtualView> userView;
    private VirtualView currentView;
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
        userPlayer = new HashMap<>();
    }


    @Override
    /**
     * Called when the VirtualView notify changes
     */
    public void update(Observable virtualView, Object message) {
        //new client connected is actually a ToServerCommand,
        //however I did not create the class, I am lazy
        if (message.equals("new client connected")) {
            //notify all clients connected
            for (String user : Lobby.getUsers()) {
                callView(new NewConnectionMessage(Lobby.getUsers()), user);
            }
        } else if (message.equals("we are at least 2")) {
            //this timer will modify the model(Game) where the seconds integer is hold
            Timer t = new Timer(5);
            t.start();
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
        //TODO
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
        else if (choice.equals("targets"))
            msg = new ShowPossibleTargetsMessage(lightVersion);
        else if (choice.equals("discard"))
            msg = new AskForCubeMessage(lightVersion);
        else if (choice.equals("cube"))
            msg = new AskForCubeMessage(lightVersion);
        else
            msg = null; // OTHER options

        callView(msg, getUserFromPlayer(currPlayer));
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
     * @param player value of the map
     * @return the user associated to the param player
     */
    private String getUserFromPlayer(Player player) {
        String user = null;

        for (Map.Entry<String, Player> entry : userPlayer.entrySet()) {
            if (entry.getValue() == player)
                user = entry.getKey();
        }

        return user;
    }

    /**
     * Common method across RMI and Socket to send requests to client
     *
     * @param msg  to the destination client
     * @param user recipient of the message
     */
    private void callView(ToClientMessage msg, String user) {
        if (Lobby.getRmiServer().isConnected(user))
            Lobby.getRmiServer().sendToClient(msg, user);
        if (Lobby.getSocketServer().isConnected(user))
            Lobby.getSocketServer().sendToClient(msg, user);
    }

    /**
     * Associate a user to a player in the model
     *
     * @param user   chosen by the client
     * @param player to be associated with the user selected
     */
    public void setPlayerToUser(String user, Player player) {
        userPlayer.put(user, player);
    }

    public void addVirtualView(VirtualView virtualView, String user) {
        userView.put(user, virtualView);
    }


}
