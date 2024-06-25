package com.example.server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button Login;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private ImageView userIcon, passwordIcon;

    @FXML
    private Label Error;
    @FXML
    private Button signup;

    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (Username.getText().isBlank() || Password.getText().isBlank()) {
                    Error.setStyle(errorMessage);
                    new animatefx.animation.Shake(Username).play();
                    new animatefx.animation.Wobble(userIcon).play();
                    new animatefx.animation.Shake(Password).play();
                    new animatefx.animation.Wobble(passwordIcon).play();
                }

                if (Username.getText().isBlank() && Password.getText().isBlank()) {
                    Error.setText("You Can't Leave Fields Empty");
                    Username.setStyle(errorStyle);
                    Password.setStyle(errorStyle);
                    new animatefx.animation.Shake(Username).play();
                    new animatefx.animation.Wobble(userIcon).play();
                    new animatefx.animation.Shake(Password).play();
                    new animatefx.animation.Wobble(passwordIcon).play();
                } else if (Username.getText().isBlank()) {
                    Error.setText("Username is Required");
                    Username.setStyle(errorStyle);
                    Password.setStyle(successStyle);
                    new animatefx.animation.Shake(Username).play();
                    new animatefx.animation.Wobble(userIcon).play();

                } else if (Password.getText().isBlank()) {
                    Error.setText("Password is Required");
                    Username.setStyle(successStyle);
                    Password.setStyle(errorStyle);
                    new animatefx.animation.Shake(Password).play();
                    new animatefx.animation.Wobble(passwordIcon).play();
                } else {
                    boolean agree = false;

                    try {
                        agree = DAO.Select_AdminLoginInfo(Username.getText(), Password.getText());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (agree) {

                        Error.setText("Login Successful");
                        Error.setStyle(successMessage);
                        Username.setStyle(successStyle);
                        Password.setStyle(successStyle);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        DBUtils.ChangeScene(event, "serverInterface.fxml", "Admin Interface");
                    } else {
                        Error.setText("u have to be admin to get access");
                        Error.setStyle(errorStyle);
                        Username.setStyle(errorStyle);
                        Password.setStyle(errorStyle);

                    }


                }
            }
        });
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event,"SignUp.fxml","Signup");
            }
        });

    }

}