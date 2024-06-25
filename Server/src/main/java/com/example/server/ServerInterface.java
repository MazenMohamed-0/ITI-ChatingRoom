package com.example.server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerInterface implements Initializable {

    @FXML
    private Button checkUsersList;

    @FXML
    private Button insertUser;

    @FXML
    private Button deleteUser;

    @FXML
    private Button updateUser;

    @FXML
    private Button goToDatabase;

    @FXML
    private Button signOut;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        checkUsersList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "usersList.fxml", "Status");
            }
        });


        insertUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "UserInfo.fxml", "InsertUser");
            }
        });


        deleteUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "DeleteUser.fxml", "DeleteUser");
            }
        });


        updateUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "UpdateUser.fxml", "updateUser");
            }
        });


        goToDatabase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "DataBase.fxml", "Database");
            }
        });


        signOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "hello-view.fxml", "Admin Interface");
            }
        });








    }
}
