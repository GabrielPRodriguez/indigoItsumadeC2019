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
//import javafx.scene.shape.;

public class SearchDisplayController extends SearchController{

    private Scene searchScene;
    public SearchResults currentResults = Results;
    private HashMap<String, String> ParamToTextFields;
    //currentResults =

    @FXML
    Button returnToSearch;

    @FXML
    TextField randomText;

    @FXML
    ListView ResultsDisplay;

    /*

     */

    @FXML
    Text repIDNo;

    @FXML
    Text Plant;

    @FXML
    Text Domestic;

    @FXML
    Text SerialNo;

    @FXML
    Text TypeOfProd;

    @FXML
    Text BrandName;

    @FXML
    Text FancName;

    @FXML
    Text Vintage;

    @FXML
    Text GrapeVarietal;

    @FXML
    Text PHLevel;

    @FXML
    Text WineAppell;

    @FXML
    Text AlcoholPerc;

    @FXML
    Text PhoneNum;

    @FXML
    Text Email;






    public void setSearchScene(Scene scene)
    {
        searchScene = scene;
    }

    public void openSearchScene(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(searchScene);
    }

    public SearchDisplayController(){
        this.ParamToTextFields = new HashMap<String, String>();
        //TODO replace with for loop, search for text pre-req
        this.ParamToTextFields.put("repIDNo", "N.A.");
        this.ParamToTextFields.put("Plant", "N.A.");
        this.ParamToTextFields.put("Domestic", "N.A.");
        this.ParamToTextFields.put("SerialNo", "N.A.");
        this.ParamToTextFields.put("TypeOfProd", "N.A.");
        this.ParamToTextFields.put("BrandName", "N.A.");
        this.ParamToTextFields.put("FancName", "N.A.");
        this.ParamToTextFields.put("Vintage", "N.A.");
        this.ParamToTextFields.put("GrapeVarietal", "N.A.");
        this.ParamToTextFields.put("PHLevel", "N.A.");
        this.ParamToTextFields.put("WineAppell", "N.A.");
        this.ParamToTextFields.put("AlcoholPerc", "N.A.");
        this.ParamToTextFields.put("PhoneNum", "N.A.");
        this.ParamToTextFields.put("Email", "N.A.");


    }


}
