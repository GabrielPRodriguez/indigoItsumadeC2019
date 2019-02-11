package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Controllers2.ToolBarController;
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

import static edu.wpi.cs3733c19.teamI.Main.Results;

// controller for the search screen UI
public class SearchController extends ToolBarController {
    private Scene displayScene; // scene that we use to display results
    public SearchDisplayController dispController; //controller of the display screen, needed to access update functions
    private Scene homePage; //home page that we may want to switch back to


    //Anchor pane
    @FXML
    AnchorPane anchor;

    // Search Button
    @FXML
    Button performSearch;

    @FXML
    Button goHome;

    @FXML
    Button clearSearch;

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
    RadioButton Wine;
    @FXML
    RadioButton Beer;
    @FXML
    RadioButton Liqour;

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

    //following function iterates through all elements on search page and determines if they are a control
    //if they are, it uses the fxID as the parameter name and the data as the paramter data
    //TODO was a terrible idea this needs to be handled differently ASAP, at very least move all search controls to seprate anchor

    @FXML
    protected void fillSearchParam() throws Exception
    {
        LinkedList<DataField> userParam = new LinkedList<>();
        for(int i = 0; i <anchor.getChildren().size(); i++) {
            Node element = anchor.getChildren().get(i); //get next UI element
            if(element instanceof TextField) { //if its a text input, grab text
                String information = ((TextField)element).getText();
                if(information.isEmpty() == false) { //if it has info, create data field representing search parameter
                    DataField Entry = new DataField(information, element.getId());
                    userParam.add(Entry); //add parameter to our list of parameters
                }
            }
            else if(element instanceof RadioButton) //special case for radio buttons
            {
               RadioButton radio = ((RadioButton)element);
                if(radio.isSelected()){ //if input has been selected
                    if((radio.getText().equals("Liquor")) ||(radio.getText().equals("Beer")) || (radio.getText().equals("Wine"))) {
                        //if it is for beverage type, add to search param accoridngly
                        DataField Entry = new DataField(radio.getText(), "beverageType");
                        userParam.add(Entry);
                    }
                    else{ //TODO this should be more restrictive, what if other radio buttons are added?
                        //otherwise, radio button represent domestic or imported, add accordingly
                        DataField Entry = new DataField(radio.getText(), "domesticOrImported");
                        userParam.add(Entry);
                    }
                }
            }
        }
        if(userParam.isEmpty() == false) { //if we have search parameters, perform search
            Results.gatherSearchParam(userParam); //results references global searchResults instance
        }


    }

    //this function clears all search parameter inputs
    @FXML
    protected void clearSearchResult() throws Exception {
        repID.clear();
        plantRegistry.clear();
        domestic.setSelected(false);
        imported.setSelected(false);
        serialNumber.clear();
        Wine.setSelected(false);
        Beer.setSelected(false);
        Liqour.setSelected(false);
        brandName.clear();
        fancifulName.clear();
        vintage.clear();
        pHValue.clear();
        alcoholContent.clear();
        formula.clear();
        grapeVarietals.clear();
        wineAppellation.clear();
        phoneNumber.clear();
        email.clear();

    }

//setter for display screen. used in Main()
    public void setDisplayScene(Scene scene) {
        displayScene = scene;
    }

    //this function performs a search, populates the table view, and then shows the results screen
    public void openDisplayScene(ActionEvent actionEvent) throws Exception{
        dispController.clearPage();//first, clear previous search result display
        fillSearchParam();//perform search
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();//get stage
        primaryStage.setScene(displayScene); // show display screen
        dispController.updateSearchDisplay(); //update table view
    }
    
    //setter used to define what the home page is, used in Main()
    public void setHomePage(Scene homePage) {
        this.homePage = homePage;
    }

    //returns the SearchResults global instance
    public SearchResults get_results(){
        return Results;
    }

    //function displays the home screen
    public void openHome(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow(); //get current stage
        primaryStage.setScene(homePage); //display hompage
    }




}
