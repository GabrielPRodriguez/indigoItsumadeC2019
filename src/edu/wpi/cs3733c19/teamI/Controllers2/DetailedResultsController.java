package edu.wpi.cs3733c19.teamI.Controllers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DetailedResultsController {
    private ToolBarController toolBarController;
    private Scene ResultsScene;

    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }
    public void setResultsScene(Scene resultsScene){
        this.ResultsScene = resultsScene;
    }

    @FXML
    public void goHome(ActionEvent actionEvent){ toolBarController.goHome(actionEvent); }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        toolBarController.goSubmit(actionEvent);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        toolBarController.goLogin(actionEvent);
    }

    @FXML
    public void goWorkflow(ActionEvent actionEvent){toolBarController.goWorkflow(actionEvent);}

    @FXML
    public void goAbout(ActionEvent actionEvent){toolBarController.goAbout(actionEvent);}

    @FXML
    public void goSearch(ActionEvent actionEvent){toolBarController.goSearch(actionEvent);}

    @FXML
    public void goResults(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(ResultsScene);
        primaryStage.setMaximized(true);
    }

    @FXML
    public void goExit(ActionEvent actionEvent){toolBarController.goExit(actionEvent);}

}
