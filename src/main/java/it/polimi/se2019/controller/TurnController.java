package it.polimi.se2019.controller;

import it.polimi.se2019.model.Game;

import java.util.*;

/**
 * @author Gabriel Raul Marini
 */
public class TurnController {
    private String curr;
    private String first;
    private List<String> turningOrder;
    private Timer turnTimer;

    public TurnController(){
        turningOrder = new ArrayList<>();
        turnTimer = new Timer();
    }

    /**
     * Init the game logic
     */
    public void start(){
        curr = first;
        Controller.getInstance().getPlayerManager().setCurrentPlayer(Game.getInstance().getPlayer(first));

    }

    /**
     * @param user to be added to the ordered list
     */
    public void addUser(String user){
        if(turningOrder.isEmpty())
            first = user;
        turningOrder.add(user);
    }

    /**
     * End the turn of the current user and switch to the next
     */
    public void endTurn(){
        int currIndex = turningOrder.indexOf(curr);
        if((currIndex + 1) == turningOrder.size())
            curr = turningOrder.get(0);
        else
            curr = turningOrder.get(currIndex + 1);
        Controller.getInstance().getPlayerManager().setCurrentPlayer(Game.getInstance().getPlayer(curr));
    }

    /**
     * @return The player who has the turn
     */
    public String getTurnUser() {
        return curr;
    }

    public List<String> getUsers(){
        return turningOrder;
    }

    public void removeUser(String user){
        turningOrder.remove(user);
    }

    /**
     * @return The current value of the timer
     */
    public Timer getTurnTimer() {
        return turnTimer;
    }


}