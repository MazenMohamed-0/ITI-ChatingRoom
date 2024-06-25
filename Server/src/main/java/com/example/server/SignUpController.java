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

public class SignUpController implements Initializable {
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private TextField confirmpassword;
    @FXML
    private Button cancel;
    @FXML
    private Button signup;
    @FXML
    private Button back;

    @FXML
    private Label Error;

    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (username.getText().isBlank() || password.getText().isBlank() || confirmpassword.getText().isBlank()) {
                    Error.setStyle(errorMessage);
                    new animatefx.animation.Shake(username).play();
                    new animatefx.animation.Shake(password).play();
                    new animatefx.animation.Shake(confirmpassword).play();

                }

                if (username.getText().isBlank() && password.getText().isBlank() && confirmpassword.getText().isBlank()) {
                    Error.setText("You Can't Leave Fields Empty");
                    username.setStyle(errorStyle);
                    password.setStyle(errorStyle);
                    confirmpassword.setStyle(errorStyle);
                    new animatefx.animation.Shake(username).play();
                    new animatefx.animation.Shake(password).play();
                    new animatefx.animation.Shake(confirmpassword).play();

                } else if (username.getText().isBlank()) {
                    Error.setText("Username is Required");
                    username.setStyle(errorStyle);
                    password.setStyle(successStyle);
                    confirmpassword.setStyle(successStyle);
                    new animatefx.animation.Shake(username).play();


                } else if (password.getText().isBlank()) {
                    Error.setText("Password is Required");
                    username.setStyle(successStyle);
                    password.setStyle(errorStyle);
                    confirmpassword.setStyle(successStyle);
                    new animatefx.animation.Shake(password).play();

                } else if (confirmpassword.getText().isBlank()) {
                    Error.setText("Can you confirm your password");
                    username.setStyle(successStyle);
                    password.setStyle(successStyle);
                    confirmpassword.setStyle(errorStyle);
                    new animatefx.animation.Shake(confirmpassword).play();
                }


                else {
                    if (password.getText().equals(confirmpassword.getText())) {
                        try {
                            DAO.insertAdmin(username.getText(),password.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        DBUtils.ChangeScene(event, "hello-view.fxml", "Welcome");


                    } }
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "SignUp.fxml", "SignUp");
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "hello-view.fxml", "Welcome");
            }
        });
    }
}