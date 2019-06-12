package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;

public class TimerTurn extends Thread {
    private int seconds;

    public TimerTurn(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        try {
            int slept = 0;
            while (slept < seconds * 1000) {
                sleep(1000);
                slept = slept + 1000;

                Controller.getInstance().setTurnTimer(seconds - slept / 1000);

            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}