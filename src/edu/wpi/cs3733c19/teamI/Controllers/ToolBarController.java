package edu.wpi.cs3733c19.teamI.Controllers;

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
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarController implements Initializable {

    private Scene HomeScene;
    private Scene SearchScene;
    private Scene SubScene;
    private Scene AboutScene;
    private Scene Login;
    private Scene FormCheck;

    public void initializeSceneSwitch() throws IOException {

        FXMLLoader searchPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SearchV2.fxml"));
        Parent firstPane = searchPaneLoader.load();
        Scene searchScene = new Scene(firstPane, 1289, 918);
        SearchScene = searchScene;

        FXMLLoader formCheckerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/FormChecker.fxml"));
        Parent formCheckerPane = formCheckerPaneLoader.load();
        Scene formCheckerScene = new Scene(formCheckerPane, 1289, 918);
        FormCheck = formCheckerScene;

        FXMLLoader resultsPageLoader = new FXMLLoader(getClass().getResource("Boundaries/ResultsPage.fxml"));
        Parent resultPane = resultsPageLoader.load();
        Scene resultScene = new Scene(resultPane, 1289, 918);

        FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("Boundaries/homepage.fxml"));
        Parent homePane = homePaneLoader.load();
        Scene homeScene = new Scene(homePane, 1289, 918);
        HomeScene = homeScene;


        FXMLLoader subPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SubmissionProject.fxml"));
        Parent subPane = subPaneLoader.load();
        Scene subScene = new Scene(subPane, 1289, 918);
        SubScene = subScene;

        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/Login.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 1289, 918);
        Login = loginScene;

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
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(HomeScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        try{
            initializeSceneSwitch();

        }
        catch (IOException e){
            
        }
    }
}
