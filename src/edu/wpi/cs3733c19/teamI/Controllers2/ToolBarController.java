package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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
        primaryStage.setMaximized(true);
        primaryStage.setScene(HomeScene);
    }

    @FXML
    public void goAbout(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(AboutScene);
    }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(SubScene);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(Login);
    }

    @FXML
    public void Logout(ActionEvent actionEvent){

    }

    @FXML
    public void goPending(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(Pending);
    }

    @FXML
    public void goWorkflow(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(FormCheck);
    }

    public void goAdvancedSearch(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(SearchScene);
    }

    public void goSearch(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(Result);
    }
    public void goDetails(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setMaximized(true);
        primaryStage.setScene(Info);

    }



}
