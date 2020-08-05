package ServerConection;


import Commands.CommandManager;
import Commands.iCommand;
import GameFactory.CharacterFactory;
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

        host.setGame(this);
        client.setGame(this);

        this.players.put("Host",new ServerUser(host,host.getGameInput()));
        this.players.put("Client",new ServerUser(client,client.getGameInput()));

        this.initGame();

    }

    private void initGame(){


        JSONObject message = new JSONObject();
        message.put("Request", 5);
        message.put("Command", "Message Command");


        this.players.get("Host").getConnection().setConnectionState(ConnectionState.PLAYING);
        message.put("Message","[System] *** Your Turn ***");
        this.players.get("Host").getConnection().writeOutput(message.toJSONString());

        this.players.get("Client").getConnection().setConnectionState(ConnectionState.WAITING);
        message.put("Message","[System] *** Enemy Turn ***");
        this.players.get("Client").getConnection().writeOutput(message.toJSONString());
    }

    public void changeTurn(ServerUser user) {

        JSONObject message = new JSONObject();
        message.put("Request", 5);
        message.put("Command", "Message Command");

        for (String userKey : this.players.keySet()) {

            if (this.players.get(userKey).equals(user)) {
                this.players.get(userKey).getConnection().setConnectionState(ConnectionState.WAITING);
                message.put("Message","[System] *** Enemy Turn ***");
                this.players.get(userKey).getConnection().writeOutput(message.toJSONString());
            } else {
                this.players.get(userKey).getConnection().setConnectionState(ConnectionState.PLAYING);
                message.put("Message","[System] *** Your Turn ***");
                this.players.get(userKey).getConnection().writeOutput(message.toJSONString());
            }
        }
    }

    public ServerUser getEnemy(ServerUser user){

        for(String userKey : this.players.keySet()){

            if(!this.players.get(userKey).equals(user)){
                return this.players.get(userKey);
            }
        }

        return null;
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

    public String[] tokenizer(String input){

        return input.split("\\s+");
    }

    public void isGameOver(){


        if(this.players.get("Host").isGameOver()){
            JSONObject jsonWinner = new JSONObject();
            JSONObject jsonLooser = new JSONObject();

            jsonWinner.put("Request",-1);
            jsonWinner.put("State","Winner");

            jsonLooser.put("Request",-1);
            jsonLooser.put("State","Looser");


            this.players.get("Host").getConnection().writeOutput(jsonLooser.toJSONString());
            this.players.get("Client").getConnection().writeOutput(jsonWinner.toJSONString());

        }else if(this.players.get("Client").isGameOver()){
            JSONObject jsonWinner = new JSONObject();
            JSONObject jsonLooser = new JSONObject();

            jsonWinner.put("Request",-1);
            jsonWinner.put("State","Winner");

            jsonLooser.put("Request",-1);
            jsonLooser.put("State","Looser");

            this.players.get("Host").getConnection().writeOutput(jsonWinner.toJSONString());
            this.players.get("Client").getConnection().writeOutput(jsonLooser.toJSONString());
        }
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

    public CharacterFactory getCharacterFactory() {
        return characterFactory;
    }

    public void broadcast(String output){

        for(String playerKey : this.players.keySet()){

            this.players.get(playerKey).getConnection().writeOutput(output);
        }
    }


}
