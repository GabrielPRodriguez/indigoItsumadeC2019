package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.*;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;


public class LoginAccountController implements Initializable {

    private ToolBarController toolBarController = ToolBarController.getInstance();


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

    @FXML
    ToggleGroup ToggleType;

    @FXML
    Label ErrorMessage;

    @FXML
    Label UserNameError;

    @FXML
    Label PasswordError;




    @FXML
    public void login(ActionEvent actionEvent) throws Exception {
        if(!attemptLogin(actionEvent)){

        }
        else {
            User.userPower powerCreate = User.userPower.Standard;

            powerCreate = getType();
            System.out.println(powerCreate.toString());
            String toggleGroupValue = "Standard";
            //check the text file for the user type and assign it
            if (ToggleType.getSelectedToggle() != null) {
                RadioButton selectedRadioButton = (RadioButton) ToggleType.getSelectedToggle();
                toggleGroupValue = selectedRadioButton.getText();
            }
            System.out.println(powerCreate.toString());

            if (ToggleType.getSelectedToggle() == null) {
                powerCreate = powerCreate;
            } else if (toggleGroupValue.equals("Manufacturer")) {
                powerCreate = User.userPower.Company;
                System.out.println("toggle man");
            } else if (toggleGroupValue.equals("Agent")) {
                powerCreate = User.userPower.TTBEmployee;
                System.out.println("toggle employ");
            } else {
                powerCreate = User.userPower.Standard;
                System.out.println("togg stand");
            }
            toolBarController.login(Email.getText(), Password.getText(), powerCreate);
        }
    }

    @FXML
    public void logInCreate(ActionEvent actionEvent) throws Exception {
        //make account if possible
        attemptCreate(actionEvent);
        //User curUser = User.getUser(Email.getText(), Password.getText(), User.userPower.Standard);

        login(actionEvent);
        //
    }

    private String invalidCharacters = ":!#$%^&*()/,><;-=_+";
    boolean isValid = true;

    public String readFile(String users) throws Exception { //this file will populate users with a string of all users
        // pass the path to the file as a parameter
        //userLogin = new FileReader("UserSheet.txt");
        FileReader userLogin;
        userLogin = new FileReader(System.getProperty("user.dir") + "/UserSheet.txt");
        int i;
        while ((i=userLogin.read()) != -1) {
            users = users + ((char) i);
        }
        return users;
    }

    public void attemptCreate(ActionEvent actionEvent) throws Exception{
        String users = "";

        users = readFile(users);



        boolean isValid2 = true;
        for (int i = 0; i < invalidCharacters.length(); i++) {

            Character currChar = invalidCharacters.charAt(i);
            if (EmailCreate.getText().contains(currChar.toString()) || PasswordCreate.getText().contains(currChar.toString())) {
                UserNameError.setText("username or password contains illegal characters");
                PasswordError.setText("");
                isValid2 = false;
            }
        }
        if (isValid2 == false){
        }
        else if(PasswordCreate.getText().length() < 8){
            PasswordError.setText("Password too short");
            UserNameError.setText("");
        }
        else if (!EmailCreate.getText().contains("@") || !EmailCreate.getText().contains(".")){
            UserNameError.setText("Please Enter Email");
            PasswordError.setText("");
        }
        else if(ToggleType.getSelectedToggle() == null){
            UserNameError.setText("Select Type Above");
            PasswordError.setText("");
        }
        else if (users.contains(":"+EmailCreate.getText()+":")){
            UserNameError.setText("Email already taken");
            PasswordError.setText("");
        }
        else if (!PasswordCreate.getText().equals(PasswordCreateCheck.getText())){
            PasswordError.setText("Passwords do not match");
            UserNameError.setText("");
        }
        else{
            UserNameError.setText("");
            PasswordError.setText("");
            User.userPower powerCreate;
            RadioButton selectedRadioButton = (RadioButton) ToggleType.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            if(toggleGroupValue.equals("Manufacturer")){
                powerCreate = User.userPower.Company;
            }
            else if(toggleGroupValue.equals("Agent")){
                powerCreate = User.userPower.TTBEmployee;
            }
            else{
                powerCreate = User.userPower.Standard;
            }
            createAccount(EmailCreate.getText(), PasswordCreate.getText(), powerCreate);
            Email.setText(EmailCreate.getText());
            Password.setText(PasswordCreate.getText());
        }

    }

    public void createAccount(String userCreate, String passCreate, User.userPower typeCreate) throws Exception {
        FileWriter userWriter = new FileWriter("UserSheet.txt", true);
        BufferedWriter outputStream = new BufferedWriter(userWriter);
        String addUser = userCreate;
        String userType;

        if(typeCreate.equals(User.userPower.TTBEmployee)){
            userType = "&";
        }
        else if (typeCreate.equals(User.userPower.Company)){
            userType = "#";
        }
        else if (typeCreate.equals(User.userPower.SuperAdmin)){
            userType = "%";
        }
        else{
            userType = "!";
        }
        System.out.println("Creating an Account");
        addUser = ":"+addUser+":"+encryptPassword(passCreate)+":"+ userType+":";

        outputStream.write("\n"+ addUser);
        outputStream.flush();
        outputStream.close();
        //and then scene switcher code, wait for merge
        //openDisplayScene(actionEvent);
    }

    public boolean attemptLogin(ActionEvent actionEvent) throws Exception { //attempts a login and will either create an account or login
        String users = "";

        users = readFile(users);

        if(Email.getText().isEmpty()){
            ErrorMessage.setText(("Enter a username to login"));
            ErrorMessage.setOpacity(1);
            return false;
        }

        else if (users.contains(":"+Email.getText()+":"+encryptPassword(Password.getText())+":")){ //this file checks for the user and pass in the file
            System.out.println("Login Complete");
            ErrorMessage.setOpacity(0);
            return true;
        }

        else if (users.contains(":"+Email.getText()+":")){
            ErrorMessage.setText("Select an unused username");
            ErrorMessage.setOpacity(1);
            return false;

        }
        else { //user name and password dont match
            ErrorMessage.setText("Invalid Username/ Password");
            ErrorMessage.setOpacity(1);
            return false;
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
    public void initialize(URL location, ResourceBundle resources){
    }

    User.userPower getType() throws Exception {
        String users = "";
        users = readFile(users);
        int index = 0;
        String type = "!";
        for (int i = -1; (i = users.indexOf(encryptPassword(Password.getText()), i + 1)) != -1; i++) {
            index = i;
        } // prints "4", "13", "22"
        int buffer = encryptPassword(Password.getText()).length();
        if(!(index == 0)) {
            type = users.substring(index+buffer+1, index+buffer+2);
        }
        System.out.println(type);
        if (type.equals("&")){
            return User.userPower.TTBEmployee;
        }
        else if (type.equals("#")){
            return User.userPower.Company;
        }
        else if (type.equals("%")){
            return User.userPower.SuperAdmin;
        }
        else{
            return User.userPower.Standard;
        }

    }


}
