package Controllers.Views;

import Controllers.Main;
import Controllers.Models.Lobby;
import Controllers.Models.User;
import ServerConection.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Optional;

public class ManagerScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;
    private Client client;
    private ObservableList<Lobby> lobbies;
    private Thread lobbyUpdater;

    //*** Table View ***
    @FXML
    private TableView<Lobby> lobbies_TableView;
    @FXML
    private TableColumn<Lobby,String> hostID_TableColumn;
    @FXML
    private TableColumn<Lobby,String> lobbyId_TableColumn;

    //*** Buttons ***
    @FXML
    private Button createLobby_Button;
    @FXML
    private Button joinLobby_Button;
    @FXML
    private Button return_Button;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public ManagerScene_Controller(){

        this.lobbies = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize(){

        //*** Initialize Table ***
        this.lobbyId_TableColumn.setCellValueFactory(cellData->cellData.getValue().idProperty());
        this.hostID_TableColumn.setCellValueFactory(cellData->cellData.getValue().hostProperty());

    }

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp) {

        this.mainApp = mainApp;

        this.client = this.mainApp.getServerClient();
        this.updateLobbies();
    }

    //*** Server Connection ***
    private void updateLobbies(){

        this.lobbyUpdater =  new Thread(){
            public  void run(){

                while(true) {

                    try {

                        JSONObject tempJson = new JSONObject();
                        tempJson.put("Request",1);

                        client.writeOutput(tempJson.toJSONString());



                        parseLobbies(client.readInput());

                        //Wait 10 seconds
                        lobbyUpdater.sleep(10000);

                    } catch (InterruptedException e) {

                        System.out.println("Error while Updating Lobbies");
                        e.printStackTrace();

                    }
                }


            }
        };

       this.lobbyUpdater.start();
    }

    private void parseLobbies(String inputString){

        JSONParser parser = new JSONParser();

        try{

            //Parse Json
            JSONObject jsonInput = (JSONObject) parser.parse(inputString);
            JSONArray lobbyJsonArray = (JSONArray) jsonInput.get("ActiveLobbies");

            //Clear Content from Observable List
            this.lobbies.clear();

            //Create Lobbies Parsed List
            ArrayList<Lobby> lobbyList = new ArrayList<>();
            System.out.println("Parsing lobbies");
            for(int room=0; room<lobbyJsonArray.size();room++){

                JSONObject tempRoom = (JSONObject) lobbyJsonArray.get(room);
                System.out.println(tempRoom.toJSONString());
                lobbyList.add(new Lobby((String) tempRoom.get("LobbyID"),(String) tempRoom.get("Host")));
            }

            this.lobbies.addAll(lobbyList);
            this.lobbies_TableView.setItems(this.lobbies);

        }catch (ParseException e){

            System.out.println("Error while parsing input data");
            e.printStackTrace();

        }
    }

    //*** Button Handlers ***
    @FXML
    private void onHandleCreateLobby(){

        TextInputDialog inputDialog =  new TextInputDialog("LobbyID");
        inputDialog.setTitle("Create Lobby");
        inputDialog.setHeaderText(null);
        inputDialog.setContentText("Enter lobby ID: ");

        Optional<String> username = inputDialog.showAndWait();

        if(username.isPresent()){

            //Write Output
            JSONObject jsonOutput = new JSONObject();
            jsonOutput.put("Request",2);
            jsonOutput.put("LobbyID",username.get());
            jsonOutput.put("Host",this.mainApp.getActiveUser().getUsername());

            this.client.writeOutput(jsonOutput.toJSONString());

            //Read Input
            String inputString = this.client.readInput();
            JSONParser  parser = new JSONParser();

            try{

                JSONObject jsonInput = (JSONObject) parser.parse(inputString);

                if((boolean) jsonInput.get("RequestState")== true){

                    this.lobbyUpdater.stop();
                    this.mainApp.showTeamScene();

                }else{
                    System.out.println("Error while creating lobby");
                }

            }catch (ParseException e){
                e.printStackTrace();
            }
        }


    }

    @FXML
    private void onHandleJoinLobby(){

        Lobby selectedLobby = this.lobbies_TableView.getSelectionModel().getSelectedItem();

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",3);
        jsonOutput.put("LobbyID",selectedLobby.getId());

        this.client.writeOutput(jsonOutput.toJSONString());
        String inputString = this.client.readInput();

        JSONParser parser = new JSONParser();

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);

            if((boolean) inputJson.get("RequestState") == true){

                this.lobbyUpdater.stop();
                this.mainApp.showTeamScene();

            }else{
                //Lobby is full
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Full Lobby");

                alert.showAndWait();
            }

        }catch (ParseException e){

            e.printStackTrace();
        }

    }

    @FXML
    private void onHandleReturn(){

        this.lobbyUpdater.stop();
        this.mainApp.showMenuScene();
    }

}
