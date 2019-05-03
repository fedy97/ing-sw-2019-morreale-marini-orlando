package it.polimi.se2019;

import it.polimi.se2019.model.player.Player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lobby {

    private static List<String> users;

    private static Map<String, Player> userPlayer;

    public static void main(String[] args) {
        users = new ArrayList<>();

    }



}
