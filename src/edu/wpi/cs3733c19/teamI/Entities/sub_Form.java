package edu.wpi.cs3733c19.teamI.Entities;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.LinkedList;

//this class is used to more easily represent data in our tableview on the results UI. Simply contains all attributes
//a form can have
//TODo evaluate if it is safe to switch this out for the standard form class
public class sub_Form {

//form attributes as strings
    private String repID;
    private String plantRegistry;
    private String domesticOrImported;
    private String serialNumber;
    private String beverageType;
    private String brandName;
    private String fancifulName;
    private String vintage;
    private String grapeVarietals;
    private String pHValue;
    private String wineAppellation;
    private String alcoholContent;
    private String phoneNumber;
    private String email;
    private int index;
    private String form_ID;

    //getters
    public String getRepID() {
        return repID;
    }

    public String getPlantRegistry() {
        return plantRegistry;
    }

    public String getDomesticOrImported() {
        return domesticOrImported;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getBeverageType() {
        return beverageType;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getFancifulName() {
        return fancifulName;
    }

    public String getVintage() {
        return vintage;
    }

    public String getGrapeVarietals() {
        return grapeVarietals;
    }

    public String getpHValue() {
        return pHValue;
    }

    public String getWineAppellation() {
        return wineAppellation;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getForm_ID() {return form_ID;}

    public int getIndex(){return index;}

    //setters
    public void setRepID(String repID) {
        this.repID = repID;
    }

    public void setPlantRegistry(String plantRegistry) {
        this.plantRegistry = plantRegistry;
    }

    public void setDomesticOrImported(String domesticOrImported) {
        this.domesticOrImported = domesticOrImported;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setBeverageType(String beverageType) {
        this.beverageType = beverageType;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public void setGrapeVarietals(String grapeVarietals) {
        this.grapeVarietals = grapeVarietals;
    }

    public void setpHValue(String pHValue) {
        this.pHValue = pHValue;
    }

    public void setWineAppellation(String wineAppellation) {
        this.wineAppellation = wineAppellation;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndex(int index) { this.email = email; }

    public void setForm_ID(String form_ID) { this.form_ID = form_ID; }

    //this function is used to generate the CSV download. It returns every form field as a list of string
    // seperated by commas
    public String returnAll(String delim)
    {
        String CSV = this.repID + delim +this.plantRegistry + delim +this.domesticOrImported + delim +this.serialNumber + delim +this.beverageType + delim
                +this.brandName + delim +this.fancifulName + delim + this.vintage + delim +this.grapeVarietals
                + this.pHValue + delim + this.wineAppellation + delim + this.alcoholContent+ delim + this.phoneNumber+ delim +
                this.email + "\n";
        return CSV;
    }

    public String returnColumnNames(String delim){
        String csvColumn = "RepID" + delim + "Plant Registry" + delim + "Domestic/Imported" + delim + "Serial Number" + delim + "Beverage Type" + delim
                + "Brand Name" + delim + "Fanciful Name" + delim + "Vintage" + delim + "Grape Varientals" + delim + "pH Value" + delim + "Wine Appellation"
                + delim + "Alcohol Content" + delim + "Phone Number" + delim + "Email";
        return csvColumn;
    }

    public ObservableList<String> getSummary(){
        ObservableList<String> list_of_Param = FXCollections.observableArrayList();
        list_of_Param.add(this.getForm_ID());
        list_of_Param.add("RepID: " + this.getRepID());
        list_of_Param.add("PlantRegistry: " + this.getPlantRegistry());
        list_of_Param.add("Origin: " + this.getDomesticOrImported());
        list_of_Param.add("Serial Number: " + this.getSerialNumber());
        list_of_Param.add("Type: " + this.getBeverageType());
        list_of_Param.add("Brand Name: " + this.getBrandName());
        list_of_Param.add("Fanciful Name: " + this.getFancifulName());
        list_of_Param.add("Vintage: " + this.getVintage());
        list_of_Param.add("Grape Varietals: " + this.getGrapeVarietals());
        list_of_Param.add("PH Value: " + this.getpHValue());
        list_of_Param.add("Wine Appellation: " + this.getWineAppellation());
        list_of_Param.add("Alcohol Content: " + this.getAlcoholContent());
        return(list_of_Param );

    }

    //constructor. This constructor takes a hasmap as an input, so we can quickly move between the DB query return type
    // and displayable values
    public sub_Form(HashMap<String, ReturnedValue> entry, int index){
        this.form_ID = entry.get("formID").to_string();
        this.repID = entry.get("repID").to_string();
        this.plantRegistry = entry.get("plantRegistry").to_string();
        this.domesticOrImported = entry.get("domesticOrImported").to_string();
        this.serialNumber = entry.get("serialNumber").to_string();
        this.beverageType = entry.get("beverageType").to_string();
        this.brandName = entry.get("brandName").to_string();
        this.fancifulName = entry.get("fancifulName").to_string();
        this.vintage = entry.get("vintage").to_string();
        this.grapeVarietals = entry.get("grapeVarietals").to_string();
        this.pHValue = entry.get("pHValue").to_string();
        this.wineAppellation = entry.get("wineAppellation").to_string();
        this.alcoholContent = entry.get("alcoholContent").to_string();
        this.phoneNumber = entry.get("phoneNumber").to_string();
        this.email = entry.get("email").to_string();
        this.index = index;
    }
}

