package edu.wpi.cs3733c19.teamI.Entities;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import javafx.scene.image.Image;

import java.util.HashMap;

public class FormWorkflow {

    private String repID; // true
    private String plantRegistry; // true
    private String domesticOrImported; // true
    private String serialNumber; // true
    private String brandName; // true
    private String beverageType; // true
    private String fancifulName; // true
    private String streetAddress; // true
    private String extraInfo; // true
    private String dateOfApplication; // true
    private String formula; // true
    private String grapeVarietals; // true
    private String vintage; // true
    private String wineAppellation; // true
    private String email; // true
    private String phoneNumber; // true
    private String pHValue; // true
    private String alcoholContent; // true
    private String status; // true
    private String volume; // true
    private String city; // true
    private String state; // true
    private String zip; // true
    private String permitname; // true
    // private Image front_Upload; //
    // private Image back_Upload; //
    private String qualifier; // true
    private String name; // true
    private int index;
    private String form_ID;
    private String expireDate;
    private String approvingUser;
    private String approvalDate;
    private String issuedDate;
    private String surrenderDate;
    private String appType;


    public FormWorkflow(HashMap<String, ReturnedValue> entry, int index){
        this.form_ID = entry.get("formID").to_string();
        this.repID = entry.get("repID").to_string();
        this.plantRegistry = entry.get("plantRegistry").to_string();
        this.domesticOrImported = entry.get("domesticOrImported").to_string();
        this.serialNumber = entry.get("serialNumber").to_string();
        this.brandName = entry.get("brandName").to_string();
        this.beverageType = entry.get("beverageType").to_string();
        this.fancifulName = entry.get("fancifulName").to_string();
        this.streetAddress = entry.get("streetAddress").to_string();
        this.extraInfo = entry.get("extraInfo").to_string();
        this.dateOfApplication = entry.get("dateOfApplication").to_string();
        this.formula = entry.get("formula").to_string();
        this.grapeVarietals = entry.get("grapeVarietals").to_string();
        this.vintage = entry.get("vintage").to_string();
        this.wineAppellation = entry.get("wineAppellation").to_string();
        this.email = entry.get("email").to_string();
        this.phoneNumber = entry.get("phoneNumber").to_string();
        this.pHValue = entry.get("pHValue").to_string();
        this.alcoholContent = entry.get("alcoholContent").to_string();
        this.status = entry.get("status").to_string();
        this.volume = entry.get("volume").to_string();
        this.city = entry.get("city").to_string();
        this.state = entry.get("state").to_string();
        this.zip = entry.get("zip").to_string();
        this.permitname = entry.get("permitName").to_string();
        this.qualifier = entry.get("qualifier").to_string();
        this.name = entry.get("name").to_string();
        this.expireDate = entry.get("expirationDate").to_string();
        this.approvingUser = entry.get("approvingUser").to_string();
        this.approvalDate = entry.get("approvalDate").to_string();
        this.issuedDate = entry.get("issuedDate").to_string();
        this.surrenderDate = entry.get("surrenderDate").to_string();
        this.appType = entry.get("appType").to_string();


        this.index = index;
    }


    public String getRepID() {
        return repID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public String getPlantRegistry() {
        return plantRegistry;
    }

    public void setPlantRegistry(String plantRegistry) {
        this.plantRegistry = plantRegistry;
    }

    public String getDomesticOrImported() {
        return domesticOrImported;
    }

    public void setDomesticOrImported(String domesticOrImported) {
        this.domesticOrImported = domesticOrImported;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBeverageType() {
        return beverageType;
    }

    public void setBeverageType(String beverageType) {
        this.beverageType = beverageType;
    }

    public String getFancifulName() {
        return fancifulName;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(String dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getGrapeVarietals() {
        return grapeVarietals;
    }

    public void setGrapeVarietals(String grapeVarietals) {
        this.grapeVarietals = grapeVarietals;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public String getWineAppellation() {
        return wineAppellation;
    }

    public void setWineAppellation(String wineAppellation) {
        this.wineAppellation = wineAppellation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getpHValue() {
        return pHValue;
    }

    public void setpHValue(String pHValue) {
        this.pHValue = pHValue;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPermitname() {
        return permitname;
    }

    public void setPermitname(String permitname) {
        this.permitname = permitname;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getForm_ID() {
        return form_ID;
    }

    public void setForm_ID(String form_ID) {
        this.form_ID = form_ID;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getApprovingUser() {
        return approvingUser;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public String getSurrenderDate() {
        return surrenderDate;
    }
    public String getAppType(){
        return appType;
    }
}
