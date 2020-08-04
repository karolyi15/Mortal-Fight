package Controllers.Views;

import Controllers.Main;
import GameFactory.CharacterType;
import ServerConection.Client;
import ServerConection.ConnectionState;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


public class LobbyScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;
    private Client client;
    private ArrayList<ComboBox> selectedCharacters;
    private ArrayList<CharacterType> availableCharacters;

    private Thread updater;

    //*** Buttons ***
    @FXML
    private Button ready_Button;
    @FXML
    private Button return_Button;

    //*** Labels ***
    @FXML
    private Label connectionStatus_Label;

    //*** Combo Boxes ***
    @FXML
    private ComboBox<ConnectionState> character1;
    @FXML
    private ComboBox<ConnectionState> character2;
    @FXML
    private ComboBox<ConnectionState> character3;
    @FXML
    private ComboBox<ConnectionState> character4;
    @FXML
    private ComboBox<ConnectionState> character5;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public LobbyScene_Controller(){

        this.selectedCharacters = new ArrayList<>();
        this.availableCharacters = new ArrayList<>();
    }

    @FXML
    private void initialize(){

        this.initCharacterSelection();
        this.connectionStatus_Label.setText("Waiting");
    }

    //Setters & Getters
    public void setMainApp(Main mainApp){

        this.mainApp = mainApp;
        this.client = this.mainApp.getServerClient();
    }

    private void initAvailableCharacters(){

        for(int character=0; character<CharacterType.values().length;character++){

            this.availableCharacters.add(CharacterType.values()[character]);
        }
    }

    private void initCharacterSelection(){
        this.initAvailableCharacters();

        this.selectedCharacters.add(this.character1);
        this.selectedCharacters.add(this.character2);
        this.selectedCharacters.add(this.character3);
        this.selectedCharacters.add(this.character4);
        this.selectedCharacters.add(this.character5);

        for(int character=0;character<this.selectedCharacters.size();character++){

            this.selectedCharacters.get(character).getItems().addAll(this.availableCharacters);
        }
    }

    private boolean validateCharacters(){

        ArrayList<CharacterType> openSet = new ArrayList<>();

        for(int character=0;character<this.selectedCharacters.size();character++){

            if(this.selectedCharacters.get(character).getValue() == null || openSet.contains((CharacterType) this.selectedCharacters.get(character).getValue())){
                return false;
            }

            openSet.add((CharacterType) this.selectedCharacters.get(character).getValue());
        }

        this.connectionStatus_Label.setText("Ready");
        return  true;
    }

    private JSONArray getSelectedCharacters(){

        JSONArray characterArray = new JSONArray();

        for(int character=0;character<this.selectedCharacters.size();character++){

            characterArray.add(this.selectedCharacters.get(character).getValue().toString());
        }

        return characterArray;
    }



    //*** Button Handlers ***
    @FXML
    private void onHandleReady(){

        if(this.validateCharacters()){
            //Change connection State
            JSONObject gamaData = new JSONObject();
            gamaData.put("Request",0);
            gamaData.put("User",this.mainApp.getActiveUser().toJson());
            gamaData.put("Characters",this.getSelectedCharacters());

            this.client.writeOutput(gamaData.toJSONString());

            JSONObject connectionData = new JSONObject();
            connectionData.put("Request",4);
            connectionData.put("Connection",ConnectionState.READY.toString());

            this.client.writeOutput(connectionData.toJSONString());
            this.mainApp.showGameScene();

        }else{
            //Missing Character
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Missing or duplicated Character");

            alert.showAndWait();
        }
    }

    @FXML
    private void onHandleReturn(){

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",4);
        jsonOutput.put("Connection",ConnectionState.FINISHED.toString());

        this.client.writeOutput(jsonOutput.toJSONString());

        this.mainApp.showRoomsScene();
    }



}
