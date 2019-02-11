package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.DBTypes;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.Form;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class FormSubController implements Initializable {

    private Scene HomeScene;

    public void setHomeScene(Scene home)
    {
        HomeScene = home;
    }

        @FXML
        Button save_Button;

        @FXML
        Button Home;

        @FXML
        Button submit_Button;

        @FXML
        RadioButton domestic_RadButton;

        @FXML
        RadioButton imported_RadButton;

        @FXML
        RadioButton wine_RadButton;

        @FXML
        RadioButton beer_RadButton;

        @FXML
        RadioButton liquor_RadButton;

        @FXML
        ToggleGroup Domestic;

        @FXML
        ToggleGroup Drinks;

        @FXML
        TextField repIdNum_Field;

        @FXML
        TextField permitNum_Field;

        @FXML
        TextField serialNum_Field;

        @FXML
        TextField phoneNum_Field;

        @FXML
        TextField email_Field;

        @FXML
        TextField brandName_Field;

        @FXML
        TextField fancyName_Field;

        @FXML
        TextField date_Field;

        @FXML
        TextArea brandedInfo_Field;

        @FXML
        TextField applicantName_Field;

        @FXML
        TextArea applicantNameAddr_Field;

        @FXML
        TextArea mailAddr_Field;

        @FXML
        TextField formula_Field;

        @FXML
        TextField grape_Field;

        @FXML
        TextField appellation_Field;

        @FXML
        TextField ph_Field;

        @FXML
        TextField alcoholPercent_Field;

        @FXML
        TextField vintage_Field;

        @FXML
        Label submit_message;



        //Enables the fields that are only used for wine when the wine radial button is selected

        @FXML
        private void activateWineFields(ActionEvent wineSelect) throws IOException {
            appellation_Field.setDisable(false);
            ph_Field.setDisable(false);
            vintage_Field.setDisable(false);
            grape_Field.setDisable(false);
        }


        //Disables the wine-only fields when the beer or liquor radial buttons are selected

        @FXML
        private void disableWineFields(ActionEvent wineDeselect) throws IOException{
            appellation_Field.setDisable(true);
            ph_Field.setDisable(true);
            vintage_Field.setDisable(true);
            grape_Field.setDisable(true);
        }

        //sets the values for each field into a Form object when the submit button is pressed.
        @FXML
        private void handleSubmitButton(ActionEvent event) throws IOException, Exception{
            if(event.getSource()== submit_Button){
                Form sentForm = new Form();
                boolean readyToSend = true; //if true by the end of the method, the form will be sent to database
                submit_message.setText(""); //string to insert into textfield either confirming submission or printing error messge

                //sets Domestic Or Imported field

                if (Domestic.getSelectedToggle() != null) {
                    RadioButton selectedOption = (RadioButton) Domestic.getSelectedToggle();
                    sentForm.setDomesticOrImported(selectedOption.getText());
                }


                //sets the Type of Drink (either wine, beer, or liquor)

                if(Drinks.getSelectedToggle() != null){
                    RadioButton selectedBevType = (RadioButton) Drinks.getSelectedToggle();
                    sentForm.setBeverageType(selectedBevType.getText());
                }

                //sets the REP ID number
                sentForm.setRepID(repIdNum_Field.getText());

                //Sets Plant Registry/Basic Permit/Brewer's Number
                sentForm.setPlantRegistry(permitNum_Field.getText());


                //Sets Serial Number
                try {
                    sentForm.setSerialNumber((serialNum_Field.getText()));
                }
                catch (NumberFormatException e){
                    readyToSend = false;
                    String oldMessage = submit_message.getText();
                    submit_message.setText(oldMessage + "  Please enter a number for Serial Number" );
                }

                //Sets Phone Number
                sentForm.setPhoneNumber(phoneNum_Field.getText());

                //Sets Email
                sentForm.setEmail(email_Field.getText());

                //Sets Brand Name
                sentForm.setBrandName(brandName_Field.getText());

                //Sets Fanciful Name
                sentForm.setFancifulName(fancyName_Field.getText());

                //Sets Date of Submission

                sentForm.setDateOfApplication(date_Field.getText());

                //Sets Branded/Embossed Non-label info
                sentForm.setExtraInfo(brandedInfo_Field.getText());

                //Sets Name of Applicant
                sentForm.setNameOfApplicant(applicantName_Field.getText());

                //Sets Name and Address
                sentForm.setNameAndAddress(applicantNameAddr_Field.getText());

                //Sets Mailing Address
                sentForm.setMailingAddress(mailAddr_Field.getText());

                //Sets Formula
                sentForm.setFormula(formula_Field.getText());

                //sets non-read approval status
                sentForm.setFormStatus("unread");

                //only pull from these fields if Beverage Type is Wine
                if(sentForm.getBeverageType().equals("Wine")){
                    //Sets Grape Varietals
                    sentForm.setGrapeVarietals(grape_Field.getText());

                    //Sets Wine Appellation
                    sentForm.setWineAppellation(appellation_Field.getText());

                    //Sets Wine Vintage year
                    sentForm.setVintage(vintage_Field.getText());

                    //Sets Wine pH value
                    try {
                        sentForm.setpHValue(Double.parseDouble(ph_Field.getText()));
                    }
                    catch (NumberFormatException e){
                        readyToSend = false;
                        String oldMessage = submit_message.getText();
                        submit_message.setText(oldMessage + "  Please enter a number for pH Value" );


                    }
                }


                //Sets alcohol content percentage and checks to see if it is a double
                try {
                    sentForm.setAlcoholContent(Double.parseDouble(alcoholPercent_Field.getText()));
                }
                catch (NumberFormatException e){
                    readyToSend = false;
                    String oldMessage = submit_message.getText();
                    submit_message.setText(oldMessage + "  Please enter a number for Alcohol Percentage.");

                }

                //checks if required fields are missing, prints an error message stating which ones are
                //missing, prevents the form from actually being submitted
                //to set more required fields, please edit missingRequired() and getMissingStatement() in Form.java

                if(sentForm.missingRequired()){
                    readyToSend = false;
                    String oldMessage = submit_message.getText();

                    String errorMessage = sentForm.getMissingFieldsStatement(); //apply to a Label
                    submit_message.setText(oldMessage + "  " +  errorMessage);
                }

                //sends the form to database when int/double fields contain the correct datatype and
                //all required fields have received input

                if(readyToSend){
                    //add code to sent to database
                    //add code to sent to database
                    /*
                    String jsonString = new JSONObject()
                            .put("repID", sentForm.getrepID())
                            .put("plantRegistry", sentForm.getplantRegistry())
                            .put("domesticOrImported", sentForm.getdomesticOrImported())
                            .put("serialNumber", sentForm.getserialNumber())
                            .put("brandName", sentForm.getbrandName())
                            .put("beverageType", sentForm.getbeverageType())
                            .put("fancifulName", sentForm.getfancifulName())
                            .put("nameAndAddress", sentForm.getnameAndAddress())
                            .put("mailingAddress", sentForm.getmailingAddress())
                            .put("extraInfo", sentForm.getextraInfo())
                            .put("dateOfApplication", sentForm.getdateOfApplication())
                            .put("nameOfApplicant", sentForm.getnameOfApplicant())
                            .put("formula", sentForm.getformula())
                            .put("grapeVarietals", sentForm.getgrapeVarietals())
                            .put("vintage", sentForm.getvintage())
                            .put("wineAppellation", sentForm.getwineAppellation())
                            .put("email", sentForm.getemail())
                            .put("phoneNumber", sentForm.getphoneNumber())
                            .put("pHValue", sentForm.getpHValue())
                            .put("alcoholContent", sentForm.getalcoholContent()).toString();

                    */
                    //System.out.println("making db");
                    SQLDriver driver = new SQLDriver();
                    //sets the names of columns in the database, if additional form fields are added, please add a new column
                    String [] columns = {"formID",  "repID",    "plantRegistry",    "domesticOrImported",   "serialNumber", "brandName",    "beverageType",     "fancifulName", "nameAndAddress",   "mailingAddress",   "extraInfo",    "dateOfApplication",    "nameOfApplicant",  "formula",  "grapeVarietals",   "vintage",  "wineAppellation",  "email",    "phoneNumber",  "pHValue",  "alcoholContent",   "status",   "approvingUser",    "approvalDate",     "expirationDate"};
                    String [] types =   {"real",    "text",     "text",             "text",                 "text",         "text",         "text",             "text",         "text",             "text",             "text",         "text",                 "text",             "text",     "text",             "text",     "text",             "text",     "text",         "real",     "real",             "text",     "text",             "text",             "text"};
                        //these two lines are the formID and its type, formatted for easy readability
                    //contains the datatype of each column in the database, when adding a new column, please also add it's datatype here/
                    //"text" for strings and "real" for doubles/integers

                    DBTypes [] full_types = new DBTypes[types.length];

                    for (int i = 0; i < columns.length; i++){ //this for loop takes the columns and their types and makes the full_types list for later
                        full_types[i] = new DBTypes(types[i]);
                    }

                    try{
                        driver.create_table("form_data", "form_data.db", columns, full_types);
                    }
                    catch(Exception e){
                        System.out.println("exception, create_table");
                    }
                    //int _id_count = driver.select_all("form_data.db", "form_data").size();

                    //iterates through the formID column of the database in order to find the current highest formID value
                    double _id_count = 0;
                    for (HashMap<String, ReturnedValue>result:driver.select_all("form_data.db", "form_data")){
                        double _test = result.get("formID").to_double();
                        if (_test > _id_count){
                            _id_count = _test;
                        }
                    }

                    //collects values from fields of sentForm object (see Form.java)
                    DBValue [] all_vals = {new DBValue<Integer>((int)(_id_count)+1), new DBValue<String>(sentForm.getrepID()), new DBValue<String>(sentForm.getplantRegistry()), new DBValue<String>(sentForm.getdomesticOrImported()), new DBValue<String>(sentForm.getserialNumber()), new DBValue<String>(sentForm.getbrandName()), new DBValue<String>(sentForm.getbeverageType()), new DBValue<String>(sentForm.getfancifulName()), new DBValue<String>(sentForm.getnameAndAddress()), new DBValue<String>(sentForm.getmailingAddress()), new DBValue<String>(sentForm.getextraInfo()), new DBValue<String>(sentForm.getdateOfApplication()), new DBValue<String>(sentForm.getnameOfApplicant()), new DBValue<String>(sentForm.getformula()), new DBValue<String>(sentForm.getgrapeVarietals()), new DBValue<String>(sentForm.getvintage()), new DBValue<String>(sentForm.getwineAppellation()), new DBValue<String>(sentForm.getemail()), new DBValue<String>(sentForm.getphoneNumber()), new DBValue<Double>(sentForm.getpHValue()), new DBValue<Double>(sentForm.getalcoholContent()), new DBValue<String>("unread"), new DBValue<String>("noUser"), new DBValue<String>("NoDateAprroved"), new DBValue<String>("NoDateExir")};

                    //the values above are actually entered into the database (contained in form_data.db)
                    driver.insert_vals("form_data", "form_data.db", all_vals);

                    //displays message after form has successfully been entered into the database
                    String success = "Form successfully submitted.";
                    submit_message.setText(success);

                    //clears all fields
                    Domestic.selectToggle(null);
                    Drinks.selectToggle(null);
                    repIdNum_Field.clear();
                    serialNum_Field.clear();
                    brandName_Field.clear();
                    permitNum_Field.clear();
                    formula_Field.clear();
                    alcoholPercent_Field.clear();
                    fancyName_Field.clear();
                    phoneNum_Field.clear();
                    mailAddr_Field.clear();
                    email_Field.clear();
                    appellation_Field.clear();
                    grape_Field.clear();
                    ph_Field.clear();
                    vintage_Field.clear();
                    applicantName_Field.clear();
                    applicantNameAddr_Field.clear();
                    brandedInfo_Field.clear();


                }



            }
        }

        //the following runs formsubmission page is opened

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //REP ID Tooltip
        final Tooltip tooltipRepID = new Tooltip();
        tooltipRepID.setText("REPRESENTATIVE ID. NUMBER (If any)");
        repIdNum_Field.setTooltip(tooltipRepID);

        // Plant Registry/Basic Permit/ Brewer's NO.
        final Tooltip tooltipPlantRegistry = new Tooltip("Plant Registry/Basic Permit/ Brewer's NO. (Required)");
        permitNum_Field.setTooltip(tooltipPlantRegistry);

        // Domestic
        final Tooltip tooltipDomestic = new Tooltip("SOURCE OF PRODUCT (Required)");
        domestic_RadButton.setTooltip(tooltipDomestic);

        final Tooltip tooltipWine = new Tooltip("TYPE OF PRODUCT (Required)");
        wine_RadButton.setTooltip(tooltipWine);

        // Serial Number
        final Tooltip tooltipSerialNumber = new Tooltip("SERIAL NUMBER (Required)");
        serialNum_Field.setTooltip(tooltipSerialNumber);

        // Type of Product
        final Tooltip tooltipTypeOfProduct = new Tooltip("TYPE OF PRODUCT (Required)");
        serialNum_Field.setTooltip(tooltipTypeOfProduct);

        // Brand Name
        final Tooltip tooltipBrandName = new Tooltip("BRAND NAME (Required)");
        brandName_Field.setTooltip(tooltipBrandName);

        // Fanciful Name
        final Tooltip tooltipFancifulName = new Tooltip("FANCIFUL NAME (If any)");
        fancyName_Field.setTooltip(tooltipFancifulName);

        // Name and Adress of application
        final Tooltip tooltipNameAndAdress = new Tooltip("NAME AND ADDRESS OF APPLICANT AS SHOWN ON PLANT REGISTRY, BASIC PERMIT, OR BREWER'S NOTICE. INCLUDE APPROVED DBA OR TRADENAME IF USED ON THE LABEL (Required)");
        applicantNameAddr_Field.setTooltip(tooltipNameAndAdress);

        // Mailing Address if different
        final Tooltip tooltipMailingAdress = new Tooltip("MAILING ADDRESS, IF DIFFERENT");
        mailAddr_Field.setTooltip(tooltipMailingAdress);

        // Fanciful Name
        final Tooltip tooltipFormula = new Tooltip("FORMULA");
        formula_Field.setTooltip(tooltipFormula);

        // Grape Varietals
        final Tooltip tooltipGrapeVariental = new Tooltip("GRAPE VARIETAL(S) Wine only");
        grape_Field.setTooltip(tooltipGrapeVariental);

        // Appellation
        final Tooltip tooltipWineAppellation = new Tooltip("WINE APPELLATION (If on label)");
        appellation_Field.setTooltip(tooltipWineAppellation);

        // Phone Number
        final Tooltip tooltipPhoneNumber = new Tooltip("PHONE NUMBER");
        phoneNum_Field.setTooltip(tooltipPhoneNumber);

        // Phone Number
        final Tooltip tooltipEmailAddress = new Tooltip("EMAIL ADDRESS");
        email_Field.setTooltip(tooltipEmailAddress);

        // Phone Number
        final Tooltip tooltipAlcoholPercentage = new Tooltip("Alcohol Percentage");
        alcoholPercent_Field.setTooltip(tooltipAlcoholPercentage);

        // Phone Number
        final Tooltip tooltipPH = new Tooltip("PH Level (Wine only)");
        ph_Field.setTooltip(tooltipPH);

        // Vintage
        final Tooltip tooltipVintage = new Tooltip("Vintage (Wine only)");
        vintage_Field.setTooltip(tooltipVintage);





    }

    //return to the homepage

    public void goHome(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(HomeScene);
    }
}
