package it.polimi.se2019.view;

import it.polimi.se2019.network.client.Client;

/**
 * @author Simone Orlando
 */
public abstract class State {
    protected Client client;

    public abstract void startRoutine();

    protected void showCurrentPlayer() {
        //TODO
    }

    protected void showGameField() {
        //TODO
    }

    protected void showPlayerState() {
        //TODO
    }
}
