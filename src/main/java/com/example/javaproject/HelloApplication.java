package com.example.javaproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.EventListener;

public class HelloApplication extends Application {
    String pswd = "1234";
    String user = "rinsarmu";
    boolean numeric = false;
    boolean userFlag = false;
    boolean passFlag = false;
    boolean isSaved = false;
    String fileLocation = " ";
    String fileName = " ";
    String phoneNumber = "1212121212";
    Stage window;
    Scene scene1, scene2, scene3;

    @Override

    public void start(Stage stage) throws IOException {
        //===========[ Variable and scene declaration start=> ]===========
        Label errorPassword = new Label();
        Label errorUserName = new Label();
        TextArea userText = new TextArea();
        PasswordField passText = new PasswordField();
        TextArea editText = new TextArea();
        Button login = new Button("Login");
        Button exit = new Button("Exit");
        Button cancel = new Button("Cancel");
        VBox layout1 = new VBox(10);
        VBox buttons = new VBox(20);
        VBox boxFields = new VBox(25);

//===========[ Variable and scene declaration <= end ]===========
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}