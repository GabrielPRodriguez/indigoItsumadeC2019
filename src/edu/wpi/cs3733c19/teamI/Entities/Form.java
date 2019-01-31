package edu.wpi.cs3733c19.teamI.Entities;

public class Form {

        private String repID;
        private String plantRegistry;
        private String domesticOrImported;
        private int serialNumber;
        private String brandName;
        private String beverageType;
        private String fancifulName;
        private String nameAndAddress;
        private String mailingAddress;
        private String extraInfo; //optional
        private String dateOfApplication;
        private String nameOfApplicant;
        private String formula;
        private String grapeVarietals; //for wine only
        private String vintage; //for wine only
        private String wineAppellation; //wine only/optional
        private String email;
        private String phoneNumber;
        private double pHValue; //wine only
        private double alcoholContent;

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


}
