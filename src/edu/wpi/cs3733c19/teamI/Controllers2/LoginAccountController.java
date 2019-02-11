package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginAccountController implements Initializable {

    @FXML
    JFXTabPane primaryPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        primaryPane.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            primaryPane.setTabMinWidth(primaryPane.getWidth() / 2);
            primaryPane.setTabMaxWidth(primaryPane.getWidth() / 2);
        });
    }
}
