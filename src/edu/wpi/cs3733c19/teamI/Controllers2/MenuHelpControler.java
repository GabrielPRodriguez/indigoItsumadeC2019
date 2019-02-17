package edu.wpi.cs3733c19.teamI.Controllers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuHelpControler implements Initializable {

    private ToolBarController toolBarController;

    public void setToolBarController(ToolBarController toolBarController) {
        this.toolBarController = toolBarController;
    }

    @FXML
    Button closeHelp;

    @FXML
    public void goHome(ActionEvent actionEvent){
        toolBarController.goHome(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
