package Controllers.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private StringProperty subject;
    private StringProperty content;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    public Message(String subject,String content){

        this.subject = new SimpleStringProperty(subject);
        this.content = new SimpleStringProperty(content);
    }

    //*** Setters & Getters ***
    public StringProperty getSubject() {

        return subject;
    }

    public void setSubject(String subject) {

        this.subject = new SimpleStringProperty(subject);
    }

    public StringProperty getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = new SimpleStringProperty(content);
    }
}
