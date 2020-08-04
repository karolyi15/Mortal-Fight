package ServerConection;


import GameFactory.CharacterFactory;
import GameFactory.WeaponFactory;
import org.json.simple.JSONObject;

public class Game {

    private UserConnection host;
    private JSONObject hostUserData;

    private UserConnection client;
    private JSONObject clientUserData;


    private CharacterFactory characterFactory;


    public Game(UserConnection host, UserConnection client){


        this.host = host;
        this.client = client;

        this.characterFactory = new CharacterFactory();

    }

    private void initGame(){

    }

    //Inner Classes

    private class ServerUser{

        public ServerUser(){

        }
    }

}
