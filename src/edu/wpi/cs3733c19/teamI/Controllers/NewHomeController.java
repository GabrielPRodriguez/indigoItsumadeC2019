package edu.wpi.cs3733c19.teamI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewHomeController extends ToolBarController{

    private Scene Home;
    private Scene FormSub;
    private Scene Login;

    public void setHomeScene(Scene home)
    {
        this.Home = home;
    }
    public void setFormSub(Scene formSub){this.FormSub = formSub;}
    public void setLogin(Scene login){this.Login = login;}

    private ToolBarController toolBarController;

    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }

    @FXML
    public void goHome(ActionEvent actionEvent){
        System.out.println("going home");
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Home);

    }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
       // Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //primaryStage.setScene(FormSub);
        toolBarController.goSubmit(actionEvent);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Login);
    }
}
