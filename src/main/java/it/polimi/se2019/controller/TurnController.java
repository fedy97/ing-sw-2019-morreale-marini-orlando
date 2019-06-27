package it.polimi.se2019.controller;

import it.polimi.se2019.controller.validator.*;
import it.polimi.se2019.model.PointsCounter;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.network.message.to_client.EnablePlayerActionsMessage;
import it.polimi.se2019.network.message.to_client.ShowChoosePowerUpMessage;
import it.polimi.se2019.network.message.to_client.ShowMessage;
import it.polimi.se2019.network.message.to_client.StartTimerTurnMessage;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Raul Marini
 */
public class TurnController implements Serializable {
    private String curr;
    private String first;
    private List<String> turningOrder;
    private int seconds;
    private int frenzyStart;
    private int currIndex;
    private Controller c;
    private boolean firstTurn;

    public TurnController() {
        firstTurn = true;
        turningOrder = new ArrayList<>();
        seconds = HandyFunctions.parserSettings.getTurnTimer();
    }

    /**
     * Init the game logic
     */
    public void start() {
        curr = first;
        c = Controller.getInstance();
        c.getPlayerManager().setCurrentPlayer(Controller.getInstance().getGame().getPlayer(first));
        c.notifyAll(new StartTimerTurnMessage(seconds, getTurnUser()));
    }

    public void notifyFirst() {
        c.callView(new EnablePlayerActionsMessage(UserValidActions.NO_SHOOT.getActions()), c.getPlayerManager().getCurrentPlayer().getName());
        c.callView(new ShowMessage("It's your turn!"), c.getPlayerManager().getCurrentPlayer().getName());
    }

    /**
     * @param user to be added to the ordered list
     */
    public void addUser(String user) {
        if (turningOrder.isEmpty())
            first = user;
        turningOrder.add(user);
    }

    /**
     * End the turn of the current user and switch to the next
     */
    public void endTurn() {
        Player previousPlayer = c.getPlayerManager().getCurrentPlayer();
        previousPlayer.getPlayerBoard().getAmmoBox().getOptionals().clear();

        checkDeadPlayers();

        curr = nextUser();

        //Refill operations
        if (currIndex == 0) {
            refillGameField();
            firstTurn = false;
        }

        if (c.isFrenzyModeOn() && currIndex == frenzyStart)
            c.endGame();

        Player currPlayer = c.getGame().getPlayer(curr);
        c.getPlayerManager().setCurrentPlayer(currPlayer);

        //Check if we have to activate the final frenzy
        checkFinalFrenzy();

        //Validator setting
        setRightValidator(currPlayer);

        //Enable actions to the right player
        enablePlayers(currPlayer);

        //notify the model changes
        c.getGame().notifyChanges();
    }


    private String nextUser() {
        currIndex = turningOrder.indexOf(curr);
        if (currIndex + 1 == turningOrder.size())
            currIndex = 0;
        else
            currIndex++;
        return turningOrder.get(currIndex);
    }

    /**
     * Set the right validator according to the player health state
     *
     * @param currPlayer
     */
    private void setRightValidator(Player currPlayer) {
        if (c.isFrenzyModeOn()) {
            if (currIndex >= frenzyStart && frenzyStart > 0) {
                c.setValidator(new Frenzy1Validator(c));
                currPlayer.setFrenzyModeType(1);
            } else {
                c.setValidator(new Frenzy2Validator(c));
                currPlayer.setFrenzyModeType(2);
            }
        } else {
            if (currPlayer.isDamaged())
                c.setValidator(new DamagedValidator(c));
            else if (currPlayer.isSeriouslyDamaged())
                c.setValidator(new CriticalDamagedValidator(c));
            else
                c.setValidator(new HealthyValidator(c));
        }
    }

