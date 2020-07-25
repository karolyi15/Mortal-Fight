package Controllers.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private StringProperty username;
    private IntegerProperty wins;
    private IntegerProperty looses;
    private IntegerProperty attack;
    private IntegerProperty success;
    private IntegerProperty failed;
    private IntegerProperty giveUp;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public User(String userName){

        this.username =new SimpleStringProperty(userName);
        this.wins = new SimpleIntegerProperty(0);
        this.looses = new SimpleIntegerProperty(0);
        this.attack = new SimpleIntegerProperty(0);
        this.success = new SimpleIntegerProperty(0);
        this.failed = new SimpleIntegerProperty(0);
        this.giveUp = new SimpleIntegerProperty(0);
    }

    public User(String userName, int wins, int looses, int attack, int success, int failed, int giveUp){

        this.username = new SimpleStringProperty(userName);
        this.wins = new SimpleIntegerProperty(wins);
        this.looses = new SimpleIntegerProperty(looses);
        this.attack = new SimpleIntegerProperty(attack);
        this.success = new SimpleIntegerProperty(success);
        this.failed = new SimpleIntegerProperty(failed);
        this.giveUp = new SimpleIntegerProperty(giveUp);
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

    public int getWins() {
        return wins.get();
    }

    public IntegerProperty winsProperty() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins.set(wins);
    }

    public int getLooses() {
        return looses.get();
    }

    public IntegerProperty loosesProperty() {
        return looses;
    }

    public void setLooses(int looses) {
        this.looses.set(looses);
    }

    public int getAttack() {
        return attack.get();
    }

    public IntegerProperty attackProperty() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack.set(attack);
    }

    public int getSuccess() {
        return success.get();
    }

    public IntegerProperty successProperty() {
        return success;
    }

    public void setSuccess(int success) {
        this.success.set(success);
    }

    public int getFailed() {
        return failed.get();
    }

    public IntegerProperty failedProperty() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed.set(failed);
    }

    public int getGiveUp() {
        return giveUp.get();
    }

    public IntegerProperty giveUpProperty() {
        return giveUp;
    }

    public void setGiveUp(int giveUp) {
        this.giveUp.set(giveUp);
    }
}
