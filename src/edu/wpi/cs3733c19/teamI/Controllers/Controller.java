package edu.wpi.cs3733c19.teamI.Controllers;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    private Scene homePage;

    public void setHomePage(Scene homePage) {
        homePage = homePage;
    }

    public void openHome(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(homePage);
    }
}
