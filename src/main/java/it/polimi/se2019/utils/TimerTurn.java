package it.polimi.se2019.utils;

import it.polimi.se2019.view.client.gui.GUI;

public class TimerTurn extends Thread {
    private int seconds;
    private GUI gui;
    private String curr;
    public TimerTurn(int seconds, GUI gui, String curr) {
        this.seconds = seconds;
        this.gui = gui;
        this.curr = curr;

    }

    @Override
    public void run() {
        try {
            int slept = 0;
            while (slept < seconds * 1000) {
                sleep(1000);
                slept = slept + 1000;

                gui.updateTimerTurnLabel(seconds - slept / 1000, curr);

            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}