package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class UseWeaponController {

    @FXML
    private Button weapon1;
    @FXML
    private Button weapon2;
    @FXML
    private Button weapon3;
    @FXML
    private Button w1eff1;
    @FXML
    private Button w1eff2;
    @FXML
    private Button w1eff3;
    @FXML
    private Button w2eff1;
    @FXML
    private Button w2eff2;
    @FXML
    private Button w2eff3;
    @FXML
    private Button w3eff1;
    @FXML
    private Button w3eff2;
    @FXML
    private Button w3eff3;
    @FXML
    private ImageView weapon1image;
    @FXML
    private ImageView weapon2image;
    @FXML
    private ImageView weapon3image;

    private LightGameVersion lightGameVersion;
    private GUI gui;
    private List<ImageView> weaponsImages;
    private List<Button> weaponsButtons;

    public void initialize() {
        weaponsImages = new ArrayList<>();
        weaponsButtons = new ArrayList<>();
        weaponsImages.add(weapon1image);
        weaponsImages.add(weapon2image);
        weaponsImages.add(weapon3image);
        weaponsButtons.add(weapon1);
        weaponsButtons.add(weapon2);
        weaponsButtons.add(weapon3);
    }

    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    protected void updateMyWeapons(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
        List<CardRep> myWeaponsReps = lightGameVersion.getPlayerWeapons().get(gui.getCharInString());
        for (CardRep myWeapon : myWeaponsReps) {
            weaponsImages.get(myWeaponsReps.indexOf(myWeapon)).setImage(new Image(myWeapon.getPath()));
            if (myWeapon.isLoaded()) HandyFunctions.enlightenButton(weaponsButtons.get(myWeaponsReps.indexOf(myWeapon)));
            else HandyFunctions.darkenButton(weaponsButtons.get(myWeaponsReps.indexOf(myWeapon)));
        }
        for (int j = myWeaponsReps.size(); j < 3; j++) {
            weaponsImages.get(j).setImage(new Image("/assets/weapons/back.png"));
            HandyFunctions.darkenButton(weaponsButtons.get(j));
        }
    }

    protected void enlightenRightEffects(List<Integer> effects){
        //TODO
    }

    public void weapon1Click() {
        int hash = lightGameVersion.getPlayerWeapons().get(gui.getCharInString()).get(0).getId();
        gui.useWeapon(Integer.toString(hash));
        deactivateAll();
    }

    public void weapon2Click() {
        int hash = lightGameVersion.getPlayerWeapons().get(gui.getCharInString()).get(1).getId();
        gui.useWeapon(Integer.toString(hash));
        deactivateAll();

    }

    public void weapon3Click() {
        int hash = lightGameVersion.getPlayerWeapons().get(gui.getCharInString()).get(2).getId();
        gui.useWeapon(Integer.toString(hash));
        deactivateAll();
    }

    public void w1eff1Click() {
    }

    public void w1eff2Click() {
    }

    public void w1eff3Click() {
    }

    public void w2eff1Click() {
    }

    public void w2eff2Click() {
    }

    public void w2eff3Click() {
    }

    public void w3eff1Click() {
    }

    public void w3eff2Click() {
    }

    public void w3eff3Click() {
    }

    private void deactivateAll() {
        HandyFunctions.darkenButton(weapon1);
        HandyFunctions.darkenButton(weapon2);
        HandyFunctions.darkenButton(weapon3);
    }

}
