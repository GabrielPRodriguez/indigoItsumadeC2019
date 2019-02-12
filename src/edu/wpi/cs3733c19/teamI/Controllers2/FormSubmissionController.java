package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBTypes;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.Form;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FormSubmissionController implements Initializable {

    @FXML
    HBox wineFields;

    @FXML
    JFXRadioButton wine_RadButton;

    @FXML
    ToggleGroup beverage;

    @FXML
    JFXButton submit;


    private void setWineToggle(){

        JFXRadioButton rb = (JFXRadioButton)beverage.getSelectedToggle();

        if(rb == wine_RadButton)
        {
            wineFields.setVisible(true);
            wineFields.setDisable(false);
            wineFields.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        }
        else {
            wineFields.setVisible(false);
            wineFields.setDisable(true);
            wineFields.setMaxSize(0,0);
        }

    }
    private ToolBarController toolBarController;


    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }


    @FXML
    public void goHome(ActionEvent actionEvent){ toolBarController.goHome(actionEvent); }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        toolBarController.goSubmit(actionEvent);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        toolBarController.goLogin(actionEvent);
    }

    @FXML
    public void goWorkflow(ActionEvent actionEvent){toolBarController.goWorkflow(actionEvent);}

    @FXML
    public void goAbout(ActionEvent actionEvent){toolBarController.goAbout(actionEvent);}

    @FXML
    Button save_Button;

    @FXML
    JFXRadioButton domestic_RadButton;

    @FXML
    JFXRadioButton imported_RadButton;



    @FXML
    JFXRadioButton beer_RadButton;

    @FXML
    JFXRadioButton liquor_RadButton;

    @FXML
    ToggleGroup Domestic;

    @FXML
    ToggleGroup Drinks;

    @FXML
    JFXTextField repIdNum_Field;

    @FXML
    JFXTextField permitNum_Field;

    @FXML
    JFXTextField serialNum_Field;

    @FXML
    JFXTextField phoneNum_Field;

    @FXML
    JFXTextField email_Field;

    @FXML
    JFXTextField brandName_Field;

    @FXML
    JFXTextField fancyName_Field;

    @FXML
    JFXTextField date_Field;

    @FXML
    JFXTextArea brandedInfo_Field;

    @FXML
    JFXTextField applicantName_Field;

    @FXML
    JFXTextArea applicantNameAddr_Field;

    @FXML
    JFXTextArea mailAddr_Field;

    @FXML
    JFXTextField formula_Field;

    @FXML
    JFXTextField grape_Field;

    @FXML
    JFXTextField appellation_Field;

    @FXML
    JFXTextField ph_Field;

    @FXML
    JFXTextField alcoholPercent_Field;

    @FXML
    JFXTextField vintage_Field;

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
        if(event.getSource()== submit){
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

                //System.out.println("making db");
                SQLDriver driver = new SQLDriver();
                //sets the names of columns in the database, if additional form fields are added, please add a new column
                String [] columns = {"formID", "repID", "plantRegistry", "domesticOrImported", "serialNumber", "brandName", "beverageType", "fancifulName", "nameAndAddress", "mailingAddress", "extraInfo", "dateOfApplication", "nameOfApplicant", "formula", "grapeVarietals", "vintage", "wineAppellation", "email", "phoneNumber", "pHValue", "alcoholContent", "status", "approvingUser", "approvalDate", "expirationDate"};
                //contains the datatype of each column in the database, when adding a new column, please also add it's datatype here/
                //"text" for strings and "real" for doubles/integers
                DBTypes[] full_types = {new DBTypes("real"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("real"), new DBTypes("real"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text")};
                try{
                    driver.create_table("form_data", "form_data.db", columns, full_types);
                }
                catch(Exception e){
                    System.out.println("exception, create_table");
                }
                //int _id_count = driver.select_all("form_data.db", "form_data").size();

                //iterates through the formID column of the database in order to find the current highest formID value
                double _id_count = 0;
                for (HashMap<String, ReturnedValue> result:driver.select_all("form_data.db", "form_data")){
                    double _test = result.get("formID").to_double();
                    if (_test > _id_count){
                        _id_count = _test;
                    }
                }

                //collects values from fields of sentForm object (see Form.java)
                DBValue[] all_vals = {new DBValue<Integer>((int)(_id_count)+1), new DBValue<String>(sentForm.getrepID()), new DBValue<String>(sentForm.getplantRegistry()), new DBValue<String>(sentForm.getdomesticOrImported()), new DBValue<String>(sentForm.getserialNumber()), new DBValue<String>(sentForm.getbrandName()), new DBValue<String>(sentForm.getbeverageType()), new DBValue<String>(sentForm.getfancifulName()), new DBValue<String>(sentForm.getnameAndAddress()), new DBValue<String>(sentForm.getmailingAddress()), new DBValue<String>(sentForm.getextraInfo()), new DBValue<String>(sentForm.getdateOfApplication()), new DBValue<String>(sentForm.getnameOfApplicant()), new DBValue<String>(sentForm.getformula()), new DBValue<String>(sentForm.getgrapeVarietals()), new DBValue<String>(sentForm.getvintage()), new DBValue<String>(sentForm.getwineAppellation()), new DBValue<String>(sentForm.getemail()), new DBValue<String>(sentForm.getphoneNumber()), new DBValue<Double>(sentForm.getpHValue()), new DBValue<Double>(sentForm.getalcoholContent()), new DBValue<String>("unread"), new DBValue<String>("noUser"), new DBValue<String>("NoDateAprroved"), new DBValue<String>("NoDateExir")};

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
        beverage.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                setWineToggle();
            }
        });



    }
}
