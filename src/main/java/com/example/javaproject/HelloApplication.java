package com.example.javaproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    boolean numeric = false,errorId = false,
            alertId = true,userFlag = false,
            passFlag = false,isSaved = false,
            isLoggedIn = false,
            gradeFlag = true,
            decimalFlag = false;

    String initialDirect = "/home/kuusaa/Documents",loggedType = "notepad",fileName = " ",fileLocation = " ",phoneNumber = "1212121212";
    Stage window;
    Scene scene1, scene2, scene3, scene,sceneStudent;
    static Label errorPassword,errorUserName,searchedName, searchedGpa,id,name,grade;;
    static TextArea userText,editText,idArea, nameArea, gradeArea, searchArea;;
    static PasswordField passText;
    static  Button login,exit, cancel,btn,searchBtn, nextBtn,prevBtn, cancelSave,saveStudent,viewStudent;
    static  VBox  layout1, layout2, buttons,boxFields,vb;
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

    static String [] listId = new String[8];
    static  double checkGrade = 0;
    int searchedId,currentId = 0;
    @Override

    public void start(Stage stage) throws IOException {
//        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
        variableDeclaration(stage);
        addingChildrenToParent();
        uIDesign();

//===========[ Action start => ]===========

        notePadView.setOnMouseClicked(e ->{
            if (isLoggedIn) {
                window.setScene(scene2);
                window.setTitle("Notepad Page");

            } else {
                loggedType = "notepad";
                window.setScene(scene1);

            }
            System.out.println("Image");

        });

        registerView.setOnMouseClicked(e->{
            if (isLoggedIn) {
                                window.setScene(scene2);
                startRegistration(window);
                System.out.println("jd");

            } else {
                loggedType = "register";
                window.setScene(scene1);
            }
        });

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
            authentication();
        });

        //Leave the System
        exit.setOnAction(e -> {
            System.exit(0);
        });

        // Open Action
        open.setOnAction(e->{
            openFromFile();
        });

        //        Save Action
        item2.setOnAction(e->{
            saveToFile();

        });

        //Save As Action
        item3.setOnAction(e->{
            saveAsFile();
        });

        //Exit Action
        item4.setOnAction(e -> {
            System.exit(0);

        });

        //Registration action

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

