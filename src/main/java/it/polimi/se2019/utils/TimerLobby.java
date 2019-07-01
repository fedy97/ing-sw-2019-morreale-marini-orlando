package it.polimi.se2019.utils;

import it.polimi.se2019.controller.Controller;

/**
 * this class is used to create the timer in waiting lobby, each second
 * will be called setSecondsLeftLobby
 */
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
                if (Controller.getInstance().isTimerStarted()) {
                    sleep(1000);
                    slept = slept + 1000;
                    Controller.getInstance().setSecondsLeftLobby(seconds - slept / 1000);
                } else
                    break;
            }
        } catch (Exception ec) {
            CustomLogger.logException(this.getClass().getName(), ec);
        }

    }
}

