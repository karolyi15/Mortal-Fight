package ServerConection;


import Commands.CommandManager;
import Commands.iCommand;
import GameFactory.CharacterFactory;
import GameFactory.WeaponFactory;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class Game {


    private CommandManager commandManager;

    private HashMap<String, ServerUser> players;
    private CharacterFactory characterFactory;


    public Game(UserConnection host, UserConnection client){

        this.players = new HashMap<>();
        this.characterFactory = new CharacterFactory();
        this.commandManager = CommandManager.getInstance();

        this.players.put("Host",new ServerUser(host,host.getGameInput()));
        this.players.put("Client",new ServerUser(client,client.getGameInput()));

        this.initGame();

    }

    private void initGame(){

        this.players.get("Host").setGame(this);
        this.players.get("Client").setGame(this);

        this.players.get("Host").getConnection().setConnectionState(ConnectionState.PLAYING);
        this.players.get("Client").getConnection().setConnectionState(ConnectionState.WAITING);
    }

    public synchronized void executeCommand(JSONObject jsonCommand,ServerUser user){

        if((long) jsonCommand.get("Request")==5){


            String[] tokenizedCommand = this.tokenizer((String) jsonCommand.get("Command"));

            iCommand command =this.commandManager.getCommand(tokenizedCommand[1]);
            command.execute(tokenizedCommand,user);

        }else if((long) jsonCommand.get("Request")==7) {
            //Return enemy data
            user.getConnection().writeOutput(this.getEnemyData(user).toJSONString());
        }

    }

    private String[] tokenizer(String input){

        return input.split("\\s+");
    }

    private synchronized JSONObject getEnemyData(ServerUser user){

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",7);

        for(String userKey : this.players.keySet()){

            if(!this.players.get(userKey).equals(user)){
                jsonOutput.put("User",this.players.get(userKey).userStatsJson());
            }
        }

        return jsonOutput;
    }

    public void broadcast(String output){

        for(String playerKey : this.players.keySet()){

            this.players.get(playerKey).getConnection().writeOutput(output);
        }
    }


}
