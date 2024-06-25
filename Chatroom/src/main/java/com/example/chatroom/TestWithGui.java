package com.example.chatroom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestWithGui implements Initializable, Runnable {


    @FXML
    public  TextArea chat ;

    @FXML
    private Button b1;

    @FXML
    private TextField message ;




    @Override
    public synchronized void initialize(URL url, ResourceBundle resourceBundle) {
        chat.setText("welcome"+"\n");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Operation = ". "+Clinte.Username+"."+message.getText();
                new Clinte(Operation);

                for (String ch : Clinte.allMessages) {
                    chat.appendText(ch+"\n");
                }
                Clinte.allMessages.clear();
            }

        });


    }

    @Override
    public void run() {
        for (String ch : Clinte.allMessages) {
            chat.appendText(ch+"\n");


        }
        Clinte.allMessages.clear();
    }
}
