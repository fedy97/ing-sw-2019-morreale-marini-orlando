package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;
import org.junit.Test;

public class TestTimers {

    @Test
    public void testLobbyTimer() {
        Controller.getInstance().setTimerStarted(true);
        TimerLobby timerLobby = new TimerLobby(5);
        timerLobby.start();
        timerLobby.interrupt();

    }

    @Test
    public void testMapTimer() {
        TimerMap timerMap = new TimerMap(5);
        timerMap.start();
        timerMap.interrupt();
    }

    @Test
    public void testCharacterTimer() {
        TimerCharacter timerCharacter = new TimerCharacter(5);
        timerCharacter.start();
        timerCharacter.interrupt();
    }
}
