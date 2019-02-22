package edu.wpi.cs3733c19.teamI;

import javafx.fxml.FXML;

import java.io.IOException;

public class HelpWorkflowController {
    private ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    public void goWorkflowAgent() throws IOException {
        toolBarController.goWorkflowAgent();
    }
}
