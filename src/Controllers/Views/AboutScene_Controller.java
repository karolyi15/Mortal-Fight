package Controllers.Views;

import Controllers.Main;
import Controllers.Models.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AboutScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Main mainApp;

    //*** Table View ***
    @FXML
    private TableView<Message> messages_TableView;
    @FXML
    private TableColumn<Message,String> messageId_TableColumn;

    //*** Buttons ***
    @FXML
    private Button return_Button;

    //*** Text Area ***
    @FXML
    private TextArea content_TextArea;

    //*** Labels ***
    @FXML
    private Label messageId_Label;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    @FXML
    private void initialize(){

        //*** Initialize Table View ***
        this.messageId_TableColumn.setCellValueFactory(cellData->cellData.getValue().getId());

        //*** Clear Text Area ***
        this.showMessage(null);

        //*** Selection Listener ***
        this.messages_TableView.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> showMessage(newValue));
    }

    //*** Setters & Getters ***
    public void setMainApp(Main mainApp) {

        this.mainApp = mainApp;

        //*** Set Messages to Table View ***
        this.messages_TableView.setItems(this.mainApp.getMessageData());
    }

    //*** Button Handlers ***
    @FXML
    private void onHandleReturn(){

        this.mainApp.showTitleScene();
    }

    //*** Display Messages ***
    private void showMessage(Message message){

        if(message != null){

            this.messageId_Label.setText(message.getId().getValue());
            this.content_TextArea.setText(message.getContent().getValue());

        }else{

            this.messageId_Label.setText("");
            this.content_TextArea.setText("");
        }
    }
}
