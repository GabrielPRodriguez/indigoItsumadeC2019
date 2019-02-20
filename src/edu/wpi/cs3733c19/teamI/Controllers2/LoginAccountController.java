package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.*;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.DataField;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.*;


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
    Label UserNameError;

    @FXML
    Label ErrorMessage;

    @FXML
    Label PasswordError;
    @FXML
    JFXTextField state;
    @FXML
    JFXTextField zip;
    @FXML
    JFXTextField city;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField firstName;
    @FXML
    JFXTextField lastName;
    @FXML
    JFXTextField phone;
    @FXML
    JFXTextField delim;
    @FXML
    Text req_error;
    @FXML
    Label firstNameError;
    @FXML
    Label lastNameError;




    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }




    @FXML
    public void login(ActionEvent actionEvent) throws Exception {
        System.out.println("about to check..");
        if(!attemptLogin(actionEvent)){
            System.out.println("attempt login fail");

        }
        else {
            User.userPower powerCreate = User.userPower.Standard;
            //TODO: eliminate following toggel button code, shold be handled by DB

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

            ArrayList<HashMap<String, ReturnedValue>> users = new ArrayList<>();
            SQLDriver loginDriver = new SQLDriver();
            LinkedList<String> param = new LinkedList<String>();
            param.add("email");
            HashMap<String, DataField> searchParam = new HashMap<>();
            searchParam.put("email", new DataField(Email.getText(), "email"));
            users = loginDriver.get_user_data_by_value("credentials", "user_database.db", param, searchParam);
            if(users.get(0).get("role").to_double() == 0){
                powerCreate = User.userPower.TTBEmployee;
            }
            else if(users.get(0).get("role").to_double() == 1) {
                powerCreate = User.userPower.Company;
            }
            else{
                powerCreate= User.userPower.Specialist;
            }
            System.out.println(encryptPassword("Specialist"));

            toolBarController.login(Email.getText(), Password.getText(), powerCreate, users.get(0).get("state").to_string(), users.get(0).get("city").to_string(), users.get(0).get("zipCode").to_string(), users.get(0).get("streetAdress").to_string(), users.get(0).get("firstName").to_string(), users.get(0).get("lastName").to_string(), users.get(0).get("phoneNumber").to_string(), users.get(0).get("RepIDnum").to_string() );
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

        ArrayList<HashMap<String, ReturnedValue>> users = new ArrayList<HashMap<String, ReturnedValue>>();
        SQLDriver loginDriver = new SQLDriver();
        LinkedList<String> param = new LinkedList<String>();
        param.add("email");
        HashMap<String, DataField> searchParam = new HashMap<>();
        searchParam.put("email", new DataField(EmailCreate.getText(), "email"));

        try {
            users = loginDriver.get_user_data_by_value("credentials", "user_database.db", param, searchParam);//readFile(users);
        }
        catch(Exception e){
            System.out.println("no db");
        }


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
            firstNameError.setText("");
            lastNameError.setText("");
        }
        else if (!EmailCreate.getText().contains("@") || !EmailCreate.getText().contains(".")){
            UserNameError.setText("Please Enter Email");
            PasswordError.setText("");
            firstNameError.setText("");
            lastNameError.setText("");
        }
        else if(ToggleType.getSelectedToggle() == null){
            UserNameError.setText("Select Type Above");
            PasswordError.setText("");
            firstNameError.setText("");
            lastNameError.setText("");
        }
        else if (!users.isEmpty()){
            UserNameError.setText("Email already taken");
            PasswordError.setText("");
            firstNameError.setText("");
            lastNameError.setText("");
        }
        else if (!PasswordCreate.getText().equals(PasswordCreateCheck.getText())){
            PasswordError.setText("Passwords do not match");
            UserNameError.setText("");
            firstNameError.setText("");
            lastNameError.setText("");
        }

        else if(firstName.getText().isEmpty() || lastName.getText().isEmpty()){
            firstNameError.setText("First and Last Name Required");
            lastNameError.setText("First and Last Name Required");
            PasswordError.setText("");
            UserNameError.setText("");

        }
        else{ //add user to db
            UserNameError.setText("");
            PasswordError.setText("");
            firstNameError.setText("");
            lastNameError.setText("");
            User.userPower powerCreate;
            RadioButton selectedRadioButton = (RadioButton) ToggleType.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            String role = "0.0";
            if(toggleGroupValue.equals("Agent")){
                role = "0.0";
            }
            else{
                role = "1.0";
            }




            double _id_count = 0;
            for (HashMap<String, ReturnedValue>result:loginDriver.select_all("user_database.db", "credentials")){
               double _test = result.get("RepIDnum").to_double();
               if (_test > _id_count){
                   _id_count = _test;
               }
            }


            DBValue [] user_row = {new DBValue<Integer>((int)(_id_count)+1), new DBValue<String>(firstName.getText()), new DBValue<String>(lastName.getText()), new DBValue<String>(phone.getText()), new DBValue<String>(address.getText()),
                    new DBValue<String>(city.getText()), new DBValue<String>(zip.getText()), new DBValue<String>(state.getText()),
                    new DBValue<String>(delim.getText()), new DBValue<String>(EmailCreate.getText()), new DBValue<String>(encryptPassword(PasswordCreate.getText())), new DBValue<String>(role), new DBValue<String>("NULL")};
            loginDriver.insert_vals("credentials", "user_database.db", user_row);
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
        ArrayList<HashMap<String, ReturnedValue>> users = new ArrayList<>();
        SQLDriver loginDriver = new SQLDriver();
        LinkedList<String> param = new LinkedList<String>();
        param.add("email");
        HashMap<String, DataField> searchParam = new HashMap<>();
        searchParam.put("email", new DataField(Email.getText(), "email"));
        System.out.println("Data collected");

        users = loginDriver.get_user_data_by_value("credentials", "user_database.db", param, searchParam);//readFile(users);
        if(Email.getText().isEmpty() || Password.getText().isEmpty()){
            req_error.setOpacity(1);
            return(false);
        }
        if(Email.getText().isEmpty()){
            ErrorMessage.setText(("Enter a username to login"));
            ErrorMessage.setOpacity(1);
            return false;
        }

        else if ((!users.isEmpty() && users.get(0).get("password").to_string().equals(encryptPassword(Password.getText())))){//users.contains(":"+Email.getText()+":"+encryptPassword(Password.getText())+":")){ //this file checks for the user and pass in the file
            ErrorMessage.setOpacity(0);
            return true;
        }

//        else if (users.contains(":"+Email.getText()+":")){
//            ErrorMessage.setText("Select an unused username");
//            ErrorMessage.setOpacity(1);
//            return false;
//
//        }
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
