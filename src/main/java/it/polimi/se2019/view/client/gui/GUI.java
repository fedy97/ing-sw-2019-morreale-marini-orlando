package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.Action;
import it.polimi.se2019.view.client.RemoteView;
import it.polimi.se2019.view.State;

/**
 * @author Simone Orlando
 */
public class GUI extends RemoteView {

    private LoginPage login;
    public GUI() {
        login = new LoginPage();
    }

    @Override
    public void start() {
        login.startLoginPage();
    }

    @Override
    public void startGame() {

    }

    @Override
    public void setComunicationType() {

    }

    @Override
    public void startConnection() {

    }

    @Override
    public void setUserName() {

    }

    @Override
    public void waitGameStart() {

    }

    @Override
    public void menageTurn() {

    }


    @Override
    public void setState(State newState) {

    }

}

