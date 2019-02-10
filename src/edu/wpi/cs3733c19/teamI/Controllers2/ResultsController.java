package edu.wpi.cs3733c19.teamI.Controllers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ResultsController {
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
    public void goLogin(ActionEvent actionEvent) {
        toolBarController.goLogin(actionEvent);
    }


}
