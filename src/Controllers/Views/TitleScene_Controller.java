package Controllers.Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TitleScene_Controller {


    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //*** Main App ***
    private Main mainApp;

    //*** Buttons ***
    @FXML
    private Button start_Button;
    @FXML
    private Button about_Button;

    //*** Logo Image View ***
    @FXML
    private ImageView logo_ImageView;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    @FXML
    private void initialize(){

        //Set Logo to Image View
        this.logo_ImageView.setImage(new Image("file:Resources/Imgs/Title_Img.jpeg"));

        //Center Logo
        this.logo_ImageView.setX(6);
    }

    public void initScene(){

        this.mainApp.playMusic("Resources/Sounds/Title_Music.mp3");
    }

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp){

        this.mainApp=mainApp;
    }

    //*** Button Handlers ***
    @FXML
    private void onHandleStart(){

        this.mainApp.showMenuScene();
    }

    @FXML
    private void onHandleAbout(){

        this.mainApp.showAboutScene();
    }
}
