package Controllers.Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;

    @FXML
    private Button selectUser_Button;
    @FXML
    private Button createUser_Button;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    //*** Button Handlers ***
    @FXML
    private void onHandleSelectUser(){

        this.mainApp.showGameScene();
    }
    @FXML
    private void onHandleCreateUser(){

    }

}
