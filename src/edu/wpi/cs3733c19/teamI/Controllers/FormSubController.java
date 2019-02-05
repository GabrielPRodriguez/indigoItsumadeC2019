package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.DBTypes;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.Form;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;



public class FormSubController{





        @FXML
        Button save_Button;

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
        TextField appelllation_Field;

        @FXML
        TextField ph_Field;

        @FXML
        TextField alcoholPercent_Field;

        @FXML
        TextField vintage_Field;





        @FXML
        private void activateWineFields(ActionEvent wineSelect) throws IOException {
            appelllation_Field.setDisable(false);
            ph_Field.setDisable(false);
            vintage_Field.setDisable(false);
            grape_Field.setDisable(false);
        }

        @FXML
        private void disableWineFields(ActionEvent wineDeselect) throws IOException{
            appelllation_Field.setDisable(true);
            ph_Field.setDisable(true);
            vintage_Field.setDisable(true);
            grape_Field.setDisable(true);
        }

        //sets the values for each field into a Form object when the submit button is pressed.
        @FXML
        private void handleSubmitButton(ActionEvent event) throws IOException, Exception{
            if(event.getSource()== submit_Button){
                Form sentForm = new Form();
                boolean readyToSend = true;

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
                    sentForm.setSerialNumber(Integer.parseInt(serialNum_Field.getText()));
                }
                catch (NumberFormatException e){
                    readyToSend = false;
                    //TODO: print error message in fxml next to field "Please Enter a Number for this field
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
                //Note: get_datetime() vs date field? which should be used?

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

                //only pull from these fields if Beverage Type is Wine
                if(sentForm.getBeverageType().equals("Wine")){
                    //Sets Grape Varietals
                    sentForm.setGrapeVarietals(grape_Field.getText());

                    //Sets Wine Appellation
                    sentForm.setWineAppellation(appelllation_Field.getText());

                    //Sets Wine Vintage year
                    sentForm.setVintage(vintage_Field.getText());

                    //Sets Wine pH value
                    try {
                        sentForm.setpHValue(Double.parseDouble(ph_Field.getText()));
                    }
                    catch (NumberFormatException e){
                        readyToSend = false;
                        //TODO: print error message in fxml next to field "Please Enter a Number for this field

                    }
                }


                //Sets alcohol content percentage
                try {
                    sentForm.setAlcoholContent(Double.parseDouble(alcoholPercent_Field.getText()));
                }
                catch (NumberFormatException e){
                    readyToSend = false;
                    //TODO: print error message in fxml next to field "Please Enter a Number for this field
                }

                if(sentForm.missingRequired()){
                    readyToSend = false;

                    String errorMessage = sentForm.getMissingFieldsStatement(); //apply to a Label
                }

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
                    System.out.println("making db");
                    SQLDriver driver = new SQLDriver();
                    String [] columns = {"formID", "repID", "plantRegistry", "domesticOrImported", "serialNumber", "brandName", "beverageType", "fancifulName", "nameAndAddress", "mailingAddress", "extraInfo", "dateOfApplication", "nameOfApplicant", "formula", "grapeVarietals", "vintage", "wineAppellation", "email", "phoneNumber", "pHValue", "alcoholContent", "status"};
                    DBTypes [] full_types = {new DBTypes("real"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("real"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("real"), new DBTypes("real"), new DBTypes("real")};
                    try{
                        driver.create_table("form_data", "form_data.db", columns, full_types);
                    }
                    catch(Exception e){
                        System.out.println("exception, create_table");
                    }
                    //int _id_count = driver.select_all("form_data.db", "form_data").size();
                    double _id_count = 0;
                    for (HashMap<String, ReturnedValue>result:driver.select_all("form_data.db", "form_data")){
                        double _test = result.get("formID").to_double();
                        if (_test > _id_count){
                            _id_count = _test;
                        }
                    }
                    DBValue [] all_vals = {new DBValue<Integer>((int)(_id_count)+1), new DBValue<String>(sentForm.getrepID()), new DBValue<String>(sentForm.getplantRegistry()), new DBValue<String>(sentForm.getdomesticOrImported()), new DBValue<Integer>(sentForm.getserialNumber()), new DBValue<String>(sentForm.getbrandName()), new DBValue<String>(sentForm.getbeverageType()), new DBValue<String>(sentForm.getfancifulName()), new DBValue<String>(sentForm.getnameAndAddress()), new DBValue<String>(sentForm.getmailingAddress()), new DBValue<String>(sentForm.getextraInfo()), new DBValue<String>(sentForm.getdateOfApplication()), new DBValue<String>(sentForm.getnameOfApplicant()), new DBValue<String>(sentForm.getformula()), new DBValue<String>(sentForm.getgrapeVarietals()), new DBValue<String>(sentForm.getvintage()), new DBValue<String>(sentForm.getwineAppellation()), new DBValue<String>(sentForm.getemail()), new DBValue<String>(sentForm.getphoneNumber()), new DBValue<Double>(sentForm.getpHValue()), new DBValue<Double>(sentForm.getalcoholContent()), new DBValue<Integer>(0)};
                    /*
                    0: needs reviewing
                    1: approved
                    2: rejected
                     */
                    driver.insert_vals("form_data", "form_data.db", all_vals);

                    String success = "Form successfully submitted."; //apply to a Label

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
                    appelllation_Field.clear();
                    grape_Field.clear();
                    ph_Field.clear();
                    vintage_Field.clear();
                    applicantName_Field.clear();
                    applicantNameAddr_Field.clear();
                    brandedInfo_Field.clear();


                }



            }
        }






}