package edu.wpi.cs3733c19.teamI.Entities;

import java.util.ArrayList;

public class SearchResults {


    private String repID; //optional
    private String plantRegistry; //optional
    private String domesticOrImported; //required
    private int serialNumber; //required
    private String brandName; //required
    private String beverageType; //required
    private String fancifulName; //optional
    private String nameAndAddress; //required
    private String mailingAddress; //optional
    private String extraInfo; //optional
    private String dateOfApplication; //required (assumed)
    private String nameOfApplicant; //required (assumed)
    private String formula; //optional
    private String grapeVarietals; //for wine only/optional
    private String vintage; //for wine only
    private String wineAppellation; //wine only/optional
    private String email; //optional
    private String phoneNumber; //required
    private double pHValue; //wine only
    private double alcoholContent; //required



    private ArrayList<Form> listOfForms;

    //setters
    protected void setRepID(String ID){
        this.repID = ID;
    }

    protected void setPlantRegistry(String plant){
        this.plantRegistry = plant;
    }

    protected void setDomesticOrImported(String origin) { //TODO change to appropriate data type?
        this.domesticOrImported = origin;
    }





    //getters





    protected void FilterEntries(String field) //TODO filter by Double/ int, or string
    {

    }

    protected void UpdateList()// call DB function to query new forms
    {

    }

    protected void MakeCSV()    //TODO how make csv? what is return
    {

    }


    //Constructor/////////////////////////////////////////////////

    public SearchResults() {
        this.repID = "";
        this.plantRegistry = "";
        this.domesticOrImported = "";
        this.serialNumber = -1;
        this.brandName = "";
        this.beverageType = "";
        this.fancifulName = "";
        this.nameAndAddress = "";
        this.mailingAddress = "";
        this.extraInfo = "";
        this.dateOfApplication = "";
        this.nameOfApplicant = "";
        this.formula = "";
        this.grapeVarietals = "";
        this.vintage = "";
        this.wineAppellation="";
        this.email = "";
        this.phoneNumber = "";
        this.pHValue = -1;
        this.alcoholContent = -1;
    }

}
