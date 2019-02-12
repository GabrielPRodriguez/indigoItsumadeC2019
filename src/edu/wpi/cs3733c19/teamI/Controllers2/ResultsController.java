package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultsController {
    private ToolBarController toolBarController;

    @FXML
    JFXListView<Label> listView;

    public void setUp() {
        for (int i = 0; i < 4; i++) listView.getItems().add(new Label("Item " + i + " DRANK " + i));
        listView.getStyleClass().add("mylistview");
    }



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



}
