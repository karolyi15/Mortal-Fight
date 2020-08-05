package ServerConection;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //*** Sever Socket***
    private int port=9090;
    private ServerSocket serverSocket;

    //*** Server State ***
    private boolean running;

    //*** Server Register ***
    private HashMap<String, ServerLobby> activeLobbies;
    private ArrayList<ServerUser> ranking;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public Server(){

        //*** Init Sever State
        this.running = false;

        //*** Init Lobby System ***
        this.activeLobbies = new HashMap<>();
        this.ranking = new ArrayList<>();
    }

    public void run(){

        try {

            //****************** Init Server *********************//

            //Creates a server socket, handle 50 queued connections
            this.serverSocket = new ServerSocket(port);
            //Server State
            this.running = true;
            System.out.print("Server is listening in port: " + port+"\n");


            while (running) {
                //*************** Init Client Socket ****************//

                //Starts listening for incoming client requests
                Socket socket = serverSocket.accept();

                //Update Active Connections
                System.out.println("*** New User Connected ***");

                //Start User Connection
                new UserConnection(this,socket).start();

            }

            this.serverSocket.close();

        }catch(IOException e) {

            System.out.println("Error while attempting to connect user");
        }

    }

    public void terminateServer(){

        //All clients will be disconnected
        this.running = false;
    }

    //*** Setters & Getters ***
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isRunning() {
        return this.running;
    }


    //*** Manage Lobbies ***

    public synchronized JSONObject getLobbies(){

        JSONObject jsonOutput = new JSONObject();
        JSONArray jsonArrayLobbies = new JSONArray();

        for(String lobbyID : this.activeLobbies.keySet()){

            JSONObject tempLobby = new JSONObject();
            tempLobby.put("LobbyID",lobbyID);
            tempLobby.put("Host",this.activeLobbies.get(lobbyID).getHostUsername());

            jsonArrayLobbies.add(tempLobby);
        }

        jsonOutput.put("ActiveLobbies",jsonArrayLobbies);
        System.out.println("Active lobbies: "+jsonOutput.toJSONString());
        return jsonOutput;
    }

    public synchronized JSONObject createLobby(String lobbyID, String hostUsername ,UserConnection user){

        JSONObject jsonOutput = new JSONObject();

        if(this.activeLobbies.containsKey(lobbyID)){
            //Lobby already exist, cant create it
            jsonOutput.put("RequestState",false);

        }else{
            //Creating Lobby
            ServerLobby newLobby = new ServerLobby(this,hostUsername,user);
            this.activeLobbies.put(lobbyID,newLobby);
            jsonOutput.put("RequestState",true);
        }

        return jsonOutput;
    }

    public synchronized JSONObject joinLobby(String lobbyID, UserConnection user){

        JSONObject jsonOutput = new JSONObject();

        if(this.activeLobbies.containsKey(lobbyID) && this.activeLobbies.get(lobbyID).join(user)){
            //Join Lobby
            jsonOutput.put("RequestState", true);

        }else{
            //Lobby is no longer available
            jsonOutput.put("RequestState",false);
        }

        return jsonOutput;
    }

    public synchronized void deleteLobby(ServerLobby lobby){

        for(String lobbyID : this.activeLobbies.keySet()){

            if(this.activeLobbies.get(lobbyID).equals(lobby)){

               this.activeLobbies.remove(lobbyID);

                break;
            }
        }
    }

    //*** Ranking ***
    public void addToRanking(ServerUser user){

        this.ranking.add(user);
    }

    public JSONArray getRanking(){

        JSONArray rankingData = new JSONArray();

        for(int user=0; user<this.ranking.size();user++){

            rankingData.add(this.ranking.get(user).userStatsJson());
        }

        return rankingData;
    }


    //*** Main ***
    public static  void  main(String[] args){
        Server server=new Server();
        server.run();
    }
}