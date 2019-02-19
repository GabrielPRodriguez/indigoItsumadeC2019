package edu.wpi.cs3733c19.teamI.Entities;

import javafx.scene.image.Image;

public class Form {

    private String repID; //optional
    private String plantRegistry; //optional
    private String domesticOrImported; //required
    private String serialNumber; //required
    private String brandName; //required
    private String beverageType; //required
    private String fancifulName; //optional
    private String nameAndAddress; //doesn't exist
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
    private String formStatus; //generated in background
    private String approvalDate; //generated in background
    private String approvingUser; //generated in background
    private String expirationDate; //generated in background
    private String volume;
    private String city;
    private String street;
    private String state;
    private String zip;
    private String permitname;
    private Image front_Upload;
    private Image back_Upload;

    public Form() {
        this.repID = "";
        this.plantRegistry = "";
        this.domesticOrImported = "";
        this.serialNumber = "";
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
        this.wineAppellation = "";
        this.email = "";
        this.phoneNumber = "";
        this.pHValue = 0;
        this.alcoholContent = 0;
        this.formStatus = "unread"; //new forms have had no action taken on them
        this.approvalDate = "1/1/1";
        this.approvingUser = "UserNotFound";
        this.expirationDate = "ExpirNotFound";
        this.volume ="";
        this.city = "";
        this.state = "";
        this.street = "";
        this.zip = "";
        this.permitname = "";
        this.front_Upload = null;
        this.back_Upload = null;
    }


    public Form(String repID, String plantRegistry, String domesticOrImported, String serialNumber, String brandName, String beverageType, String fancifulName, String nameAndAddress, String mailingAddress, String extraInfo, String date, String nameOfApplicant, String formula, String grapeVarietals, String vintage, String wineAppellation, String email, String phoneNumber, double pHValue, double alcoholContent, String formStatus, String approvingUser, String approvalDate, String expirationDate, String volume, String street, String city, String state, String zip, String permitname, Image front_Upload, Image back_Upload) {
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
        this.formStatus = formStatus;
        this.approvalDate = approvalDate;
        this.approvingUser = approvingUser;
        this.expirationDate = expirationDate;
        this.volume = volume;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.permitname = permitname;
        this.front_Upload = front_Upload;
        this.back_Upload = back_Upload;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPermitname(String permitname) {
        this.permitname = permitname;
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

    public void setFormStatus(String approvalStatus){ //this should probably be an enum for safety
        this.formStatus = approvalStatus;
    }

    public void setApprovingUser(String approvingUser){ //this should probably be an enum for safety
        this.approvingUser = approvingUser;
    }

    public void setApprovalDate(String approvalDate){ //this should probably be an enum for safety
        this.approvalDate = approvalDate;
    }

    public void setExpirationDate(String expirationDate){ //this should probably be an enum for safety
        this.expirationDate = expirationDate;
    }

    public void setFront_Upload(Image front_Upload)
    {
        this.front_Upload = front_Upload;
    }

    public void setBack_Upload(Image back_Upload)
    {
        this.back_Upload = back_Upload;
    }

    public String getBeverageType() {
        return beverageType;
    }

    public String getrepID() {
        return this.repID;
    }

    public String getplantRegistry() {
        return this.plantRegistry;
    }

    public String getdomesticOrImported() {
        return this.domesticOrImported;
    }

    public String getserialNumber() {
        return this.serialNumber;
    }

    public String getbrandName() {
        return this.brandName;
    }

    public String getbeverageType() {
        return this.beverageType;
    }

    public String getfancifulName() {
        return this.fancifulName;
    }

    public String getnameAndAddress() {
        return this.nameAndAddress;
    }

    public String getmailingAddress() {
        return this.mailingAddress;
    }

    public String getextraInfo() {
        return this.extraInfo;
    }

    public String getdateOfApplication() {
        return this.dateOfApplication;
    }

    public String getnameOfApplicant() {
        return this.nameOfApplicant;
    }

    public String getformula() {
        return this.formula;
    }

    public String getgrapeVarietals() {
        return this.grapeVarietals;
    }

    public String getvintage() {
        return this.vintage;
    }

    public String getwineAppellation() {
        return this.wineAppellation;
    }

    public String getemail() {
        return this.email;
    }

    public String getphoneNumber() {
        return this.phoneNumber;
    }

    public double getpHValue() {
        return this.pHValue;
    }

    public double getalcoholContent() {
        return this.alcoholContent;
    }

    public String getVolume() {
        return this.volume;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getState() {
        return this.state;
    }

    public String getZip() {
        return this.zip;
    }

    public String getPermitname() {
        return this.permitname;
    }

    public String getFormStatus(){
        return this.formStatus;
    }

    public Image getFront_Upload()
    {
        return this.front_Upload;
    }

    public Image getBack_Upload()
    {
        return this.back_Upload;
    }

    //inputs an int attribute to see if a value has been set
    //returns true if intVal is equal to zero
    private boolean isEmpty(int intVal) {
        return (intVal == 0);

    }

    //inputs a string attribute to see if a value has been set
    //returns true if string is empty
    private boolean isEmpty(String stringVal) {
        return stringVal.equals("");

    }

    //inputs a double attribute to see if a value has been set
    //returns true if doubleVal is zero
    private boolean isEmpty(double doubleVal) {
        return (doubleVal == 0.0);

    }

    //returns true if any required field is empty
    public boolean missingRequired() {
        return (isEmpty(domesticOrImported) || isEmpty(serialNumber) || isEmpty(brandName)
                || isEmpty(beverageType)  || isEmpty(dateOfApplication) || isEmpty(nameOfApplicant)
                || isEmpty(phoneNumber) || isEmpty(alcoholContent));

    }

    //creates a string listing the field names that are both empty and required
    public String getMissingFieldsStatement() {
        String missingStatement = "The following empty fields are required: ";
        if (isEmpty(domesticOrImported)) {
            missingStatement = missingStatement + " Domestic or Imported,";
        }
        if (isEmpty(serialNumber)) {
            missingStatement = missingStatement + " Serial Number,";
        }
        if (isEmpty(brandName)) {
            missingStatement = missingStatement + " Brand Name,";
        }
        if (isEmpty(beverageType)) {
            missingStatement = missingStatement + " Type of Product,";
        }

        if (isEmpty(dateOfApplication)) {
            missingStatement = missingStatement + " Date of Application,";
        }
        if (isEmpty(nameOfApplicant)) {
            missingStatement = missingStatement + " Print Name of Applicant,";
        }
        if (isEmpty(phoneNumber)) {
            missingStatement = missingStatement + " Phone Number,";
        }
        if (isEmpty(alcoholContent)) {
            missingStatement = missingStatement + " Alcohol Percentage,";
        }

        //removes extra comma
        missingStatement = missingStatement.substring(0, missingStatement.length() - 1);


        return missingStatement;

    }

}

