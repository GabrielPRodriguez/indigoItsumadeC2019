package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NewHomeController implements Initializable {



    @FXML
    VBox primary;

    @FXML
    JFXSpinner spin;

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
    public void goExit(ActionEvent actionEvent){toolBarController.goExit(actionEvent);}



    @FXML
    public void setAlgorithm(){
        RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        System.out.println("We're not crazy");

        if (toggleGroupValue.equals("SQL")){  // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new SQLFuzzy());
            System.out.println("Algo 1");
        }else if(toggleGroupValue.equals("Levenshtein")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new DLFuzzy());
            System.out.println("Algo 2");
        }else if(toggleGroupValue.equals("Damerau-Levenshtein")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new LFuzzy());
            System.out.println("Algo 3");
        }


    }


    public void goSearch(ActionEvent actionEvent) throws Exception {

        if (searchTextField.getText() == null || searchTextField.getText().trim().isEmpty()){
            // TODO Insert here anything you want the app to do when user click search and box is epty

        }
        else {
           // spin.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
            setAlgorithm();
           // System.out.println(searchAlgorithmSelection.run(searchTextField.getText().trim()));
            // toolBarController.transferSearchInfo(searchAlgorithmSelection.run(searchTextField.getText().trim()));
            // toolBarController.goSearch(actionEvent);
            // System.out.println("Finished Gosearch");

            toolBarController.setResultsMap(searchAlgorithmSelection.run(searchTextField.getText().trim()));
            toolBarController.goSearch(actionEvent);

            // TODO:this will return what fuzzys return
            // TODO link the return of the fuzzy alghoriths to a listView on the next page (maybe just have field of hashmap that gets passed to the next scene)

        }

    }

    void logButton(){
        if(toolBarController == null){
            tb_loginButton.setText("login");
        }
        else if(toolBarController.isSignedIn()){
            tb_loginButton.setText("logout");
        }
        else if(!toolBarController.isSignedIn()) {
            tb_loginButton.setText("login");
        }else{
            tb_loginButton.setText("login");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        spin.setMaxSize(0,0);
        //System.out.println("helooooooo");
    }

    



}
