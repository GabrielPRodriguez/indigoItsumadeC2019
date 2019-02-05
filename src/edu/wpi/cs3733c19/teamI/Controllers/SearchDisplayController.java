package edu.wpi.cs3733c19.teamI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchDisplayController {

    private Scene searchScene;
    private Scene homePage;

    @FXML
    Button returnToSearch;

    @FXML
    TextField randomText;

    public void setSearchScene(Scene scene)
    {
        searchScene = scene;
    }
    public void setHomePage(Scene scene){
        homePage = scene;
    }

    public void openSearchScene(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(searchScene);
    }


    public void openHome(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(homePage);
    }
}
