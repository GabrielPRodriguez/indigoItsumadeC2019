package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import edu.wpi.cs3733c19.teamI.Algorithms.LFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.SQLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.UserSearch;
import edu.wpi.cs3733c19.teamI.Algorithms.fuzzyContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdvancedSearchController implements Initializable {

    private fuzzyContext searchAlgorithmSelection = new fuzzyContext();

    @FXML
    JFXSpinner spin;

    @FXML
    JFXComboBox fieldSelector1;

    @FXML
    JFXComboBox fieldSelector2;

    @FXML
    JFXComboBox fieldSelector3;

    @FXML
    JFXButton search;

    @FXML
    TextField searchBar;

    @FXML
    ToggleGroup algorithmGroup;

    @FXML
    RadioButton levenshteinRad;

    @FXML
    RadioButton sqlRad;

    MongoDriver querydata = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
    //SQLDriver querydata = new SQLDriver();

    public void spinnerVisible()
    {
        spin.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
    }

    HashMap<String, String > hmap = new HashMap<String,String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spin.setMaxSize(0,0);

        Label formID = new Label("Form ID Number");
        Label repID = new Label("COLA Representative ID Number");
        Label plantRegistry = new Label("Plant Registry Number");
        Label domestic = new Label("Domestic");
        Label imported = new Label("Imported");
        Label serialNumber = new Label("Serial Number");
        Label brandName = new Label("Brand Name");
        Label beverageType = new Label("Type of Beverage");
        Label fancifulName = new Label("Name other than Brand Name");
        Label permitName = new Label("Permit Name");
        Label streetAddress = new Label("Street Address of Applicant");
        Label city = new Label("City of Applicant");
        Label state = new Label("State of Applicant");
        Label zip = new Label("Zip Code of Applicant");
        Label extraInfo = new Label("Any Information Embossed Onto the Packaging");
        Label dateOfApplicaiton = new Label("Application Date");
        Label formula = new Label("Formula of Alcohol");
        Label grapeVarietals = new Label("Grape Varietal(s)");
        Label vintage = new Label("Vintage Year");
        Label wineAppellation = new Label("Wine Appellation");
        Label email = new Label("Email Address of Applicant");
        Label phoneNumber = new Label("Phone Number of Applicant");
        Label pHValue = new Label("PH of Wine");
        Label alcoholContent = new Label("Alcohol Percentage");
        Label status = new Label("Status of Application");
        Label approvingUser = new Label("Approving User of TTB");
        Label approvalDate = new Label("Date of Application Approval");
        Label expirationDate = new Label("Expiration Date of Application");
        Label issuedDate = new Label("Date License was Issued");
        Label volume = new Label("Volume of Alcohol");
        Label appType = new Label("Type of Application");
        Label surrenderDate = new Label("Date of Application Surrender");
        Label qualification = new Label("Qualification of Application");


        hmap.put("Form ID Number", "formID");
        hmap.put("COLA Representative ID Number", "repID");
        hmap.put("Plant Registry Number", "plantRegistry");
        hmap.put("Domestic", "domestic");
        hmap.put("Imported", "imported");
        hmap.put("Serial Number", "serialNumber");
        hmap.put("Brand Name", "brandName");
        hmap.put("Type of Beverage", "beverageType");
        hmap.put("Name other than Brand Name", "fancifulName");
        hmap.put("Permit Name", "permitName");
        hmap.put("Street Address of Applicant", "streetAddress");
        hmap.put("City of Applicant", "city");
        hmap.put("State of Applicant", "state");
        hmap.put("Zip Code of Applicant", "zip");
        hmap.put("Any Information Embossed Onto the Packaging", "extraInfo");
        hmap.put("Application Date", "dateOfApplicaiton");
        hmap.put("Formula of Alcohol","formula");
        hmap.put("Grape Varietal(s)","grapeVarietals");
        hmap.put("Vintage Year","vintage");
        hmap.put("Wine Appellation","wineAppellation");
        hmap.put("Email Address of Applicant","email");
        hmap.put("Phone Number of Applicant","phoneNumber");
        hmap.put("PH of Wine","pHValue");
        hmap.put("Alcohol Percentage","alcoholContent");
        hmap.put("Status of Application","status");
        hmap.put("Approving User of TTB","approvingUser");
        hmap.put("Date of Application Approval","approvalDate");
        hmap.put("Expiration Date of Application","expirationDate");
        hmap.put("Date License was Issued","issuedDate");
        hmap.put("Volume of Alcohol","volume");
        hmap.put("Type of Application","appType");
        hmap.put("Date of Application Surrender","surrenderDate");
        hmap.put("Qualification of Application","qualification");


        ObservableList<Label> labelList =
                FXCollections.observableArrayList(
                        formID,
                        repID,
                        plantRegistry,
                        domestic,
                        imported,
                        serialNumber,
                        brandName,
                        beverageType,
                        fancifulName,
                        permitName,
                        streetAddress,
                        city,
                        state,
                        zip,
                        extraInfo,
                        dateOfApplicaiton,
                        formula,
                        grapeVarietals,
                        vintage,
                        wineAppellation,
                        email,
                        phoneNumber,
                        pHValue,
                        alcoholContent,
                        status,
                        approvingUser,
                        approvalDate,
                        expirationDate,
                        issuedDate,
                        volume,
                        appType,
                        surrenderDate,
                        qualification

                );

        fieldSelector1.setItems(labelList);
        fieldSelector2.setItems(labelList);
        fieldSelector3.setItems(labelList);
    }


    private ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    public void setAlgorithm(){
        RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
        String toggleGroupValue = selectedRadioButton.getText();

        if (toggleGroupValue.equals("SQL")){
            searchAlgorithmSelection.setContext(new SQLFuzzy());

        }else if(toggleGroupValue.equals("Levenshtein")){
            searchAlgorithmSelection.setContext(new LFuzzy());

        }


    }

    public void setSearchParam(){
        ArrayList<String> paramList = new ArrayList<String>();
        paramList.add(((Label)fieldSelector1.getValue()).getText());
        paramList.add(((Label)fieldSelector2.getValue()).getText());
        paramList.add(((Label)fieldSelector3.getValue()).getText());
        searchAlgorithmSelection.setParam(paramList);
    }



    public void performSearch(ActionEvent event) throws Exception {
       RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
       String toggleGroupValue = selectedRadioButton.getText();

       System.out.println("Field Selector1: " + ((Label)fieldSelector1.getValue()).getText());
       System.out.println("Field Selector2: " + ((Label)fieldSelector2.getValue()).getText());
       System.out.println("Field Selector3: " + ((Label)fieldSelector3.getValue()).getText());


       toolBarController.goSearch();
    }


}
