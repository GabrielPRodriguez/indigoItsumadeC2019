package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.User;
import edu.wpi.cs3733c19.teamI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ToolBarController {

    static ToolBarController instance;

    public ToolBarController()
    {

    }

    static {
        instance = new ToolBarController();
    }

    public static ToolBarController getInstance() {
        return instance;
    }

    public Stage primaryStage = Main.getWindow();

    private ResultsController ResultsController;
    private DetailedResultsController InfoController;
    private User curUser;
    private boolean signedIn = false;

    private ArrayList<HashMap<String, ReturnedValue>> resultsMap = new ArrayList<HashMap<String, ReturnedValue>>();


    void setResultsMap(ArrayList<HashMap<String, ReturnedValue>> resultsMap){
            this.resultsMap = resultsMap;
            ResultsController.convertToForms(0);

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

    void login(String username, String password, User.userPower power) throws IOException {
        signedIn = true;
        curUser = User.getUser(username, password, power);
        if(curUser.getUserType().equals(User.userPower.TTBEmployee)){
            goWorkflow();
        }
        else if(curUser.getUserType().equals(User.userPower.Company)){
            goSubmit();
        }
        else{
            goSearch();
        }

        tb_loginButton.setText("Logout");
    }


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



    public void goHome() throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Home.fxml"));
        primaryStage.getScene().setRoot(homeParent);
    }


    public void goAbout() throws IOException {
        Parent aboutParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/About.fxml"));
        primaryStage.getScene().setRoot(aboutParent);
    }

    public void goSubmit() throws IOException {

        if(curUser == null) {
            goLogin();
        }
        else if (!curUser.getUserType().equals(User.userPower.TTBEmployee)){
            Parent formSubParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/FormSubmission.fxml"));
            primaryStage.getScene().setRoot(formSubParent);

        }
    }

    public void goLogin() throws IOException {

        if(!signedIn) {
            Parent loginParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Login_CreateAccount.fxml"));
            primaryStage.getScene().setRoot(loginParent);
        }
        else if (signedIn){
            signedIn = false;
            curUser = null;
            Parent loginParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Login_CreateAccount.fxml"));
            primaryStage.getScene().setRoot(loginParent);
        }
    }


    public void goWorkflow() throws IOException {
        if (curUser == null) {
            goLogin();
        }
        else if (!curUser.getUserType().equals(User.userPower.Company)){
            Parent workflowAgentParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/WorkflowAgent.fxml"));
            System.out.println("parent: " + workflowAgentParent);

            primaryStage.getScene().setRoot(workflowAgentParent);
        }
    }

    public void goAdvancedSearch() throws IOException {
        Parent advancedSearchParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/AdvancedSearch.fxml"));
        primaryStage.getScene().setRoot(advancedSearchParent);

    }

    public void goSearch() throws IOException {

        Parent searchParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Home.fxml"));
        primaryStage.getScene().setRoot(searchParent);
    }


    public void goExit(ActionEvent actionEvent){
        System.exit(0);
    }

    public void transferSearchInfo(ArrayList<HashMap<String, ReturnedValue>> resultsList){
        ResultsController.setTestString("a new string");
        ResultsController.setResultsList(resultsList);
        System.out.println("toolbar: " + resultsList);
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