    /**
     * Set the allowed action to every player
     *
     * @param currPlayer
     */
    private void enablePlayers(Player currPlayer) {
        for (Player player : c.getGame().getPlayers()) {
            // && c.getPingsList().size() >= 2
            if (player.equals(currPlayer) && player.isConnected()) {

                if (firstTurn || player.getFrenzyModeType() == 2)
                    c.callView(new EnablePlayerActionsMessage(UserValidActions.NO_SHOOT.getActions()), player.getName());
                else
                    c.callView(new EnablePlayerActionsMessage(UserValidActions.ALL.getActions()), player.getName());
                c.sendMessage("It's your turn!", player.getName());

                if (currPlayer.getCurrentPlatform() == null) {
                    List<CardRep> cardReps = new ArrayList<>();
                    PowerUpCard p1 = c.getDecksManager().drawPowerUp();
                    PowerUpCard p2 = c.getDecksManager().drawPowerUp();
                    c.getPlayerManager().getCurrentPlayer().addPowerUpCard(p1);
                    c.getPlayerManager().getCurrentPlayer().addPowerUpCard(p2);
                    cardReps.add(new CardRep(p1));
                    cardReps.add(new CardRep(p2));
                    ShowChoosePowerUpMessage message = new ShowChoosePowerUpMessage(cardReps);
                    c.callView(message, currPlayer.getName());
                }

            } else if (player.isConnected()) {
                c.callView(new EnablePlayerActionsMessage(UserValidActions.NONE.getActions()), player.getName());
                c.sendMessage("It's ".concat(c.getPlayerManager().getCurrentPlayer().getName()).concat(" turn!"), player.getName());
            }
            c.callView(new StartTimerTurnMessage(seconds, getTurnUser()), player.getName());
        }
    }

    /**
     * Perform the refill operations at the end of the main turn
     */
    private void refillGameField() {

        for (int i = 0; i < c.getDecksManager().getToFill().size(); i++) {
            try {
                AmmoCard newAmmo = c.getDecksManager().getNewAmmoCard(c.getDecksManager().getAmmoGarbageDeck().get(i));
                c.getDecksManager().getToFill().get(i).setPlatformAmmoCard(newAmmo);
                c.getGame().notifyChanges();
            } catch (Exception e) {
                CustomLogger.logException(this.getClass().getName(), e);
            }
        }

        for (Platform platform : c.getGame().getGameField().getPlatforms()) {
            if (platform.isGenerationSpot()) {
                try {
                    while (platform.getWeapons().size() < 3)
                        platform.getWeapons().add(c.getGame().getWeaponsDeck().drawCard());

                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
                }
            }
        }

        c.getDecksManager().getToFill().clear();
        c.getDecksManager().getAmmoGarbageDeck().clear();
    }

    /**
     * Check if players where killed during the turn
     */
    private void checkDeadPlayers() {
        int kills = 0;

        for (Player player : c.getGame().getPlayers()) {
            System.out.println(player.getName());
            HandyFunctions.printList(player.getPlayerBoard().getDamageLine());
            if (player.isDead()) {
                c.getGame().setScore(PointsCounter.getPoints(player));
                player.addDeath();

                try {
                    if (!c.isFrenzyModeOn()) {
                        if (player.wasOverkilled())
                            c.getGame().getGameField().getSkullsBoard().addKillMarks(player.getPlayerBoard().getDamageLine().get(10), 2);
                        else
                            c.getGame().getGameField().getSkullsBoard().addKillMarks(player.getPlayerBoard().getDamageLine().get(10), 1);
                    }
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
                }
                player.getPlayerBoard().resetDamageLine();
                askToSpawn(player);
                kills++;
            }
        }

        if (kills > 1)
            c.getPlayerManager().getCurrentPlayer().addPoint(1);
    }

    /**
     * Check if is the case to activate the final frenzy
     */
    private void checkFinalFrenzy() {
        if (!c.isFrenzyModeOn() && c.getGame().getGameField().getSkullsBoard().getCurrentSkulls() == 0) {
            c.activateFrenzyMode();
            frenzyStart = currIndex;
        }
    }

    private void askToSpawn(Player p) {
        p.addPowerUpCard(c.getDecksManager().drawPowerUp());
        List<CardRep> reps = new ArrayList<>();
        for (PowerUpCard powerUpCard : p.getPowerUpCards())
            reps.add(new CardRep(powerUpCard));
        ShowChoosePowerUpMessage message = new ShowChoosePowerUpMessage(reps);
        c.callView(message, p.getName());
    }

    public String getTurnUser() {
        return curr;
    }

    public List<String> getUsers() {
        return turningOrder;
    }

    public void removeUser(String user) {
        turningOrder.remove(user);
    }

}