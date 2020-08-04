package Controllers.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lobby {

    private StringProperty id;
    private StringProperty host;

    public Lobby(String id, String host){
        System.out.println("Creating Lobby\n lobbyID: "+id);
        this.id = new SimpleStringProperty(id);
        this.host = new SimpleStringProperty(host);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getHost() {
        return host.get();
    }

    public StringProperty hostProperty() {
        return host;
    }

    public void setHost(String host) {
        this.host.set(host);
    }
}
