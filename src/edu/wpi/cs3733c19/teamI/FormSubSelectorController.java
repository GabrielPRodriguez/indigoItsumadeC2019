package edu.wpi.cs3733c19.teamI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormSubSelectorController implements Initializable {
    ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    JFXButton singlePageButton;

    @FXML
    JFXButton multiPageButton;

    @FXML
    private void goNormalFormSub() throws IOException {
        toolBarController.goNormalFormSub();
    }

    @FXML
    private void goMultiFormSub() throws IOException{
        toolBarController.goMultiFormSub();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
