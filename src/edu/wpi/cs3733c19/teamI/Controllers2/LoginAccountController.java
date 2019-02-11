package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginAccountController implements Initializable {

    @FXML
    JFXTabPane primaryPane;

    private ToolBarController toolBarController;


    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }


    @FXML
    public void goHome(ActionEvent actionEvent){
        toolBarController.goHome(actionEvent);

    }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        toolBarController.goSubmit(actionEvent);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        toolBarController.goLogin(actionEvent);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
