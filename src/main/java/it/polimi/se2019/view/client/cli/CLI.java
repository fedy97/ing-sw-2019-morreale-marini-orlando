package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.to_server.SendCharacterChosenMessage;
import it.polimi.se2019.network.message.to_server.SendMapChosenMessage;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.client.RemoteView;

import java.io.Console;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Level;

/**
 * @author Simone Orlando
 */
public class CLI extends RemoteView {

    private CliReader reader;
    private int connectionChosen;
    private String ip;
    private int mapChosen = 0;
    private int timeLeftForMaps = 0;
    private int timeLeftForChar = 0;
    private int[] vote;
    private boolean firstTime = true;
    private ArrayList<String> charChosen;
    private int myChar = 0;
    private String myCharEnumString;

    public CLI() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        reader = new CliReader(5);
        vote = new int[4];
        vote[0] = 0;
        vote[1] = 0;
        vote[2] = 0;
        vote[3] = 0;
        charChosen = new ArrayList<>();
    }

    @Override
    public void updateVotesCharacterChosen(String c) {
        if (!c.equals(myCharEnumString))
            charChosen.add(c);
        showChooseCharacter();
    }

    @Override
    public void updateTimerCharacter(int count) {
        timeLeftForChar = count;
        showChooseCharacter();
    }

    @Override
    public void showChooseCharacter() {
        if(!firstTime) {
            new Thread( () -> {
                boolean okChar = false;
                Console c = System.console();
                while (!okChar) {
                    int temp;
                    temp = Character.getNumericValue((c.readPassword())[0]);
                    if (temp == 1) {
                        myCharEnumString = "BANSHEE";
                    } else if (temp == 2) {
                        myCharEnumString = "SPROG";
                    } else if (temp == 3) {
                        myCharEnumString = "DOZER";
                    } else if (temp == 4) {
                        myCharEnumString = "VIOLET";
                    } else {
                        myCharEnumString = "DISTRUCTOR";
                    }
                    if (!charChosen.contains(myCharEnumString)) {
                        okChar = true;
                        myChar = temp;
                    }
                }
                SendCharacterChosenMessage message = new SendCharacterChosenMessage(myCharEnumString);
                message.setSender(userName);
                viewSetChanged();
                notifyObservers(message);
            }).start();
            firstTime = true;
        }
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.possibleCharMessage(timeLeftForChar,charChosen,myChar);
    }

    @Override
    public void updateTimerMap(int count) {
        timeLeftForMaps = count;
        showChooseMap();
    }

    @Override
    public void updateVotesMapChosen(Map<Integer, Integer> map) {
        for(int i=1; i<=4;i++) {
            vote[i-1] = map.get(i);
        }
        showChooseMap();
    }

    @Override
    public void showChooseMap() {
        if(firstTime) {
            new Thread( () -> {
                Console c = System.console();
                mapChosen = Character.getNumericValue((c.readPassword())[0]);
                SendMapChosenMessage message = new SendMapChosenMessage(mapChosen);
                message.setSender(userName);
                viewSetChanged();
                notifyObservers(message);
            }).start();
            firstTime = false;
        }
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.possibleMapsMessage(timeLeftForMaps,vote);
    }

    @Override
    public void updateTimerLobby(int count) {
        CliPrinter.timerMessage(count);
    }

    @Override
    public void start() {
        CliPrinter.welcomeMessage();
        setCommunicationType();
        setUserName();
        waitGameStart();
        startConnection();
    }

    @Override
    public void updatePlayersOnWaitingList(List<String> users) {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxLobbyMessage(users);
    }

    @Override
    public void startGame() {
        HandyFunctions.printConsole("The game is started!\n");
    }

    @Override
    public void setCommunicationType() {
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxConnectionMessage();
        connectionChosen = reader.getInt();
    }

    @Override
    public void startConnection() {
        final int SOCKET = 1;
        if(connectionChosen == SOCKET) {
            SocketClient client = new SocketClient(this, userName);
            client.connect(ip,1100);
            this.addObserver(client);
        }
        else {

            RMIClient client = new RMIClient(this,HandyFunctions.randomIntegerBetWeen(1500,2000),userName);
            client.connect(ip,1099);
            this.addObserver(client);
        }
    }

    @Override
    public void setUserName() {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxUsernameMessage();
        userName = reader.getString();
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxIpMessage(userName);
        ip = reader.getString();
    }

    @Override
    public void waitGameStart() {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxWaitingMessage();
    }

    @Override
    public void setState(State newState) {
        state = newState;
    }

    @Override
    public void update(Observable obs, Object obj) {
        //TODO
    }

    @Override
    public void lightWeapons(List<String> weapons) {
        //TODO
    }

    @Override
    public void lightPlatforms(List<String> platforms) {
        for (String platform : platforms) {
            HandyFunctions.printLineConsole("Platform" + platform + "was lighted!");
        }
        //TODO
    }
}
