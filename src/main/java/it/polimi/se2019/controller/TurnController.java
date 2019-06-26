package it.polimi.se2019.controller;

import it.polimi.se2019.controller.validator.CriticalDamagedValidator;
import it.polimi.se2019.controller.validator.DamagedValidator;
import it.polimi.se2019.controller.validator.HealthyValidator;
import it.polimi.se2019.controller.validator.UserValidActions;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.model.PointsCounter;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.enumeration.Character;
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
import java.util.Map;

/**
 * @author Gabriel Raul Marini
 */
public class TurnController implements Serializable {
    private String curr;
    private String first;
    private List<String> turningOrder;
    private int seconds;
    private Controller c;

    public TurnController() {
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
        c.callView(new EnablePlayerActionsMessage(UserValidActions.ALL.getActions()), c.getPlayerManager().getCurrentPlayer().getName());
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
        int currIndex = turningOrder.indexOf(curr);
        Player previousPlayer = c.getPlayerManager().getCurrentPlayer();
        previousPlayer.getPlayerBoard().getAmmoBox().getOptionals().clear();

        checkDeadPlayers();
        //Refill operations
        if ((currIndex + 1) == turningOrder.size()) {
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
                            try {
                                platform.getWeapons().add(c.getGame().getWeaponsDeck().drawCard());
                            } catch (NoCardException e) {
                                CustomLogger.logInfo(this.getClass().getName(), "No more weapons to refill the field!");
                            }
                    } catch (Exception e) {
                        CustomLogger.logException(this.getClass().getName(), e);
                    }
                }
            }

            c.getDecksManager().getToFill().clear();
            c.getDecksManager().getAmmoGarbageDeck().clear();
        }
        curr = nextUser();

        Player currPlayer = c.getGame().getPlayer(curr);
        c.getPlayerManager().setCurrentPlayer(currPlayer);

        if (currPlayer.isDamaged())
            c.setValidator(new DamagedValidator(c));
        else if (currPlayer.isSeriouslyDamaged())
            c.setValidator(new CriticalDamagedValidator(c));
        else
            c.setValidator(new HealthyValidator(c));

        for (Player player : c.getGame().getPlayers()) {
            if (player.equals(currPlayer) && player.isConnected() && c.getPingsList().size() >= 2) {
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

        c.getGame().notifyChanges();
    }


    private String nextUser() {
        int currIndex = turningOrder.indexOf(curr);
        if (currIndex + 1 == turningOrder.size())
            currIndex = 0;
        else
            currIndex++;
        return turningOrder.get(currIndex);
    }

    /**
     * Check if players where killed during the turn
     */
    private void checkDeadPlayers() {
        for (Player player : c.getGame().getPlayers())
            if (player.isDead()) {
                for (Map.Entry<Character, Integer> entry : PointsCounter.getPoints(player).entrySet())
                    c.getGame().getPlayer(entry.getKey()).addPoint(entry.getValue());
                askToSpawn(player);
                player.getPlayerBoard().resetDamageLine();
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