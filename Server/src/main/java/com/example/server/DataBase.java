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

public class DataBase implements Initializable {

    @FXML
    private Button Back4;

    @FXML
    private TextArea data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {


            String messageOffline = String.join("\n", DAO.database());



            data.setText(messageOffline);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Back4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");
            }
        });
    }
}
