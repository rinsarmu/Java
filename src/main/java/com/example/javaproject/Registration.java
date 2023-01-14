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
        //search
        searchBtn = new Button("Search");
        prevBtn = new Button("< prev");
        nextBtn = new Button("Next >");

        searchArea = new TextArea();
        searchedGpa = new Label();
        searchedName =  new Label();


         id = new Label("ID: ");
         name = new Label("Name: ");
        name.setPrefWidth(100d);
         grade = new Label("Grade: ");

         idArea = new TextArea();
         nameArea = new TextArea();
         gradeArea = new TextArea();
         cancelSave = new Button("Cancel");
         saveStudent = new Button("Save");
         viewStudent = new Button("view");

         hbId = new HBox(id, idArea);
        hbId.setPrefWidth(400);
         hbName = new HBox(name, nameArea);
        hbName.setPrefWidth(600);

         hbGrade = new HBox(grade, gradeArea);

         buttonStudent = new HBox(cancelSave, saveStudent, viewStudent);
         hbTitle = new HBox(new Text("Student Registration System"));
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
        cancelSave.setPadding(new Insets(10d));
        cancelSave.setStyle("-fx-border: none; -fx-background-color: #ff5722; -fx-border-radius: 12px; -fx-color: white;");

        //style one by one

        hbTitle.setAlignment(Pos.CENTER);
//        VBox vb = new VBox(hbId, hbName,hbGrade, buttons);
        VBox vb = new VBox( hbTitle,hbName, hbGrade, buttonStudent);
        vb.setSpacing(15d);
        vb.setPadding(new Insets(10d));
        vb.setPrefWidth(100d);
        vb.setAlignment(Pos.CENTER);

        saveStudent.setOnAction(e ->{
            String idEntry = idArea.getText();
            String nameEntry = nameArea.getText();
            String gradeEntry = gradeArea.getText();
            String sampleText =  idEntry +","  + nameEntry +"," + gradeEntry;
            saveDB();

        });

        viewStudent.setOnAction(e->{
            viewPage(stage);

        });
        searchBtn.setOnAction(e->{
            System.out.println("search btn");
            System.out.println(searchArea.getText());
            searchedId = parseId(searchArea.getText());
            showData();
        });
        nextBtn.setOnAction(e->{
            searchedId ++;
            showData();


        });

        prevBtn.setOnAction(e->{
            searchedId--;
            showData();


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
           preparedStatement.setString(1, nameArea.getText());
            preparedStatement.setString(2, gradeArea.getText());
            preparedStatement.executeUpdate();

            con.close();
//            Connection con = new Driver
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewPage(Stage stage){

        VBox vboxSearch = new VBox(searchArea, searchBtn, searchedName, searchedGpa, nextBtn,prevBtn);

        Scene scene = new Scene(vboxSearch, 400,400);
        stage.setScene(scene);
        stage.show();

    }

    private void showData(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Registration","root", "root@123");
            System.out.println("connection....");

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM student where id ="+searchedId;
            ResultSet resultSet = statement.executeQuery(query);
            String name = "";
            String gpa = "";
            while(resultSet.next()){
                name = resultSet.getString("name");
                gpa = resultSet.getString("gpa");
                searchedName.setText("Name : " +name);
                searchedGpa.setText("GPA : " + gpa);
            }
            System.out.println("gpa :" + gpa);
            System.out.println("Name :" + name);
            con.close();
//            Connection con = new Driver
        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("ID not found");
        }

    }

    private Integer parseId(String id){
        return Integer.parseInt(String.valueOf(id));
    }

    public static void main(String[] args) {
        launch();

    }


}