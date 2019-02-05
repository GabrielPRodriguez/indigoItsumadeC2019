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
import javafx.scene.control.RadioButton;

import java.util.LinkedList;

public class SearchController {
    private SearchResults Results = new SearchResults();
    private Scene displayScene;
    public SearchResults get_results(){
        return Results;
    }
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
    protected void fillSearchParam() throws Exception
    {
        LinkedList<DataField> userParam = new LinkedList<>();
        for(int i = 0; i <anchor.getChildren().size(); i++) {
            Node element = anchor.getChildren().get(i);
            if(element instanceof TextField) {
                String information = ((TextField)element).getText();
                if(information.isEmpty() == false) {
                    DataField Entry = new DataField(information, element.getId());
                    userParam.add(Entry);
                }
            }
            else if(element instanceof RadioButton)
            {
               RadioButton radio = ((RadioButton)element);
                if(radio.isSelected()){
                    if((radio.getId() == "liquor") ||(radio.getId() == "beer") || (radio.getId() == "wine")) {
                        DataField Entry = new DataField(radio.getId(), "beverageType");
                        userParam.add(Entry);
                    }
                    else{
                        DataField Entry = new DataField(radio.getId(), "domesticOrImported");
                        userParam.add(Entry);
                    }
                }
            }
        }
        if(userParam.isEmpty() == false) {
            System.out.println("userParam below");
            System.out.println(userParam);
            Results.gatherSearchParam(userParam);
            System.out.println(Results.getParameters().getFirst());
        }


    }


    public void setDisplayScene(Scene scene) {
        displayScene = scene;
    }

    public void openDisplayScene(ActionEvent actionEvent) throws Exception{
        fillSearchParam();
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(displayScene); // See the search results class for information about query returns
    }



}
