package it.polimi.se2019.view.client;

import it.polimi.se2019.model.rep.AmmoRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import it.polimi.se2019.view.View;

import java.util.List;
import java.util.Map;

public abstract class RemoteView extends View {
    /**
     * @param users to display on the lobby
     */
    public abstract void updatePlayersOnWaitingList(List<String> users);

    /**
     * @param count to display, it decreases every second in lobby
     */
    public abstract void updateTimerLobby(int count);

    /**
     * @param count to display, it decreases every second in choosing map
     */
    public abstract void updateTimerMap(int count);

    /**
     * @param count to display, it decreases every second in choosing character
     */
    public abstract void updateTimerCharacter(int count);

    /**
     * this is the view-model
     *
     * @param lightGameVersion is the model serialized, ready to be deserialized by the view
     */
    public abstract void updateAll(LightGameVersion lightGameVersion);

    /**
     * shows the choose map screen
     */
    public abstract void showChooseMap();

    /**
     * @param config
     */
    public abstract void showChooseCharacter(String config);

    /**
     * @param ammoReps
     * @param posWeapons
     * @param arrChars
     */
    public abstract void showGameBoard(List<AmmoRep> ammoReps, Map<String, List<CardRep>> posWeapons, List<String> arrChars);

    /**
     * the 2 powerups drawed from the deck by the controller
     * p1 and p2 are the serialized reps of the PowerUpCard Objects
     *
     * @param p1
     * @param p2
     */
    public abstract void showChoosePowerup(CardRep p1, CardRep p2);

    /**
     * @param randomChar if the player does not choose a character
     */
    public abstract void setRandomChar(String randomChar);

    /**
     * @param map of votes
     */
    public abstract void updateVotesMapChosen(Map<Integer, Integer> map);

    /**
     * @param c chose by another player, now it becomes unavailable
     */
    public abstract void updateVotesCharacterChosen(String c);

    /**
     * @param weapons in the gamefield to light when grabbing a new weapon
     */
    public abstract void lightWeapons(List<String> weapons);

    /**
     * displays the 3 weapons, the player will selecte which of the three will be switched
     */
    public abstract void switchWeapon();

    /**
     * @param platforms to enlight
     */
    public abstract void lightPlatforms(List<String> platforms);

    /**
     * actions to enable or disable client side
     *
     * @param actives every cell is associated with a action, see
     *                GameBoardController line 1045
     */
    public abstract void setValidActions(boolean[] actives);

    /**
     * @param msg to display if something happens,
     *            for ex to notify players if someone disconnects
     */
    public abstract void showMessage(String msg);

    /**
     * in order to decide who will be shot
     * @param targets hashcodes, in remoteview we have lightgameversion to decode it
     */
    public abstract void showTargets(List<String> targets);

    /**
     * list of all usable effects of the weapon selected
     * @param effects the list which can contain 0,1,2
     */
    public abstract void enlightenEffects(List<Integer> effects);

    /**
     * the answer can only be yes or no
     * @param message to display before the binary request
     */
    public abstract void showBinaryOption(String message);

    /**
     * used when someone wants to reconnect, the following params are requested
     * in order to set up everything again in the right way
     * @param config map
     * @param lightGameVersion
     * @param arr of the characters in game
     * @param myChar
     */
    public abstract void showReconnectedGameBoard(int config, LightGameVersion lightGameVersion, List<String> arr, String myChar);

    /**
     * if the ping is received, a response will be immediately sent to the server
     */
    public abstract void receivePingFromServer();

    /**
     * pinging the waiting list
     */
    public abstract void receiveWaitingPingFromServer();

    public abstract void resetTimer();

    /**
     * this method is called only once, not every second, by the server
     * @param count is the starting time of the timer
     * @param curr player to disconnect in case the timer's up
     */
    public abstract void startTimerTurn(int count, String curr);

    public abstract void updateTimerTurn(int seconds, String curr);

    /**
     * @param weapons hashcodes
     */
    public abstract void showReloadableWeapons(List<String> weapons);

    /**
     * @param weapons hashcodes
     */
    public abstract void showUsableWeapons(List<String> weapons);

    /**
     * @param powerups hashcodes
     */
    public abstract void showUsablePowerups(List<String> powerups);

}
