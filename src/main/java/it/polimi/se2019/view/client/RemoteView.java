package it.polimi.se2019.view.client;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.view.View;

import java.util.List;

/**
 * @author Simone Orlando
 */
public abstract class RemoteView extends View {

    public abstract void lightWeapons(List<String> weapons);

}
