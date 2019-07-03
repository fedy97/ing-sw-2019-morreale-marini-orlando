package it.polimi.se2019.view;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Simone Orlando
 */
public abstract class View extends Observable implements Observer, Serializable {
    protected String userName;

    public void viewSetChanged() {
        this.setChanged();
    }

    public String getUserName() {
        return userName;
    }

    public abstract void start();

    public abstract void setCommunicationType();

    public abstract void startConnection();

    public abstract void setUserName();

    public abstract void waitGameStart();

    public abstract void startGame();

}
