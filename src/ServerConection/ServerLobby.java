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

    private Thread updater;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public ServerLobby(Server server, String hostUsername, UserConnection playerHost){

        this.server = server;
        this.hostUsername = hostUsername;

        this.playerHost = playerHost;
        this.waitPlayer();
   }

    //*** Destructor ***
    public void terminateLobby(){

        this.server.deleteLobby(this);
    }

    private boolean gameReady(){

        if(this.playerHost.getConnectionState()==ConnectionState.READY && this.playerClient.getConnectionState()==ConnectionState.READY){
            return true;
        }

        return false;
    }


    public synchronized boolean join(UserConnection player){

        if(this.playerClient == null){

            this.playerClient = player;

            this.playerHost.setConnectionState(ConnectionState.SELECTING);
            this.playerClient.setConnectionState(ConnectionState.SELECTING);

            return true;

        }else{
            return false;
        }
    }

    private void waitPlayer(){

        this.updater = new Thread(){

            public void run(){

                while(!gameReady()){

                    try {

                        updater.sleep(3000);

                    }catch (InterruptedException e){

                        e.printStackTrace();
                    }
                }


                new Game(playerHost, playerClient);
                terminateLobby();
            }
        };

        this.updater.start();

    }



    //*** Setters & Getters ***

    public String getHostUsername() {
        return this.hostUsername;
    }
}
