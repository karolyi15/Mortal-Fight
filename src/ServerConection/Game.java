package ServerConection;


import Commands.CommandManager;
import Commands.iCommand;
import GameFactory.CharacterFactory;
import GameFactory.WeaponFactory;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class Game {


    private JSONObject hostUserData;


    private JSONObject clientUserData;

    private CommandManager commandManager;

    private HashMap<String, UserConnection> players;
    private CharacterFactory characterFactory;


    public Game(UserConnection host, UserConnection client){

        this.players = new HashMap<>();
        this.characterFactory = new CharacterFactory();
        this.commandManager = CommandManager.getInstance();

        this.players.put("Host",host);
        this.players.put("Client",client);

        this.initGame();

    }

    private void initGame(){

        this.players.get("Host").setGame(this);
        this.players.get("Client").setGame(this);

        this.players.get("Host").setConnectionState(ConnectionState.PLAYING);
        this.players.get("Client").setConnectionState(ConnectionState.WAITING);
    }

    public synchronized void executeCommand(JSONObject jsonCommand,UserConnection user){

        if((long) jsonCommand.get("Request")==5){


            String[] tokenizedCommand = this.tokenizer((String) jsonCommand.get("Command"));

            iCommand command =this.commandManager.getCommand(tokenizedCommand[1]);
            command.execute(tokenizedCommand,user);
        }

    }

    private String[] tokenizer(String input){

        return input.split("\\s+");
    }

    public void broadcast(String output){

        for(String playerKey : this.players.keySet()){

            this.players.get(playerKey).writeOutput(output);
        }
    }

}
