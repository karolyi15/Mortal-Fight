package Controllers.Views;

import Controllers.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;


public class TeamScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;

    //*** Table View ***
    @FXML
    private TableView<Character> userTeam_TableView;
    @FXML
    private TableColumn<Character,String> character_TableColumn;

    //*** Buttons ***
    @FXML
    private Button select_Button;
    @FXML
    private Button next_Button;
    @FXML
    private Button previous_Button;

    //*** Labels ***
    @FXML
    private Label characterName_Label;
    @FXML
    private Label characterType_Label;

    //*** Image View ***
    @FXML
    private ImageView characterImg_ImageView;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***


    //Setters & Getters
    public void setMainApp(Main mainApp){

        this.mainApp = mainApp;
    }
}
