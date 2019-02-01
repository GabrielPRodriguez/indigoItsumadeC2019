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
    protected void updateFormList() //TODO this needs to implement database functions
    {

    }



    protected void setRepID(String ID){
        this.repID = ID;
    }

    protected void setPlantRegistry(String plant){
        this.plantRegistry = plant;
    }

    protected void setDomesticOrImported(String origin) { //TODO change to appropriate data type?
        this.domesticOrImported = origin;
    }

    protected void setSerialNumber(String serial)
    {
        try {
            this.serialNumber = Integer.parseInt(serial);
        }
        catch(NumberFormatException nfe) //TODO generate user alert
        {
            this.serialNumber = -1;
        }
    }

    protected void setBrandName(String name)
    {
        this.brandName = name;
    }

    protected void setBeverageType(String type)
    {
        this.beverageType = type;
    }

    protected void setFancifulName(String name)
    {
        this.fancifulName = name;
    }

    protected void setNameAndAddress(String addr) //TODO this may take more formating
    {
        this.nameAndAddress = addr;
    }

    protected void setMailingAddress(String addr)
    {
        this.mailingAddress = addr;
    }

    protected void setextraInfo(String info)
    {
        this.extraInfo = info;
    }

    protected void dateOfApplication(String date)
    {
        this.dateOfApplication = date;
    }

    protected void setNameOfApplicant(String name)
    {
        this.nameOfApplicant = name;
    }

    protected void setFormula(String form)
    {
        this.formula = form;
    }

    protected void setGrapeVarietals(String grape)
    {
        this.grapeVarietals = grape;
    }

    protected void setVintage(String vint)
    {
        this.vintage = vint;
    }

    protected void setWineAppellation(String wine)
    {
        this.wineAppellation = wine;
    }

    protected void setEmail(String mail)
    {
        this.email = mail;
    }

    protected void setPhoneNumber(String num)
    {
        this.phoneNumber = num;
    }

    protected void setPHValue(String PH) //TODO I assume there are bounds for a PH val
    {
        try
        {
            this.pHValue = Integer.parseInt(PH);
        }
        catch(NumberFormatException nfe) //TODO generate user alert
        {
            this.pHValue = -1;
        }
    }

    protected void setAlcoholContent(String percent)
    {
        try
        {
            this.alcoholContent = Integer.parseInt(percent);
        }
        catch(NumberFormatException nfe) //TODO generate user alert
        {
            this.alcoholContent = -1;
        }
    }




    //getters TODO generate getter functions





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


        this.listOfForms = new ArrayList<>();
    }

}
