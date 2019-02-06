package edu.wpi.cs3733c19.teamI.Entities;

import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;

import java.util.HashMap;

public class sub_Form {


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


    public String returnAll()
    {
        String CSV = this.repID + "," +this.plantRegistry + "," +this.domesticOrImported + "," +this.serialNumber + "," +this.beverageType + ","
                +this.brandName + "," +this.fancifulName + "," + this.vintage + "," +this.grapeVarietals
                + this.pHValue + "," + this.wineAppellation + "," + this.alcoholContent+ "," + this.phoneNumber+ "," +
                this.email + "\n";
        return CSV;
    }

    public sub_Form(HashMap<String, ReturnedValue> entry){
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
    }
}

