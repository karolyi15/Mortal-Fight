package Controllers.Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TitleScene_Controller {

    private Main mainApp;

    @FXML
    private Button start_Button;
    @FXML
    private Button about_Button;


    public void setMainApp(Main mainApp){

        this.mainApp=mainApp;
    }

    @FXML
    private void onHandleStart(){

        System.out.println("Start Game");
    }

    @FXML
    private void onHandleAbout(){

        System.out.println("Game Info");
    }
}
