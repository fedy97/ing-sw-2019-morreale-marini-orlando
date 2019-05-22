package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.to_server.*;
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
    private String chosenBoard;
    private int[][] map;

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
        map = new int[3][4];
    }

    @Override
    public void updateAll(LightGameVersion lightGameVersion) {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        CliSetUp.cursorLeft(109);
        CliSetUp.cursorDown(2);
        CliPrinter.printPlatformWeapons(lightGameVersion);
        CliSetUp.cursorRight(38);
        CliSetUp.cursorUp(23);
        CliPrinter.printMap(lightGameVersion, chosenBoard);
        CliSetUp.cursorDown(9);
        CliSetUp.cursorLeft(110);
        CliPrinter.standardActionsMessage();
        CliSetUp.cursorUp(34);
        CliSetUp.cursorRight(10);
        CliPrinter.drawPlayersInfoBox(lightGameVersion);
        CliSetUp.cursorDown(20);
        CliSetUp.cursorLeft(106);
        new Thread( () -> {
            Console c = System.console();
            int choise;
            choise = Character.getNumericValue((c.readPassword())[0]);
            if (choise == 1)
                iWantToMove();
            else if(choise == 2)
                iWantToGrab();
            else
                iWantToShoot();
        }).start();
    }

    public void iWantToMove() {
        PerformActionMessage message = new PerformActionMessage("action1");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToGrab() {
        PerformActionMessage message = new PerformActionMessage("action2");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToShoot() {

    }

    @Override
    public void showChoosePowerup(CardRep p1, CardRep p2) {
        CliPrinter.stamp("\n");
        CliPrinter.choosePowerUpMessage(p1, p2);
        new Thread( () -> {
            int choosenPowerUp;
            Console c = System.console();
            choosenPowerUp = Character.getNumericValue((c.readPassword())[0]);
            ArrayList<Integer> arrayList = new ArrayList<>();
            if (choosenPowerUp == 1) {
                arrayList.add(p1.getId());
                arrayList.add(p2.getId());
            }
            else {
                arrayList.add(p2.getId());
                arrayList.add(p1.getId());
            }
            SendInitPowerUpMessage message = new SendInitPowerUpMessage(arrayList);
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
        }).start();
    }
    //TODO show the right player board given the arrChars, an arraylist of objects like "SPROG"
    @Override
    public void showGameBoard(List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons, List<String> arrChars) {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        if (chosenBoard.equals("1"))
            CliPrinter.printMap1(map, ammoReps, posWeapons);
        else if (chosenBoard.equals("2"))
            CliPrinter.printMap2(map, ammoReps, posWeapons);
        else if (chosenBoard.equals("3"))
            CliPrinter.printMap3(map, ammoReps, posWeapons);
        else
            CliPrinter.printMap4(map, ammoReps, posWeapons);
    }

    @Override
    public void updateVotesCharacterChosen(String c) {
        if (!c.equals(myCharEnumString))
            charChosen.add(c);
        showChooseCharacter(chosenBoard);
    }

    @Override
    public void updateTimerCharacter(int count) {
        timeLeftForChar = count;
        showChooseCharacter(chosenBoard);
    }

    @Override
    public void showChooseCharacter(String config) {
        chosenBoard = config;
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
    public void lightWeapons(List<Integer> weapons) {
        //TODO
    }

    @Override
    public void lightPlatforms(List<String> platforms) {
        CliPrinter.printPossiblePlatform(platforms);
        new Thread( () -> {
            String platform;
            Scanner c = new Scanner(System.in);
            platform = c.next();
            MoveCurrPlayerMessage message = new MoveCurrPlayerMessage(platform);
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
        }).start();
    }

    @Override
    public void setRandomChar(String randomChar) {
        myCharEnumString = randomChar;
    }
}
