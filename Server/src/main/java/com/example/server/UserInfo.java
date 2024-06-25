package com.example.server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserInfo implements Initializable {

    @FXML
    private Button Add;

    @FXML
    private Button Back1;

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;


    @FXML
    private Label Message;

    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Username.getText().isBlank() || Password.getText().isBlank()) {
                    Message.setStyle(errorMessage);
                    new animatefx.animation.Shake(Username).play();
                    new animatefx.animation.Shake(Password).play();
                }

                if (Username.getText().isBlank() && Password.getText().isBlank()) {
                    Message.setText("You Can't Leave Fields Empty");
                    Username.setStyle(errorStyle);
                    Password.setStyle(errorStyle);
                    new animatefx.animation.Shake(Username).play();
                    new animatefx.animation.Shake(Password).play();

                } else if (Username.getText().isBlank()) {
                    Message.setText("Username is Required");
                    Username.setStyle(errorStyle);
                    Password.setStyle(successStyle);
                    new animatefx.animation.Shake(Username).play();


                } else if (Password.getText().isBlank()) {
                    Message.setText("Password is Required");
                    Username.setStyle(successStyle);
                    Password.setStyle(errorStyle);
                    new animatefx.animation.Shake(Password).play();


                } else {
                    try {
                        DAO.insertUser(Username.getText(), Password.getText());

                        Message.setText("The User Added Successfully");
                        Message.setStyle(successMessage);
                        Username.setStyle(successStyle);
                        Password.setStyle(successStyle);
                        Username.setStyle(successStyle);


                        Thread.sleep(1000);
                    } catch (InterruptedException | SQLException e) {
                        e.printStackTrace();
                    }

                    DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");

                }


            }
        });

        Back1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");
            }
        });
    }
}
