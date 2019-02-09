package edu.wpi.cs3733c19.teamI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class HomeController extends ToolBarController{

    private Scene submissionScene;
    private Scene searchScene;
    private Scene work;
    private Scene loginScene;

    @FXML
    Button search;

    @FXML
    Button submit;

    @FXML
    Button login;

    public void setSubmission(Scene scene) {
        submissionScene = scene;
    }
    public void setSearch(Scene scene) {
        searchScene = scene;
    }
    public void setWork(Scene scene) {
        work = scene;
    }
    public void setLogin(Scene scene) {
        loginScene = scene;
    }

    @FXML
    public void openSubmission(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(submissionScene);
    }

    public void openWork(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(work);
    }

    public void openSearch(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(searchScene); //test changes
    }

    public void openForm(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(submissionScene);
    }

    public void openLogin(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
    }
}
