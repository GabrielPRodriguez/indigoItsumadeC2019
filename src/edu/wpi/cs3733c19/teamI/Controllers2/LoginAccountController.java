package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;


public class LoginAccountController implements Initializable {

    @FXML
    JFXTextField Email;

    @FXML
    JFXPasswordField Password;

    @FXML
    JFXRadioButton Agent;

    @FXML
    JFXRadioButton Manufacturer;

    @FXML
    JFXTextField EmailCreate;

    @FXML
    JFXPasswordField PasswordCreate;

    @FXML
    JFXPasswordField PasswordCreateCheck;

    @FXML
    JFXTabPane primaryPane;

    private ToolBarController toolBarController;


    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }


    @FXML
    public void goHome(ActionEvent actionEvent){ toolBarController.goHome(actionEvent); }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        toolBarController.goSubmit(actionEvent);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        toolBarController.goLogin(actionEvent);
    }

    @FXML
    public void goWorkflow(ActionEvent actionEvent){toolBarController.goWorkflow(actionEvent);}

    @FXML
    public void goAbout(ActionEvent actionEvent){toolBarController.goAbout(actionEvent);}

    @FXML
    public void login(ActionEvent actionEvent) throws Exception {
        attemptLogin(actionEvent);
        User curUser = User.getUser(Email.getText(), Password.getText(), User.userPower.Standard);
        //check if their info is valid
        toolBarController.login(actionEvent); //doesnt work
    }

    @FXML
    public void logInCreate(ActionEvent actionEvent) throws Exception {
        //make account if possible
        attemptLogin(actionEvent);
        User curUser = User.getUser(Email.getText(), Password.getText(), User.userPower.Standard);
        toolBarController.login((actionEvent));
    }

    String invalidCharacters = ":!#$%^&*()/,><;-=_+";
    boolean isValid = true;

    public String readFile(String users) throws Exception { //this file will populate users with a string of all users
        // pass the path to the file as a parameter
        //userLogin = new FileReader("UserSheet.txt");
        FileReader userLogin;
        userLogin = new FileReader("UserSheet.txt");
        int i;
        while ((i=userLogin.read()) != -1) {
            users = users + ((char) i);
        }
        return users;
    }

    public void attemptLogin(ActionEvent actionEvent) throws Exception { //attempts a login and will either create an account or login
        String users = "";

        users = readFile(users);
        for (int i = 0; i < invalidCharacters.length(); i++) {
            Character currChar = invalidCharacters.charAt(i);
            if (Email.getText().contains(currChar.toString()) || Password.getText().contains(currChar.toString())) {
                //errorMessage.setText("username or password contains illegal characters");
                isValid = false;
                System.out.println("illegal");
            }
        }
        if (isValid == false){

        }
        else if(Email.getText().isEmpty()){
           // errorMessage.setText(("Enter a username to login"));
            System.out.println("no user");
        }
        else if(Email.getText().length() < 8){
            //errorMessage.setText("Username too short");
            System.out.println("short");
        }/*
        else if(SignIn.getSelectedToggle()==null){
            //errorMessage.setText(("Must select log in type"));
            System.out.println("log type");
        }
*/
        else if (users.contains(":"+Email.getText()+":"+encryptPassword(Password.getText())+":")){ //this file checks for the user and pass in the file
            System.out.println("logging in");
            String pass = encryptPassword(Password.getText());
            System.out.println(pass);
            System.out.println(decryptPassword(pass));

            login(actionEvent);  //if they exist, login
        }

        else if (users.contains(":"+Email.getText()+":none:") && (Password.getText().isEmpty())){
            login(actionEvent);
        }
        else if (users.contains(":"+Email.getText()+":")){
            //errorMessage.setText("Select an unused username");
        }
        else {
            //createAccount(actionEvent); //otherwise make them an account
        }
    }

    public String encryptPassword(String origionalPassword) throws Exception
    {
        String strData;

        try
        {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] e = cipher.doFinal(origionalPassword.getBytes());
            strData = new BASE64Encoder().encode(e);
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
        return strData;
    }

    public String decryptPassword(String encryptedPassword) throws Exception
    {
        String strData;

        try
        {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedPassword);
            byte[] d = cipher.doFinal(decodedValue);
            strData = new String(d);
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
        return strData;
    }

    //Generages the key needed for encryption/ decryption
    private Key generateKey() throws Exception
    {
        String key = "AaBbCcDdEeFfGgHh";
        byte[] encryptionKey = key.getBytes();
        Key k = new SecretKeySpec(encryptionKey, "AES");

        return k;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
