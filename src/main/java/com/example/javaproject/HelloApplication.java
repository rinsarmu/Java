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
import java.sql.*;
import java.util.EventListener;

public class HelloApplication extends Application {
   static String pswd = "";
    static String user = "";
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
        MenuItem open = new MenuItem("Open");
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

//===========[ Adding childrens to the parent & scene window start => ]===========

        buttons.getChildren().addAll(login, exit);
        layout1.getChildren().addAll(errorUserName, userText, errorPassword, passText);
        boxFields.getChildren().addAll(layout1, buttons);
        menuFile.getItems().addAll(open, item2, item3, item4);
        menuEdit.getItems().addAll(undo,cut,copy,paste,delete);
        menuBar.getMenus().addAll(menuFile,menuEdit, menuView);
        layout2.getChildren().addAll(menuBar, editText);

        window.setScene(scene1);
        window.setTitle("Login Page");
        window.show();

//===========[  Adding childrens to the parent <= end ]===========

// ===========[ UI to the child and stylize top to bottom => ]===========
        errorUserName.setTextFill(Color.rgb(255, 0, 0));

        errorPassword.setTextFill(Color.rgb(255, 0, 0));
        errorPassword.setStyle("-fx-display: none");

        userText.setPrefHeight(45d);
        userText.setFocusTraversable(false);
        userText.setStyle("   -fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: #dddfe2; -fx-font-size: 14px ");
        userText.setPromptText("Email or Phone Number");
        userText.setPrefWidth(364d);

        passText.setPrefHeight(45d);
        passText.setPromptText("Your Password");
        passText.setPrefWidth(364d);
        passText.setStyle("   -fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: #dddfe2  ");
        passText.setFocusTraversable(false);

        editText.setStyle("-fx-background-color: #fff; -fx-border-color: #fff; -fx-border-width: 0; -fx-border-image-width: 0; -fx-background-image: null; -fx-region-background: null;-fx-border-insets: 0; -fx-background-size:0; -fx-border-image-insets:0;");
        editText.setPrefHeight(500d);

        login.setPrefWidth(364d);
        login.setPrefHeight(38d);
        login.setStyle(" -fx-border-color: none;-fx-border-radius: 20px; -fx-background-color: #1877f2;  -fx-color: red; -fx-font-weight: bold; -fx-font-size: 20px");

        exit.setPrefWidth(380d);
        exit.setPrefHeight(38d);
        exit.setStyle(" -fx-border-color: none;-fx-border-radius: 20px; -fx-background-color: #FF9966;  -fx-color: red; -fx-font-weight: bold; -fx-font-size: 20px");

        layout1.setPadding(new Insets(10d));
        boxFields.setPadding(new Insets(10d, 20d, 10d, 20d));
//===========[ UI to the child and stylize top to bottom <= ]===========

//===========[ Action start => ]===========


        login.setOnMouseEntered(e->{
            login.setEffect(dropShadow);
        });
        login.setOnMouseExited(e->{
            login.setEffect(null);;
        });

        exit.setOnMouseEntered(e->{
            exit.setEffect(dropShadow);
        });
        exit.setOnMouseExited(e->{
            exit.setEffect(null);;
        });

        userText.setOnMouseEntered(e->{
            userText.setEffect(dropShadow);
        });
        userText.setOnMouseExited(e->{
            userText.setEffect(null);;
        });

        passText.setOnMouseEntered(e->{
            passText.setEffect(dropShadow);
        });
        passText.setOnMouseExited(e->{
            passText.setEffect(null);;
        });

