package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.DataField;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedList;

public class SearchController {
    private SearchResults Results = new SearchResults();
    private Scene displayScene;

    //Anchor pane
    @FXML
    AnchorPane anchor;

    // Search Button
    @FXML
    Button performSearch;

    // REP ID NO
    @FXML
    TextField repID;

    // Plant Registry/Basic Permit/ Brewer's NO
    @FXML
    TextField plantRegistry;

    // #3 domesticOrImported (they work as two separe entities)
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
    TextField brandName;

    // fancifulName
    @FXML
    TextField fancifulName;

    // Vintage (wine only)
    @FXML
    TextField vintage;

    // PH Level (Wine only)
    @FXML
    TextField pHValue;

    // Alcohol Percentage
    @FXML
    TextField alcoholContent;

    // Formula
    @FXML
    TextField formula;

    // Grape Variental(s) (wine only)
    @FXML
    TextField grapeVarietals;

    // Wine Appellation (if on label)
    @FXML
    TextField wineAppellation;

    // Phone Number
    @FXML
    TextField phoneNumber;

    // Email Address
    @FXML
    TextField email;

    @FXML
    protected void fillSearchParam()
    {
        LinkedList<DataField> userParam = new LinkedList<>();
       // System.out.println(anchor.getChildren().size());
        for(int i = 0; i <anchor.getChildren().size(); i++) {
            Node element = anchor.getChildren().get(i);
            if(element instanceof TextField) {
                String information = ((TextField)element).getText();
                System.out.println(information);
                if(information.isEmpty() == false) {
                    DataField Entry = new DataField(information, element.getId());
                    userParam.add(Entry);
                }
            }
        }
        if(userParam.isEmpty() == false) {
            Results.gatherSearchParam(userParam);
            System.out.println(Results.getParameters().getFirst());
        }


    }


    public void setDisplayScene(Scene scene) {
        displayScene = scene;
    }

    public void openDisplayScene(ActionEvent actionEvent) {
        fillSearchParam();
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(displayScene);
    }



}
