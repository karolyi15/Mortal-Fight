package Controllers.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private StringProperty id;
    private StringProperty content;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public Message(String id,String content){

        this.id = new SimpleStringProperty(id);
        this.content = new SimpleStringProperty(content);
    }

    //*** Setters & Getters ***
    public StringProperty getId() {

        return id;
    }

    public void setId(String id) {

        this.id = new SimpleStringProperty(id);
    }

    public StringProperty getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = new SimpleStringProperty(content);
    }
}
