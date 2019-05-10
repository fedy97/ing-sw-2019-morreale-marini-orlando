package it.polimi.se2019.controller;

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
        turningOrder = new ArrayList<String>();
        turnTimer = new Timer();
    }

    /**
     * Init the game logic
     */
    public void start(){
        curr = first;
    }

    /**
     * @param user to be added to the ordered list
     */
    public void addUser(String user){
        if(turningOrder.size() == 0)
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
    }

    /**
     * @return The player who has the turn
     */
    public String getTurnUser() {
        return curr;
    }

    /**
     * @return The current value of the timer
     */
    public Timer getTurnTimer() {
        return turnTimer;
    }

    private void stopTimer() {
        //TODO
    }

}