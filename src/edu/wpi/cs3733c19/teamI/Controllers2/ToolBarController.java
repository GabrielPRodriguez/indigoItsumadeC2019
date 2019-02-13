package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    private ArrayList<HashMap<String, ReturnedValue>> resultsMap = new ArrayList<HashMap<String, ReturnedValue>>();


    public void setResultsMap(ArrayList<HashMap<String, ReturnedValue>> resultsMap){
            this.resultsMap = resultsMap;
            ResultsController.convertToForms();

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

    public void setResultsController(ResultsController resultsController){
        ResultsController = resultsController;
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

    }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(SubScene);
        primaryStage.setFullScreen(true);

    }

    @FXML
    public void goLogin(ActionEvent actionEvent){

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Login);
        primaryStage.setFullScreen(true);

    }

    @FXML
    public void goLogout(ActionEvent actionEvent){

    }

    @FXML
    public void login(ActionEvent actionEvent){
        tb_loginButton.setText("Logout");
    }

    @FXML
    public void goPending(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Pending);
        primaryStage.setFullScreen(true);

    }

    @FXML
    public void goWorkflow(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(FormCheck);
        primaryStage.setFullScreen(true);

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
    public void goDetails(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Info);
        primaryStage.setFullScreen(true);


    }




}
