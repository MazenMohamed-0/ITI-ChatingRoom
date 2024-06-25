package com.example.chatroom;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;


public class ChatroomController implements Initializable {
    @FXML
    private ComboBox<String> status;
    @FXML
    private Button logout;
    @FXML
    private CheckBox available;
    private String [] stat= {"available","not available"};
    boolean urstat ;
    @FXML
    private VBox vboxMessage;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Button send;
    @FXML
    private TextField textfield;
    @FXML
    private Label labelUsername;
    @FXML
    private Button save;
    @FXML
    private Button open;
    @FXML
    private TextArea test;
    @FXML
    private Button refresh;
    @FXML
    private Label state;
    private HashSet<String> uniqueMessages = new HashSet<>();
    public ChatroomController() throws IOException {
    }

    @FXML
    protected void available(ActionEvent event) throws IOException{
        if(available.isSelected()){
            urstat = true;
            state.setText("Available");
        } else {
            urstat = false;
            state.setText("Not Available");
        }
        System.out.println(urstat);
    }


    private File chatFile;

    public ArrayList <String> mymessage= new ArrayList<String>();;

    @FXML
    protected void send(ActionEvent event){
        send.setDefaultButton(true);
        String ss = textfield.getText();
        String Operation = ". "+Clinte.Username+"."+ss;
        mymessage.add(Operation);
        new Clinte(Operation);
        textfield.setText(" ");
    }
    @FXML
    protected void  save(ActionEvent event){
        try (FileWriter writer = new FileWriter(chatFile, true)) {
            for(int i=0;i<mymessage.size();i++){
                String[] cutter = mymessage.get(i).split("\\.");
                String name = cutter[1];
                String message = cutter[2];
                String concat = name + ": " + message;
                writer.write(concat  + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event, "hello-view.fxml", "Welcome");
                String Operation = "changeStatus."+Clinte.Username+". ";
                new Clinte(Operation);
            }
        });

        new Thread(() -> {
            while (true) {
                // Check if there are new messages in the Clinte.allMessages vector
                if (!Clinte.allMessages.isEmpty()) {
                    // Retrieve the new messages
                    ArrayList<String> newMessages = new ArrayList<>(Clinte.allMessages);
                    Clinte.allMessages.clear();
                    // Add the messages to the mymessage array list and display them
                    for (String ch : newMessages) {
                        String[] cutter = ch.split("\\.");
                        String name = cutter[1];
                        String message = cutter[2];
                        String concat = name + ": " + message;

                        if (!uniqueMessages.contains(concat)) {
                            uniqueMessages.add(concat);
                            HBox hbox = new HBox();
                            hbox.setAlignment(Pos.BOTTOM_LEFT);
                            hbox.setPadding(new Insets(5, 5, 5, 10));
                            Text text = new Text(concat);
                            TextFlow textflow = new TextFlow(text);
                            textflow.setStyle("  -fx-background-color: #6AB04C;\n" +
                                    "  -fx-background-radius: 20px;\n" +
                                    "  -fx-border-radius: 20px;\n" +
                                    "  -fx-padding: 8px 12px;\n" +
                                    "  -fx-border-color: transparent;\n" +
                                    "  -fx-font-size: 14px;\n" +
                                    "  -fx-text-fill: #FFFFFF;");
                            hbox.getChildren().add(textflow);
                            Platform.runLater(() -> vboxMessage.getChildren().add(hbox));
                        }
                    }
                }

                // Wait for a short time before checking for new messages again
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        ObservableList<String> list = FXCollections.observableArrayList(stat);
        status.setItems(list);

        vboxMessage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                scrollpane.setVvalue((double) t1);
            }
        });

        String name = Clinte.Username;
        labelUsername.setText(name);
        chatFile = new File("chat.txt");
        if (chatFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(chatFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.BOTTOM_LEFT);
                    hbox.setPadding(new Insets(5,5,5,10));
                    Text text = new Text(line);
                    TextFlow textflow = new TextFlow(text);
                    textflow.setStyle("  -fx-background-color: #6AB04C;\n" +
                            "  -fx-background-radius: 20px;\n" +
                            "  -fx-border-radius: 20px;\n" +
                            "  -fx-padding: 8px 12px;\n" +
                            "  -fx-border-color: transparent;\n" +
                            "  -fx-font-size: 14px;\n" +
                            "  -fx-text-fill: #000000;");
                    hbox.getChildren().add(textflow);
                    vboxMessage.getChildren().add(hbox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
}