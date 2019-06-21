package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.exceptions.NoInputException;
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
import java.io.IOException;
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
    private boolean isAsking;
    private LightGameVersion lightGameVersion;
    private boolean[] actives;
    private int begin;
    private Timer timerTurn;
    public static int counter;
    private int currState;

    public CLI() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        reader = new CliReader(1);
        vote = new int[4];
        vote[0] = 0;
        vote[1] = 0;
        vote[2] = 0;
        vote[3] = 0;
        charChosen = new ArrayList<>();
        map = new int[3][4];
        isAsking = false;
        actives = new boolean[6];
        for (int i=0; i<6; i++)
            actives[i] = false;
        begin = 1;
    }

    @Override
    public void updateAll(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
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
        CliSetUp.cursorLeft(106);
        CliPrinter.standardActionsMessage();
        CliSetUp.savePosition();
        CliSetUp.cursorLeft(99);
        CliPrinter.boxMessage(null);
        CliSetUp.restorePosition();
        CliSetUp.cursorUp(34);
        CliSetUp.cursorRight(104);
        CliPrinter.drawPlayersInfoBox(lightGameVersion);
        CliSetUp.cursorDown(20);
        CliSetUp.cursorLeft(106);
        currState = 1;
    }

    private void getActionInput() {
        if (!isAsking && isMyTurn()) {
            new Thread(() -> {
                isAsking = true;
                Console c = System.console();
                int choise;
                choise = Character.getNumericValue((c.readPassword())[0]);
                isAsking = false;
                if (choise == 1 && actives[0])
                    iWantToMove();
                else if (choise == 2 && actives[1])
                    iWantToGrab();
                else if (choise == 3 && actives[2])
                    iWantToShoot();
                else if (choise == 4 && actives[3])
                    iWantToReload();
                else if (choise == 5 && actives[4])
                    iWantToUsePowerUp();
                else
                    endTurn();
                currState = 0;
            }).start();
        }
    }

    public boolean isMyTurn () {
        for (int i=0; i<6; i++) {
            if (actives[i] == true)
                return true;
        }
        return false;
    }

    public void iWantToMove() {
        PerformActionMessage message = new PerformActionMessage("action1");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToGrab() {
        isAsking = true;
        PerformActionMessage message = new PerformActionMessage("action2");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToShoot() {
        PerformActionMessage message = new PerformActionMessage("action3");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToReload() {
        PerformActionMessage message = new PerformActionMessage("action4");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToUsePowerUp() {

    }

    public void endTurn() {
        EndTurnMessage message = new EndTurnMessage(null);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    @Override
    public void showChoosePowerup(CardRep p1, CardRep p2) {
        currState = 1;
        CliPrinter.stamp("\n");
        CliPrinter.choosePowerUpMessage(p1, p2);
        CliSetUp.cursorRight(9);
        CliSetUp.cursorUp(1);
        CliPrinter.boxMessage(null);
        CliSetUp.cursorUp(10);
        CliSetUp.cursorLeft(10);
        new Thread(() -> {
            int choosenPowerUp;
            Console c = System.console();
            choosenPowerUp = Character.getNumericValue((c.readPassword())[0]);
            ArrayList<Integer> arrayList = new ArrayList<>();
            if (choosenPowerUp == 1) {
                arrayList.add(p1.getId());
                arrayList.add(p2.getId());
            } else {
                arrayList.add(p2.getId());
                arrayList.add(p1.getId());
            }
            SendInitPowerUpMessage message = new SendInitPowerUpMessage(arrayList);
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
            begin = 0;
            currState = 0;
        }).start();
    }

    //TODO show the right player board given the arrChars, an arraylist of objects like "SPROG"
    @Override
    public void showGameBoard(List<AmmoRep> ammoReps, Map<String, List<CardRep>> posWeapons, List<String> arrChars) {
        CliPrinter.reset();
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
        CliPrinter.reset();
        chosenBoard = config;
        if (!firstTime) {
            new Thread(() -> {
                boolean okChar = false;
                //Console c = System.console();
                CliReader reader2 = new CliReader(1);
                //while (!okChar) {
                    int temp;
                    //temp = Character.getNumericValue((c.readPassword())[0]);
                    try {
                        temp = reader2.getTimedInt();
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
                        SendCharacterChosenMessage message = new SendCharacterChosenMessage(myCharEnumString);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    }
                    catch (NoInputException|IOException e) {

                    }
                //}

            }).start();
            firstTime = true;
        }
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.possibleCharMessage(timeLeftForChar, charChosen, myChar);
    }

    @Override
    public void updateTimerMap(int count) {
        timeLeftForMaps = count;
        showChooseMap();
    }

    @Override
    public void updateVotesMapChosen(Map<Integer, Integer> map) {
        for (int i = 1; i <= 4; i++) {
            vote[i - 1] = map.get(i);
        }
        showChooseMap();
    }

    @Override
    public void showChooseMap() {

        CliPrinter.reset();
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.possibleMapsMessage(timeLeftForMaps, vote);
        if (firstTime) {
            new Thread(() -> {
                //Console c = System.console();
                //mapChosen = Character.getNumericValue((c.readPassword())[0]);
                CliReader reader1 = new CliReader(1);
                try {
                    mapChosen = reader1.getTimedInt();
                    if (mapChosen == 1 || mapChosen == 2 || mapChosen == 3 || mapChosen == 4) {
                        SendMapChosenMessage message = new SendMapChosenMessage(mapChosen);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    }
                    else {
                        mapChosen = 1;
                        SendMapChosenMessage message = new SendMapChosenMessage(mapChosen);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    }
                }
                catch (NoInputException| IOException e) {

                }
            }).start();
            firstTime = false;
        }
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
        if (connectionChosen != 1 && connectionChosen != 2)
            connectionChosen = 1;
    }

    @Override
    public void startConnection() {
        final int SOCKET = 1;
        if (connectionChosen == SOCKET) {
            SocketClient client = new SocketClient(this, userName);
            client.connect(ip, 1100);
            this.addObserver(client);
        } else {

            RMIClient client = new RMIClient(this, HandyFunctions.randomIntegerBetWeen(1500, 2000), userName);
            client.connect(ip, 1099);
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
        Map<Integer, Integer> hashes;
        currState = 0;
        hashes = CliPrinter.printPossibleWeapon(lightGameVersion, weapons);
        new Thread( () -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            ChosenWeaponMessage message = new ChosenWeaponMessage(Integer.toString(hashes.get(choise).intValue()));
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
            isAsking = false;
            currState = 1;
        }).start();
    }

    @Override
    public void lightPlatforms(List<String> platforms) {
        CliPrinter.printPossiblePlatform(platforms);
        new Thread(() -> {
            String platform;
            Console c = System.console();
            platform = c.readLine();
            MoveCurrPlayerMessage message = new MoveCurrPlayerMessage(platform);
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
            if (!platform.equals("0,2") && !platform.equals("1,0") && !platform.equals("2,3")) {
                isAsking = false;
            }
        }).start();
    }

    @Override
    public void setRandomChar(String randomChar) {
        myCharEnumString = randomChar;
    }

    @Override
    public void setValidActions(boolean[] actives) {
        this.actives = actives;
        isAsking = false;
        if (isMyTurn() && begin == 0) {
            getActionInput();
        }
    }

    @Override
    public void showMessage(String msg) {
        CliSetUp.savePosition();
        CliSetUp.cursorDown(15);
        CliSetUp.cursorRight(5);
        HandyFunctions.printConsole(msg);
        CliSetUp.restorePosition();
    }

    @Override
    public void switchWeapon() {
        CliPrinter.discartWeaponMessage(lightGameVersion, myCharEnumString);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            CliSetUp.restorePosition();
            Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
            List<CardRep> myWeapons = playerWeapons.get(myCharEnumString);
            int idCard;
            if(choise == 0 || choise == 1 || choise == 2) {
                idCard = myWeapons.get(choise).getId();
            }
            else {
                idCard = myWeapons.get(2).getId();
            }
            DiscardWeaponMessage message = new DiscardWeaponMessage(Integer.toString(idCard));
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
        }).start();
    }

    @Override
    public void showTargets(List<String> targets) {
        //Per decidere chi colpire
    }

    @Override
    public void enlightenEffects(List<Integer> effects) {
        //lista effetti arma selezionata
    }

    @Override
    public void showBinaryOption(String message) {

    }

    @Override
    public void showReconnectedGameBoard(int configMap, LightGameVersion lightGameVersion, List<String> charsInGame, String myChar) {

    }

    @Override
    public void receivePingFromServer() {
        ResponseToPingMessage message = new ResponseToPingMessage(myCharEnumString);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    @Override
    public void startTimerTurn(int count,String currPlayer) {
        try {
            timerTurn.cancel();
            timerTurn.purge();
        }
        catch (Exception e) {

        }
        timerTurn = new Timer();
        counter = count;
        timerTurn.schedule(new TimerTask() {
            @Override
            public void run() {
                if (counter >= 0) {
                    CliSetUp.savePosition();
                    if (currState == 0)
                        CliSetUp.cursorUp(5);
                    HandyFunctions.printConsole("\rTimer: " + counter);
                    CliSetUp.restorePosition();
                    counter--;
                }
            }
         }, 0,1*1000);
    }

    @Override
    public void showReloadableWeapons(List<String> weapons) {
        CliPrinter.reloadWeaponMessage(lightGameVersion,myCharEnumString,weapons);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            CliSetUp.restorePosition();
            Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
            List<CardRep> myWeapons = playerWeapons.get(myCharEnumString);
            int idCard;
            if(choise == 0 || choise == 1 || choise == 2) {
                idCard = myWeapons.get(choise).getId();
            }
            else {
                idCard = myWeapons.get(2).getId();
            }

            ReloadWeaponsMessage message = new ReloadWeaponsMessage(idCard);
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
        }).start();
    }

    @Override
    public void showUsableWeapons(List<String> weapons) {
        CliPrinter.chooseWeaponMessage(lightGameVersion,myCharEnumString,weapons);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            CliSetUp.restorePosition();
            Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
            List<CardRep> myWeapons = playerWeapons.get(myCharEnumString);
            int idCard;
            if(choise == 0 || choise == 1 || choise == 2) {
                idCard = myWeapons.get(choise).getId();
            }
            else {
                idCard = myWeapons.get(2).getId();
            }

            ActivateCardMessage message = new ActivateCardMessage(idCard);
            message.setSender(userName);
            viewSetChanged();
            notifyObservers(message);
        }).start();
    }

    @Override
    public void showUsablePowerups(List<String> powerups) {

    }
}
