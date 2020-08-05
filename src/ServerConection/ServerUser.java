package ServerConection;

import org.json.simple.JSONObject;

public class ServerUser {

    private UserConnection connection;

    private String username;
    private long ranking;
    private long wins;
    private long looses;
    private long attack;
    private long success;
    private long failed;
    private long giveUp;

    public ServerUser(UserConnection connection, JSONObject inputJson){

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
    }

    //Characters



    //Setters & Getters


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
