package Controllers.Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class GameScene_Controller {

    private Main mainApp;

    @FXML
    private TextArea console;
    @FXML
    private TextArea gameLog;


    public void setMainApp(Main mainApp){

        this.mainApp = mainApp;
    }
}
