package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;

public class TimerLobby extends Thread {
    private int seconds;
    public TimerLobby(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        try {
            int slept = 0;
            while (slept < seconds * 1000) {
                sleep(1000);
                slept = slept + 1000;
                Controller.getInstance().setSecondsLeftLobby(seconds - slept / 1000);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }
}

