module com.example.chatroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires AnimateFX;


    opens com.example.chatroom to javafx.fxml;
    exports com.example.chatroom;
}