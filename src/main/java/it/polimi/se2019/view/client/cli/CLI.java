package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.exceptions.NoInputException;
import it.polimi.se2019.model.rep.AmmoRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.to_server.*;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.TimerTurn;
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

    //private Timer timerTurn;
    private CliState currentState;
    public static int counter;
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
    private int currState;
    private String lastMsg;
    private boolean powerUpChosen;
    private CardRep p1, p2;
    private boolean firstCallChoosePU;
    private TimerTurn timerTurn;
    private boolean isChoosingPowerUp;
    private boolean isReloadedWeapons;
    private List<CardRep> powerUps;
    private int actionType;
    private int chosingmap;
    private int inputSeconds;

    public CLI() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        reader = new CliReader(10);
        vote = new int[4];
        vote[0] = 0;
        vote[1] = 0;
        vote[2] = 0;
        vote[3] = 0;
        charChosen = new ArrayList<>();
        map = new int[3][4];
        isAsking = false;
        actives = new boolean[6];
        for (int i = 0; i < 6; i++)
            actives[i] = false;
        begin = 1;
        lastMsg = "";
        powerUpChosen = false;
        p1 = null;
        p2 = null;
        firstCallChoosePU = true;
        isChoosingPowerUp = false;
        isReloadedWeapons = false;
        actionType = 0;
        chosingmap = 0;
        currentState = CliState.POWERUPCHOOSING;
        inputSeconds = 1;
    }

    @Override
    public void updateAll(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
        actionType = lightGameVersion.getPlayerBoardRep().get(myCharEnumString).getActionType();
        currentState = CliState.ACTIONSELECTION;

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
        if (powerUpChosen || p1 == null || p2 == null) {
            if (actionType == 0)
                CliPrinter.standardActionsMessage();
            else if(actionType == 1)
                CliPrinter.actionMessage1();
            else if(actionType == 2)
                CliPrinter.actionMessage2();
            else if(actionType == 3)
                CliPrinter.actionMessage3();
            else
                CliPrinter.actionMessage4();
            CliSetUp.savePosition();
            CliSetUp.cursorLeft(99);
            CliPrinter.boxMessage(null);
            CliSetUp.restorePosition();
        } else {
            CliSetUp.cursorUp(1);
            showChoosePowerup(powerUps);
            CliSetUp.cursorRight(9);
            CliSetUp.cursorDown(10);
        }

        CliSetUp.cursorUp(34);
        CliSetUp.cursorRight(104);
        CliPrinter.drawPlayersInfoBox(lightGameVersion, myCharEnumString);
        CliSetUp.cursorRight(59);
        CliSetUp.cursorUp(3);
        CliPrinter.drawGameInfoBox(lightGameVersion);
        CliSetUp.cursorDown(20);
        CliSetUp.cursorLeft(106);
        //showMessage(lastMsg);
        CliSetUp.cursorLeft(63);
        CliSetUp.cursorDown(9);
        CliPrinter.boxMessage(lastMsg);
        //CliSetUp.cursorUp(2);
        //CliSetUp.cursorLeft(50);
        CliSetUp.cursorUp(11);
        CliSetUp.cursorRight(10);
        currState = 2;
    }

    private void getActionInput() {
        if(actionType == 0 || actionType == 1 || actionType == 2) {
            if (!isAsking && isMyTurn() && currentState == CliState.ACTIONSELECTION) {
                new Thread(() -> {
                    isAsking = true;
                    Scanner flush = new Scanner(System.in);
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
                    else if (choise == 6 && actives[5])
                        endTurn();
                    else if (choise == 7)
                        iWantToConvertPowerUp();
                    else {
                        updateAll(lightGameVersion);
                        getActionInput();
                    }
                    currState = 0;
                }).start();
            }
        }
        else {
            if (actionType == 3) {
                if (!isAsking && isMyTurn() && powerUpChosen && !isChoosingPowerUp && !isReloadedWeapons && currentState == CliState.ACTIONSELECTION) {
                    new Thread(() -> {
                        isAsking = true;
                        Console c = System.console();
                        int choise;
                        choise = Character.getNumericValue((c.readPassword())[0]);
                        isAsking = false;
                        if (choise == 1 && actives[0])
                            iWantToShoot();
                        else if (choise == 2 && actives[1])
                            iWantToMove();
                        else if (choise == 3 && actives[2])
                            iWantToGrab();
                        else if (choise == 4 && actives[3])
                            iWantToReload();
                        else if (choise == 5 && actives[4])
                            iWantToUsePowerUp();
                        else if (choise == 6 && actives[5])
                            endTurn();
                        else if (choise == 7)
                            iWantToConvertPowerUp();
                        else {
                            updateAll(lightGameVersion);
                            getActionInput();
                        }
                        currState = 0;
                    }).start();
                }
            }
            else {
                if (!isAsking && isMyTurn() && powerUpChosen && !isChoosingPowerUp && !isReloadedWeapons && currentState == CliState.ACTIONSELECTION) {
                    new Thread(() -> {
                        isAsking = true;
                        Console c = System.console();
                        int choise;
                        choise = Character.getNumericValue((c.readPassword())[0]);
                        isAsking = false;
                        if (choise == 1 && actives[0])
                            iWantToShoot();
                        else if (choise == 2 && actives[1])
                            iWantToGrab();
                        else if (choise == 4 && actives[2])
                            iWantToReload();
                        else if (choise == 5 && actives[3])
                            iWantToUsePowerUp();
                        else if (choise == 6 && actives[4])
                            endTurn();
                        else if (choise == 7)
                            iWantToConvertPowerUp();
                        else {
                            updateAll(lightGameVersion);
                            getActionInput();
                        }
                        currState = 0;
                    }).start();
                }
            }
        }
    }

    public boolean isMyTurn() {
        for (int i = 0; i < 6; i++) {
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
        isAsking = true;
        PerformActionMessage message = new PerformActionMessage("action3");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToReload() {
        isAsking = true;
        isReloadedWeapons = true;
        PerformActionMessage message = new PerformActionMessage("action4");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void iWantToUsePowerUp() {
        isAsking = true;
        isChoosingPowerUp = true;
        PerformActionMessage message = new PerformActionMessage("action5");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    public void endTurn() {
        EndTurnMessage message = new EndTurnMessage(null);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
        updateAll(lightGameVersion);
    }

    public void iWantToConvertPowerUp() {
        currentState = CliState.POWERUPCONVERTING;
        CliSetUp.savePosition();
        isAsking = true;
        CliPrinter.stamp("\n");
        CliPrinter.convertPowerUpMessage(lightGameVersion,myCharEnumString);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            Map<String, List<CardRep>> playerPowerUps = lightGameVersion.getPlayerPowerups();
            List<CardRep> myPowerUps = playerPowerUps.get(myCharEnumString);
            if (choise < myPowerUps.size()) {
                int hash = myPowerUps.get(0).getId();
                BuyWithPowerupsMessage message = new BuyWithPowerupsMessage(Integer.toString(hash));
                notifyController(message);
                isAsking = false;
                CliSetUp.restorePosition();
                //updateAll(lightGameVersion);
                //CliPrinter.stamp("\n");
            }
            else {
                iWantToConvertPowerUp();
            }
        }).start();
    }

    @Override
    public void showChoosePowerup(List<CardRep> cards) {
        currentState = CliState.POWERUPCHOOSING;
        CliSetUp.savePosition();
        powerUps = cards;
        if (cards.size() == 2) {
            this.p1 = cards.get(0);
            this.p2 = cards.get(1);
            currState = 1;
            CliPrinter.stamp("\n");
            CliPrinter.choosePowerUpMessage(p1, p2);
            CliSetUp.cursorRight(9);
            CliPrinter.boxMessage(null);
            CliSetUp.cursorUp(10);
            CliSetUp.cursorLeft(10);
            if (firstCallChoosePU) {
                firstCallChoosePU = false;
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
                    CliSetUp.restorePosition();
                    powerUpChosen = true;
                    begin = 0;
                    currState = 0;
                    currentState = CliState.ACTIONSELECTION;
                    getActionInput();
                }).start();
            }
        }
        else {
            CliPrinter.choosePowerUpMessage2(cards);
            new Thread(() -> {
                int choosenPowerUp;
                Scanner s = new Scanner(System.in);
                choosenPowerUp = s.nextInt();
                ArrayList<Integer> arrayList = new ArrayList<>();
                if (choosenPowerUp < cards.size() && choosenPowerUp >= 0) {
                    if (choosenPowerUp == 0) {
                        arrayList.add(cards.get(0).getId());
                        arrayList.add(cards.get(1).getId());
                        arrayList.add(cards.get(2).getId());
                    }
                    else if(choosenPowerUp == 1) {
                        arrayList.add(cards.get(1).getId());
                        arrayList.add(cards.get(0).getId());
                        arrayList.add(cards.get(2).getId());
                    }
                    else {
                        arrayList.add(cards.get(2).getId());
                        arrayList.add(cards.get(0).getId());
                        arrayList.add(cards.get(1).getId());
                    }
                }
                SendInitPowerUpMessage message = new SendInitPowerUpMessage(arrayList);
                message.setSender(userName);
                viewSetChanged();
                notifyObservers(message);
                CliSetUp.restorePosition();
                powerUpChosen = true;
                begin = 0;
                currState = 0;
                currentState = CliState.ACTIONSELECTION;
            }).start();
        }
    }

    //TODO show the right player board given the arrChars, an arraylist of objects like "SPROG"
    @Override
    public void showGameBoard(List<AmmoRep> ammoReps, Map<String, List<CardRep>> posWeapons, List<String> arrChars) {
        currentState = CliState.ACTIONSELECTION;
        currState = 3;
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
                CliReader reader2 = new CliReader(inputSeconds);
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
                } catch (NoInputException | IOException e) {

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
        chosingmap = 1;
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
                CliReader reader1 = new CliReader(inputSeconds);
                try {
                    mapChosen = reader1.getTimedInt();
                    if (mapChosen == 1 || mapChosen == 2 || mapChosen == 3 || mapChosen == 4) {
                        SendMapChosenMessage message = new SendMapChosenMessage(mapChosen);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    } else {
                        mapChosen = 1;
                        SendMapChosenMessage message = new SendMapChosenMessage(mapChosen);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    }
                } catch (NoInputException | IOException e) {

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
        if(chosingmap == 1)
            return;
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
        CliPrinter.stamp("\n");
        currentState = CliState.WEAPONSCHOOSING;
        CliSetUp.savePosition();
        if(weapons.size() == 0)
            return;
        Map<Integer, Integer> hashes;
        currState = 0;
        hashes = CliPrinter.printPossibleWeapon(lightGameVersion, weapons);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            if (choise < weapons.size() && choise >= 0) {
                ChosenWeaponMessage message = new ChosenWeaponMessage(Integer.toString(hashes.get(choise).intValue()));
                notifyController(message);
                isAsking = false;
                currState = 1;
                CliSetUp.restorePosition();
                //updateAll(lightGameVersion);
                //CliPrinter.stamp("\n");
            }
            else {
                updateAll(lightGameVersion);
                //CliSetUp.cursorLeft(22);
                lightWeapons(weapons);
            }
        }).start();
    }

    @Override
    public void lightPlatforms(List<String> platforms) {
        CliPrinter.stamp("\n");
        currentState = CliState.PLATFORMSCHOOSING;
        CliSetUp.savePosition();
        if(platforms.size() == 0)
            return;
        CliPrinter.printPossiblePlatform(platforms);
        new Thread(() -> {
            String platform;
            Console c = System.console();
            platform = c.readLine();
            if (platforms.contains(platform)) {
                MoveCurrPlayerMessage message = new MoveCurrPlayerMessage(platform);
                notifyController(message);
                CliSetUp.restorePosition();
                //updateAll(lightGameVersion);
                //CliPrinter.stamp("\n");
                if (!platform.equals("0,2") && !platform.equals("1,0") && !platform.equals("2,3")) {
                    isAsking = false;
                    //updateAll(lightGameVersion);
                    //CliPrinter.stamp("\n");
                }
            } else {
                updateAll(lightGameVersion);
                CliSetUp.cursorDown(1);
                CliSetUp.cursorLeft(22);
                lightPlatforms(platforms);
            }
        }).start();
    }

    @Override
    public void setRandomChar(String randomChar) {
        myCharEnumString = randomChar;
    }

    @Override
    public void setValidActions(boolean[] actives) {
        System.out.print("CHIAMATO");
        this.actives = actives;
        isAsking = false;
        begin = 0;
        if (isMyTurn() && begin == 0 && currentState == CliState.ACTIONSELECTION) {
            getActionInput();
        }
    }

    @Override
    public void showMessage(String msg) {
        lastMsg = msg;
    }

    @Override
    public void switchWeapon() {
        currentState = CliState.WEAPONSWITCHING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        CliPrinter.discartWeaponMessage(lightGameVersion, myCharEnumString);
        currState = 0;
        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            int choice;
            int idCard;
            choice = s.nextInt();
            CliSetUp.restorePosition();
            Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
            List<CardRep> myWeapons = playerWeapons.get(myCharEnumString);

            if (choice == 0 || choice == 1 || choice == 2) {
                idCard = myWeapons.get(choice).getId();
            } else {
                idCard = myWeapons.get(2).getId();
            }
            DiscardWeaponMessage message = new DiscardWeaponMessage(Integer.toString(idCard));
            notifyController(message);
            CliSetUp.restorePosition();
            currState = 1;
            //updateAll(lightGameVersion);
            //CliPrinter.stamp("\n");
        }).start();
    }

    @Override
    public void showTargets(List<String> targets) {
        currentState = CliState.TARGETSHOWING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(targets.size() == 0)
            return;
        CliPrinter.showTargetMessage(lightGameVersion, targets);
        List<String> toSend = new ArrayList<>();
        new Thread(() -> {
            boolean isOk = true;
            String choise;
            Scanner s = new Scanner(System.in);
            choise = s.next();
            for (int i = 0; i < choise.length(); i++) {
                if (choise.charAt(i) != ',' && Character.getNumericValue(choise.charAt(i)) < targets.size()) {
                    toSend.add(targets.get(Character.getNumericValue(choise.charAt(i))));
                }
            }
            if (toSend.size() == 0)
                isOk = false;
            else {
                for (String str : toSend) {
                    if (!targets.contains(str))
                        isOk = false;

                }
            }
            if (isOk) {
                SendTargetsMessage message = new SendTargetsMessage(toSend);
                notifyController(message);
                currState = 1;
                isAsking = false;
                CliSetUp.restorePosition();
                //updateAll(lightGameVersion);
                //CliPrinter.stamp("\n");
            } else {
                showTargets(targets);
            }
        }).start();

    }

    @Override
    public void enlightenEffects(List<Integer> effects) {
        currentState = CliState.EFFECTSSHOWING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(effects.size() == 0)
            return;
        CliPrinter.enlightenEffectsMessage(effects);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            if (!effects.contains(choise))
                choise = effects.get(0).intValue();
            ChosenEffectMessage message = new ChosenEffectMessage(choise);
            notifyController(message);
            CliSetUp.restorePosition();
            //updateAll(lightGameVersion);
            //CliPrinter.stamp("\n");
            currState = 1;
            //CliSetUp.cursorUp(5);
        }).start();
    }

    @Override
    public void showBinaryOption(String message) {
        currentState = CliState.OPTIONSHOWING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        CliPrinter.binaryOptionMessage(message);
        new Thread(() -> {
            char choise;
            Scanner s = new Scanner(System.in);
            choise = s.next().charAt(0);
            boolean toSend;
            if (choise == 'y')
                toSend = true;
            else
                toSend = false;
            ResponseToBinaryOption msg = new ResponseToBinaryOption(toSend);
            notifyController(msg);
            if (message.equals("Do you want to move the target 1 square away?")) {
                CliSetUp.cursorUp(5);
            }
            currState = 1;
            CliSetUp.restorePosition();
            //updateAll(lightGameVersion);
            //CliPrinter.stamp("\n");
        }).start();
    }

    @Override
    public void showReconnectedGameBoard(int configMap, LightGameVersion lightGameVersion, List<String> charsInGame, String myChar) {
        chosenBoard = Integer.toString(configMap);
        myCharEnumString = myChar;
        updateAll(lightGameVersion);
    }


    @Override
    public void startTimerTurn(int count, String currPlayer) {
        try {
            if (timerTurn != null) timerTurn.interrupt();
        } catch (Exception ex) {
            CustomLogger.logException(this.getClass().getName(), ex);
        }
        timerTurn = new TimerTurn(count, this, currPlayer);
        timerTurn.start();
    }

    @Override
    public void updateTimerTurn(int seconds, String curr) {
        /*
        CliSetUp.savePosition();
        if (currState == 0)
            CliSetUp.cursorUp(5);
        else if (currState == 1)
            CliSetUp.cursorUp(1);
        else if (currState == 3)
            CliSetUp.cursorDown(1);
        HandyFunctions.printConsole("\rTimer: " + seconds);
        CliSetUp.restorePosition();

         */
    }

    @Override
    public void showReloadableWeapons(List<String> weapons) {
        currentState = CliState.WEAPONSRELOADING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(weapons.size() == 0)
            return;
        currState = 0;
        CliPrinter.reloadWeaponMessage(lightGameVersion, myCharEnumString, weapons);
        new Thread(() -> {
            ReloadWeaponsMessage message = new ReloadWeaponsMessage(Integer.toString(showWeapons(weapons)));
            notifyController(message);
            currState = 1;
            isAsking = false;
            isReloadedWeapons = false;
            CliSetUp.restorePosition();
            //updateAll(lightGameVersion);
            //CliPrinter.stamp("\n");
        }).start();
    }

    @Override
    public void showUsableWeapons(List<String> weapons) {
        currentState = CliState.WEAPONSUSING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(weapons.size() == 0)
            return;
        CliPrinter.chooseWeaponMessage(lightGameVersion, myCharEnumString, weapons);
        currState = 0;
        new Thread(() -> {
            ActivateCardMessage message = new ActivateCardMessage(Integer.toString(showWeapons(weapons)));
            notifyController(message);
            currState = 1;
            CliSetUp.restorePosition();
            //updateAll(lightGameVersion);
            //CliPrinter.stamp("\n");
        }).start();
    }

    @Override
    public void showUsablePowerups(List<String> powerups) {
        currentState = CliState.POWERUPSUSING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(powerups.size() == 0)
            return;
        currState = 0;
        CliPrinter.usePowerUpMessage(lightGameVersion, myCharEnumString, powerups);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            CliSetUp.restorePosition();
            Map<String, List<CardRep>> playePowerUps = lightGameVersion.getPlayerPowerups();
            List<CardRep> myPowerUps = playePowerUps.get(myCharEnumString);
            int idCard;
            if (choise < powerups.size()) {
                idCard = myPowerUps.get(choise).getId();
            } else {
                idCard = myPowerUps.get(0).getId();
            }

            ActivateCardMessage message = new ActivateCardMessage(Integer.toString(idCard));
            notifyController(message);
            currState = 1;
            isAsking = false;
            isChoosingPowerUp = false;
            CliSetUp.restorePosition();
            //updateAll(lightGameVersion);
            //CliPrinter.stamp("\n");
        }).start();
    }


    @Override
    public void resetTimer() {

    }

    private int showWeapons(List<String> weapons) {
        if(weapons.size() == 0)
            return -1;
        int choise;
        Scanner s = new Scanner(System.in);
        choise = s.nextInt();
        CliSetUp.restorePosition();
        Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeapons = playerWeapons.get(myCharEnumString);

        int idCard;
        if (choise < weapons.size()) {
            idCard = myWeapons.get(choise).getId();
        } else {
            idCard = myWeapons.get(0).getId();
        }

        return idCard;
    }

    @Override
    public void showAmmoToDiscard() {
        currentState = CliState.AMMODISCARTING;
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        currState = 0;
        List<String> ammoList = CliPrinter.discartAmmoMessage(lightGameVersion,myCharEnumString);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            choise = s.nextInt();
            if (choise < ammoList.size() && choise >= 0) {
                ChosenAmmoMessage message = new ChosenAmmoMessage(ammoList.get(choise));
                notifyController(message);
            }
            currState = 1;
            isAsking = false;
            isChoosingPowerUp = false;
            CliSetUp.restorePosition();
            //updateAll(lightGameVersion);
            //CliPrinter.stamp("\n");
        }).start();
    }

    @Override
    public void showUsernameAlreadyInUse(String user) {
        CliSetUp.cursorRight(60);
        CliPrinter.stamp("Username not available!",CliColor.TEXTRED);
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {

        }
        setUserName();
        startConnection();
    }

    @Override
    public void showScoreboard(Map<String, Integer> scoreboard) {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.stamp("CLASSIFICA: ", CliColor.TEXTGREEN);
        CliPrinter.stamp("\n");
        for (String s: scoreboard.keySet()) {
            CliPrinter.stamp(s + ": " + scoreboard.get(s) + "\n");
        }
    }
}
