package edu.wpi.cs3733c19.teamI;

import javafx.fxml.FXML;

import java.io.IOException;

public class HelpSubmitController {
    private ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    public void goNormalFormSub() throws IOException {
        toolBarController.goNormalFormSub();
    }
}
