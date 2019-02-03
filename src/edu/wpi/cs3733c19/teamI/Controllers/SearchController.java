package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.DataField;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedList;

public class SearchController {
    private SearchResults Results = new SearchResults();
    private Scene displayScene;

    // Search Button
    @FXML
    Button performSearch;

    // REP ID NO
    @FXML
    TextField repIDNO;

    // Plant Registry/Basic Permit/ Brewer's NO
    @FXML
    TextField plantRegistry;

    // #3 Domestic or Imported (they work as two separe entities)
    @FXML
    RadioButton domestic;
    @FXML
    RadioButton imported;

    // Serial Number
    @FXML
    TextField serialNumber;

    // Type of Product
    @FXML
    RadioButton wine;
    @FXML
    RadioButton beer;
    @FXML
    RadioButton liqour;

    // Brand Name
    @FXML
    TextField brandNameField;

    // fancifulName
    @FXML
    TextField fancifulName;

    // Vintage (wine only)
    @FXML
    TextField vintage;

    // PH Level (Wine only)
    @FXML
    TextField phLevel;

    // Alcohol Percentage
    @FXML
    TextField alcoholPercentage;

    // Formula
    @FXML
    TextField formula;

    // Grape Variental(s) (wine only)
    @FXML
    TextField grapeVarietal;

    // Wine Appellation (if on label)
    @FXML
    TextField wineAppellation;

    // Phone Number
    @FXML
    TextField phoneNumber;

    // Email Address
    @FXML
    TextField emailAddress ;

    /*
    @FXML
    protected void fillSearchParam()
    {
        LinkedList<DataField> userParam = new LinkedList<>();
        DataField brand = new DataField(brandNameField.getText(),"brandName");
        userParam.add(brand);
//
        Results.gatherSearchParam(userParam);

        System.out.println(Results.getParameters().getFirst());

    }
*/


    public void setDisplayScene(Scene scene) {
        displayScene = scene;
    }

    public void openDisplayScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(displayScene);
    }



}
