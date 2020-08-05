package Controllers.Models;

import javafx.beans.property.*;
import org.json.simple.JSONObject;

public class User {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //*** User Stats ***
    private StringProperty username;
    private LongProperty ranking;
    private LongProperty wins;
    private LongProperty looses;
    private LongProperty attack;
    private LongProperty success;
    private LongProperty failed;
    private LongProperty giveUp;

    //*** User Team ***

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public User(String userName){

        this.username =new SimpleStringProperty(userName);
        this.ranking = new SimpleLongProperty(0);
        this.wins = new SimpleLongProperty(0);
        this.looses = new SimpleLongProperty(0);
        this.attack = new SimpleLongProperty(0);
        this.success = new SimpleLongProperty(0);
        this.failed = new SimpleLongProperty(0);
        this.giveUp = new SimpleLongProperty(0);
    }

    public User(String userName, long ranking ,long wins, long looses, long attack, long success, long failed, long giveUp){

        this.username = new SimpleStringProperty(userName);
        this.ranking = new SimpleLongProperty(ranking);
        this.wins = new SimpleLongProperty(wins);
        this.looses = new SimpleLongProperty(looses);
        this.attack = new SimpleLongProperty(attack);
        this.success = new SimpleLongProperty(success);
        this.failed = new SimpleLongProperty(failed);
        this.giveUp = new SimpleLongProperty(giveUp);
    }

    //*** Setters & Getters ***


    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public long getRanking() {
        return ranking.get();
    }

    public LongProperty rankingProperty() {
        return ranking;
    }

    public void setRanking(long ranking) {
        this.ranking.set(ranking);
    }

    public long getWins() {
        return wins.get();
    }

    public LongProperty winsProperty() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins.set(wins);
    }

    public long getLooses() {
        return looses.get();
    }

    public LongProperty loosesProperty() {
        return looses;
    }

    public void setLooses(long looses) {
        this.looses.set(looses);
    }

    public long getAttack() {
        return attack.get();
    }

    public LongProperty attackProperty() {
        return attack;
    }

    public void setAttack(long attack) {
        this.attack.set(attack);
    }

    public long getSuccess() {
        return success.get();
    }

    public LongProperty successProperty() {
        return success;
    }

    public void setSuccess(long success) {
        this.success.set(success);
    }

    public long getFailed() {
        return failed.get();
    }

    public LongProperty failedProperty() {
        return failed;
    }

    public void setFailed(long failed) {
        this.failed.set(failed);
    }

    public long getGiveUp() {
        return giveUp.get();
    }

    public LongProperty giveUpProperty() {
        return giveUp;
    }

    public void setGiveUp(long giveUp) {
        this.giveUp.set(giveUp);
    }

    //*** Send User Data ***
    public JSONObject toJson(){

        JSONObject userJson = new JSONObject();

        userJson.put("Username",this.username.getValue());
        userJson.put("Ranking",this.ranking.getValue());
        userJson.put("Wins",this.wins.getValue());
        userJson.put("Looses",this.looses.getValue());
        userJson.put("Attack",this.attack.getValue());
        userJson.put("Success",this.success.getValue());
        userJson.put("Failed",this.failed.getValue());
        userJson.put("GiveUp",this.giveUp.getValue());

        return userJson;
    }

    public void updateStats(JSONObject userData){

        //Stats Information
        this.ranking = new SimpleLongProperty((long) userData.get("Ranking"));
        this.wins = new SimpleLongProperty((long) userData.get("Wins"));
        this.looses = new SimpleLongProperty((long) userData.get("Looses"));
        this.attack = new SimpleLongProperty((long) userData.get("Attack"));
        this.success = new SimpleLongProperty((long) userData.get("Success"));
        this.failed = new SimpleLongProperty((long) userData.get("Failed"));
        this.giveUp = new SimpleLongProperty((long) userData.get("GiveUp"));
    }
}
