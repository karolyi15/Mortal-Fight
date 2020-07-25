package Controllers.Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class    TitleScene_Controller {


    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;

    @FXML
    private Button start_Button;
    @FXML
    private Button about_Button;
    @FXML
    private ImageView logo_ImageView;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Initialize ***
    public void initScene(){

        //this.mainApp.playMusic("Resources/Sounds/Title_Music.mp3");
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
