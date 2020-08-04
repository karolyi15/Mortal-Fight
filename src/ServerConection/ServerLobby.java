package ServerConection;

import org.json.simple.JSONObject;

public class ServerLobby {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //Sever Connection
    private Server server;

    private String hostUsername;

    private UserConnection playerHost;
    private UserConnection playerClient;

    private Thread stateManager;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public ServerLobby(Server server, String hostUsername, UserConnection playerHost){

        this.hostUsername = hostUsername;

        this.server = server;
        this.playerHost = playerHost;

        this.playerHost.setConnectionState(ConnectionState.WAITING);
        this.waitPlayer();
   }

    //*** Destructor ***
    public void terminateLobby(){

        this.stateManager.stop();
        this.server.deleteLobby(this);
    }

    public synchronized boolean join(UserConnection player){

        if(this.playerClient == null) {

            System.out.println("New player join " + this.hostUsername + "'s lobby");

            this.playerClient = player;

            this.playerHost.setConnectionState(ConnectionState.SELECTING);
            this.playerClient.setConnectionState(ConnectionState.SELECTING);
            return true;

        }else{

            return false;
        }
    }

    public synchronized void waitPlayer(){

        this.stateManager = new Thread(){

            public void run(){

                while(!playerHost.getConnectionState().equals(ConnectionState.FINISHED)){

                    try {
                        if (playerClient != null) {

                            if (playerHost.getConnectionState().equals(ConnectionState.READY) && playerClient.getConnectionState().equals(ConnectionState.READY)) {

                                //Create Game
                                System.out.println("New Game Created");
                                terminateLobby();
                            }

                        } else {

                            playerHost.setConnectionState(ConnectionState.WAITING);
                        }

                        //Wait for 3 seconds
                        stateManager.sleep(3000);

                    }catch (InterruptedException e){

                        e.printStackTrace();
                    }
                }

                terminateLobby();

            }
        };

        this.stateManager.start();
   }

    //*** Setters & Getters ***

    public String getHostUsername() {
        return this.hostUsername;
    }
}
