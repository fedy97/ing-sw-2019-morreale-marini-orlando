package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;
import org.junit.Test;

public class TestTimers {

    @Test
    public void testLobbyTimer() {
        Controller.getInstance().setTimerStarted(true);
        TimerLobby timerLobby = new TimerLobby(5);
        timerLobby.start();

    }

    @Test
    public void testMapTimer() {
        TimerMap timerMap = new TimerMap(5);
        timerMap.start();
    }

    @Test
    public void testCharacterTimer() {
        TimerCharacter timerCharacter = new TimerCharacter(5);
        timerCharacter.start();
    }
    @Test
    public void testTurnTimer(){
        TimerTurn timerTurn = new TimerTurn(5,null,"BANSHEE");
        try {
            timerTurn.start();
        } catch (Exception e){
            timerTurn.interrupt();
        }
    }
}
