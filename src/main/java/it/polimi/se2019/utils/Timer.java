package it.polimi.se2019.utils;

import it.polimi.se2019.model.Game;

public class Timer extends Thread {
    private int seconds;

    public Timer(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        try {
            int slept = 0;
            while (slept < seconds * 1000) {
                sleep(1000);
                slept = slept + 1000;
                Game.getInstance().setSecondsLeft(seconds - slept / 1000);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }
}

