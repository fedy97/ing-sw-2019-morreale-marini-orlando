package it.polimi.se2019.view.client;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class RemoteView extends View {

    public abstract void updatePlayersOnWaitingList(List<String> users);

    public abstract void updateTimerLobby(int count);

    public abstract void updateTimerMap(int count);

    public abstract void updateTimerCharacter(int count);

    public abstract void updateAll(LightGameVersion lightGameVersion);

    public abstract void showChooseMap();

    public abstract void showChooseCharacter(String config);

    public abstract void showGameBoard(List<AmmoRep> ammoReps, Map<String, List<CardRep>> posWeapons, List<String> arrChars);

    public abstract void showChoosePowerup(CardRep p1, CardRep p2);

    public abstract void setRandomChar(String randomChar);

    public abstract void updateVotesMapChosen(Map<Integer, Integer> map);

    public abstract void updateVotesCharacterChosen(String c);

    public abstract void lightWeapons(List<String> weapons);

    public abstract void switchWeapon();

    public abstract void buyWithPowerups(Map<String, Boolean> powerups);

    public abstract void lightPlatforms(List<String> platforms);

    public abstract void setValidActions(boolean[] actives);

    public abstract void showMessage(String msg);

}
