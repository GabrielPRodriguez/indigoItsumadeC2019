package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import java.io.IOException;

public class barController {
    private ToolBarController toolBarController = ToolBarController.getInstance();
    @FXML
    JFXButton goWork;
    @FXML
    public void goWork() throws IOException {
        toolBarController.goWorkflowAgent();
    }

}
