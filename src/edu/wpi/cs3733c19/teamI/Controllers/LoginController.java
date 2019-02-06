package edu.wpi.cs3733c19.teamI.Controllers;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class LoginController {
    private Scene formCheckerScene;
    //public LoginController formCheckerController;
    
    @FXML
    TextField username;

    @FXML
    TextField password;

    @FXML
    Button login;

    FileReader userLogin;

    public void attemptLogin(ActionEvent actionEvent) throws Exception { //attempts a login and will either create an account or login
        String users = "";
        users = readFile(users);
        //System.out.println(users);
        if (users.contains(username.getText() + "\n")){ //this file checks for the user in the file
            System.out.println("logging in");
            login();  //if they exist, login
        }
        else {
            createAccount(); //otherwise make them an account
        }
        System.out.println("About to open display scene");
        openDisplayScene(actionEvent);

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

    public void login(){ //logs the user in and moves them to the check forms scene

        //scene switcher code, wait for merge

    }

    public void createAccount() throws Exception {  //creates an account, then logs the user in and moves them to the checks form scene
        FileWriter userWriter = new FileWriter("UserSheet.txt", true);
        BufferedWriter outputStream = new BufferedWriter(userWriter);
        String addUser = "\n" + username.getText() + "\n";
        outputStream.write(addUser);
        //System.out.println(addUser);
        System.out.println("creating account");
        outputStream.flush();
        outputStream.close();
            //and then scene switcher code, wait for merge
        //openDisplayScene();

    }

    public void setFormCheckerScene(Scene scene) {
        formCheckerScene = scene;
    }

    public void openDisplayScene(ActionEvent actionEvent) {
        System.out.println("made it into display scene");
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(formCheckerScene); // See the form checker page
        System.out.println("Did eveything in display scene");
    }
}
