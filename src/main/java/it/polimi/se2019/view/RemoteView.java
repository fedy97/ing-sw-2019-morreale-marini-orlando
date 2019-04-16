package it.polimi.se2019.view;

/**
 * @author Gabriel Raul Marini
 */
public abstract class RemoteView {

    public RemoteView() {

    }

    public abstract void showMessage(String msg);

    public abstract void reportError();

    public void update() {
        //TODO
    }
}
