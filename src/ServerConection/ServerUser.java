package ServerConection;

import GameFactory.Character;
import GameFactory.CharacterStyle;
import GameFactory.CharacterType;
import GameFactory.ElementType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class ServerUser {

    private UserConnection connection;
    private Boolean tie;

    private String username;
    private long ranking;
    private long wins;
    private long looses;
    private long attack;
    private long success;
    private long failed;
    private long giveUp;

    private HashMap<String, Character> characters;
    private Character activeCharacter;

    public ServerUser(UserConnection connection, JSONObject inputJson){

        this.characters = new HashMap<>();

        this.connection = connection;
        JSONObject userData = (JSONObject) inputJson.get("User");

        this.username = (String) userData.get("Username");
        this.ranking = (long) userData.get("Ranking");
        this.wins = (long) userData.get("Wins");
        this.looses = (long) userData.get("Looses");
        this.attack = (long) userData.get("Attack");
        this.success = (long) userData.get("Success");
        this.failed = (long) userData.get("Failed");
        this.giveUp =(long) userData.get("GiveUp");

        this.connection.setServerUser(this);
        this.createCharacters((JSONArray) inputJson.get("Characters"));
    }

    //Characters

    private CharacterType parseCharacterType(String type){

        switch (type){

            case "CYRAX":
                return CharacterType.CYRAX;
            case "NOOBSAIBOT":
                return CharacterType.NOOBSAIBOT;
            case "REPTILE":
                return CharacterType.REPTILE;
            case "GORO":
                return CharacterType.GORO;
            case "KANO":
                return CharacterType.KANO;
            case "BARAKA":
                return CharacterType.BARAKA;
            case "SHANGTSUNG":
                return CharacterType.SHANGTSUNG;
            case "JAX":
                return CharacterType.JAX;
            case "MILEENA":
                return CharacterType.MILEENA;
            case "SHAOKAHN":
                return CharacterType.SHAOKAHN;
            case "SONYABLADE":
                return CharacterType.SONYABLADE;
            case "KENSHI":
                return CharacterType.KENSHI;
            case "KUNGLAO":
                return CharacterType.KUNGLAO;
            case "KITANA":
                return CharacterType.KITANA;
            case "RAIDEN":
                return CharacterType.RAIDEN;
            case "CASSIECAGE":
                return CharacterType.CASSIECAGE;
            case "JOHNNYCAGE":
                return CharacterType.JOHNNYCAGE;
            case "LIUKANG":
                return CharacterType.LIUKANG;
            case "SUBZERO":
                return CharacterType.SUBZERO;
            case "SCORPION":
                return CharacterType.SCORPION;
        }

        //Default
        return  CharacterType.SUBZERO;
    }

    private void createCharacters(JSONArray characters){

        for(int character=0;character<characters.size();character++){

            Character tempCharacter = this.connection.getGame().getCharacterFactory().getCharacter(CharacterStyle.WARRIOR);
            tempCharacter.setCharacter(this.parseCharacterType((String)characters.get(character)));

            this.characters.put((String)characters.get(character),tempCharacter);
            if(character==0){
                this.activeCharacter =tempCharacter;
            }
        }
    }

    public boolean setActiveCharacter(String character){

        try {

            if (this.characters.get(character).getState() == true) {
                this.activeCharacter = this.characters.get(character);
                return true;
            }

        }catch (Exception e){
            return false;
        }

        return false;
    }

    public Character getActiveCharacter(){
        return this.activeCharacter;
    }

    //Setters & Getters


    public Boolean getTie() {
        return tie;
    }

    public void setTie(Boolean tie) {
        this.tie = tie;
    }

    public UserConnection getConnection() {
        return connection;
    }

    public void setGame(Game game){

        this.connection.setGame(game);
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getRanking() {
        return ranking;
    }

    public void setRanking(long ranking) {
        this.ranking = ranking;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getLooses() {
        return looses;
    }

    public void setLooses(long looses) {
        this.looses = looses;
    }

    public long getAttack() {
        return attack;
    }

    public void setAttack(long attack) {
        this.attack = attack;
    }

    public long getSuccess() {
        return success;
    }

    public void setSuccess(long success) {
        this.success = success;
    }

    public long getFailed() {
        return failed;
    }

    public void setFailed(long failed) {
        this.failed = failed;
    }

    public long getGiveUp() {
        return giveUp;
    }

    public void setGiveUp(long giveUp) {
        this.giveUp = giveUp;
    }

    //Communication

    public boolean isGameOver(){

        boolean alive = false;

        for(String characterID : this.characters.keySet()){

            if(this.characters.get(characterID).getState()){
                alive=true;
            }
        }

        return alive;
    }

    public JSONObject userStatsJson(){

        JSONObject userJson = new JSONObject();

        userJson.put("Username",this.username);
        userJson.put("Ranking",this.ranking);
        userJson.put("Wins",this.wins);
        userJson.put("Looses",this.looses);
        userJson.put("Attack",this.attack);
        userJson.put("Success",this.success);
        userJson.put("Failed",this.failed);
        userJson.put("GiveUp",this.giveUp);

        return userJson;
    }
}
