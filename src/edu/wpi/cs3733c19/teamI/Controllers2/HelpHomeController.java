package edu.wpi.cs3733c19.teamI.Controllers2;

import javafx.fxml.FXML;

import java.io.IOException;

public class HelpHomeController {

    private ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    public void goHome() throws IOException {
        toolBarController.goHome();
    }
}
