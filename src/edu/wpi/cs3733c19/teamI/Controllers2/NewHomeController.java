package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import edu.wpi.cs3733c19.teamI.Algorithms.DLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.LFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.SQLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.fuzzyContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NewHomeController implements Initializable {

    @FXML
    VBox primary;

    @FXML
    JFXSpinner spin;

    private fuzzyContext searchAlgorithmSelection = new fuzzyContext();

    @FXML
    TextField searchTextField;

    @FXML
    JFXButton search;

    @FXML
    JFXButton advancedSearch;

    @FXML
    ToggleGroup algorithmGroup;

    @FXML
    JFXRadioButton algorithm1;


    @FXML
    JFXRadioButton algorithm2;

    @FXML
    JFXRadioButton algorithm3;


    @FXML
    public void setAlgorithm(){
        RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        System.out.println("We're not crazy");

        if (toggleGroupValue.equals("SQL")){  // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new SQLFuzzy());
            System.out.println("Algo 1");
        }else if(toggleGroupValue.equals("Levenshtein")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new LFuzzy());
            System.out.println("Algo 2");
        }else if(toggleGroupValue.equals("Damerau-Levenshtein")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new DLFuzzy());
            System.out.println("Algo 3");
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    



}
