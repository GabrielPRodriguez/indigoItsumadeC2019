package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarController implements Initializable{

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

    public void initializeSceneSwitch() throws IOException {

        FXMLLoader searchPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Advanced Search.fxml"));
        Parent searchPane = searchPaneLoader.load();
        Scene searchScene = new Scene(searchPane, 1289, 918);
        SearchScene = searchScene;

        AdvancedSearchController advancedSearchController = searchPaneLoader.getController();
        advancedSearchController.setToolBarController(this);

        FXMLLoader formCheckerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/WorkflowAgent.fxml"));
        Parent formCheckerPane = formCheckerPaneLoader.load();
        Scene formCheckerScene = new Scene(formCheckerPane, 1289, 918);
        FormCheck = formCheckerScene;



        FXMLLoader resultsPageLoader = new FXMLLoader(getClass().getResource("Boundaries_2/SearchResults.fxml"));
        Parent resultPane = resultsPageLoader.load();
        Scene resultScene = new Scene(resultPane, 1289, 918);
        Result = resultScene;

        FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Home.fxml"));
        Parent homePane = homePaneLoader.load();
        Scene homeScene = new Scene(homePane, 1289, 918);
        HomeScene = homeScene;


        FXMLLoader subPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/FormSubmission.fxml"));
        Parent subPane = subPaneLoader.load();
        Scene subScene = new Scene(subPane, 1289, 918);
        SubScene = subScene;

        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Login_CreateAccount.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 1289, 918);
        Login = loginScene;

        FXMLLoader aboutPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/About.fxml"));
        Parent aboutPane = aboutPaneLoader.load();
        Scene aboutScene = new Scene(aboutPane, 1289, 918);
        AboutScene = aboutScene;

        FXMLLoader manufacturerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/WorkflowManufacturer.fxml"));
        Parent manufacturerPane = manufacturerPaneLoader.load();
        Scene manufacturerScene = new Scene(manufacturerPane, 1289,918);
        Pending = manufacturerScene;

        FXMLLoader detailedLoader = new FXMLLoader(getClass().getResource("Boundaries_2/DetailedSearchResults.fxml"));
        Parent detailedPane = detailedLoader.load();
        Scene detailedScene = new Scene(detailedPane,1289,918);
        Info = detailedScene;




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



    @FXML
    JFXButton tb_homeButton;

    @FXML
    JFXButton tb_aboutButton;

    @FXML
    JFXButton tb_submitFormButton;

    @FXML
    MenuButton tb_loginButton;

    @FXML
    MenuItem tb_logout;

    @FXML
    MenuItem tb_workFlow;

    @FXML
    MenuItem tb_pendingApps;

    @FXML
    public void goHome(ActionEvent actionEvent){
        System.out.println("HomeAction");
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(HomeScene);
    }

    @FXML
    public void goAbout(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(AboutScene);
    }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(SubScene);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Login);
    }

    @FXML
    public void Logout(ActionEvent actionEvent){

    }

    @FXML
    public void goPending(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Pending);
    }

    @FXML
    public void goWorkflow(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(FormCheck);
    }

    public void goAdvancedSearch(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(SearchScene);
    }

    public void goSearch(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Result);
    }
    public void goDetails(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Info);

    }


    public void initialize(URL location, ResourceBundle resources)  {
        try{
            initializeSceneSwitch();

        }
        catch (IOException e){

        }
    }
}
