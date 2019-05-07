package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

public class LoginController implements Observer {

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

    @Override
    public void update(Observable o, Object arg) {
        /*
        we do not need this method here
         */
    }

    @FXML
    public void login() throws RemoteException {

        if (getUsername().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username");
            alert.showAndWait();
        }
        else
        {
            Client client;
            if (getConnection().equals("RMI")) {
                client=new RMIClient(null,1560,getUsername());
                client.connect(getIp(),1099);
            }
            else {
                client = new SocketClient(null,getUsername());
                client.connect(getIp(),1100);
            }
            showWaitingLobby();
        }
    }

    private void showWaitingLobby() {
        Platform.runLater(() ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/waitingLobby.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(0);
                });
                Stage stageToBeClosed = (Stage)loginButton.getScene().getWindow();
                stageToBeClosed.close();
            } catch (IOException ex) {
                HandyFunctions.LOGGER.log(Level.SEVERE, "error loading waiting lobby");
            }
        });
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
     * @return the text of the button selected
     */
    private String getConnection() {
        ToggleButton selectedButton = (ToggleButton)connectionType.getSelectedToggle();
        return selectedButton.getText();
    }

}
