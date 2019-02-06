package edu.wpi.cs3733c19.teamI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class LoginController {
    private Scene formCheckerScene;
    private FormCheckerController formCheck;

    public void setFormCheck(FormCheckerController formCheck) {
        this.formCheck = formCheck;
    }

    //public LoginController formCheckerController;

    private Scene Home;

    public void setHomeScene(Scene home)
    {
        this.Home = home;
    }
    
    @FXML
    TextField username;

    @FXML
    Button goToHome;

    @FXML
    TextField password;

    @FXML
    Button login;

    @FXML
    Label errorMessage;

    FileReader userLogin;

    public void attemptLogin(ActionEvent actionEvent) throws Exception { //attempts a login and will either create an account or login
        String users = "";
        users = readFile(users);
        //System.out.println(users);
        if(username.getText().isEmpty()){
            errorMessage.setText(("Must use a username"));
        }
        else if (users.contains(username.getText() + ":" + password.getText()+":")){ //this file checks for the user and pass in the file
            System.out.println("logging in");
            login(actionEvent);  //if they exist, login
        }
        else if (users.contains(username.getText()+":none:") && (password.getText().isEmpty())){
            login(actionEvent);
        }
        else if (users.contains(username.getText())){
            errorMessage.setText("Username already taken!");
        }
        else {
            createAccount(actionEvent); //otherwise make them an account
        }
        System.out.println("About to open display scene");

    }

    public String getName()
    {
        return(this.username.getText());
    }

    public String readFile(String users) throws Exception { //this file will populate users with a string of all users
        // pass the path to the file as a parameter
        userLogin = new FileReader("UserSheet.txt");
        int i;
        while ((i=userLogin.read()) != -1) {
            users = users + ((char) i);
        }
        //System.out.println(users);
        return users;
    }

    public void login(ActionEvent actionEvent){ //logs the user in and moves them to the check forms scene
        openDisplayScene(actionEvent);
    }

    public void createAccount(ActionEvent actionEvent) throws Exception {  //creates an account, then logs the user in and moves them to the checks form scene
        FileWriter userWriter = new FileWriter("UserSheet.txt", true);
        BufferedWriter outputStream = new BufferedWriter(userWriter);
        String addUser = "\n" + username.getText();
        //System.out.println(password.getText());
        if(!password.getText().isEmpty()){
            addUser = addUser+":"+password.getText()+":";
        }
        else{
            addUser = addUser + ":none:";
        }
        outputStream.write(addUser);
        //System.out.println(addUser);
        System.out.println("creating account");
        outputStream.flush();
        outputStream.close();
            //and then scene switcher code, wait for merge
        openDisplayScene(actionEvent);

    }

    public void setFormCheckerScene(Scene scene) {
        formCheckerScene = scene;
    }

    public void openDisplayScene(ActionEvent actionEvent) {
        System.out.println("made it into display scene");
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(formCheckerScene); // See the form checker page
        formCheck.setUserName(username.getText());
        System.out.println("Did eveything in display scene");
    }

    public void goHome(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Home);

    }

}
