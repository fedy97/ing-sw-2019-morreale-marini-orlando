package it.polimi.se2019.view;

/**
 * @author Simone Orlando
 */
public abstract class View {

    protected State state;
    protected String userName;

    public abstract void start();

    protected abstract void setComunicationType();

    protected abstract void startConnection();

    protected abstract void setUserName();

    protected abstract void waitGameStart();

    protected abstract void menageTurn();

    protected abstract void getCurrentPlayer();

    protected abstract void setState(State newState);
}
