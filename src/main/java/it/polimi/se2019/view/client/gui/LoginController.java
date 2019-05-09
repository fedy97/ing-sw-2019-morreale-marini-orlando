package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.client.RemoteView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

import java.util.Random;
import java.util.logging.Level;

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

    private ToggleGroup connectionType = new ToggleGroup();

    public void initialize() {
        connectionType.getToggles().addAll(rmiButton, socketButton);
        socketButton.setSelected(true);
    }

    @FXML
    public void login() throws RemoteException {

        if (getUsername().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username");
            alert.showAndWait();
        } else {
            GUI gui = new GUI((Stage)loginButton.getScene().getWindow());
            if (getConnection().equals("RMI")) {
                RMIClient client = new RMIClient(gui, HandyFunctions.randomIntegerBetWeen(1500,3000), getUsername());
                client.connect(getIp(), 1099);
                gui.addObserver(client);
            } else {
                SocketClient client = new SocketClient(gui, getUsername());
                client.connect(getIp(), 1100);
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

    /**
     * Gets the connection type (socket or RMI)
     *
     * @return the text of the button selected
     */
    private String getConnection() {
        ToggleButton selectedButton = (ToggleButton) connectionType.getSelectedToggle();
        return selectedButton.getText();
    }

}
