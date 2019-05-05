package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.network.client.Client;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

public class LoginController implements Observer {

    @FXML
    private TextField userTextField;
    @FXML
    private TextField ipAddressField;

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
            if (getIp().equals(""))
                ipAddressField.setText("127.0.0.1");
            if (getConnection().equals("RMI")) {
                client=new RMIClient(null,1560,getUsername());
                client.connect(getIp(),1099);
            }
            else {
                client = new SocketClient(null,getUsername());
                client.connect(getIp(),1100);
            }
            //TODO show progress dialog to wait for players
        }
    }

    private String getUsername() {
        return userTextField.getText();
    }

    private String getIp() {
        return ipAddressField.getText();
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
