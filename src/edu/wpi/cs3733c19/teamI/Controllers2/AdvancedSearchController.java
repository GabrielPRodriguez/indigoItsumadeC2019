package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import edu.wpi.cs3733c19.teamI.Algorithms.LFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.SQLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.UserSearch;
import edu.wpi.cs3733c19.teamI.Algorithms.fuzzyContext;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.User;
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

import java.lang.reflect.Array;
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
    TextField searchBox1;

    @FXML
    TextField searchBox2;

    @FXML
    TextField searchBox3;

    @FXML
    ToggleGroup algorithmGroup;

    @FXML
    RadioButton levenshteinRad;

    @FXML
    RadioButton sqlRad;

    //MongoDriver querydata = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
    SQLDriver querydata = new SQLDriver();

    public void spinnerVisible()
    {
        spin.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
    }

    HashMap<String, String> hmap = new HashMap<>();
    HashMap<String, Integer> hmap2 = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spin.setMaxSize(0,0);

        Label formID = new Label("Form ID Number");
        Label repID = new Label("COLA Representative ID Number");
        Label plantRegistry = new Label("Plant Registry Number");
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

        hmap2.put("Form ID Number", 1);
        hmap2.put("COLA Representative ID Number", 2);
        hmap2.put("Plant Registry Number", 3);
        hmap2.put("Serial Number", 1);
        hmap2.put("Brand Name", 1);
        hmap2.put("Type of Beverage", 2);
        hmap2.put("Name other than Brand Name", 2);
        hmap2.put("Permit Name", 3);
        hmap2.put("Street Address of Applicant", 3);
        hmap2.put("City of Applicant", 2);
        hmap2.put("State of Applicant", 2);
        hmap2.put("Zip Code of Applicant", 2);
        hmap2.put("Any Information Embossed Onto the Packaging", 3);
        hmap2.put("Application Date", 3);
        hmap2.put("Formula of Alcohol",3);
        hmap2.put("Grape Varietal(s)",3);
        hmap2.put("Vintage Year",2);
        hmap2.put("Wine Appellation",3);
        hmap2.put("Email Address of Applicant",3);
        hmap2.put("Phone Number of Applicant",3);
        hmap2.put("PH of Wine",3);
        hmap2.put("Alcohol Percentage",2);
        hmap2.put("Status of Application",1);
        hmap2.put("Approving User of TTB",1);
        hmap2.put("Date of Application Approval",2);
        hmap2.put("Expiration Date of Application",1);
        hmap2.put("Date License was Issued",1);
        hmap2.put("Volume of Alcohol",1);
        hmap2.put("Type of Application",3);
        hmap2.put("Date of Application Surrender", 3);
        hmap2.put("Qualification of Application",3);



        ObservableList<Label> labelList =
                FXCollections.observableArrayList(
                        formID,
                        repID,
                        plantRegistry,
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



    public void performSearch(ActionEvent event) throws Exception {
       SQLDriver bigDriver = new SQLDriver();
       ArrayList<HashMap<String, ReturnedValue>> derek = new ArrayList<>();
       RadioButton selectedRadioButton = (RadioButton) algorithmGroup.getSelectedToggle();
       String toggleGroupValue = selectedRadioButton.getText();


        ArrayList<String> search3= new ArrayList<>();
        ArrayList<String> search2 = new ArrayList<>();
        ArrayList<String> search1= new ArrayList<>();

        try {
            if (!fieldSelector3.getValue().equals("")) {
                search3.add(hmap.get(((Label) fieldSelector3.getValue()).getText()));
            }
        }
        catch (Exception e) {

        }

        try {
            if (!fieldSelector2.getValue().equals("")) {
                search2.add(hmap.get(((Label) fieldSelector2.getValue()).getText()));
            }
        }
        catch (Exception e)
        {

        }

        try {
            if (!fieldSelector1.getValue().equals("")) {
                search1.add(hmap.get(((Label) fieldSelector1.getValue()).getText()));
            }
        }
        catch (Exception e){

        }

        try {
            if (search2.size() < 1) {
                search2.add(search1.get(0));
                search1.clear();
            }
        }
        catch (Exception e){

        }

        try {
            if (search3.size() < 1) {
                search3.add(search2.get(0));
                search2.clear();
            }
        }
        catch (Exception e)
        {

        }
        String s1 = searchBox1.getText();
        String s2 = searchBox2.getText();
        String s3 = searchBox3.getText();
        if (s2.equals("")){
            s2 = s1;
        }
        if (s3.equals("")){
            s3 = s2;
        }
        if (s2.equals("")){
            s2 = s1;
        }





        derek = bigDriver.search_for_l_multiple("form_data", "stringified_ids_db.db", search3, s3, 75000);

       try{
           int maxLength2 = hmap2.get(search2.get(0));
           derek = bigDriver.search_for_l_Rasheeda(derek, search2, s2, maxLength2);
       }
       catch (Exception e){
           //does nothing
        }
       try{
           int maxLength3 = hmap2.get(search3.get(0));
           derek = bigDriver.search_for_l_Rasheeda(derek, search1, s1, maxLength3);
       }
       catch(Exception e){
           //s=also dontohgin
        }

       toolBarController.setResultsMap(derek);
       toolBarController.goSearch();
    }



    public void setSearchParam(){

    }




}
