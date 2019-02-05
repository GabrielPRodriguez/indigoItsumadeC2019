package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.HashMap;

import static edu.wpi.cs3733c19.teamI.Main.Results;
//import javafx.scene.shape.;

public class SearchDisplayController {

    private Scene searchScene;
    //public SearchResults currentResults = new SearchResults();
    private HashMap<String, Text> ParamToTextFields;
    public String StringTest;
    //currentResults =
    private Scene homePage;

    @FXML
    Button returnToSearch;

    @FXML
    TextField randomText;

    @FXML
    ListView ResultsDisplay;

    /*

     */

    @FXML
    Text repID;

    @FXML
    Text plantRegistry;

    @FXML
    Text domesticOrImported;

    @FXML
    Text serialNumber;

    @FXML
    Text beverageType;

    @FXML
    Text brandName;

    @FXML
    Text fancifulName;

    @FXML
    Text vintage;

    @FXML
    Text grapeVarietals;

    @FXML
    Text pHValue;

    @FXML
    Text wineAppellation;

    @FXML
    Text alcoholContent;

    @FXML
    Text phoneNumber;

    @FXML
    Text email;






    public void setSearchScene(Scene scene)
    {
        searchScene = scene;
    }
    public void setHomePage(Scene scene){
        homePage = scene;
    }

    public void openSearchScene(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(searchScene);
    }

    public void updateSearchDisplay(){

        this.ParamToTextFields = new HashMap<String, Text>();
        //TODO replace with for loop, search for text pre-req
        this.ParamToTextFields.put("repID", this.repID);
        this.ParamToTextFields.put("plantRegistry", this.plantRegistry);
        this.ParamToTextFields.put("domesticOrImported", this.domesticOrImported);
        this.ParamToTextFields.put("serialNumber", this.serialNumber);
        this.ParamToTextFields.put("beverageType", this.beverageType);
        this.ParamToTextFields.put("brandName", this.brandName);
        this.ParamToTextFields.put("fancifulName", this.fancifulName);
        this.ParamToTextFields.put("vintage", this.vintage);
        this.ParamToTextFields.put("grapeVarietals", this.grapeVarietals);
        this.ParamToTextFields.put("pHValue", this.pHValue);
        this.ParamToTextFields.put("wineAppellation", this.wineAppellation);
        this.ParamToTextFields.put("alcoholContent", this.alcoholContent);
        this.ParamToTextFields.put("phoneNumber", this.phoneNumber);
        this.ParamToTextFields.put("email", this.email);


        System.out.println("i am here");
        for(int i = 0; i<Results.getParameters().size(); i++)
        {
            String currentField = Results.getParameters().get(i);
            ParamToTextFields.get(currentField).setText(Results.getSearchParamEntry(currentField));
        }

    }

    public SearchDisplayController(){

//
//
//
//        this.ParamToTextFields = new HashMap<String, Text>();
//        //TODO replace with for loop, search for text pre-req
//        this.ParamToTextFields.put("repID", repID);
//        this.ParamToTextFields.put("plantRegistry", plantRegistry);
//        this.ParamToTextFields.put("domesticOrImported", domesticOrImported);
//        this.ParamToTextFields.put("serialNumber", serialNumber);
//        this.ParamToTextFields.put("beverageType", beverageType);
//        this.ParamToTextFields.put("brandName", brandName);
//        this.ParamToTextFields.put("fancifulName", fancifulName);
//        this.ParamToTextFields.put("vintage", vintage);
//        this.ParamToTextFields.put("grapeVarietals", grapeVarietals);
//        this.ParamToTextFields.put("pHValue", pHValue);
//        this.ParamToTextFields.put("wineAppellation", wineAppellation);
//        this.ParamToTextFields.put("alcoholContent", alcoholContent);
//        this.ParamToTextFields.put("phoneNumber", phoneNumber);
//        this.ParamToTextFields.put("email", email);
//
//
//        System.out.println("i am here");
//        for(int i = 0; i<currentResults.getParameters().size(); i++)
//        {
//            String currentField = currentResults.getParameters().get(i);
//            ParamToTextFields.get(currentField).setText(currentResults.getSearchParamEntry(currentField));
//        }

        //this.repID.setText("Hi!");



    }


    public void openHome(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(homePage);
    }
}
