package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.User;
import edu.wpi.cs3733c19.teamI.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ToolBarController implements Initializable {

    static ToolBarController instance;
    Stage root = Main.getRoot();
    //Ignore this and make sure not to write new ToolBarController() because there should only be one that you fetch from getInstance()
    public ToolBarController(){
    }

    static{
        instance = new ToolBarController();
    }

    public static ToolBarController getInstance() {
        return instance;
    }

    private ResultsController ResultsController = new ResultsController();
    private DetailedResultsController InfoController;
    private User curUser;
    private boolean signedIn = false;

    private ArrayList<HashMap<String, ReturnedValue>> resultsMap = new ArrayList<HashMap<String, ReturnedValue>>();


    public void setResultsMap(ArrayList<HashMap<String, ReturnedValue>> resultsMap){
            this.resultsMap = resultsMap;
            ResultsController.convertToForms(0);


        }



    public ArrayList<HashMap<String, ReturnedValue>> getResultsMap() {
        return this.resultsMap;
    }



    public void setResultsController(ResultsController resultsController){ ResultsController = resultsController; }

    public void setInfoController(DetailedResultsController details) { InfoController = details; }

    public DetailedResultsController getInfoController() {return InfoController; };




    @FXML
    JFXButton tb_homeButton;

    @FXML
    JFXButton tb_aboutButton;

    @FXML
    JFXButton tb_submitFormButton;

    @FXML
    JFXButton tb_loginButton;

    @FXML
    JFXButton tb_logout;

    @FXML
    JFXButton tb_workflowButton;

    @FXML
    JFXButton tb_pendingApps;

    @FXML
    public void goHome() throws IOException {
        root = Main.getRoot();
        Parent homeParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Home.fxml"));
        root.getScene().setRoot(homeParent);

    }

    @FXML
    public void goAbout() throws IOException {
        root = Main.getRoot();
        Parent aboutParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/About.fxml"));
        root.getScene().setRoot(aboutParent);
    }

    @FXML
    public void goSubmit() throws IOException {

        if(curUser == null) {
            goLogin();
        }
        else if (!curUser.getUserType().equals(User.userPower.TTBEmployee)){
            root = Main.getRoot();
            Parent formSubParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/FormSubmission.fxml"));
            root.getScene().setRoot(formSubParent);
        }
    }

    @FXML
    public void goLogin() throws IOException {

        if(signedIn == false) {
            root = Main.getRoot();
            Parent loginParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Login_CreateAccount.fxml"));
            root.getScene().setRoot(loginParent);
        }
        else if (signedIn == true){
            signedIn = false;
            curUser = null;
            root = Main.getRoot();
            Parent loginParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/Login_CreateAccount.fxml"));
            root.getScene().setRoot(loginParent);
        }
    }

    @FXML
    public void goLogout(){

    }

    public void loginRFID(String username, String password, User.userPower power){
        signedIn = true;
        curUser = User.getUser(username, password, power);

    }

    @FXML
    public void login(String username, String password, User.userPower power) throws IOException {
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

    }

    @FXML
    public void goPending(){


    }

    @FXML
       public void goWorkflow() throws IOException {
        if (curUser == null) {
            goLogin();
        }
        else if (!curUser.getUserType().equals(User.userPower.Company)){
            root = Main.getRoot();
            Parent formCheckParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/WorkflowAgent.fxml"));
            root.getScene().setRoot(formCheckParent);
        }
    }

    public void goAdvancedSearch() throws IOException {
        root = Main.getRoot();
        Parent advSearchParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/AdvancedSearch.fxml"));
        root.getScene().setRoot(advSearchParent);

    }

    public void goSearch() throws IOException {
        root = Main.getRoot();
        Parent loginParent = FXMLLoader.load(getClass().getResource("../Boundaries_2/SearchResults.fxml"));
        root.getScene().setRoot(loginParent);

    }
    public void goDetails(){



    }
    public void goExit(){
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root = Main.getRoot();
    }
}
