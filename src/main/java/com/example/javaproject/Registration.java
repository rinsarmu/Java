package com.example.javaproject;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class Registration extends Application {
    FileChooser fileChooser;
    Button btn;
    String fileLocation = " ";
    static boolean  errorId = false; //set for error in textid
    boolean decimalFlag = false; //handle occurence of more than 2 decimal point
    boolean gradeFlag = true;
    Stage window;
    static String [] listId = new String[8];
    static  double checkGrade = 0;
    boolean alertId = true;

    public void start(Stage stage) throws IOException {

        Label id = new Label("ID: ");
        Label name = new Label("Name: ");
        name.setPrefWidth(100d);
        Label grade = new Label("Grade: ");

        TextArea idArea = new TextArea();
        TextArea nameArea = new TextArea();
        TextArea gradeArea = new TextArea();

        Button cancel = new Button("Cancel");
        Button save = new Button("Save");
        Button view = new Button("view");

        HBox hbId = new HBox(id, idArea);
        hbId.setPrefWidth(400);
        HBox hbName = new HBox(name, nameArea);
        hbName.setPrefWidth(600);

        HBox hbGrade = new HBox(grade, gradeArea);

        HBox buttons = new HBox(cancel, save, view);
        HBox hbTitle = new HBox(new Text("Student Registration System"));
        hbId.setPrefHeight(10d);
        hbGrade.setPrefHeight(38d);
        hbName.setPrefHeight(38d);
        id.setPrefWidth(80d);

        //styling one by one
        idArea.setPrefWidth(300d);
        idArea.setPrefHeight(10d);
        id.setPrefHeight(38d);
        id.setPrefWidth(60d);
        id.setAlignment(Pos.CENTER);
        id.setPadding(new Insets(10d));

        idArea.setOnKeyTyped(e->{
            String string = idArea.getText();
//            if(string.length() > 0)              errorId = false;

            if(string.matches("^\\d+$")){
                idArea.setStyle("-fx-text-inner-color: black; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: #dddfe2;");

            } else {
                idArea.setStyle("-fx-text-inner-color: red; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: red;");
                errorId = true;
            }

        });
        //styling one by one
        gradeArea.setPrefWidth(300d);
        gradeArea.setPrefHeight(10d);
        grade.setPrefHeight(38d);
        grade.setPrefWidth(60d);

        grade.setAlignment(Pos.CENTER);
        grade.setPadding(new Insets(10d));

        //styling one by one
        nameArea.setPrefWidth(300d);
        nameArea.setPrefHeight(10d);
        name.setPrefHeight(38d);
        name.setPrefWidth(60d);

        name.setAlignment(Pos.CENTER);
        name.setPadding(new Insets(10d));
        //style one by one
        cancel.setPadding(new Insets(10d));
        cancel.setStyle("-fx-border: none; -fx-background-color: #ff5722; -fx-border-radius: 12px; -fx-color: white;");

        //style one by one

        hbTitle.setAlignment(Pos.CENTER);
//        VBox vb = new VBox(hbId, hbName,hbGrade, buttons);
        VBox vb = new VBox( hbTitle,hbId,hbName, hbGrade, buttons);
        vb.setSpacing(15d);
        vb.setPadding(new Insets(10d));
        vb.setPrefWidth(100d);
        vb.setAlignment(Pos.CENTER);

        save.setOnAction(e ->{
            String idEntry = idArea.getText();
            String nameEntry = nameArea.getText();
            String gradeEntry = gradeArea.getText();
            String sampleText =  idEntry +","  + nameEntry +"," + gradeEntry;
            saveDB();

        });

        Scene scene = new Scene(vb, 400d, 500d);
//scene.getStylesheets().add(HelloApplication.class.getResource("style.css ").toExternalForm());
        scene.getStylesheets().add("HelloApplication/style.css");
        stage.setTitle("Student registration");
        stage.setScene(scene);
        stage.show();
    }

    private void saveDB() {
        System.out.println("Db functionality....");
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Registration","root", "root@123");
            System.out.println("connection....");

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "INSERT INTO student(name,gpa) Values(?,?)";
           PreparedStatement preparedStatement = con.prepareStatement(query);
           preparedStatement.setString(1, "Sagni alemayo");
            preparedStatement.setString(2, "3.4");
            preparedStatement.executeUpdate();

            con.close();
//            Connection con = new Driver
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();

    }


}