package it.polimi.se2019.view.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WaitingLobbyPage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/waitingLobby.fxml"));
        primaryStage.setTitle("Waiting Lobby");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void startWaitingLobbyPage() {
        launch();
    }

}
