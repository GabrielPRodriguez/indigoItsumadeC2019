package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class FormSubmissionController implements Initializable {

    @FXML
    HBox wineFields;

    @FXML
    JFXRadioButton wineRad;

    @FXML
    ToggleGroup beverage;




    private void setWineToggle(){

        JFXRadioButton rb = (JFXRadioButton)beverage.getSelectedToggle();

        if(rb == wineRad)
        {
            wineFields.setVisible(true);
            wineFields.setDisable(false);
            wineFields.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        }
        else {
            wineFields.setVisible(false);
            wineFields.setDisable(true);
            wineFields.setMaxSize(0,0);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        beverage.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                setWineToggle();
            }
        });
    }
}
