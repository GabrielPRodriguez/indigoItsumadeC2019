package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.Message;
import edu.wpi.cs3733c19.teamI.Entities.ToolBarSignInName;
import edu.wpi.cs3733c19.teamI.Entities.User;
import edu.wpi.cs3733c19.teamI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ToolBarController {


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
        System.out.println(ResultsController);
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
        curUser = User.getUser(username, password, power);
    }

    public void login(String username, String password, User.userPower power) throws IOException
    {
        //System.out.println("logging in");
       signedIn = true;
       if(curUser != null) {
           curUser.logOutUser();
       }
       curUser = curUser.getUser(username, password, power);
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
        System.out.println(signedIn);
        if (!signedIn)
        {
            goLogin();
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


    public void goLogin() throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Login_CreateAccount.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(loginParent);

    }

    public void goWorkflow() throws IOException {
        System.out.print(signedIn);

        if((curUser == null)|| curUser.getUsername().equals(""))
        {
            goLogin();
        }
        else if(curUser.getUserType().equals(User.userPower.TTBEmployee))
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
        System.out.println("toolSearch");

       // Parent searchParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Home.fxml"));
        primaryStage = Main.getWindow();
        primaryStage.getScene().setRoot(searchParent);
        System.out.println("Should have changed?");
    }


    public void goExit(ActionEvent actionEvent){
        System.exit(0);
    }

    public void transferSearchInfo(ArrayList<HashMap<String, ReturnedValue>> resultsList){
        ResultsController.setTestString("a new string");
        ResultsController.setResultsList(resultsList);
    }

    public void displaySignInName(String name){
        clearText.setText(name);
        // TODO implement Label on the FXML ToolBar to display the sign in of the person
        signInLabel.setText(toolBarSignInName.getText());
    }
    // TODO call this method when the person signs out of the account
    public void takeOffSignInName(){
        signInLabel.setText("");
    }


    public User getCurUser() {
        return curUser;
    }
}
