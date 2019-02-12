package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Controllers2.ToolBarController;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
//import edu.wpi.cs3733c19.teamI.Algorithms.DLFuzzy;
//import edu.wpi.cs3733c19.teamI.Algorithms.LFuzzy;
//import edu.wpi.cs3733c19.teamI.Algorithms.SQLFuzzy;
//import edu.wpi.cs3733c19.teamI.Algorithms.fuzzyContext;
import edu.wpi.cs3733c19.teamI.Controllers.SQLDriver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;
public class HomeController extends ToolBarController {

public class HomeController implements Initializable {

    private Scene submissionScene;
    private Scene searchScene;
    private Scene work;
    private Scene loginScene;
    private fuzzyContext searchAlgorithmSelection = new fuzzyContext();


    @FXML
    Button search; // TODO make sure this is actually the advanced button to change scenes to the advanced search

    @FXML
    Button submit;

    @FXML
    Button login;

    @FXML
    Label emptyLabel;

    @FXML
    JFXTextField searchField; // TODO make sure we set the right ID on the Scene Builder side

    @FXML
    Button fuzzSearch; // TODO we have to assign this to the fuzzy search button


    @FXML
    ToggleGroup group; // TODO Make sure that the radio buttons are all added to a toggle group called group
    @FXML
    RadioButton algorithm1; // TODO make sure ID on the SceneBuilder side matches
    @FXML
    RadioButton algorithm2; // TODO make sure ID on the SceneBuilder side matches
    @FXML
    RadioButton algorithm3; // TODO make sure ID on the SceneBuilder side matches

    public void setSubmission(Scene scene) {
        submissionScene = scene;
    }
    public void setSearch(Scene scene) {
        searchScene = scene;
    }
    public void setWork(Scene scene) {
        work = scene;
    }
    public void setLogin(Scene scene) {
        loginScene = scene;
    }

    @FXML
    public void openSubmission(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(submissionScene);
    }

    public void openWork(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(work);
    }

    public void openSearch(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(searchScene); //test changes
    }

    public void openForm(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(submissionScene);
    }

    public void openLogin(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
    }

    @FXML
    public void setAlgorithm(){
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        if (toggleGroupValue.equals("algorithm1")){  // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new SQLFuzzy());
        }else if(toggleGroupValue.equals("algorithm2")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new DLFuzzy());
        }else if(toggleGroupValue.equals("algorithm2")){ // TODO Change the name in the string to the actual text of the radio button
            searchAlgorithmSelection.setContext(new LFuzzy());
        }

    }
    // TODO make sure this method is linked up to the search button on the FXML side
    @FXML
    public void startSearch(ActionEvent actionEvent){
        if (searchField.getText() == null || searchField.getText().trim().isEmpty()){
            // TODO set a label in Homepage SceneBuilder below the TextField, the Label is empty until the user preses search and TextField is empty
            // emptyLabel.setText("No search text entered");
            // emptyLabel.setTextFill(Color.web("#FF0000"));
        }else {
            setAlgorithm();
            searchAlgorithmSelection.run(searchField.getText());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
