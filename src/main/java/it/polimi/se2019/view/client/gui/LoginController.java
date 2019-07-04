package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class LoginController {

    private static final String LOCALHOST = System.getProperty("myapplication.ip");
    private static final String WHITE = "WHITE";
    private static final String HOVERED_BUTTON_STYLE = "-fx-border-color: #f7ff00; -fx-border-width: 4px; -fx-background-color: transparent; -fx-border-radius: 15;";
    private static final String ENLIGHTED_BUTTON_STYLE = "-fx-border-color: #ff0000; -fx-border-width: 2px; -fx-background-color: transparent;-fx-border-radius: 15;";
    private static final String DARKED_BUTTON_STYLE = "-fx-border-width: 0px; -fx-background-color: transparent;";
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

    /**
     * @param button to light
     */
    static void enlightenToggleButton(ToggleButton button) {
        button.setTextFill(javafx.scene.paint.Paint.valueOf(WHITE));
        button.setSelected(false);
        button.setStyle(ENLIGHTED_BUTTON_STYLE);
        button.setOnMouseExited(e -> button.setStyle(ENLIGHTED_BUTTON_STYLE));
        button.setDisable(false);
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));

    }

    /**
     * @param button to light
     */
    static void enlightenButton(Button button) {
        button.setTextFill(javafx.scene.paint.Paint.valueOf(WHITE));
        button.setStyle(ENLIGHTED_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(ENLIGHTED_BUTTON_STYLE));
        button.setDisable(false);
    }

    /**
     * @param button to force the light in choosing targets
     */
    static void forceLightToggleButton(ToggleButton button) {
        button.setTextFill(javafx.scene.paint.Paint.valueOf(WHITE));
        button.setStyle(HOVERED_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setDisable(false);
        button.setOnMouseExited(e -> button.setStyle(HOVERED_BUTTON_STYLE));

    }

    /**
     * @param button to force the light in gameboard controller
     */
    static void forceLightButton(Button button) {
        button.setDisable(false);
        button.setTextFill(javafx.scene.paint.Paint.valueOf(WHITE));
        button.setStyle(HOVERED_BUTTON_STYLE);
        button.setOnMouseExited(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));

    }

    /**
     * @param button to dark
     */
    static void darkenButton(Button button) {
        button.setStyle(DARKED_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(DARKED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(DARKED_BUTTON_STYLE));
        button.setDisable(true);
    }

    /**
     * @param button to dark
     */
    static void darkenToggleButton(ToggleButton button) {
        button.setOnMouseEntered(e -> button.setStyle(DARKED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(DARKED_BUTTON_STYLE));
        button.setStyle(DARKED_BUTTON_STYLE);
        button.setDisable(true);
    }

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
        forceLightToggleButton(socketButton);
        enlightenToggleButton(rmiButton);
        selection = socketButton.getText();
        enlightenButton(loginButton);
        loginButton.setFont(grande);
        socketButton.setFont(normale);
        rmiButton.setFont(normale);
    }

    /**
     * called if login button is pressed
     *
     * @throws RemoteException in rmi
     */
    @FXML
    public void login() throws RemoteException {
        try {
            if (getUsername().equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid username");
                alert.showAndWait();
            } else {
                GUI gui = new GUI(this, getUsername(), (Stage) loginButton.getScene().getWindow(), loginButton.getScene(), normale, grande);
                gui.setUserName();
                if (selection.equals("RMI")) {
                    RMIClient client = new RMIClient(gui, HandyFunctions.randomIntegerBetWeen(1500, 3000), getUsername());
                    client.connect(getIp(), HandyFunctions.parserClientSettings.getRmiServerPort());
                    gui.addObserver(client);
                } else {
                    SocketClient client = new SocketClient(gui, getUsername());
                    client.connect(getIp(), HandyFunctions.parserClientSettings.getSocketServerPort());
                    gui.addObserver(client);
                }
                showWaitingLobby(gui);

            }
        } catch (Exception ex) {
            CustomLogger.logException(this.getClass().getName(), ex);
        }
    }

    /**
     * @param gui to pass
     */
    private void showWaitingLobby(GUI gui) {
        gui.start();
    }

    private String getUsername() {
        return userTextField.getText();
    }

    private String getIp() {
        if (ipTextField.getText().equals("")) return LOCALHOST;
        return ipTextField.getText();
    }

    public void socketClick() {
        enlightenToggleButton(rmiButton);
        forceLightToggleButton(socketButton);
        selection = socketButton.getText();
    }

    public void rmiClick() {
        enlightenToggleButton(socketButton);
        forceLightToggleButton(rmiButton);
        selection = rmiButton.getText();
    }

    /**
     * @param user already existent
     */
    void notifyAlreadyInUse(String user) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(user + " is already in use");
        alert.showAndWait();
    }
}
