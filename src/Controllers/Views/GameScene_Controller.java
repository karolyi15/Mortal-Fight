package Controllers.Views;

import Controllers.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class GameScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;

    @FXML
    private TextArea console_TextArea;
    @FXML
    private TextArea gameLog_TextArea;
    @FXML
    private TextField userInput_TextField;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    @FXML
    private void initialize(){


    }

    public void initGameScene(){

        this.onHandleSendMessage();
        this.showUserInformation();
        this.mainApp.getServerClient().initClient(this.console_TextArea);
    }

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp){

        this.mainApp = mainApp;
    }

    //*** Display Information ***
    private void showUserInformation(){

        this.gameLog_TextArea.setText("*** My Stats ***\n" +
                "Wins: "+this.mainApp.getActiveUser().getWins()+"\n" +
                "Looses: "+this.mainApp.getActiveUser().getLooses()+"\n" +
                "Attacks: "+this.mainApp.getActiveUser().getAttack()+"\n" +
                "-Success:"+this.mainApp.getActiveUser().getSuccess()+"\n" +
                "-Failed: "+this.mainApp.getActiveUser().getFailed()+"\n" +
                "Surrenders: "+this.mainApp.getActiveUser().getGiveUp()+"\n");
    }

    private void showRanking(){

    }

    private void showEnemyInformation(){

    }

    //*** Input Handler ***
    @FXML
    private void onHandleSendMessage(){

        this.userInput_TextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyEvent.getCode().equals(KeyCode.ENTER)){

                    mainApp.getServerClient().writeOutput("["+mainApp.getActiveUser().getUsername()+"]: "+userInput_TextField.getText());
                    userInput_TextField.clear();
                }

            }
        });
    }
}
