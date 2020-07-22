package Controllers;

import Controllers.Views.AboutScene_Controller;
import Controllers.Views.GameScene_Controller;
import Controllers.Views.MenuScene_Controller;
import Controllers.Views.TitleScene_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Stage primaryStage;
    private BorderPane rootLayout;

    private MediaPlayer musicPlayer;
    private MediaPlayer soundPlayer;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Mortal Fight");

        this.initRootLayout();
        this.showTitleScene();

    }

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
            controller.initScene();

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

    public void showGameScene(){

        try{

            //Load Fxml File
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(Main.class.getResource("Views/GameScene_UI.fxml"));
            BorderPane gameScene = loader.load();

            //Set Controller to Game Scene
            GameScene_Controller controller = loader.getController();
            controller.setMainApp(this);

            //Set Game Scene to Root Layout
            this.rootLayout.setCenter(gameScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        launch(args);
    }
}
