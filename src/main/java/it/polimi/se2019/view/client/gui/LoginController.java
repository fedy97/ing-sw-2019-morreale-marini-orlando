package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;

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
    public void login() {

        if (userTextField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username");
            alert.showAndWait();
        }
    }
}
