package ServerConection;

import Commands.CommandManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private int port=9090;
    private ServerSocket serverSocket;
    private boolean running;
    private int connections;

    private HashMap<String, ServerThread> users;
    private CommandManager commandManager;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public Server(){

        this.running = false;
        this.users =  new HashMap<>();

        this.commandManager = CommandManager.getInstance();

        this.connections=0;
    }

    public void run(){

        try {

            //****************** Init Server *********************//

            //Creates a server socket, handle 50 queued connections
            this.serverSocket = new ServerSocket(port);
            this.running = true;
            System.out.print("Server is listening in port: " + port+"\n");

            while (running) {
                //*************** Init Client Socket ****************//

                //Starts listening for incoming client requests
                Socket socket = serverSocket.accept();
                System.out.print("New client connected\n");
                ServerThread tempThread = new ServerThread(socket,this);
                this.users.put(String.valueOf(this.connections),tempThread);
                this.connections++;

                tempThread.start();

            }


            this.serverSocket.close();


        }catch(IOException e){
            e.printStackTrace(); }

    }

    public void terminateServer(){

        //************** Terminate Server *****************//

        //All clients will be disconnected
       this.running = false;

    }


    //*** Setters & Getters
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isRunning() {
        return this.running;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    //*** Client Communication ***
    public void broadcast(String content, ServerThread userExcluded){

        for( String userName: this.users.keySet()){

            this.users.get(userName).sendMessage(content);
        }

    }


    //*** Main ***
    public static  void  main(String[] args){
        Server server=new Server();
        server.run();
    }
}