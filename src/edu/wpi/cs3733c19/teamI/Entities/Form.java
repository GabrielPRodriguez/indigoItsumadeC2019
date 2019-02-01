package edu.wpi.cs3733c19.teamI.Entities;

public class Form {

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

    public Form() {
        this.repID = "";
        this.plantRegistry = "";
        this.domesticOrImported = "";
        this.serialNumber = 0;
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
        this.pHValue = 0;
        this.alcoholContent = 0;
    }



    public Form(String repID, String plantRegistry, String domesticOrImported, int serialNumber, String brandName, String beverageType, String fancifulName, String nameAndAddress, String mailingAddress, String extraInfo, String date, String nameOfApplicant, String formula, String grapeVarietals, String vintage, String wineAppellation, String email, String phoneNumber, double pHValue, double alcoholContent) {
        this.repID = repID;
        this.plantRegistry = plantRegistry;
        this.domesticOrImported = domesticOrImported;
        this.serialNumber = serialNumber;
        this.brandName = brandName;
        this.beverageType = beverageType;
        this.fancifulName = fancifulName;
        this.nameAndAddress = nameAndAddress;
        this.mailingAddress = mailingAddress;
        this.extraInfo = extraInfo;
        this.dateOfApplication = date;
        this.nameOfApplicant = nameOfApplicant;
        this.formula = formula;
        this.grapeVarietals = grapeVarietals;
        this.vintage = vintage;
        this.wineAppellation = wineAppellation;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pHValue = pHValue;
        this.alcoholContent = alcoholContent;
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

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setBeverageType(String beverageType) {
        this.beverageType = beverageType;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }

    public void setNameAndAddress(String nameAndAddress) {
        this.nameAndAddress = nameAndAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setDateOfApplication(String dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public void setNameOfApplicant(String nameOfApplicant) {
        this.nameOfApplicant = nameOfApplicant;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setGrapeVarietals(String grapeVarietals) {
        this.grapeVarietals = grapeVarietals;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public void setWineAppellation(String wineAppellation) {
        this.wineAppellation = wineAppellation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setpHValue(double pHValue) {
        this.pHValue = pHValue;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getBeverageType() {
        return beverageType;
    }

    //inputs an int attribute to see if a value has been set
    //returns true if intVal is equal to zero
    private boolean isEmpty(int intVal){
        return (intVal == 0);

    }

    //inputs a string attribute to see if a value has been set
    //returns true if string is empty
    private boolean isEmpty(String stringVal){
        return stringVal.equals("");

    }

    //inputs a double attribute to see if a value has been set
    //returns true if doubleVal is zero
    private boolean isEmpty(double doubleVal){
        return (doubleVal == 0.0);

    }

    //returns true if any required field is empty
    public boolean missingRequired(){
        return (isEmpty(domesticOrImported)||isEmpty(serialNumber)||isEmpty(brandName)
                ||isEmpty(beverageType)||isEmpty(nameAndAddress)||isEmpty(dateOfApplication) ||isEmpty(nameOfApplicant)
                ||isEmpty(phoneNumber)||isEmpty(alcoholContent));

    }

    //creates a string listing the field names that are both empty and required
    public String getMissingFieldsStatement(){
        String missingStatement = "The following empty fields are required: ";
        if(isEmpty(domesticOrImported)){
            missingStatement = missingStatement + " Domestic or Imported,";
        }
        if(isEmpty(serialNumber)){
            missingStatement = missingStatement + " Serial Number,";
        }
        if(isEmpty(brandName)){
            missingStatement = missingStatement + " Brand Name,";
        }
        if(isEmpty(beverageType)){
            missingStatement = missingStatement + " Type of Product,";
        }
        if(isEmpty(nameAndAddress)){
            missingStatement = missingStatement + " Name and Address of Applicant,";
        }
        if(isEmpty(dateOfApplication)){
            missingStatement = missingStatement + " Date of Application,";
        }
        if(isEmpty(nameOfApplicant)){
            missingStatement = missingStatement + " Print Name of Applicant,";
        }
        if(isEmpty(phoneNumber)){
            missingStatement = missingStatement + " Phone Number,";
        }
        if(isEmpty(alcoholContent)){
            missingStatement = missingStatement + " Alcohol Percentage,";
        }

        //removes extra comma
        missingStatement = missingStatement.substring(0, missingStatement.length() - 1);


        return missingStatement;

    }




}