//===========[ Action END <= ]===========

    }

    private void variableDeclaration(Stage stage) throws FileNotFoundException {
        //===========[ Variable and scene declaration start=> ]===========.
        errorPassword = new Label();
        errorUserName = new Label();
        userText = new TextArea();
        passText = new PasswordField();
        editText = new TextArea();
        login = new Button("Login");
        exit = new Button("Exit");
        cancel = new Button("Cancel");
        layout1 = new VBox(10);
        buttons = new VBox(20);
        boxFields = new VBox(25);
        layout2 = new VBox(25);

        // --- Menu Item
        undo = new MenuItem("Open");
        cut = new MenuItem("Save");
        copy = new MenuItem("Save as");
        paste = new MenuItem("Exit");
        delete = new MenuItem("Exit");
        find = new MenuItem("Exit");
        findNext = new MenuItem("Exit");
        findPrevious = new MenuItem("Exit");
        replace = new MenuItem("Exit");

        // --- EDIT Item

        open = new MenuItem("Open");
        item2 = new MenuItem("Save");
        item3 = new MenuItem("Save as");
        item4 = new MenuItem("Exit");
        menuBar = new MenuBar();

        // --- Menu File
        menuFile = new Menu("File");
        menuEdit = new Menu("Edit");
        menuView = new Menu("View");
        fileChooser = new FileChooser();
        //Folder Icon
        folder = new FileInputStream("/home/kuusaa/Pictures/image/folder.png");
        folderImage = new Image(folder);
        imageView = new ImageView();

        //Notepad Icon
        notePad = new FileInputStream("/home/kuusaa/Pictures/image/notepad.jpg");
        notePadImage = new Image(notePad);
        notePadView = new ImageView();

        //Registration Icon
        register = new FileInputStream("/home/kuusaa/Pictures/image/register.jpg");
        registerImage = new Image(register);
        registerView = new ImageView();

        root = new Group();

        //Scene
        scene = new Scene(root, 595, 370);
        scene2 = new Scene(layout2, 400, 500);
        scene1 = new Scene(boxFields, 400, 400);
        window = stage;

        dropShadow = new DropShadow();


        //From Registrtion
        //search
        searchBtn = new Button("Search");
        prevBtn = new Button("< prev");
        nextBtn = new Button("Next >");

        searchArea = new TextArea();
        searchedGpa = new Label();
        searchedName =  new Label();
        id = new Label("ID: ");
        name = new Label("Name: ");
        grade = new Label("Grade: ");

        idArea = new TextArea();
        nameArea = new TextArea();
        gradeArea = new TextArea();
        cancelSave = new Button("Cancel");
        saveStudent = new Button("Save");
        viewStudent = new Button("view");

        hbId = new HBox(id, idArea);
        hbName = new HBox(name, nameArea);
        hbGrade = new HBox(grade, gradeArea);
        buttonStudent = new HBox(cancelSave, saveStudent, viewStudent);
        hbTitle = new HBox(new Text("Student Registration System"));
         vb = new VBox( hbTitle,hbName, hbGrade, buttonStudent);
        sceneStudent = new Scene(vb, 400d, 500d);
//===========[ Variable and scene declaration <= end ]===========


    }
    private void addingChildrenToParent() {
        //===========[ Adding childrens to the parent & scene window start => ]===========

        buttons.getChildren().addAll(login, exit);
        layout1.getChildren().addAll(errorUserName, userText, errorPassword, passText);
        boxFields.getChildren().addAll(layout1, buttons);
        menuFile.getItems().addAll(open, item2, item3, item4);
        menuEdit.getItems().addAll(undo,cut,copy,paste,delete);
        menuBar.getMenus().addAll(menuFile,menuEdit, menuView);
        layout2.getChildren().addAll(menuBar, editText);

        root.getChildren().addAll(imageView, notePadView, registerView);

        window.setScene(scene);
        window.setTitle("Login Page");
        window.show();

//===========[  Adding childrens to the parent <= end ]===========

    }
    private void uIDesign() {
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

        //Icons
        imageView.setImage(folderImage);
        imageView.setX(50);
        imageView.setY(70);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        notePadView.setImage(notePadImage);
        notePadView.setX(170);
        notePadView.setY(85);
        notePadView.setFitWidth(80);
        notePadView.setPreserveRatio(true);

        registerView.setImage(registerImage);
        registerView.setX(300);
        registerView.setY(90);
        registerView.setFitWidth(60);
        registerView.setPreserveRatio(true);

        //Registration
        hbId.setPrefWidth(400);
        hbName.setPrefWidth(600);
        name.setPrefWidth(100d);


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

        vb.setSpacing(15d);
        vb.setPadding(new Insets(10d));
        vb.setPrefWidth(100d);
        vb.setAlignment(Pos.CENTER);
//===========[ UI to the child and stylize top to bottom <=end ]===========


    }
    private void authentication() {
        String check = userText.getText();
        numeric = check.matches("-?\\d+(\\.\\d+)?");
        System.out.println(numeric);

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
            switch(loggedType){
                case "notepad" :
                    window.setScene(scene2);
                    window.setTitle("Untitle - Notepad");
                    break;
                case  "register" :
                    startRegistration(window);
//
                    break;

                case "folder" :
                    window.setScene(scene2);
                    window.setTitle("Untitle - Notepad");
                    break;

                default:
                    window.setScene(scene2);
                    window.setTitle("Untitle - Notepad");

            }

        } else {
            System.out.println("UserName doesnot exist");
        }
    }
    private void openFromFile() {
        // File system;
        fileChooser.setTitle("Open");
        fileChooser.setInitialDirectory(new File(initialDirect));
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
                editText.appendText(line + "\n");
            }

        }
        System.out.println("Saved");

    }
    private void saveToFile() {
        fileChooser.setTitle("Save");
        fileChooser.setInitialDirectory(new File(initialDirect));
        String sampleText = editText.getText();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        System.out.println("Location"+ fileLocation);
        File file;

        if (fileLocation.isBlank()){
            System.out.println("not saved beore");
            file = fileChooser.showSaveDialog(window);
//                if(!file.exists()){
//                    return;
//                }

            //TO save the file with .txt extension if it isnot exist with it.
            if(!file.getName().contains(".")) {
                fileLocation = file.getAbsolutePath();

                file = new File(fileLocation + ".txt");
            }

            System.out.println(file.getAbsoluteFile());
            fileLocation = file.getAbsolutePath();
            if(file != null){
                try {
                    saveTextToFile(sampleText, file);
                } catch (FileNotFoundException ex) {
                    System.out.println("line 386");;
                }
            }
        } else {
            file = new File(fileLocation);
            System.out.println("Not empty");
            window.setTitle(fileName + " Notepad");
            try {
                saveTextToFile(sampleText, file);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        }

    }
    private void saveAsFile() {
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
                System.out.println("line 420");
            }
        }


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

    private void startRegistration(Stage stage){
        stage.setTitle("Student registration");
        stage.setScene(sceneStudent);
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
        LoginDb();
        launch();
    }
}