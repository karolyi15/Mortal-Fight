package Controllers.Views;

import Controllers.Main;
import ServerConection.Client;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class GameScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;
    private Client client;
    private int gameLog;

    @FXML
    private TextArea console_TextArea;
    @FXML
    private TextArea gameLog_TextArea;
    @FXML
    private TextField userInput_TextField;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public GameScene_Controller(){

        this.gameLog = 0;
    }

    @FXML
    private void initialize(){


    }

    public void initGameScene(){

        this.onHandleSendMessage();
        this.showUserInformation();

        new ConsoleThread(this.console_TextArea,this).start();

        this.initGameLog();
    }

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp){

        this.mainApp = mainApp;
        this.client = this.mainApp.getServerClient();

        this.initGameScene();
    }



    //*** Display Information ***
    private void showUserInformation(){

        this.gameLog_TextArea.clear();
        this.gameLog_TextArea.setText("*** My Stats ***\n" +
                "Wins: "+this.mainApp.getActiveUser().getWins()+"\n" +
                "Looses: "+this.mainApp.getActiveUser().getLooses()+"\n" +
                "Attacks: "+this.mainApp.getActiveUser().getAttack()+"\n" +
                "-Success:"+this.mainApp.getActiveUser().getSuccess()+"\n" +
                "-Failed: "+this.mainApp.getActiveUser().getFailed()+"\n" +
                "Surrenders: "+this.mainApp.getActiveUser().getGiveUp()+"\n");
    }

    //*** Game Log ***
    private void initGameLog(){

        this.gameLog_TextArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                    handleGameLog();
                }
            }
        });
    }

    private void handleGameLog() {

        //Updater
        this.gameLog += 1;
        if (this.gameLog > 2) {
            this.gameLog = 0;
        }

        //Display
        if (gameLog == 0) {
            //User Data
            this.showUserInformation();
        } else {
            JSONObject jsonOutput = new JSONObject();

            if (this.gameLog == 1) {
                //Enemy Data
                jsonOutput.put("Request",7);

            } else if (this.gameLog == 2) {
                //Ranking Data
                jsonOutput.put("Request",6);
            }

            this.client.writeOutput(jsonOutput.toJSONString());
        }
    }

    private void showRanking(JSONArray rankingData){

        this.gameLog_TextArea.clear();
        String rankingContent =  "*** Ranking ***\n";

        for(int user=0; user<rankingData.size(); user++){

            JSONObject tempUser = (JSONObject) rankingData.get(user);

            rankingContent+= "Position... "+user+1+
                    "Username: "+(String) tempUser.get("Username")+"\n" +
                    "Wins: "+(String) tempUser.get("Wins")+"\n" +
                    "Looses: "+(String) tempUser.get("Looses")+"\n";
        }


        this.gameLog_TextArea.setText(rankingContent);

    }

    private void showEnemyInformation(JSONObject enemyData){

        this.gameLog_TextArea.clear();
        this.gameLog_TextArea.setText("* Enemy Stats *\n" +
                "Wins: "+(long) enemyData.get("Wins")+"\n" +
                "Looses: "+(long) enemyData.get("Looses")+"\n" +
                "Attacks: "+(long) enemyData.get("Attack")+"\n" +
                "-Success:"+(long) enemyData.get("Success")+"\n" +
                "-Failed: "+(long) enemyData.get("Failed")+"\n" +
                "Surrenders: "+(long) enemyData.get("GiveUp")+"\n");
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

        private GameScene_Controller controller;
        private TextArea console;
        private Client client;

        public ConsoleThread(TextArea console, GameScene_Controller controller){

            this.console = console;
            this.controller = controller;
            this.client = this.controller.client;

        }

        public void run(){


            while(true){

                String inputString = this.client.readInput();
                this.parseCommand(inputString);

            }
        }

        private void parseCommand(String inputString){

            JSONParser parser = new JSONParser();

            try{

                JSONObject inputJson = (JSONObject) parser.parse(inputString);

                if((long)inputJson.get("Request") == 5) {
                    //Commands
                    if (inputJson.get("Command").equals("Message Command")) {

                        this.console.appendText("\n" + (String) inputJson.get("Message"));

                    } else if (inputJson.get("Command").equals("Attack Command")) {

                    } else if (inputJson.get("Command").equals("Reload Command")) {


                    } else {
                        //Case Not FoundCommand/ Error Command
                        this.console.appendText("\n" + (String) inputJson.get("Message"));
                    }
                }else if((long)inputJson.get("Request") == 6){
                    //Display Ranking
                    this.controller.showRanking((JSONArray) inputJson.get("Ranking"));
                }else if((long)inputJson.get("Request") == 7){
                    //Display Enemy
                    this.controller.showEnemyInformation((JSONObject) inputJson.get("User"));
                }

            }catch (ParseException e){

                e.printStackTrace();
            }
        }

    }
}
