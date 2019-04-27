package it.polimi.se2019.view;

import it.polimi.se2019.model.player.Player;

import java.rmi.Remote;

/**
 *
 */
public abstract class View implements Remote {

    protected Player player;

    public View() {}
}
