package com.example.chatroom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 926, 650);
        stage.setTitle("Welcome");
       // stage.getIcons().add(new Image("D:\\semister4\\Field trainning\\Project\\ChatFDT\\Chatroom\\src\\main\\resources\\com\\example\\chatroom\\LOGO.png"));
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}