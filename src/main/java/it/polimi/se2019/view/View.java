package it.polimi.se2019.view;

import it.polimi.se2019.Action;

/**
 * @author Simone Orlando
 */
public abstract class View {

    protected State state;
    protected String userName;
    protected Action chosenAction;

    public abstract void start();

    public abstract void setComunicationType();

    public abstract void startConnection();

    public abstract void setUserName();

    public abstract void waitGameStart();

    public abstract void menageTurn();

    public abstract void setState(State newState);

    public abstract void startGame();

}
