package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeControllerTest implements Initializable{
    @FXML
    JFXComboBox<JFXButton> search;

    @FXML
    VBox primary;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
