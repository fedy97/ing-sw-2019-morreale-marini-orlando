package it.polimi.se2019.view.client.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//useful only to preview this page
public class ChooseMapPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/chooseMap.fxml"));
        primaryStage.setTitle("Choose Map");
        primaryStage.setScene(new Scene(root, 586, 514));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

    }

    public static void startLoginPage(){
        launch();
    }
}