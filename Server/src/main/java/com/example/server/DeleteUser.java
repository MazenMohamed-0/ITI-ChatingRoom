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

public class DeleteUser implements Initializable {

    @FXML
    private Button Delete;


    @FXML
    private Button Back2;

    @FXML
    private Label Message2;


    @FXML
    private TextField UserId;


    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (UserId.getText().isBlank()) {
                    Message2.setStyle(errorMessage);
                    new animatefx.animation.Shake(UserId).play();


                }
                if (UserId.getText().isBlank()) {
                    Message2.setText("User ID is Required");
                    UserId.setStyle(errorStyle);
                    new animatefx.animation.Shake(UserId).play();


                } else {

                    try {
                        DAO.deleteUser(UserId.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    Message2.setText("The User Deleted Successfully");
                    Message2.setStyle(successMessage);
                    UserId.setStyle(successStyle);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");

                }


            }
        });

        Back2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");
            }
        });
    }
}