        //Authentication
        login.setOnAction(e -> {
            String check = userText.getText();
            numeric = check.matches("-?\\d+(\\.\\d+)?");
            System.out.println(numeric);
            if (numeric) {
                if (phoneNumber.equals(check)) {
                    userText.setStyle("-fx-border-color: #dddfe2;");
                    errorUserName.setText("");
                    userFlag = true;
                } else {
                    System.out.println("Incorrect.");
                    errorUserName.setText("Error in phone number");
                    window.setScene(scene1);
                    userFlag = false;
                    userText.setStyle("-fx-border-color: #red;");

                }
            } else {
                if (user.equals(check)) {
                    errorUserName.setText("");
                    userFlag = true;
                    userText.setStyle("-fx-border-color: #dddfe2;");

                } else {
                    System.out.println("Incorrect.");
                    errorUserName.setText("Error in username");
                    window.setScene(scene1);
                    userFlag = false;
                    userText.setStyle("-fx-border-color: red;");

                }
            }

            if (pswd.equals(passText.getText())) {
                errorPassword.setText("");
                passFlag = true;
                passText.setStyle("-fx-border-color: #dddfe2;");

            } else {
                System.out.println("Incorrect.");
                errorPassword.setText("Error in password");
                window.setScene(scene1);
                passFlag = false;
                passText.setStyle("-fx-border-color: red;");

            }
            if (passFlag && userFlag) {
                System.out.println("Logged in");
                System.out.println(check);
                window.setScene(scene2);
                window.setTitle("Untitled - Notepad");
            } else {
                System.out.println("UserName doesnot exist");
            }
        });

        //Leave the System
        exit.setOnAction(e -> {
            System.exit(0);
        });

        // Open Action
        open.setOnAction(e->{

            // File system;
            fileChooser.setTitle("Open Resource FIle");
            if(fileLocation.isBlank()){
                fileChooser.setInitialDirectory(new File("/home/kuusaa/Documents"));

            } else {
                fileChooser.setInitialDirectory(new File(fileLocation));

            }
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("pdf Files", "*.pdf")
            );

            File selectedFile = fileChooser.showOpenDialog(window);
            String line;

            if (selectedFile != null) {
                fileName = selectedFile.getName();
                fileLocation = selectedFile.getAbsolutePath();
                window.setTitle(fileName + " - Notepad");
                editText.setText(" ");
                System.out.println(selectedFile.getAbsoluteFile());
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(selectedFile));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                while (true) {
                    try {
                        if (!((line = reader.readLine()) != null)) break;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println(line);
                    //                editText.setText((line));
                    editText.appendText(line);
                }

            }
            System.out.println("Saved");

        });


        //        Save Action
        item2.setOnAction(e->{
            String sampleText = editText.getText();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            System.out.println("Location"+ fileLocation);
            File file;

            if (fileLocation.isBlank()){
                System.out.println("not saved beore");
                file = fileChooser.showSaveDialog(window);
                System.out.println(file.getAbsoluteFile());
                fileLocation = file.getAbsolutePath();
                if(file != null){
                    try {
                        saveTextToFile(sampleText, file);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                file = new File(fileLocation);
                System.out.println("Not empty");
                try {
                    saveTextToFile(sampleText, file);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


            }

        });

        //Save As Action
        item3.setOnAction(e->{
            String sampleText = editText.getText();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            System.out.println("Loca"+ fileLocation);
            File  file = fileChooser.showSaveDialog(window);
            System.out.println("save as");


            if(file != null){
                try {
                    fileLocation = file.getAbsolutePath();
                    saveTextToFile(sampleText, file);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });

        //Exit Action
        item4.setOnAction(e -> {
            System.exit(0);

        });


//===========[ Action END <= ]===========




    }

    private void saveTextToFile(String sampleText, File file) throws FileNotFoundException {
        System.out.println("sample :" + sampleText);
        PrintWriter writer = new PrintWriter(file);
        writer.println(sampleText);
        writer.close();
    }

    private static void LoginDb(){
        System.out.println("Db functionality....");
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Registration","root", "root@123");
            System.out.println("connection....");

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM login";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                user = resultSet.getString("username");
                pswd = resultSet.getString("password");
            }
            con.close();
//            Connection con = new Driver
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        LoginDb();
        System.out.println("user"+ user);
        System.out.println("password"+ pswd);
        launch();
    }
}