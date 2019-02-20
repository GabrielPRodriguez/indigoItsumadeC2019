package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.*;
import edu.wpi.cs3733c19.teamI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ToolBarController implements Initializable {

    DataTransfer data = DataTransfer.getInstance();

    public Stage primaryStage = Main.getWindow();

    @FXML
    JFXButton tb_homeButton;

    @FXML
    JFXButton tb_aboutButton;

    @FXML
    JFXButton tb_submitFormButton;

    @FXML
    JFXButton tb_loginButton;

    @FXML
    JFXButton tb_workflowButton;


    @FXML
    Label signInLabel;   //here is what we are going to write to when the person signs in
    @FXML
    Label signInLabel2;

    static ToolBarController instance;
    private static User curUser = null;
    private ResultsController ResultsController;
    private Parent searchParent;
    private DetailedResultsController InfoController;
    private  static boolean signedIn = false;

    private ArrayList<HashMap<String, ReturnedValue>> resultsMap = new ArrayList<HashMap<String, ReturnedValue>>();

    // Observable Classes for displaying the name
    private Message clearText;
    private ToolBarSignInName toolBarSignInName;

    public ToolBarController() {
        clearText = new Message();
        toolBarSignInName = new ToolBarSignInName();
        clearText.register(toolBarSignInName);
    }

    static {
        instance = new ToolBarController();
    }

    public static ToolBarController getInstance() {
        return instance;
    }


    void setResultsMap(ArrayList<HashMap<String, ReturnedValue>> resultsMap){
            this.resultsMap = resultsMap;
            ResultsController.convertToForms(0);

    }

    public void setSearchParent(Parent searchParent) {
        this.searchParent = searchParent;
    }

    ArrayList<HashMap<String, ReturnedValue>> getResultsMap() {
        return this.resultsMap;
    }

    public void setResultsController(ResultsController resultsController){ ResultsController = resultsController; }

    public void setInfoController(DetailedResultsController details) { InfoController = details; }

    public DetailedResultsController getInfoController() {return InfoController; }

    public void loginRFID(String username, String password, User.userPower power){
        signedIn = true;
        curUser = User.getUser(username, password, power, "", "","","","","","","", "");
    }

    public void login(String username, String password, User.userPower power, String theState, String theCity, String theZip, String theStreet, String theFirstName, String theLastName, String thePhone, String theRepID, String delim ) throws IOException
    {
       signedIn = true;
       if(curUser != null) {
           curUser.logOutUser();
       }
       curUser = curUser.getUser(username, password, power, theState, theCity,theZip,theStreet, theFirstName,theLastName,thePhone,theRepID, delim);
        data.UserName = curUser.getUsername();
        //data.LogButtonName = "Logout";

        goWorkflow();
        //displaySignInName(username);
    }

    public void goHome() throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Home.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(homeParent);
    }

    public void goAbout() throws IOException {
        Parent aboutParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/About.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(aboutParent);
    }

    public void goSubmit() throws IOException {
        if((curUser == null) || curUser.getUsername().equals(""))
        {
            goLogin();
            return;
        }else if (curUser.getUserType().equals(User.userPower.TTBEmployee)){
            return;
        }
        Parent submitParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/FormSubSelector.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(submitParent);

    }

    public void goNormalFormSub() throws IOException {
        Parent formSubParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/FormSubmission.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(formSubParent);
    }

    public void goMultiFormSub() throws IOException {
        Parent multiFormSubParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/MultiFormSub.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(multiFormSubParent);
    }
    /*
    create an init in this class
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = DataTransfer.getInstance();
        if(curUser == null){tb_loginButton.setText("Login");}
        else if((curUser.getUsername().equals(""))) {tb_loginButton.setText("Login");}
        else{tb_loginButton.setText("Logout");}
        if(!data.UserName.isEmpty()){
            displaySignInName(data.UserName);
        }else{
            takeOffSignInName();
        }

        //signInLabel.setText(data.UserName);

    }


    public void goLogin() throws IOException {
        if(curUser != null){
            curUser.logOutUser(); //log out current user
            data.UserName = curUser.getUsername();//update name
            if(data.loginStatus == 0){
                data.loginStatus = 1;
                data.LogButtonName = "Logout";
            }
            else{
                data.loginStatus = 0;
                data.LogButtonName = "Login";
            }
        }

        Parent loginParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Login_CreateAccount.fxml"));
      //  loginParent.get
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(loginParent);
       // primaryStage.getScene().getRoot().g
        takeOffSignInName();
        primaryStage.show();

    }

    public void goWorkflow() throws IOException {
        System.out.print(signedIn);

        if((curUser == null)|| curUser.getUsername().equals(""))
        {
            goLogin();
        }
        else if(curUser.getUserType().equals(User.userPower.TTBEmployee)||curUser.getUserType().equals(User.userPower.Specialist))
        {
            goWorkflowAgent();
        }
        else if(curUser.getUserType().equals(User.userPower.Company))
        {
            goWorkflowManufacturer();
        }
        else if(curUser.getUserType().equals(User.userPower.Standard))
        {
            goHome();
        }
    }

    public void goWorkflowAgent() throws IOException {
        Parent agentWorkParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/WorkflowAgent.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(agentWorkParent);
    }

    public void goWorkflowManufacturer() throws IOException
    {
        Parent manuWorkParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/WorkflowManufacturer.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(manuWorkParent);
    }


    public void goAdvancedSearch() throws IOException {
        Parent advancedSearchParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/AdvancedSearch.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(advancedSearchParent);

    }

    public void goSearch() throws IOException {

        Parent searchParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/SearchResults.fxml"));
       // FXMLLoader resultsPageLoader = new FXMLLoader(getClass().getResource("../Boundaries_2/SearchResults.fxml"));

       // Parent resultPane = resultsPageLoader.load();
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(searchParent); }


    public void goExit(ActionEvent actionEvent){
        System.exit(0);
    }

    public void transferSearchInfo(ArrayList<HashMap<String, ReturnedValue>> resultsList){
        ResultsController.setTestString("a new string");
        ResultsController.setResultsList(resultsList);
    }

    public void goHelpHome() throws IOException {
        Parent helphomeParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/HelpHomePage.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(helphomeParent);
    }

    public void goHelpSubmit() throws IOException {
        Parent helpsubmitParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/HelpSubmitForm.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(helpsubmitParent);
    }

    public void goHelpWorkflow() throws IOException {
        Parent helpworkflowParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/HelpWorkFlow.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(helpworkflowParent);
    }

    public void displaySignInName(String name){
        //clearText.setText(name);
        // TODO implement Label on the FXML ToolBar to display the sign in of the person
        //signInLabel.setText("FUCK");


        //data = DataTransfer.getInstance();
        //signInLabel.setText(data.UserName);
        clearText.setText(name);
        signInLabel.setText("Signed in as");
        signInLabel2.setText(toolBarSignInName.getText());
    }
    // TODO call this method when the person signs out of the account
    public void takeOffSignInName(){
        signInLabel.setText("");
        signInLabel2.setText("");
    }


    public User getCurUser() {
        return curUser;
    }
}
