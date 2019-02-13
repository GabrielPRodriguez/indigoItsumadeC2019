package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class ToolBarController {


    public ToolBarController(){
        FXMLLoader toolBarLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Toolbar.fxml"));
        toolBarLoader.setRoot(this);
        toolBarLoader.setController(this);
    }

    protected Scene HomeScene;
    protected Scene SearchScene;
    private Scene SubScene;
    private Scene AboutScene;
    private Scene Login;
    private Scene FormCheck;
    private Scene Pending;
    private Scene Info;
    private Scene Result;
    private ResultsController ResultsController;
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

    public void setPending(Scene pending) {
        Pending = pending;
    }

    public void setInfo(Scene info) {
        Info = info;
    }

    public void setResult(Scene result) {
        Result = result;
    }

    public void setHomeScene(Scene homeScene) {
        HomeScene = homeScene;
    }

    public void setSearchScene(Scene searchScene) {
        SearchScene = searchScene;
    }

    public void setSubScene(Scene subScene) {
        SubScene = subScene;
    }

    public void setAboutScene(Scene aboutScene) {
        AboutScene = aboutScene;
    }

    public void setLogin(Scene login) {
        Login = login;
    }

    public void setFormCheck(Scene formCheck) {
        FormCheck = formCheck;
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
    JFXButton tb_workFlow;

    @FXML
    JFXButton tb_pendingApps;

    @FXML
    public void goHome(ActionEvent actionEvent){
        //System.out.println("HomeAction"); // Commented out because it prints too much
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(HomeScene);
        primaryStage.setFullScreen(true);

    }

    @FXML
    public void goAbout(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(AboutScene);
        primaryStage.setFullScreen(true);

        //about.setTb_logout();

    }

    @FXML
    public void goSubmit(ActionEvent actionEvent){

        if(curUser == null) {
            goLogin(actionEvent);
        }
        else if (!curUser.getUserType().equals(User.userPower.TTBEmployee)){

            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(SubScene);
            primaryStage.setFullScreen(true);
        }
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){

        if(signedIn == false) {
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(Login);
            primaryStage.setFullScreen(true);
        }
        else if (signedIn == true){
            signedIn = false;
            curUser = null;
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(Login);
            primaryStage.setFullScreen(true);
        }
    }

    @FXML
    public void goLogout(ActionEvent actionEvent){

    }

    public void loginRFID(String username, String password, User.userPower power){
        signedIn = true;
        curUser = User.getUser(username, password, power);

    }

    @FXML
    public void login(ActionEvent actionEvent, String username, String password, User.userPower power){
        signedIn = true;
        curUser = User.getUser(username, password, power);
        if(curUser.getUserType().equals(User.userPower.TTBEmployee)){
            goWorkflow(actionEvent);
        }
        else if(curUser.getUserType().equals(User.userPower.Company)){
            goSubmit(actionEvent);
        }
        else{
            goSearch(actionEvent);
        }

        //tb_loginButton.setText("Logout");
    }

    @FXML
    public void goPending(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Pending);
        primaryStage.setFullScreen(true);

    }

    @FXML
       public void goWorkflow(ActionEvent actionEvent) {
        if (curUser == null) {
            goLogin(actionEvent);
        }
        else if (!curUser.getUserType().equals(User.userPower.Company)){
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(FormCheck);
            primaryStage.setFullScreen(true);
        }
    }

    public void goAdvancedSearch(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(SearchScene);
        primaryStage.setFullScreen(true);

    }

    public void goSearch(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Result);
        primaryStage.setFullScreen(true);

    }
    public void goDetails(Event event){
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(Info);
        primaryStage.setFullScreen(true);


    }
    public void goExit(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
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
