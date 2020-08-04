package ServerConection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;

public class UserConnection extends Thread{

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //*** Server Connection ***
    private Server server;
    private Socket socket;

    //*** Client Communication ***
    private PrintWriter writer;

    //*** Connection State ***
    private boolean running;
    private ConnectionState connectionState;

    private JSONObject gameInput;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public UserConnection(Server server, Socket socket) {

        //Sever Connection
        this.server = server;
        this.socket = socket;

        //Connection State
        this.running = false;
        this.connectionState = ConnectionState.STARTED;
    }

    //*** Start Client Connection ***
    public void run(){

        try {
            //************** Read data to client ****************//

            //Connection State
            this.running = true;

            //Read data from the client (read to byte array)
            InputStream input = socket.getInputStream();
            //Set byte array to string
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //************** Send data to client ***************//

            //Write data as a byte array
            OutputStream output = socket.getOutputStream();
            //Converts byte array to text format
            this.writer = new PrintWriter(output, true);

            String inputString;

            do {

                inputString = reader.readLine();
                System.out.println("Input from Client: "+inputString);
                this.parseRequest(inputString);

            }while (this.running);

            socket.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //*** Connection State ***
    public void terminateUserConnection(){

        this.running = false;
    }

    public ConnectionState getConnectionState() {
        return this.connectionState;
    }

    public void setConnectionState(ConnectionState connectionState) {
        this.connectionState = connectionState;
    }

    //*** Client Communication ***
    public void writeOutput(String output){
        this.writer.println(output);
    }

    public JSONObject getGameInput(){

        return this.gameInput;
    }

    private void parseRequest(String inputString){

        JSONParser parser = new JSONParser();

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);
            long requestID = (long) inputJson.get("Request");

            if (requestID == -1){
                //Terminate Connection
                this.terminateUserConnection();

            }else if (requestID == 0){
                //Game init data
                this.gameInput = inputJson;

            }else if (requestID == 1){
                //Request Lobbies

                this.writeOutput(this.server.getLobbies().toJSONString());

            }else if (requestID == 2){
                //Create Lobby

                String lobbyID = (String) inputJson.get("LobbyID");
                String hostUsername = (String) inputJson.get("Host");
                this.writeOutput(this.server.createLobby(lobbyID,hostUsername,this).toJSONString());

            }else if (requestID == 3){
                //Join Lobby

                String lobbyID = (String) inputJson.get("LobbyID");
                this.writeOutput(this.server.joinLobby(lobbyID,this).toJSONString());

            }else if(requestID == 4){
                //Update Connection State

               this.parseConnectionState( (String) inputJson.get("Connection"));
                System.out.println("Actual State: "+this.connectionState.toString());

            }else if(requestID == 5){
                //Game Command

                this.gameInput = inputJson;

            }else{

                System.out.println("Invalid request");
            }

        }catch (ParseException e){

            System.out.println("Error while parsing input data");
            e.printStackTrace();
        }

    }

    private void parseConnectionState(String state){

        switch (state){

            case "STARTED":
                this.connectionState = ConnectionState.STARTED;
                break;

            case "WAITING":
                this.connectionState = ConnectionState.WAITING;
                break;

            case "SELECTING":
                this.connectionState = ConnectionState.SELECTING;
                break;

            case "READY":
                this.connectionState = ConnectionState.READY;
                break;

            case "PLAYING":
                this.connectionState = ConnectionState.PLAYING;
                break;

            case "FINISHED":
                this.connectionState = ConnectionState.FINISHED;
                break;
        }
    }


}
