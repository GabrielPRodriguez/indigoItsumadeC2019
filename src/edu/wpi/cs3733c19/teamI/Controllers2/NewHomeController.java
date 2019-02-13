package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733c19.teamI.Algorithms.DLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.LFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.SQLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.fuzzyContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NewHomeController implements Initializable {

    //@FXML
    //JFXComboBox<JFXButton> search;

    @FXML
    VBox primary;


    private ToolBarController toolBarController;
    private fuzzyContext searchAlgorithmSelection = new fuzzyContext();

    @FXML
    JFXButton tb_loginButton;


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
        System.out.println("logging in");
        toolBarController.goLogin(actionEvent);
    }

    @FXML
    public void goLogout(ActionEvent actionEvent){
        System.out.println("logging out");
        toolBarController.goLogout(actionEvent);
    }

    @FXML
    public void goAbout(ActionEvent actionEvent){toolBarController.goAbout(actionEvent);}

    @FXML
    public void goWorkflow(ActionEvent actionEvent){toolBarController.goWorkflow(actionEvent);}

    @FXML
    public void goAdvancedSearch(ActionEvent actionEvent){toolBarController.goAdvancedSearch(actionEvent);}



    @FXML
    public void setAlgorithm(){
        RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        if (toggleGroupValue.equals("Algorithm 1")){  // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new SQLFuzzy());
        }else if(toggleGroupValue.equals("Algorithm 2")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new DLFuzzy());
        }else if(toggleGroupValue.equals("Algorithm 3")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new LFuzzy());
        }


    }


    public void goSearch(ActionEvent actionEvent) throws Exception {
        if (searchTextField.getText() == null || searchTextField.getText().trim().isEmpty()){
            // TODO Insert here anything you want the app to do when user click search and box is epty

        }
        else {
            setAlgorithm();
            System.out.println(searchAlgorithmSelection.run(searchTextField.getText().trim()));
            // toolBarController.transferSearchInfo(searchAlgorithmSelection.run(searchTextField.getText().trim()));
            // toolBarController.goSearch(actionEvent);
            // System.out.println("Finished Gosearch");

            // TODO:this will return what fuzzys return
            // TODO link the return of the fuzzy alghoriths to a listView on the next page (maybe just have field of hashmap that gets passed to the next scene)

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}
