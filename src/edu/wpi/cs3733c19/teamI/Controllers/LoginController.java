package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;

public class LoginController {
    private Scene formCheckerScene;
    private FormCheckerController formCheck;
    private Scene Home;
    private Scene Submission;


    @FXML
    TextField username;

    @FXML
    Button goToHome;

    @FXML
    ToggleGroup SignIn;

    @FXML
    RadioButton FormLogin;

    @FXML
    RadioButton TTBLogin;

    @FXML
    TextField password;

    @FXML
    Button login;

    @FXML
    Label errorMessage;


    FileReader userLogin;
    BufferedReader userReader;
    String invalidCharacters = ":!@#$%^&*()/.,><;-=_+";
    String userType = "@";
    boolean isValid = true;

    public void attemptLogin(ActionEvent actionEvent) throws Exception { //attempts a login and will either create an account or login
        String users = "";
        users = readFile(users);
        for (int i = 0; i < invalidCharacters.length(); i++) {
            Character currChar = invalidCharacters.charAt(i);
            if (username.getText().contains(currChar.toString()) || password.getText().contains(currChar.toString())) {
                errorMessage.setText("username or password contains illegal characters");
                isValid = false;
                System.out.println("illegal");
            }
        }
        if (isValid == false){

        }
        else if(username.getText().isEmpty()){
            errorMessage.setText(("Enter a username to login"));
            System.out.println("no user");
        }
        else if(username.getText().length() < 8){
            errorMessage.setText("Username too short");
            System.out.println("short");
        }
        else if(SignIn.getSelectedToggle()==null){
            errorMessage.setText(("Must select log in type"));
            System.out.println("log type");
        }
        else if (users.contains(":"+username.getText()+":"+password.getText()+":")){ //this file checks for the user and pass in the file
            System.out.println("logging in");
            login(actionEvent);  //if they exist, login
        }
        else if (users.contains(":"+username.getText()+":none:") && (password.getText().isEmpty())){
            login(actionEvent);
        }
        else if (users.contains(":"+username.getText()+":")){
            errorMessage.setText("Select an unused username");
        }
        else {
            createAccount(actionEvent); //otherwise make them an account
        }
    }

    public void setTTBLogin(){
        userType = "#";
    }

    public void setSubmitterLogin(){
        userType = "$";
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

    public void login(ActionEvent actionEvent){ //logs the user in and moves them to the check forms scene or submit scene
        User currentUser = User.getUser(username.getText(), password.getText(), User.userPower.Standard);


        if(this.FormLogin.isSelected()){
            goSubmission(actionEvent);
        }
        else if(this.TTBLogin.isSelected()) {
            openDisplayScene(actionEvent);
        }
    }

    public void createAccount(ActionEvent actionEvent) throws Exception {  //creates an account, then logs the user in and moves them to the checks form scene
        FileWriter userWriter = new FileWriter("UserSheet.txt", true);
        BufferedWriter outputStream = new BufferedWriter(userWriter);
        String addUser =  username.getText();
        if(!password.getText().isEmpty()){
            addUser = ":"+addUser+":"+password.getText()+":"+ userType+":";
        }
        else{
            addUser = ":"+addUser + ":none:"+ userType+":";
        }
        outputStream.write("\n"+ addUser);
        outputStream.flush();
        outputStream.close();
            //and then scene switcher code, wait for merge
        openDisplayScene(actionEvent);

    }

    //Setters
    public void setFormCheckerScene(Scene scene) {
        this.formCheckerScene = scene;
    }

    public void setSubmission(Scene submission) { this.Submission = submission; }

    public void setHomeScene(Scene home){ this.Home = home;}

    public void setFormCheck(FormCheckerController formCheck) {
        this.formCheck = formCheck;
    }


    public void openDisplayScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(formCheckerScene); // See the form checker page
        formCheck.setUserName(username.getText());
    }

    public void goHome(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Home);
    }

    //function displays the submission page
    public void goSubmission(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow(); //get current stage
        primaryStage.setScene(Submission); //display homepage
    }

}
