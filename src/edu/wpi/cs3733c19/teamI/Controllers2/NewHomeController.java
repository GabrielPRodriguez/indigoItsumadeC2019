package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import edu.wpi.cs3733c19.teamI.Algorithms.*;
//import edu.wpi.cs3733c19.teamI.Algorithms.UserSearch;
import edu.wpi.cs3733c19.teamI.Entities.User;
import edu.wpi.cs3733c19.teamI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewHomeController implements Initializable {

    private ToolBarController toolBarController = ToolBarController.getInstance();
    private fuzzyContext searchAlgorithmSelection = new fuzzyContext();


    @FXML
    JFXSpinner spin;

    @FXML
    TextField searchTextField;

    @FXML
    JFXButton search;

    @FXML
    ToggleGroup algorithmGroup;

    @FXML
    JFXRadioButton algorithm1;


    @FXML
    JFXRadioButton algorithm2;

    @FXML
    JFXRadioButton algorithm3;

    @FXML
    CheckBox brandName;

    @FXML
    CheckBox fancifulName;

    @FXML
    CheckBox extraInfo;

    @FXML
    CheckBox beverageType;

    @FXML
    public void goHelpHome() throws IOException {
        toolBarController.goHelpHome();
    }

    @FXML
    public void setAlgorithm(){
        RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        if (toggleGroupValue.equals("SQL")){  // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new SQLFuzzy());

        }else if(toggleGroupValue.equals("Levenshtein")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new LFuzzy());

        }else if(toggleGroupValue.equals("user_search")){ // TODO Change the name in the string to the actual text of the radio button
            //UserSearch theSearch = new UserSearch();
            searchAlgorithmSelection.setContext(new UserSearch());


        }


    }

    public void setSearchParam(){
        ArrayList<String> paramList = new ArrayList<String>();
        if (brandName.isSelected()) {
            paramList.add("brandName");
        }
        if (fancifulName.isSelected()) {
            paramList.add("fancifulName");
        }
        if(beverageType.isSelected()){
            paramList.add("beverageType");
        }else if  (paramList.isEmpty()){
            paramList.add("brandName");
            paramList.add("fancifulName");
            paramList.add("beverageType");
        }
        searchAlgorithmSelection.setParam(paramList);
    }


    public void goSearch(ActionEvent actionEvent) throws Exception {
        RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        if (searchTextField.getText() == null || searchTextField.getText().trim().isEmpty()){
            // TODO Insert here anything you want the app to do when user click search and box is empty

        }
        else {
           // spin.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
            setAlgorithm();
            setSearchParam();
            // toolBarController.transferSearchInfo(searchAlgorithmSelection.run(searchTextField.getText().trim()));
            // toolBarController.goSearch(actionEvent);



            if (toggleGroupValue.equals("user_search")) {
                try {
                    if (toolBarController.getCurUser().getUserType().equals(User.userPower.SuperAdmin)) {
                        toolBarController.setResultsMapUser(searchAlgorithmSelection.run(searchTextField.getText().trim()));
                        toolBarController.goUserSearch();
                        System.out.println("going to user Search");
                    }
                }
                catch(Exception e){
                    //do nothing
                }

            }else {
                toolBarController.setResultsMap(searchAlgorithmSelection.run(searchTextField.getText().trim()));
                toolBarController.goSearch();
            }

            // TODO:this will return what fuzzys return
            // TODO link the return of the fuzzy alghoriths to a listView on the next page (maybe just have field of hashmap that gets passed to the next scene)

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        spin.setMaxSize(0,0);
        toolBarController = ToolBarController.getInstance();
        if (toolBarController.getCurUser() != null){
            if(toolBarController.getCurUser().getUserType() != User.userPower.SuperAdmin){
                algorithm3.setOpacity(0);
                algorithm3.setDisable(true);
            }
            else if (toolBarController.getCurUser().getUserType() == User.userPower.SuperAdmin){
                algorithm3.setOpacity(1);
                algorithm3.setDisable(false);
            }
        }
        else {
            algorithm3.setOpacity(0);
            algorithm3.setDisable(true);
        }

    }

}
