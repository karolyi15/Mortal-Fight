package Controllers.Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AboutScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;

    @FXML
    private Button return_Button;
    @FXML
    private Button Information_Button;
    @FXML
    private Button Commands_Button;
    @FXML
    private  Button Types_Button;
    @FXML
    private TextArea content_TextArea;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    //*** Button Handlers ***
    @FXML
    private void onHandleReturn(){

        this.mainApp.showTitleScene();
    }
}
