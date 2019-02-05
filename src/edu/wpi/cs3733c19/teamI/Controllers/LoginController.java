package edu.wpi.cs3733c19.teamI.Controllers;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.*;

public class LoginController {

    @FXML
    TextField username;

    @FXML
    TextField password;

    @FXML
    Button login;

    FileReader userLogin;

    public void attemptLogin() throws Exception { //attempts a login and will either create an account or login
        String users = "";
        users = readFile(users);
        if (users.contains(username.toString() + " ")){ //this file checks for the user in the file
            login();  //if they exist, login
        }
        else {
            createAccount(users); //otherwise make them an account
        }
    }

    public String readFile(String users) throws Exception { //this file will populate users with a string of all users
        // pass the path to the file as a parameter
        userLogin = new FileReader("../../UserSheet.txt");
        int i;
        while ((i=userLogin.read()) != -1) {
            users = users + ((char) i);
        }
        return users;
    }

    public void login(){ //logs the user in and moves them to the check forms scene

        //scene switcher code, wait for merge

    }

    public void createAccount(String users) throws Exception{  //creates an account, then logs the user in and moves them to the checks form scene
        FileWriter userWriter = new FileWriter("../../UserSheet.txt");
        userWriter.write(username.toString());

        //and then scene switcher code, wait for merge
    }
}
