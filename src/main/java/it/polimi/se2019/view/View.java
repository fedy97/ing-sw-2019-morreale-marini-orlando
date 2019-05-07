package it.polimi.se2019.view;

import it.polimi.se2019.Action;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Simone Orlando
 */
public abstract class View extends Observable implements Observer {

    protected State state;
    protected String userName;
    protected Action chosenAction;

    public abstract void start();

    public abstract void setCommunicationType();

    public abstract void startConnection();

    public abstract void setUserName();

    public abstract void waitGameStart();

    public abstract void menageTurn();

    public abstract void setState(State newState);

    public abstract void startGame();

}
