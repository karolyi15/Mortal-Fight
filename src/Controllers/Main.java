package Controllers;

import Controllers.Models.Message;
import Controllers.Models.User;
import Controllers.Views.*;
import ServerConection.Client;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //*** Scene System ***
    private Stage primaryStage;
    private BorderPane rootLayout;

    //*** Sound System ***
    private MediaPlayer musicPlayer;
    private MediaPlayer soundPlayer;

    //*** Data Base System ***
    private JSONObject dataBase;

    //*** Display System ***
    private ObservableList<User> userData;
    private ObservableList<Message> messageData;

    //*** User System ***
    private User activeUser;

    //*** Server Connection ***
    private Client serverClient;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public Main(){

        //*** Init Display System ***
        this.userData = FXCollections.observableArrayList();
        this.messageData = FXCollections.observableArrayList();

        //*** Init Data Base System ***
        this.loadLocalDataBase();

        //*** Init Server Connection ***
        this.serverClient =  new Client();

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //*** Init Stage System ***
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Mortal Fight");
        this.primaryStage.setResizable(false);

        //*** Show Title Scene ***
        this.initRootLayout();
        this.showTitleScene();

    }

    //*** Setters & Getters ***
    public ObservableList<User> getUserData() {
        return userData;
    }

    public ObservableList<Message> getMessageData() {
        return messageData;
    }

    public void setMessageData(ObservableList<Message> messageData) {
        this.messageData = messageData;
    }

    public User getActiveUser() {
        return this.activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public Client getServerClient() {
        return serverClient;
    }

    //*** Sound System ***
    public void playMusic(String filePath){

        Media music = new Media(new File(filePath).toURI().toString());
        this.musicPlayer = new MediaPlayer(music);

        this.musicPlayer.setVolume(1.2);
        this.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.musicPlayer.setAutoPlay(true);
    }

    public void playSound(String filePath){

        Media music = new Media(new File(filePath).toURI().toString());
        this.soundPlayer = new MediaPlayer(music);

        this.soundPlayer.setVolume(1.7);
        this.soundPlayer.setAutoPlay(true);
    }

    //*** Scene Management ***
    private void initRootLayout(){

        try{

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Views/RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();

            //Create Scene
            Scene rootLayoutScene = new Scene(this.rootLayout);

            //Set Root Layout to Stage
            this.primaryStage.setScene(rootLayoutScene);
            this.primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showTitleScene(){

        try{

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Views/TitleScene_UI.fxml"));
            AnchorPane titleScene = loader.load();

            //Set Title Scene Controller
            TitleScene_Controller controller = loader.getController();
            controller.setMainApp(this);
            //controller.initScene();

            //Set Scene to Root Layout
            this.rootLayout.setCenter(titleScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public void showMenuScene(){

        try{

            //Load Fxml File
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(Main.class.getResource("Views/MenuScene_UI.fxml"));
            AnchorPane menuScene = (AnchorPane) loader.load();

            //Set Controller to Menu Scene
            MenuScene_Controller controller = loader.getController();
            controller.setMainApp(this);

            //Set Menu Scene to Root Layout
            this.rootLayout.setCenter(menuScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public void showAboutScene(){

        try{

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Views/AboutScene_UI.fxml"));
            AnchorPane aboutScene = (AnchorPane) loader.load();

            //Set Controller to About Scene
            AboutScene_Controller controller = loader.getController();
            controller.setMainApp(this);

            //Set About Scene to Root Layout
            this.rootLayout.setCenter(aboutScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public void showTeamScene(){

        try{
            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Views/TeamScene_UI.fxml"));
            AnchorPane teamScene = (AnchorPane) loader.load();

            //Set Team Scene Controller
            TeamScene_Controller controller = loader.getController();
            controller.setMainApp(this);

            //Set Team Scene to Root Layout
            this.rootLayout.setCenter(teamScene);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showGameScene(){

        try{

            //Load Fxml File
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(Main.class.getResource("Views/GameScene_UI.fxml"));
            AnchorPane gameScene = loader.load();

            //Set Controller to Game Scene
            GameScene_Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.initGameScene();

            //Set Game Scene to Root Layout
            this.rootLayout.setCenter(gameScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    //*** Data Management ***
    public void loadLocalDataBase(){

        JSONParser parser = new JSONParser();

        try(Reader reader = new FileReader("Resources/LocalDataBase.json")){

            this.dataBase = (JSONObject) parser.parse(reader);
            JSONArray userData = (JSONArray) dataBase.get("UserData");
            this.parseUserData(userData);

        }catch (IOException e){

            e.printStackTrace();

        }catch (ParseException e){

            e.printStackTrace();

        }
    }

    private void parseUserData(JSONArray userData){
        ArrayList<User> userList = new ArrayList<>();
        for(int user=0; user<userData.size();user++){

            JSONObject tempUser = (JSONObject) userData.get(user);
            userList.add(new User((String) tempUser.get("Username"),(long) tempUser.get("Wins"),(long) tempUser.get("Looses"),(long) tempUser.get("Attack"),(long) tempUser.get("Success"),(long) tempUser.get("Failed"),(long) tempUser.get("GiveUp")));

        }

        this.userData.addAll(userList);
    }

    public void updateLocalDataBase(String dataName, JSONArray data){

        this.dataBase.put(dataName,data);

        try(FileWriter file = new FileWriter("Resources/LocalDataBase.json")){

            file.write(this.dataBase.toJSONString());

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
