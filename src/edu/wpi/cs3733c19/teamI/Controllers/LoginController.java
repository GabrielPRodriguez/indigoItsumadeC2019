package edu.wpi.cs3733c19.teamI.Controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.ToolBarController;
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
    private Scene FormSub;

    public void setFormCheck(FormCheckerController formCheck) {
        this.formCheck = formCheck;
    }

    private Scene Home;

    public void setHomeScene(Scene home)
    {
        this.Home = home;
    }
    public void setFormSub(Scene formSub){
        this.FormSub = formSub;
    }
    
    @FXML
    TextField username;

    @FXML
    JFXButton goToHome;

    @FXML
    TextField password;

    @FXML
    Button login;

    @FXML
    Label errorMessage;

    FileReader userLogin;
    BufferedReader userReader;

    public void attemptLogin(ActionEvent actionEvent) throws Exception { //attempts a login and will either create an account or login
        String users = "";
        users = readFile(users);
        if(username.getText().isEmpty()){
            errorMessage.setText(("Must use a username"));
        }
        else if (users.contains(":"+username.getText()+":"+password.getText()+":")){ //this file checks for the user and pass in the file
            System.out.println("logging in");
            login(actionEvent);  //if they exist, login
        }
        else if (users.contains(":"+username.getText()+":none:") && (password.getText().isEmpty())){
            login(actionEvent);
        }
        else if (users.contains(":"+username.getText()+":")){
            errorMessage.setText("Username already taken!");
        }
        else {
            createAccount(actionEvent); //otherwise make them an account
        }
    }

    public String getName()
    {
        return(this.username.getText());
    }

    public String readFile(String users) throws Exception { //this file will populate users with a string of all users
        // pass the path to the file as a parameter
        //userLogin = new FileReader("UserSheet.txt");
        userLogin = new FileReader("UserSheet.txt");
        int i;
        while ((i=userLogin.read()) != -1) {
            users = users + ((char) i);
        }
        return users;
    }

    public void login(ActionEvent actionEvent){ //logs the user in and moves them to the check forms scene
        openDisplayScene(actionEvent);
    }

    public void createAccount(ActionEvent actionEvent) throws Exception {  //creates an account, then logs the user in and moves them to the checks form scene
        FileWriter userWriter = new FileWriter("UserSheet.txt", true);
        BufferedWriter outputStream = new BufferedWriter(userWriter);
        String addUser =  username.getText();
        if(!password.getText().isEmpty()){
            addUser = ":"+addUser+":"+password.getText()+":";
        }
        else{
            addUser = ":"+addUser + ":none:";
        }
        outputStream.write("\n"+ addUser);
        outputStream.flush();
        outputStream.close();
            //and then scene switcher code, wait for merge
        openDisplayScene(actionEvent);

    }

    public void setFormCheckerScene(Scene scene) {
        formCheckerScene = scene;
    }

    public void openDisplayScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(formCheckerScene); // See the form checker page
        formCheck.setUserName(username.getText());
    }

    @FXML
    public void goHome(ActionEvent actionEvent){
        System.out.println("going home");
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Home);
    }

    @FXML
    public void goFormSub(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(FormSub);
    }

}
