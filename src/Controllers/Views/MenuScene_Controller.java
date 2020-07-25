package Controllers.Views;

import Controllers.Main;
import Controllers.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class MenuScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;

    //*** Table View ***
    @FXML
    private TableView<User> users_TableView;
    @FXML
    private TableColumn<User, String> userName_TableColumn;

    //*** Buttons ***
    @FXML
    private Button selectUser_Button;
    @FXML
    private Button createUser_Button;
    @FXML
    private Button deleteUser_Button;

    //*** Labels ***
    @FXML
    private Label userName_Label;
    @FXML
    private Label wins_Label;
    @FXML
    private Label looses_Label;
    @FXML
    private Label attacks_Label;
    @FXML
    private Label success_Label;
    @FXML
    private Label failed_Label;
    @FXML
    private Label giveUp_Label;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor  ***
    @FXML
    private void initialize(){

        //*** Initialize Table ***
        this.userName_TableColumn.setCellValueFactory(cellData->cellData.getValue().usernameProperty());

        //*** Clear Table ***
        this.showUserInformation(null);

        //*** Listen for Table Selection
        this.users_TableView.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> showUserInformation(newValue));
    }

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp) {

        this.mainApp = mainApp;

        this.users_TableView.setItems(this.mainApp.getUserData());
    }

    //*** Button Handlers ***
    @FXML
    private void onHandleSelectUser(){

        User selectedUser = this.users_TableView.getSelectionModel().getSelectedItem();

        if(selectedUser != null) {
            this.mainApp.setActiveUser(selectedUser);

            this.mainApp.showGameScene();
        }else{

            //No user selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("No user selected");

            alert.showAndWait();
        }
    }

    @FXML
    private void onHandleCreateUser(){

        TextInputDialog inputDialog =  new TextInputDialog("username");
        inputDialog.setTitle("Create User");
        inputDialog.setHeaderText(null);
        inputDialog.setContentText("Enter a username: ");

        Optional<String> username = inputDialog.showAndWait();

        if(username.isPresent()){

            this.users_TableView.getItems().add(new User(username.get()));
        }
    }

    @FXML
    private void onHandleDeleteUser(){

        int selectedIndex = this.users_TableView.getSelectionModel().getSelectedIndex();

        if(selectedIndex>=0) {
            this.users_TableView.getItems().remove(selectedIndex);
        }else{

            //No user selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("No user selected");

            alert.showAndWait();
        }
    }

    //*** Display Information ***
    private void showUserInformation(User user){

        if(user!=null){

            this.userName_Label.setText(user.getUsername());
            this.wins_Label.setText(String.valueOf(user.getWins()));
            this.looses_Label.setText(String.valueOf(user.getLooses()));
            this.attacks_Label.setText(String.valueOf(user.getAttack()));
            this.success_Label.setText(String.valueOf(user.getSuccess()));
            this.failed_Label.setText(String.valueOf(user.getFailed()));
            this.giveUp_Label.setText(String.valueOf(user.getGiveUp()));

        }else{

            this.userName_Label.setText("");
            this.wins_Label.setText("");
            this.looses_Label.setText("");
            this.attacks_Label.setText("");
            this.success_Label.setText("");
            this.failed_Label.setText("");
            this.giveUp_Label.setText("");
        }
    }

}
