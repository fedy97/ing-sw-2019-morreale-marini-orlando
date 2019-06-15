package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class LoginController {

    @FXML
    private TextField userTextField;
    @FXML
    private TextField ipTextField;
    @FXML
    private ToggleButton rmiButton;
    @FXML
    private ToggleButton socketButton;
    @FXML
    private Button loginButton;
    private String selection;
    private Font normale;
    private Font grande;

    public void initialize() {
        grande = Font.loadFont(
                getClass().getResource("/font.ttf").toExternalForm(),
                25
        );
        normale = Font.loadFont(
                getClass().getResource("/font.ttf").toExternalForm(),
                10
        );
        socketButton.setSelected(true);
        HandyFunctions.forceLightToggleButton(socketButton);
        HandyFunctions.enlightenToggleButton(rmiButton);
        selection = socketButton.getText();
        HandyFunctions.enlightenButton(loginButton);
        loginButton.setFont(grande);
        socketButton.setFont(normale);
        rmiButton.setFont(normale);
    }

    @FXML
    public void login() throws RemoteException {

        if (getUsername().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username");
            alert.showAndWait();
        } else {
            GUI gui = new GUI(getUsername(), (Stage) loginButton.getScene().getWindow(), loginButton.getScene(),normale,grande);
            gui.setUserName();
            if (selection.equals("RMI")) {
                RMIClient client = new RMIClient(gui, HandyFunctions.randomIntegerBetWeen(1500, 3000), getUsername());
                client.connect(getIp(), HandyFunctions.parserSettings.getRmiServerPort());
                gui.addObserver(client);
            } else {
                SocketClient client = new SocketClient(gui, getUsername());
                client.connect(getIp(), HandyFunctions.parserSettings.getSocketServerPort());
                gui.addObserver(client);
            }
            showWaitingLobby(gui);
        }
    }

    private void showWaitingLobby(GUI gui) {
        gui.start();
    }

    private String getUsername() {
        return userTextField.getText();
    }

    private String getIp() {
        if (ipTextField.getText().equals("")) return "127.0.0.1";
        return ipTextField.getText();
    }

    public void socketClick() {
        HandyFunctions.enlightenToggleButton(rmiButton);
        HandyFunctions.forceLightToggleButton(socketButton);
        selection = socketButton.getText();
    }

    public void rmiClick() {
        HandyFunctions.enlightenToggleButton(socketButton);
        HandyFunctions.forceLightToggleButton(rmiButton);
        selection = rmiButton.getText();
    }
}
