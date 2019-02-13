package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Entities.sub_Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.ListView;


public class DetailedResultsController {
    private ToolBarController toolBarController;
    public Scene ResultsScene;

    @FXML
    ListView<String> info;

    @FXML
    Label ProductName;

    @FXML
    JFXButton back_button;

    ObservableList<String> items = FXCollections.observableArrayList();

    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }

    public void updateList(sub_Form form){
        info.setItems(form.getSummary());
        ProductName.setText(form.getSummary().get(5));

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
        //primaryStage.setMaximized(true);
    }

    @FXML
    public void goExit(ActionEvent actionEvent){toolBarController.goExit(actionEvent);}

}
