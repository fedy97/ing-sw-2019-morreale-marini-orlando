package it.polimi.se2019.view.client.gui;

import it.polimi.se2019.model.AmmoRep;
import it.polimi.se2019.model.CardRep;
import it.polimi.se2019.model.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardController {

    private GUI gui;
    private String config;
    @FXML
    private ImageView mapImage;
    @FXML
    private Button sprogbutton;
    @FXML
    private Button bansheebutton;
    @FXML
    private Button distructorbutton;
    @FXML
    private Button dozerbutton;
    @FXML
    private Button violetbutton;
    @FXML
    private Button zerozero;
    @FXML
    private Button zeroone;
    @FXML
    private Button zerotwo;
    @FXML
    private Button zerothree;
    @FXML
    private Button onezero;
    @FXML
    private Button oneone;
    @FXML
    private Button onetwo;
    @FXML
    private Button onethree;
    @FXML
    private Button twozero;
    @FXML
    private Button twoone;
    @FXML
    private Button twotwo;
    @FXML
    private Button twothree;
    @FXML
    private ImageView ammozerozero;
    @FXML
    private ImageView ammozeroone;
    @FXML
    private ImageView ammozerotwo;
    @FXML
    private ImageView ammozerothree;
    @FXML
    private ImageView ammoonezero;
    @FXML
    private ImageView ammooneone;
    @FXML
    private ImageView ammoonetwo;
    @FXML
    private ImageView ammoonethree;
    @FXML
    private ImageView ammotwozero;
    @FXML
    private ImageView ammotwoone;
    @FXML
    private ImageView ammotwotwo;
    @FXML
    private ImageView ammotwothree;
    @FXML
    private ImageView bansheezerozero;
    @FXML
    private ImageView bansheezeroone;
    @FXML
    private ImageView bansheezerotwo;
    @FXML
    private ImageView bansheezerothree;
    @FXML
    private ImageView bansheeonezero;
    @FXML
    private ImageView bansheeonetwo;
    @FXML
    private ImageView bansheeonethree;
    @FXML
    private ImageView bansheetwozero;
    @FXML
    private ImageView bansheetwoone;
    @FXML
    private ImageView bansheetwotwo;
    @FXML
    private ImageView bansheetwothree;
    @FXML
    private ImageView bansheeoneone;
    @FXML
    private ImageView sprogzerozero;
    @FXML
    private ImageView sprogzeroone;
    @FXML
    private ImageView sprogzerotwo;
    @FXML
    private ImageView sprogzerothree;
    @FXML
    private ImageView sprogonezero;
    @FXML
    private ImageView sprogoneone;
    @FXML
    private ImageView sprogonetwo;
    @FXML
    private ImageView sprogonethree;
    @FXML
    private ImageView sprogtwozero;
    @FXML
    private ImageView sprogtwoone;
    @FXML
    private ImageView sprogtwotwo;
    @FXML
    private ImageView sprogtwothree;
    @FXML
    private ImageView distructorzerozero;
    @FXML
    private ImageView distructorzeroone;
    @FXML
    private ImageView distructorzerotwo;
    @FXML
    private ImageView distructorzerothree;
    @FXML
    private ImageView distructoronezero;
    @FXML
    private ImageView distructoroneone;
    @FXML
    private ImageView distructoronetwo;
    @FXML
    private ImageView distructoronethree;
    @FXML
    private ImageView distructortwozero;
    @FXML
    private ImageView distructortwoone;
    @FXML
    private ImageView distructortwotwo;
    @FXML
    private ImageView distructortwothree;
    @FXML
    private ImageView violetzerozero;
    @FXML
    private ImageView violetzeroone;
    @FXML
    private ImageView violetzerotwo;
    @FXML
    private ImageView violetzerothree;
    @FXML
    private ImageView violetonezero;
    @FXML
    private ImageView violetoneone;
    @FXML
    private ImageView violetonetwo;
    @FXML
    private ImageView violetonethree;
    @FXML
    private ImageView violettwozero;
    @FXML
    private ImageView violettwoone;
    @FXML
    private ImageView violettwotwo;
    @FXML
    private ImageView violettwothree;
    @FXML
    private ImageView dozerzerozero;
    @FXML
    private ImageView dozerzeroone;
    @FXML
    private ImageView dozerzerotwo;
    @FXML
    private ImageView dozerzerothree;
    @FXML
    private ImageView dozeronezero;
    @FXML
    private ImageView dozeroneone;
    @FXML
    private ImageView dozeronetwo;
    @FXML
    private ImageView dozeronethree;
    @FXML
    private ImageView dozertwozero;
    @FXML
    private ImageView dozertwoone;
    @FXML
    private ImageView dozertwotwo;
    @FXML
    private ImageView dozertwothree;
    @FXML
    private ImageView weapon10_1;
    @FXML
    private ImageView weapon10_2;
    @FXML
    private ImageView weapon10_3;
    @FXML
    private ImageView weapon23_1;
    @FXML
    private ImageView weapon23_2;
    @FXML
    private ImageView weapon23_3;
    @FXML
    private ImageView weapon02_1;
    @FXML
    private ImageView weapon02_2;
    @FXML
    private ImageView weapon02_3;
    @FXML
    private Button info10_1;
    @FXML
    private Button info10_2;
    @FXML
    private Button info10_3;
    @FXML
    private Button info23_1;
    @FXML
    private Button info23_2;
    @FXML
    private Button info23_3;
    @FXML
    private Button info02_1;
    @FXML
    private Button info02_2;
    @FXML
    private Button info02_3;
    @FXML
    private Button w02_3;
    @FXML
    private Button w02_2;
    @FXML
    private Button w02_1;
    @FXML
    private Button w10_1;
    @FXML
    private Button w10_2;
    @FXML
    private Button w10_3;
    @FXML
    private Button w23_1;
    @FXML
    private Button w23_2;
    @FXML
    private Button w23_3;
    @FXML
    private Button movebutton;

    private List<AmmoRep> ammoReps;
    private Map<String, ImageView> posAmmo;
    private Map<String, Button> posPlatform;
    private Map<String, List<CardRep>> posWeaponsReps;
    private Map<ImageView, AmmoRep> ammoRepImageViewMap;
    private Map<String, ArrayList<ImageView>> playerImages;
    private Map<String, ArrayList<ImageView>> posImages;
    private Map<String, ArrayList<ImageView>> posWeaponsImages;
    private boolean firstSetup = true;
    private LightGameVersion lightGameVersion;


    protected void passGUI(GUI gui) {
        this.gui = gui;
    }

    protected void setAmmoReps(List<AmmoRep> ammoReps) {
        this.ammoReps = ammoReps;
    }

    //constructor
    public void initialize() {
        Platform.runLater(
                () -> {
                    initButtons();
                    darkenAllPlatforms();
                    HandyFunctions.enlightenButton(w02_3);
                    HandyFunctions.enlightenButton(zerozero);
                    mapImage.setImage(new Image("/assets/map/" + config + ".jpg"));
                    //TODO change this, I need a map platform,ammo, I will replace this  with updateAmmocards()
                    for (Map.Entry<ImageView, AmmoRep> entry : ammoRepImageViewMap.entrySet()) {
                        if (entry.getValue() != null)
                            entry.getKey().setImage(new Image("/assets/ammos/" + entry.getValue().getType() + ".jpg"));
                    }
                    updateWeapons(null);
                });
    }

    private void initButtons() {
        posWeaponsImages = new HashMap<>();
        ArrayList<ImageView> weapons10 = new ArrayList<>();
        ArrayList<ImageView> weapons23 = new ArrayList<>();
        ArrayList<ImageView> weapons02 = new ArrayList<>();
        weapons10.add(weapon10_1);
        weapons10.add(weapon10_2);
        weapons10.add(weapon10_3);
        weapons23.add(weapon23_1);
        weapons23.add(weapon23_2);
        weapons23.add(weapon23_3);
        weapons02.add(weapon02_1);
        weapons02.add(weapon02_2);
        weapons02.add(weapon02_3);
        posWeaponsImages.put("1,0", weapons10);
        posWeaponsImages.put("2,3", weapons23);
        posWeaponsImages.put("0,2", weapons02);

        posPlatform = new HashMap<>();
        posPlatform.put("0,0", zerozero);
        posPlatform.put("0,1", zeroone);
        posPlatform.put("0,2", zerotwo);
        posPlatform.put("0,3", zerothree);
        posPlatform.put("1,0", onezero);
        posPlatform.put("1,1", oneone);
        posPlatform.put("1,2", onetwo);
        posPlatform.put("1,3", onethree);
        posPlatform.put("2,0", twozero);
        posPlatform.put("2,1", twoone);
        posPlatform.put("2,2", twotwo);
        posPlatform.put("2,3", twothree);

        posAmmo = new HashMap<>();
        posAmmo.put("0,0", ammozerozero);
        posAmmo.put("0,1", ammozeroone);
        posAmmo.put("0,2", ammozerotwo);
        posAmmo.put("0,3", ammozerothree);
        posAmmo.put("1,0", ammoonezero);
        posAmmo.put("1,1", ammooneone);
        posAmmo.put("1,2", ammoonetwo);
        posAmmo.put("1,3", ammoonethree);
        posAmmo.put("2,0", ammotwozero);
        posAmmo.put("2,1", ammotwoone);
        posAmmo.put("2,2", ammotwotwo);
        posAmmo.put("2,3", ammotwothree);

        ArrayList<ImageView> imagesDozer = new ArrayList<>();
        ArrayList<ImageView> imagesSprog = new ArrayList<>();
        ArrayList<ImageView> imagesDistructor = new ArrayList<>();
        ArrayList<ImageView> imagesViolet = new ArrayList<>();
        ArrayList<ImageView> imagesBanshee = new ArrayList<>();
        ArrayList<ImageView> imagesPos00 = new ArrayList<>();
        ArrayList<ImageView> imagesPos01 = new ArrayList<>();
        ArrayList<ImageView> imagesPos02 = new ArrayList<>();
        ArrayList<ImageView> imagesPos03 = new ArrayList<>();
        ArrayList<ImageView> imagesPos10 = new ArrayList<>();
        ArrayList<ImageView> imagesPos11 = new ArrayList<>();
        ArrayList<ImageView> imagesPos12 = new ArrayList<>();
        ArrayList<ImageView> imagesPos13 = new ArrayList<>();
        ArrayList<ImageView> imagesPos20 = new ArrayList<>();
        ArrayList<ImageView> imagesPos21 = new ArrayList<>();
        ArrayList<ImageView> imagesPos22 = new ArrayList<>();
        ArrayList<ImageView> imagesPos23 = new ArrayList<>();

        ammoRepImageViewMap = new HashMap<>();
        playerImages = new HashMap<>();
        posImages = new HashMap<>();

        imagesDozer.add(dozerzerozero);
        imagesDozer.add(dozerzeroone);
        imagesDozer.add(dozerzerotwo);
        imagesDozer.add(dozerzerothree);
        imagesDozer.add(dozeronezero);
        imagesDozer.add(dozeroneone);
        imagesDozer.add(dozeronetwo);
        imagesDozer.add(dozeronethree);
        imagesDozer.add(dozertwozero);
        imagesDozer.add(dozertwoone);
        imagesDozer.add(dozertwotwo);
        imagesDozer.add(dozertwothree);
        imagesSprog.add(sprogzerozero);
        imagesSprog.add(sprogzeroone);
        imagesSprog.add(sprogzerotwo);
        imagesSprog.add(sprogzerothree);
        imagesSprog.add(sprogonezero);
        imagesSprog.add(sprogoneone);
        imagesSprog.add(sprogonetwo);
        imagesSprog.add(sprogonethree);
        imagesSprog.add(sprogtwozero);
        imagesSprog.add(sprogtwoone);
        imagesSprog.add(sprogtwotwo);
        imagesSprog.add(sprogtwothree);
        imagesViolet.add(violetzerozero);
        imagesViolet.add(violetzeroone);
        imagesViolet.add(violetzerotwo);
        imagesViolet.add(violetzerothree);
        imagesViolet.add(violetonezero);
        imagesViolet.add(violetoneone);
        imagesViolet.add(violetonetwo);
        imagesViolet.add(violetonethree);
        imagesViolet.add(violettwozero);
        imagesViolet.add(violettwoone);
        imagesViolet.add(violettwotwo);
        imagesViolet.add(violettwothree);
        imagesBanshee.add(bansheezerozero);
        imagesBanshee.add(bansheezeroone);
        imagesBanshee.add(bansheezerotwo);
        imagesBanshee.add(bansheezerothree);
        imagesBanshee.add(bansheeonezero);
        imagesBanshee.add(bansheeoneone);
        imagesBanshee.add(bansheeonetwo);
        imagesBanshee.add(bansheeonethree);
        imagesBanshee.add(bansheetwozero);
        imagesBanshee.add(bansheetwoone);
        imagesBanshee.add(bansheetwotwo);
        imagesBanshee.add(bansheetwothree);
        imagesDistructor.add(distructorzerozero);
        imagesDistructor.add(distructorzeroone);
        imagesDistructor.add(distructorzerotwo);
        imagesDistructor.add(distructorzerothree);
        imagesDistructor.add(distructoronezero);
        imagesDistructor.add(distructoroneone);
        imagesDistructor.add(distructoronetwo);
        imagesDistructor.add(distructoronethree);
        imagesDistructor.add(distructortwozero);
        imagesDistructor.add(distructortwoone);
        imagesDistructor.add(distructortwotwo);
        imagesDistructor.add(distructortwothree);
        playerImages.put("DOZER", imagesDozer);
        playerImages.put("VIOLET", imagesViolet);
        playerImages.put("DISTRUCTOR", imagesDistructor);
        playerImages.put("BANSHEE", imagesBanshee);
        playerImages.put("SPROG", imagesSprog);
        imagesPos00.add(dozerzerozero);
        imagesPos00.add(sprogzerozero);
        imagesPos00.add(bansheezerozero);
        imagesPos00.add(distructorzerozero);
        imagesPos00.add(violetzerozero);
        imagesPos01.add(dozerzeroone);
        imagesPos01.add(sprogzeroone);
        imagesPos01.add(bansheezeroone);
        imagesPos01.add(distructorzeroone);
        imagesPos01.add(violetzeroone);
        imagesPos02.add(dozerzerotwo);
        imagesPos02.add(sprogzerotwo);
        imagesPos02.add(bansheezerotwo);
        imagesPos02.add(distructorzerotwo);
        imagesPos02.add(violetzerotwo);
        imagesPos03.add(dozerzerothree);
        imagesPos03.add(sprogzerothree);
        imagesPos03.add(bansheezerothree);
        imagesPos03.add(distructorzerothree);
        imagesPos03.add(violetzerothree);
        imagesPos10.add(dozeronezero);
        imagesPos10.add(sprogonezero);
        imagesPos10.add(bansheeonezero);
        imagesPos10.add(distructoronezero);
        imagesPos10.add(violetonezero);
        imagesPos11.add(dozeroneone);
        imagesPos11.add(sprogoneone);
        imagesPos11.add(bansheeoneone);
        imagesPos11.add(distructoroneone);
        imagesPos11.add(violetoneone);
        imagesPos12.add(dozeronetwo);
        imagesPos12.add(sprogonetwo);
        imagesPos12.add(bansheeonetwo);
        imagesPos12.add(distructoronetwo);
        imagesPos12.add(violetonetwo);
        imagesPos13.add(dozeronethree);
        imagesPos13.add(sprogonethree);
        imagesPos13.add(bansheeonethree);
        imagesPos13.add(distructoronethree);
        imagesPos13.add(violetonethree);
        imagesPos20.add(dozertwozero);
        imagesPos20.add(sprogtwozero);
        imagesPos20.add(bansheetwozero);
        imagesPos20.add(distructortwozero);
        imagesPos20.add(violettwozero);
        imagesPos21.add(dozertwoone);
        imagesPos21.add(sprogtwoone);
        imagesPos21.add(bansheetwoone);
        imagesPos21.add(distructortwoone);
        imagesPos21.add(violettwoone);
        imagesPos22.add(dozertwotwo);
        imagesPos22.add(sprogtwotwo);
        imagesPos22.add(bansheetwotwo);
        imagesPos22.add(distructortwotwo);
        imagesPos22.add(violettwotwo);
        imagesPos23.add(dozertwothree);
        imagesPos23.add(sprogtwothree);
        imagesPos23.add(bansheetwothree);
        imagesPos23.add(distructortwothree);
        imagesPos23.add(violettwothree);
        posImages.put("0,0", imagesPos00);
        posImages.put("0,1", imagesPos01);
        posImages.put("0,2", imagesPos02);
        posImages.put("0,3", imagesPos03);
        posImages.put("1,0", imagesPos10);
        posImages.put("1,1", imagesPos11);
        posImages.put("1,2", imagesPos12);
        posImages.put("1,3", imagesPos13);
        posImages.put("2,0", imagesPos20);
        posImages.put("2,1", imagesPos21);
        posImages.put("2,2", imagesPos22);
        posImages.put("2,3", imagesPos23);

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
        for (int i = 0; i < ammoReps.size(); i++) {
            AmmoRep ammoRep = ammoReps.get(i);
            ImageView imageView = imageViews.get(i);
            ammoRepImageViewMap.put(imageView, ammoRep);
        }
    }

    protected void setConfig(String config) {
        this.config = config;
    }

    protected void setPosWeaponsReps(Map<String, List<CardRep>> posWeaponsReps) {
        this.posWeaponsReps = posWeaponsReps;
    }

    protected void updateAll(LightGameVersion lightGameVersion) {
        //update position of players
        updatePositionsPlayers(lightGameVersion);
        //update the 9 weapons in the field
        updateWeapons(lightGameVersion);
        //update the ammo cards in the field
        updateAmmoCards(lightGameVersion);
        //TODO update other things of light model
    }

    private void updateAmmoCards(LightGameVersion lightGameVersion) {
        for (Map.Entry<String, AmmoRep> entry : lightGameVersion.getPlatformAmmoTile().entrySet()) {
            String pos = entry.getKey();
            AmmoRep ammoRep = entry.getValue();
            posAmmo.get(pos).setImage(new Image("/assets/ammos/" + ammoRep.getType() + ".jpg"));
        }

    }

    private void updatePositionsPlayers(LightGameVersion lightGameVersion) {
        Map<String, String> playerPos = lightGameVersion.getPlayerPlatform();
        for (Map.Entry<String, String> entry : playerPos.entrySet()) {
            String player = entry.getKey();
            String pos = entry.getValue();
            if (!pos.equals("null")) {
                ArrayList<ImageView> imagesPlayers = playerImages.get(player);
                ArrayList<ImageView> imagesPos = posImages.get(pos);
                HandyFunctions.printLineConsole(player + " " + pos);
                for (ImageView im : imagesPlayers) {
                    for (ImageView im2 : imagesPos) {
                        if (im.equals(im2)) {
                            //set all images of the char to invisible
                            for (ImageView imm : imagesPlayers)
                                imm.setVisible(false);
                            //set the only right image to visible
                            im.setVisible(true);
                        }
                    }
                }
            }
        }
    }

    private void updateWeapons(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
        for (Map.Entry<String, ArrayList<ImageView>> entry : posWeaponsImages.entrySet()) {
            String pos = entry.getKey();
            List<ImageView> weaponsImagesInSpot = entry.getValue();
            List<CardRep> weaponsToCopy;
            if (lightGameVersion == null)
                weaponsToCopy = posWeaponsReps.get(pos);
            else {
                weaponsToCopy = lightGameVersion.getPlatformWeapons().get(pos);
                firstSetup = false;
            }
            for (int i = 0; i < posWeaponsReps.size(); i++) {
                weaponsImagesInSpot.get(i).setImage(new Image(weaponsToCopy.get(i).getPath()));
                double x = weaponsImagesInSpot.get(i).getX();
                double y = weaponsImagesInSpot.get(i).getY();
                if (pos.equals("1,0") && firstSetup) {
                    weaponsImagesInSpot.get(i).setRotate(-90);
                    weaponsImagesInSpot.get(i).setFitWidth(97);
                    weaponsImagesInSpot.get(i).setFitHeight(152);
                    weaponsImagesInSpot.get(i).setX(x + 22);
                    weaponsImagesInSpot.get(i).setY(y - 25);
                } else if (pos.equals("2,3") && firstSetup) {
                    weaponsImagesInSpot.get(i).setRotate(90);
                    weaponsImagesInSpot.get(i).setFitWidth(97);
                    weaponsImagesInSpot.get(i).setFitHeight(152);
                    weaponsImagesInSpot.get(i).setX(x + 32);
                    weaponsImagesInSpot.get(i).setY(y - 25);
                }
            }
        }
    }

    private void showInstruction(CardRep cardRep) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Weapon Card");
        alert.setHeaderText(cardRep.getTitle().toUpperCase());
        alert.setContentText(cardRep.getDescription());
        ImageView imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);
        imageView.setImage(new Image(cardRep.getPath()));
        alert.setGraphic(imageView);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
    public void moveClick(){
        gui.iWantToMove("action1");
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

    public void in10_1click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("1,0").get(0));
    }

    public void in10_2click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("1,0").get(1));
    }

    public void in10_3click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("1,0").get(2));
    }

    public void in02_1click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("0,2").get(0));
    }

    public void in02_2click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("0,2").get(1));
    }

    public void in02_3click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("0,2").get(2));
    }

    public void in23_1click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("2,3").get(0));
    }

    public void in23_2click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("2,3").get(1));
    }

    public void in23_3click() {
        showInstruction(lightGameVersion.getPlatformWeapons().get("2,3").get(2));
    }

    private void darkenAllPlatforms() {
        for (Map.Entry<String, Button> entry : posPlatform.entrySet())
            HandyFunctions.darkenButton(entry.getValue());
    }

}
