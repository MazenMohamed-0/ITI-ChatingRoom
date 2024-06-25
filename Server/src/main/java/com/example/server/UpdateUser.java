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

public class UpdateUser implements Initializable {

    @FXML
    private Button Update;

    @FXML
    private Button Back3;

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private TextField UserId;

    @FXML
    private Label Message3;

    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Username.getText().isBlank() || Password.getText().isBlank() || UserId.getText().isBlank()) {
                    Message3.setStyle(errorMessage);
                    new animatefx.animation.Shake(Username).play();
                    new animatefx.animation.Shake(Password).play();
                    new animatefx.animation.Shake(UserId).play();
                }

                if (Username.getText().isBlank() && Password.getText().isBlank() && UserId.getText().isBlank()) {
                    Message3.setText("You Can't Leave Fields Empty");
                    Username.setStyle(errorStyle);
                    Password.setStyle(errorStyle);
                    UserId.setStyle(errorStyle);
                    new animatefx.animation.Shake(Username).play();
                    new animatefx.animation.Shake(Password).play();
                    new animatefx.animation.Shake(UserId).play();

                } else if (Username.getText().isBlank()) {
                    Message3.setText("Username is Required");
                    Username.setStyle(errorStyle);
                    Password.setStyle(successStyle);
                    UserId.setStyle(successStyle);
                    new animatefx.animation.Shake(Username).play();


                } else if (Password.getText().isBlank()) {
                    Message3.setText("Password is Required");
                    Username.setStyle(successStyle);
                    Password.setStyle(errorStyle);
                    UserId.setStyle(successStyle);
                    new animatefx.animation.Shake(Password).play();


                } else if (UserId.getText().isBlank()) {
                    Message3.setText("UserId is Required");
                    Username.setStyle(successStyle);
                    Password.setStyle(successStyle);
                    UserId.setStyle(errorStyle);
                    new animatefx.animation.Shake(UserId).play();


                } else {
                    try {

                        DAO.updateUser(UserId.getText(), Username.getText(), Password.getText());

                        Message3.setText("The User Updated Successful");
                        Message3.setStyle(successMessage);
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

        Back3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");
            }
        });
    }
}
