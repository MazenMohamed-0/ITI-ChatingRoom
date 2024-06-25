package com.example.server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersList implements Initializable {

    @FXML
    private Button Back;


    @FXML
    private TextArea offline;

    @FXML
    private TextArea online;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String messageOffline = String.join("\n", DAO.getOffline());

            offline.setText(messageOffline);

            String messageOnline = String.join("\n", DAO.getOnline());

            online.setText(messageOnline);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");
            }
        });
    }
}
