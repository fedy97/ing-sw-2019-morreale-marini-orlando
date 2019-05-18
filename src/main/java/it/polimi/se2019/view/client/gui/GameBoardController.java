package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardController {

    private GUI gui;
    private String config;
    @FXML
    ImageView mapImage;
    @FXML
    Button zerozero;
    @FXML
    Button zeroone;
    @FXML
    Button zerotwo;
    @FXML
    Button zerothree;
    @FXML
    Button onezero;
    @FXML
    Button oneone;
    @FXML
    Button onetwo;
    @FXML
    Button onethree;
    @FXML
    Button twozero;
    @FXML
    Button twoone;
    @FXML
    Button twotwo;
    @FXML
    Button twothree;
    @FXML
    ImageView ammozerozero;
    @FXML
    ImageView ammozeroone;
    @FXML
    ImageView ammozerotwo;
    @FXML
    ImageView ammozerothree;
    @FXML
    ImageView ammoonezero;
    @FXML
    ImageView ammooneone;
    @FXML
    ImageView ammoonetwo;
    @FXML
    ImageView ammoonethree;
    @FXML
    ImageView ammotwozero;
    @FXML
    ImageView ammotwoone;
    @FXML
    ImageView ammotwotwo;
    @FXML
    ImageView ammotwothree;

    private List<AmmoRep> ammoReps;
    private Map<AmmoRep, ImageView> ammoRepImageViewMap;

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    public void setAmmoReps(List<AmmoRep> ammoReps) {
        this.ammoReps = ammoReps;
    }

    //constructor
    public void initialize() {

        Platform.runLater(
                () -> {
                    ammoRepImageViewMap = new HashMap<>();
                    ArrayList<ImageView> imageViews = new ArrayList<>();
                    imageViews.add(ammozerozero);
                    imageViews.add(ammozeroone);
                    imageViews.add(ammozerotwo);
                    imageViews.add(ammozerothree);
                    imageViews.add(ammoonezero);
                    imageViews.add(ammooneone);
                    imageViews.add(ammoonetwo);
                    imageViews.add(ammoonethree);
                    imageViews.add(ammotwozero);
                    imageViews.add(ammotwoone);
                    imageViews.add(ammotwotwo);
                    imageViews.add(ammotwothree);
                    for (int i = 0; i < imageViews.size(); i++) {
                        AmmoRep ammoRep = ammoReps.get(i);
                        ImageView imageView = imageViews.get(i);
                        if (ammoRep != null)
                            //TODO revert this map
                            ammoRepImageViewMap.put(ammoRep, imageView);
                    }
                    mapImage.setImage(new Image("/assets/map/" + config + ".jpg"));
                    for (Map.Entry<AmmoRep, ImageView> entry : ammoRepImageViewMap.entrySet()) {
                        entry.getValue().setImage(new Image("/assets/ammos/" + entry.getKey().getType() + ".jpg"));
                    }
                });
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public void zerozeroClick() {
    }

    public void zerooneClick() {
    }

    public void zerotwoClick() {
    }

    public void zerothreeClick() {
    }

    public void onezeroClick() {
    }

    public void oneoneClick() {
    }

    public void onetwoClick() {
    }

    public void onethreeClick() {
    }

    public void twozeroClick() {
    }

    public void twooneClick() {
    }

    public void twotwoClick() {
    }

    public void twothreeClick() {
    }

    public void dozerClick() {
    }

    public void bansheeClick() {
    }

    public void sprogClick() {
    }

    public void distructorClick() {
    }

    public void violetClick() {
    }

}
