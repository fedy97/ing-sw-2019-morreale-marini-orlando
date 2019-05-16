package it.polimi.se2019.view.client;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.view.View;

import java.util.List;
import java.util.Map;

public abstract class RemoteView extends View {

    public abstract void updatePlayersOnWaitingList(List<String> users);
    public abstract void updateTimerLobby(int count);
    public abstract void updateTimerMap(int count);
    public abstract void updateTimerCharacter(int count);
    public abstract void showChooseMap();
    public abstract void showChooseCharacter(String config);
    public abstract void showGameBoard();
    public abstract void showChoosePowerup(String im1, String im2);
    public abstract void updateVotesMapChosen(Map<Integer,Integer> map);
    public abstract void updateVotesCharacterChosen(String c);
    public abstract void lightWeapons(List<String> weapons);
    public abstract void lightPlatforms(List<String> platforms);

}
