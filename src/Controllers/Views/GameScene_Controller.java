package Controllers.Views;

import Controllers.Main;
import ServerConection.Client;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.json.simple.JSONObject;


public class GameScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;
    private Client client;

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

        new ConsoleThread(this.console_TextArea,this.client).start();

    }

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp){

        this.mainApp = mainApp;
        this.client = this.mainApp.getServerClient();

        this.initGameScene();
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

                    if(!userInput_TextField.getText().equals("")){

                        JSONObject jsonOutput = new JSONObject();
                        jsonOutput.put("Request",5);
                        jsonOutput.put("Command","["+mainApp.getActiveUser().getUsername()+"]: "+userInput_TextField.getText());
                        client.writeOutput(jsonOutput.toJSONString());
                    }


                    userInput_TextField.clear();
                }

            }
        });
    }

    private class ConsoleThread extends Thread{

        private TextArea console;
        private Client client;

        public ConsoleThread(TextArea console, Client client){

            this.console = console;
            this.client = client;
        }

        public void run(){


            while(true){

                String inputString = this.client.readInput();
                this.console.appendText("\n"+inputString);

            }
        }

    }
}
