package com.example.javaproject;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class Registration extends Application {

    String initialDirect = "/home/kuusaa/Documents";
    String loggedType = "notepad";
    String fileLocation = " ";
    String fileName = " ";
    String phoneNumber = "1212121212";
    Stage window;
    Scene scene1, scene2, scene3, scene;
    static Label errorPassword,errorUserName,searchedName, searchedGpa,id,name,grade;;
    static TextArea userText,editText,idArea, nameArea, gradeArea, searchArea;;
    static PasswordField passText;
    static  Button login,exit, cancel,btn,searchBtn, nextBtn,prevBtn, cancelSave,saveStudent,viewStudent;
    static  VBox  layout1, layout2, buttons,boxFields;
    static MenuItem undo,cut,copy,paste,delete,find, findNext,findPrevious,replace,open,item2,item3,item4;
    MenuBar menuBar;
    static Menu menuFile,menuEdit,menuView;
    static FileChooser fileChooser;
    static InputStream folder,notePad,register;
    static Image folderImage,notePadImage,registerImage;
    static ImageView imageView,notePadView,registerView;
    static Group root;
    static DropShadow dropShadow;
    static HBox  hbId,hbGrade,hbTitle,buttonStudent,hbName;


    static boolean  errorId = false; //set for error in textid
    boolean decimalFlag = false; //handle occurence of more than 2 decimal point
    boolean gradeFlag = true;

    static String [] listId = new String[8];
    static  double checkGrade = 0;
    boolean alertId = true;

    int searchedId;
    int currentId = 0;


    public void start(Stage stage) throws IOException {



//scene.getStylesheets().add(HelloApplication.class.getResource("style.css ").toExternalForm());
//       Scene sceneStudent.getStylesheets().add("HelloApplication/style.css");
////        sdds
//        stage.setTitle("Student registration");
//        stage.setScene(sceneStudent);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }


}