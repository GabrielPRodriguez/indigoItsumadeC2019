package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import edu.wpi.cs3733c19.teamI.Entities.sub_Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

import static edu.wpi.cs3733c19.teamI.Main.Results;
//import javafx.scene.shape.;



public class SearchDisplayController {

    private Scene searchScene;
    //public SearchResults currentResults = new SearchResults();
    private HashMap<String, Text> ParamToTextFields;
    private final ObservableList<sub_Form> DisplayedResults = FXCollections.observableArrayList();
    public String StringTest;
    //currentResults =
    private Scene homePage;

    @FXML
    Button returnToSearch;

    @FXML
    TextField randomText;

    //Table Stuff

    @FXML
    TableView ResultsList;

    @FXML
    TableColumn SerialNumber;

    @FXML
    TableColumn SourceOfProduct;

    @FXML
    TableColumn BrandName;

    @FXML
    TableColumn BeverageType;

    @FXML
    TableColumn AlcoholContent;


    //Search param inputs

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
//iterate through text fields
        for(int i = 0; i<Results.getParameters().size(); i++)
        {
            String currentField = Results.getParameters().get(i);
            ParamToTextFields.get(currentField).setText(Results.getSearchParamEntry(currentField));
        }
        for(int i = 0; i<Results.getListOfForms().size(); i++)
        {
            DisplayedResults.add(new sub_Form(Results.getListOfForms().get(i)));
        }
        //this.DisplayedResults = FXCollections.observableArrayList();

        this.ResultsList.setEditable(true);
       // ResultsList.getItems().clear();

        this.SerialNumber.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("serialNumber"));
        this.SourceOfProduct.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("domesticOrImported"));
        this.BrandName.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("brandName"));
        this.BeverageType.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("beverageType"));
        this.AlcoholContent.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("alcoholContent"));
        this.ResultsList.setItems(DisplayedResults);



        //    this.ResultsList.

    }

    public SearchDisplayController(){

    }


    public void openHome(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(homePage);
    }
}
