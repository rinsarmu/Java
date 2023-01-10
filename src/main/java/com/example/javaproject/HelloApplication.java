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
        VBox layout2 = new VBox(25);

        // --- Menu Item
        MenuItem undo = new MenuItem("Open");
        MenuItem cut = new MenuItem("Save");
        MenuItem copy = new MenuItem("Save as");
        MenuItem paste = new MenuItem("Exit");
        MenuItem delete = new MenuItem("Exit");
        MenuItem find = new MenuItem("Exit");
        MenuItem findNext = new MenuItem("Exit");
        MenuItem findPrevious = new MenuItem("Exit");
        MenuItem replace = new MenuItem("Exit");

        // --- EDIT Item
        MenuItem item1 = new MenuItem("Open");
        MenuItem item2 = new MenuItem("Save");
        MenuItem item3 = new MenuItem("Save as");
        MenuItem item4 = new MenuItem("Exit");
        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
        FileChooser fileChooser = new FileChooser();

        //Scene
        scene2 = new Scene(layout2, 400, 500);
        scene1 = new Scene(boxFields, 400, 400);
        window = stage;

        DropShadow dropShadow = new DropShadow();

//===========[ Variable and scene declaration <= end ]===========

        //===========[ Variable and scene declaration <= end ]===========


//===========[ Adding childrens to the parent & scene window start => ]===========

        buttons.getChildren().addAll(login, exit);
        layout1.getChildren().addAll(errorUserName, userText, errorPassword, passText);
        boxFields.getChildren().addAll(layout1, buttons);
        menuFile.getItems().addAll(item1, item2, item3, item4);
        menuEdit.getItems().addAll(undo,cut,copy,paste,delete);
        menuBar.getMenus().addAll(menuFile,menuEdit, menuView);
        layout2.getChildren().addAll(menuBar, editText);

        window.setScene(scene2);
        window.setTitle("Login Page");
        window.show();

//===========[  Adding childrens to the parent <= end ]===========

    }

    public static void main(String[] args) {
        launch();
    }
}