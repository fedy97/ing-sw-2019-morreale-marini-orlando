package it.polimi.se2019.utils;

import it.polimi.se2019.view.client.RemoteView;

/**
 * this class is used to create the timer at the beginning of each turn, each second
 * will be called updateTimerTurn
 */
public class TimerTurn extends Thread {
    private int seconds;
    private RemoteView remoteView;
    private String curr;

    public TimerTurn(int seconds, RemoteView remoteView, String curr) {
        this.seconds = seconds;
        this.remoteView = remoteView;
        this.curr = curr;

    }

    @Override
    public void run() {
        try {
            int slept = 0;
            while (slept < seconds * 1000) {
                sleep(1000);
                slept = slept + 1000;

                remoteView.updateTimerTurn(seconds - slept / 1000, curr);

            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}