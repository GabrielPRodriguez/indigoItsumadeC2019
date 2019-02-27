package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.*;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBTypes;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.DataTransfer;
import edu.wpi.cs3733c19.teamI.Entities.Form;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FormSubmissionController implements Initializable {

    private ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    HBox wineFields;

    @FXML
    JFXRadioButton wine_RadButton;

    @FXML
    ToggleGroup beverage;

    @FXML
    ToggleGroup origin;

    @FXML
    JFXButton submit;

    DataTransfer data = DataTransfer.getInstance();


    @FXML
    public void goHelpSubmit() throws IOException {
        toolBarController.goHelpSubmit();
    }

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
    JFXTextField permitName;

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
    JFXDatePicker date_Field;

    @FXML
    JFXTextField brandedInfo_Field;

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

    @FXML
    JFXTextField volume_Field;

    @FXML
    JFXTextField city_Field;

    @FXML
    JFXTextField street_Field;

    @FXML
    JFXTextField zip_Field;

    @FXML
    JFXTextField state_Field;

    @FXML
    Label alcoholPercent_warning;

    @FXML
    Label serial_warning;

    @FXML
    Label ph_warning;

    @FXML
    Label brand_warning;

    @FXML
    Label domestic_warning;

    @FXML
    Label beverage_warning;

    @FXML
    Label phoneNum_warning;

    @FXML
    Label date_warning;

    @FXML
    Label name_warning;

    @FXML
    JFXButton front_Upload;

    @FXML
    JFXButton back_Upload;

    @FXML
    ImageView frontImageDisp;

    @FXML
    ImageView backImageDisp;

    @FXML
    Button addSignature;

    @FXML
    ImageView signatureDisp;

    JButton signButton, start;
    UserSignature userSignature;
    JFrame signatureArea;

    ActionListener actionListener = new ActionListener()
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent event)
        {
            if(event.getSource() == start)
            {
                userSignature.black();
            }

            else if(event.getSource() == signButton)
            {
                try
                {
                    BufferedImage im0 = new Robot().createScreenCapture(signatureArea.getBounds());
                    BufferedImage im1 = im0.getSubimage(0, 65, 600, 135);
                    Image newImage = SwingFXUtils.toFXImage(im1, null);
                    signatureDisp.setImage(newImage);
                }
                catch(AWTException e)
                {
                    e.printStackTrace();
                }

                signatureArea.dispatchEvent(new WindowEvent(signatureArea, WindowEvent.WINDOW_CLOSING));
            }
        }
    };

    @FXML
    private void handleSignatureButton(ActionEvent event)
    {
        if(event.getSource() == addSignature)
        {
            userSignature = new UserSignature();
            signatureArea = new JFrame("Signature");
            Container content = signatureArea.getContentPane();
            content.setLayout(new BorderLayout());
            content.add(userSignature, BorderLayout.CENTER);
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            signatureArea.setLocation(d.width/2 - signatureArea.getSize().width/2, d.height/2 - signatureArea.getSize().height/2);

            JPanel controls = new JPanel();
            signButton = new JButton("SIGN");
            signButton.addActionListener(actionListener);
            start = new JButton("Start");
            start.addActionListener(actionListener);

            controls.add(signButton);
            controls.add(start);

            content.add(controls, BorderLayout.NORTH);
            signatureArea.setSize(600, 200);
            signatureArea.setVisible(true);
        }
    }

    /** private void uploadFile - Used to upload an image file of either type .jpg or .png when the user
     *                              is submitting a form. This method will throw an error if a file type
     *                              other than the intended file type is uploaded.
     * @param event -> Used to run the method when either front_Upload or back_Upload buttons are pressed.
     */
    @FXML
    private void uploadFile(ActionEvent event)
    {
        if((event.getSource() == front_Upload) || (event.getSource() == back_Upload))
        {
            FileChooser chooseFile = new FileChooser();
            File selectedFile = chooseFile.showOpenDialog(null);
            chooseFile.setTitle("Please Choose an Image to Upload");

            if(selectedFile != null)
            {
                String filePath = selectedFile.getAbsolutePath();
                Image selectedImage = new Image(new File(filePath).toURI().toString());

                if(event.getSource() == front_Upload)
                {
                    frontImageDisp.setImage(selectedImage);
                }

                if(event.getSource() == back_Upload)
                {
                    backImageDisp.setImage(selectedImage);
                }
            }
        }
//        if((event.getSource() == front_Upload) || (event.getSource() == back_Upload))
//        {
//            JButton open = new JButton();
//            JFileChooser chooseFile = new JFileChooser();
//            chooseFile.setCurrentDirectory(new java.io.File("System.dir"));
//            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
//            chooseFile.setFileFilter(filter);
//            chooseFile.setDialogTitle("Please Choose an Image to Upload");
//            chooseFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//
//            if(chooseFile.showOpenDialog(open) == JFileChooser.APPROVE_OPTION)
//            {
//                File selectedFile = chooseFile.getSelectedFile();
//                String filePath = selectedFile.getAbsolutePath();
//                Image selectedImage = new Image(new File(filePath).toURI().toString());
//
//                if(event.getSource() == front_Upload)
//                {
//                    frontImageDisp.setImage(selectedImage);
//                }
//
//                if(event.getSource() == back_Upload)
//                {
//                    backImageDisp.setImage(selectedImage);
//                }
//            }
//        }
    }

    //Enables the fields that are only used for wine when the wine radial button is selected
/*
    @FXML
    private void activateWineFields(ActionEvent wineSelect) throws IOException {
        appellation_Field.setDisable(false);
        ph_Field.setDisable(false);
        vintage_Field.setDisable(false);
        grape_Field.setDisable(false);
    }


    //Disables the wine-only fields when the beer or liquor radial buttons are selected
*/
    @FXML
    private void handleSubmitButton(ActionEvent event) throws Exception{
        if(event.getSource()== submit){
            clearWarnings();
            Form sentForm = new Form();
            boolean readyToSend = true; //if true by the end of the method, the form will be sent to database
            submit_message.setText("");//string to insert into textfield either confirming submission or printing error messge

            submit_message.setTextFill(Color.web("#FF0000"));
            //sets Domestic Or Imported field

            if (origin.getSelectedToggle() != null) {
                RadioButton selectedOption = (RadioButton) origin.getSelectedToggle();
                sentForm.setDomesticOrImported(selectedOption.getText());
            }


            //sets the Type of Drink (either wine, beer, or liquor)

            if(beverage.getSelectedToggle() != null){
                RadioButton selectedBevType = (RadioButton) beverage.getSelectedToggle();
                sentForm.setBeverageType(selectedBevType.getText());
            }

            //sets the REP ID number
            sentForm.setRepID(repIdNum_Field.getText());

            //Sets Plant Registry/Basic Permit/Brewer's Number
            sentForm.setPlantRegistry(permitNum_Field.getText());


            //Sets Serial Number

            sentForm.setSerialNumber((serialNum_Field.getText()));


            //Sets Phone Number
            sentForm.setPhoneNumber(phoneNum_Field.getText());

            //Sets Email
            sentForm.setEmail(email_Field.getText());

            //Sets Brand Name
            sentForm.setBrandName(brandName_Field.getText());

            //Sets Fanciful Name
            sentForm.setFancifulName(fancyName_Field.getText());

            //Sets Date of Submission

            //sentForm.setDateOfApplication(date_Field.getText());
            try{
                sentForm.setDateOfApplication(date_Field.getValue().toString());
            }
            catch (NullPointerException e){

            }


            //Sets Branded/Embossed Non-label info
            sentForm.setExtraInfo(brandedInfo_Field.getText());

            //Sets Name of Applicant
            sentForm.setNameOfApplicant(applicantName_Field.getText());

            sentForm.setVolume(volume_Field.getText());

            sentForm.setCity(city_Field.getText());

            sentForm.setStreet(street_Field.getText());

            sentForm.setState(state_Field.getText());

            sentForm.setZip(zip_Field.getText());

            sentForm.setPermitname(permitName.getText());

            sentForm.setFormula(formula_Field.getText());

            //sets username approval status
            DataTransfer data = DataTransfer.getInstance();
            sentForm.setFormStatus("unread" + "," +data.UserName);

            if(!(frontImageDisp == null))
            {
                sentForm.setFront_Upload(frontImageDisp.getImage());
            }

            if(!(backImageDisp == null))
            {
                sentForm.setBack_Upload(backImageDisp.getImage());
            }

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
                    ph_warning.setTextFill(Color.web("#FF0000"));
                    ph_warning.setText("Please enter a number");


                }
            }


            //Sets alcohol content percentage and checks to see if it is a double
            try {
                sentForm.setAlcoholContent(Double.parseDouble(alcoholPercent_Field.getText()));
            }
            catch (NumberFormatException e){
                readyToSend = false;
                //String oldMessage = submit_message.getText();
                //submit_message.setText(oldMessage + "  Please enter a number for Alcohol Percentage.");
                alcoholPercent_warning.setTextFill(Color.web("#FF0000"));
                alcoholPercent_warning.setText("Please enter a number");

            }



            //checks if required fields are missing, prints an error message stating which ones are
            //missing, prevents the form from actually being submitted
            //to set more required fields, please edit missingRequired() and getMissingStatement() in Form.java

            if(sentForm.missingRequired()){
                readyToSend = false;
                if(sentForm.getdomesticOrImported().isEmpty()){
                    domestic_warning.setTextFill(Color.web("#FF0000"));
                    domestic_warning.setText("Required");
                }
                if(sentForm.getbeverageType().isEmpty()){
                    beverage_warning.setTextFill(Color.web("#FF0000"));
                    beverage_warning.setText("Required");
                }

                if(sentForm.getserialNumber().isEmpty()){
                    serial_warning.setTextFill(Color.web("#FF0000"));
                    serial_warning.setText("Required");
                }

                if(sentForm.getbrandName().isEmpty()){
                    brand_warning.setTextFill(Color.web("#FF0000"));
                    brand_warning.setText("Required");
                }
                if(sentForm.getdateOfApplication().isEmpty()){
                    date_warning.setTextFill(Color.web("#FF0000"));
                    date_warning.setText("Required");
                }
                if(sentForm.getnameOfApplicant().isEmpty()){
                    name_warning.setTextFill(Color.web("#FF0000"));
                    name_warning.setText("Required");
                }
                if(sentForm.getphoneNumber().isEmpty()){
                    phoneNum_warning.setTextFill(Color.web("#FF0000"));
                    phoneNum_warning.setText("Required");

                }
                //String oldMessage = submit_message.getText();

                //String errorMessage = sentForm.getMissingFieldsStatement(); //apply to a Label
                //submit_message.setText(oldMessage + "  " +  errorMessage);
            }

            //sends the form to database when int/double fields contain the correct datatype and
            //all required fields have received input


            if(readyToSend){

                /*
                if(!data.currentFormID.equals("")){
                    driver.setField(data.currentFormID, "delete", "status");
                    data.currentFormID = "";
                }
                */
                SQLDriver driver = new SQLDriver();
                //sets the names of columns in the database, if additional form fields are added, please add a new column
                String [] columns = {"formID", "repID", "plantRegistry", "domesticOrImported", "serialNumber", "brandName", "beverageType", "fancifulName", "permitName", "streetAddress","city","state", "zip", "extraInfo", "dateOfApplication", "formula", "grapeVarietals", "vintage", "wineAppellation", "email", "phoneNumber", "pHValue", "alcoholContent", "status", "approvingUser", "approvalDate", "expirationDate", "issuedDate", "volume", "appType", "surrenderDate", "qualifier", "name"};
                //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true", columns);
                //
                //contains the datatype of each column in the database, when adding a new column, please also add it's datatype here/
                //"text" for strings and "real" for doubles/integers

                DBTypes[] full_types = {new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("real"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),new DBTypes("text"),new DBTypes("text"), new DBTypes("text"), new DBTypes("text")};

                //int _id_count = driver.select_all("form_data.db", "form_data").size();

                //iterates through the formID column of the database in order to find the current highest formID value
                ArrayList<String> current_ids = new ArrayList<String>();
                for (HashMap<String, ReturnedValue> result:driver.select_all("stringified_ids_db.db", "form_data")){
                    current_ids.add(result.get("formID").to_string());
                    
                }
                
                //collects values from fields of sentForm object (see Form.java)
                DBValue[] all_vals = {new DBValue<String>(driver.generate_id(current_ids)), new DBValue<String>(sentForm.getrepID()), new DBValue<String>(sentForm.getplantRegistry()), new DBValue<String>(sentForm.getdomesticOrImported()), new DBValue<String>(sentForm.getserialNumber()), new DBValue<String>(sentForm.getbrandName()), new DBValue<String>(sentForm.getbeverageType()), new DBValue<String>(sentForm.getfancifulName()), new DBValue<String>(sentForm.getPermitname()), new DBValue<String>(sentForm.getStreet()), new DBValue<String>(sentForm.getCity()), new DBValue<String>(sentForm.getState()), new DBValue<String>(sentForm.getZip()), new DBValue<String>(sentForm.getextraInfo()), new DBValue<String>(sentForm.getdateOfApplication()), new DBValue<String>(sentForm.getformula()), new DBValue<String>(sentForm.getgrapeVarietals()), new DBValue<String>(sentForm.getvintage()), new DBValue<String>(sentForm.getwineAppellation()), new DBValue<String>(sentForm.getemail()), new DBValue<String>(sentForm.getphoneNumber()), new DBValue<Double>(sentForm.getpHValue()), new DBValue<Double>(sentForm.getalcoholContent()), new DBValue<String>(sentForm.getFormStatus()), new DBValue<String>("noUser"), new DBValue<String>("NoDateAprroved"), new DBValue<String>("NoDateExir"), new DBValue<String>("No issued date"), new DBValue<String>(sentForm.getVolume()), new DBValue<String>("No App Type"), new DBValue<String>("No Surrender Date"), new DBValue<String>("no qualification"), new DBValue<String>(sentForm.getnameOfApplicant())};

                //the values above are actually entered into the database (contained in form_data.db)
                driver.insert_vals("form_data", "stringified_ids_db.db", all_vals);

                //displays message after form has successfully been entered into the database
                String success = "Form successfully Submitted.";
                submit_message.setTextFill(Color.web("#4BB543"));
                submit_message.setText(success);
                delete();
            }


        }
    }

    //the following runs formsubmission page is opened
    @FXML
    private void delete(){
        if(!data.currentFormID.equals("")){
            try {
                SQLDriver driver  = new SQLDriver();
                driver.setField(data.currentFormID, "delete", "status");
            }
            catch(Exception e){

            }
        }
        origin.selectToggle(null);
        beverage.selectToggle(null);
        repIdNum_Field.clear();
        serialNum_Field.clear();
        brandName_Field.clear();
        permitNum_Field.clear();
        formula_Field.clear();
        alcoholPercent_Field.clear();
        fancyName_Field.clear();
        phoneNum_Field.clear();
        email_Field.clear();
        appellation_Field.clear();
        grape_Field.clear();
        ph_Field.clear();
        vintage_Field.clear();
        applicantName_Field.clear();
        brandedInfo_Field.clear();
        volume_Field.clear();
        state_Field.clear();
        street_Field.clear();
        city_Field.clear();
        zip_Field.clear();
        permitName.clear();
        alcoholPercent_warning.setText("");
        phoneNum_warning.setText("");
        name_warning.setText("");
        date_warning.setText("");
        brand_warning.setText("");
        beverage_warning.setText("");
        serial_warning.setText("");
        domestic_warning.setText("");
        ph_warning.setText("");

        LocalDate date = LocalDate.now();
        date_Field.setValue(date);
        email_Field.setText(toolBarController.getCurUser().getUsername());
        applicantName_Field.setText(toolBarController.getCurUser().getFirstName() + " " + toolBarController.getCurUser().getLastName());
        street_Field.setText(toolBarController.getCurUser().getStreet());
        city_Field.setText(toolBarController.getCurUser().getCity());
        state_Field.setText(toolBarController.getCurUser().getState());
        zip_Field.setText(toolBarController.getCurUser().getZip());
        repIdNum_Field.setText(toolBarController.getCurUser().getRepId());
        phoneNum_Field.setText(toolBarController.getCurUser().getPhone());




    }

    private void clearWarnings(){
        alcoholPercent_warning.setText("");
        phoneNum_warning.setText("");
        name_warning.setText("");
        date_warning.setText("");
        brand_warning.setText("");
        beverage_warning.setText("");
        serial_warning.setText("");
        domestic_warning.setText("");
        ph_warning.setText("");
        submit_message.setText("");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        beverage.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                setWineToggle();
            }

        }

        );

        LocalDate date = LocalDate.now();
        date_Field.setValue(date);
        email_Field.setText(toolBarController.getCurUser().getUsername());
        applicantName_Field.setText(toolBarController.getCurUser().getFirstName() + " " + toolBarController.getCurUser().getLastName());
        street_Field.setText(toolBarController.getCurUser().getStreet());
        city_Field.setText(toolBarController.getCurUser().getCity());
        state_Field.setText(toolBarController.getCurUser().getState());
        zip_Field.setText(toolBarController.getCurUser().getZip());
        repIdNum_Field.setText(toolBarController.getCurUser().getRepId());
        phoneNum_Field.setText(toolBarController.getCurUser().getPhone());

        data = DataTransfer.getInstance();
        if(!data.currentFormID.equals("")){
            SQLDriver driver = new SQLDriver();
           // MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
            try {
                HashMap<String, ReturnedValue> result = driver.get_data_by_value("form_data", "stringified_ids_db.db", "formID", new DBValue<String>(data.currentFormID));
                email_Field.setText(result.get("email").to_string());
                applicantName_Field.setText(result.get("name").to_string());
                street_Field.setText(result.get("streetAddress").to_string());
                city_Field.setText(result.get("city").to_string());
                state_Field.setText(result.get("state").to_string());
                zip_Field.setText(result.get("zip").to_string());
                repIdNum_Field.setText(result.get("repID").to_string());
                phoneNum_Field.setText(result.get("phoneNumber").to_string());
                permitNum_Field.setText(result.get("plantRegistry").to_string());
                if(result.get("domesticOrImported").to_string().equals("domestic")){
                    domestic_RadButton.setSelected(true);
                }
                else if(result.get("domesticOrImported").to_string().equals("imported")){
                    imported_RadButton.setSelected(true);
                }
                serialNum_Field.setText(result.get("serialNumber").to_string());
                brandName_Field.setText(result.get("brandName").to_string());
                String type = result.get("beverageType").to_string();
                //TODO wine special fields
                if(type.equals("Wine")){
                    wine_RadButton.setSelected(true);
                }
                else if(type.equals("Malt Beverage")){
                    beer_RadButton.setSelected(true);
                }
                else if(type.equals("Distilled Spirits")){
                    liquor_RadButton.setSelected(true);
                }
                fancyName_Field.setText(result.get("fancifulName").to_string());
                permitName.setText(result.get("permitName").to_string());
                brandedInfo_Field.setText(result.get("extraInfo").to_string());
                formula_Field.setText(result.get("formula").to_string());
                grape_Field.setText(result.get("grapeVarietals").to_string());
                vintage_Field.setText(result.get("vintage").to_string());
                appellation_Field.setText(result.get("wineAppellation").to_string());
                ph_Field.setText(result.get("pHValue").to_string());
                alcoholPercent_Field.setText(result.get("alcoholContent").to_string());
                volume_Field.setText(result.get("volume").to_string());
                //TODO actually delete forms
                driver.setField(data.currentFormID, "delete", "status");
            }
            catch (Exception e){

            }
            data.currentFormID = ""; //clear form id


        }

        if(data.upload) {
/*
            public String pdf_Mailing = "";

            public String pdf_Grape = "";
            */

            fancyName_Field.setText(data.pdf_Fancy);
            appellation_Field.setText(data.pdf_WineApp);
            permitNum_Field.setText(data.pdf_PlantReg);
            street_Field.setText(data.pdf_Address);
            formula_Field.setText(data.pdf_Formula);
            brandName_Field.setText(data.pdf_BrandName);
            //email_Field.setText(data.);
            grape_Field.setText(data.pdf_Grape);
            data.upload = false;
        }
    }


    @FXML
    private void saveApp(ActionEvent event) throws Exception{
            clearWarnings();
            Form sentForm = new Form();
            submit_message.setText("");//string to insert into textfield either confirming submission or printing error messge
            submit_message.setTextFill(Color.web("#FF0000"));
            //sets Domestic Or Imported field
            if (origin.getSelectedToggle() != null) {
                RadioButton selectedOption = (RadioButton) origin.getSelectedToggle();
                sentForm.setDomesticOrImported(selectedOption.getText());
            }
            //sets the Type of Drink (either wine, beer, or liquor)
            if(beverage.getSelectedToggle() != null){
                RadioButton selectedBevType = (RadioButton) beverage.getSelectedToggle();
                sentForm.setBeverageType(selectedBevType.getText());
            }
            //sets the REP ID number
            sentForm.setRepID(repIdNum_Field.getText());
            //Sets Plant Registry/Basic Permit/Brewer's Number
            sentForm.setPlantRegistry(permitNum_Field.getText());
            //Sets Serial Number
            sentForm.setSerialNumber((serialNum_Field.getText()));
            //Sets Phone Number
            sentForm.setPhoneNumber(phoneNum_Field.getText());
            //Sets Email
            sentForm.setEmail(email_Field.getText());
            //Sets Brand Name
            sentForm.setBrandName(brandName_Field.getText());
            //Sets Fanciful Name
            sentForm.setFancifulName(fancyName_Field.getText());
            //Sets Date of Submission
            //sentForm.setDateOfApplication(date_Field.getText());
            try{
                sentForm.setDateOfApplication(date_Field.getValue().toString());
            }
            catch (NullPointerException e){
            }
            //Sets Branded/Embossed Non-label info
            sentForm.setExtraInfo(brandedInfo_Field.getText());
            //Sets Name of Applicant
            sentForm.setNameOfApplicant(applicantName_Field.getText());
            sentForm.setVolume(volume_Field.getText());
            sentForm.setCity(city_Field.getText());
            sentForm.setStreet(street_Field.getText());
            sentForm.setState(state_Field.getText());
            sentForm.setZip(zip_Field.getText());
            sentForm.setPermitname(permitName.getText());
            sentForm.setFormula(formula_Field.getText());
            //sets non-read approval status
            sentForm.setFormStatus(toolBarController.getCurUser().getUsername());
            if(!(frontImageDisp == null))
            {
                sentForm.setFront_Upload(frontImageDisp.getImage());
            }
            if(!(backImageDisp == null))
            {
                sentForm.setBack_Upload(backImageDisp.getImage());
            }
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
                  //  readyToSend = false;
                    ph_warning.setTextFill(Color.web("#FF0000"));
                    ph_warning.setText("Please enter a number");
                }
            }
            //Sets alcohol content percentage and checks to see if it is a double
            try {
                sentForm.setAlcoholContent(Double.parseDouble(alcoholPercent_Field.getText()));
            }
            catch (NumberFormatException e){
                //readyToSend = false;
                //String oldMessage = submit_message.getText();
                //submit_message.setText(oldMessage + "  Please enter a number for Alcohol Percentage.");
                alcoholPercent_warning.setTextFill(Color.web("#FF0000"));
                alcoholPercent_warning.setText("Please enter a number");
            }

            //sends the form to database when int/double fields contain the correct datatype and
            //all required fields have received input
                String [] columns = {"formID", "repID", "plantRegistry", "domesticOrImported", "serialNumber", "brandName", "beverageType", "fancifulName", "permitName", "streetAddress","city","state", "zip", "extraInfo", "dateOfApplication", "formula", "grapeVarietals", "vintage", "wineAppellation", "email", "phoneNumber", "pHValue", "alcoholContent", "status", "approvingUser", "approvalDate", "expirationDate", "issuedDate", "volume", "appType", "surrenderDate", "qualifier", "name"};
                //
                //contains the datatype of each column in the database, when adding a new column, please also add it's datatype here/
                //"text" for strings and "real" for doubles/integers
        SQLDriver driver = new SQLDriver();

       // MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true", columns);
                DBTypes[] full_types = {new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                        new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                        new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                        new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                        new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                        new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                        new DBTypes("text"), new DBTypes("double"), new DBTypes("double"), new DBTypes("text"),
                        new DBTypes("text"),new DBTypes("text"),new DBTypes("text"), new DBTypes("text"),
                        new DBTypes("text")};
                //22,23
                //int _id_count = driver.select_all("form_data.db", "form_data").size();

                //iterates through the formID column of the database in order to find the current highest formID value
                ArrayList<String> current_ids = new ArrayList<String>();
                for (HashMap<String, ReturnedValue> result:driver.select_all("stringified_ids_db.db", "form_data")){
                    current_ids.add(result.get("formID").to_string());
                    
                }
                
                //collects values from fields of sentForm object (see Form.java)
                DBValue[] all_vals = {new DBValue<String>(driver.generate_id(current_ids)), new DBValue<String>(sentForm.getrepID()), new DBValue<String>(sentForm.getplantRegistry()), new DBValue<String>(sentForm.getdomesticOrImported()), new DBValue<String>(sentForm.getserialNumber()), new DBValue<String>(sentForm.getbrandName()), new DBValue<String>(sentForm.getbeverageType()), new DBValue<String>(sentForm.getfancifulName()), new DBValue<String>(sentForm.getPermitname()), new DBValue<String>(sentForm.getStreet()), new DBValue<String>(sentForm.getCity()), new DBValue<String>(sentForm.getState()), new DBValue<String>(sentForm.getZip()), new DBValue<String>(sentForm.getextraInfo()), new DBValue<String>(sentForm.getdateOfApplication()), new DBValue<String>(sentForm.getformula()), new DBValue<String>(sentForm.getgrapeVarietals()), new DBValue<String>(sentForm.getvintage()), new DBValue<String>(sentForm.getwineAppellation()), new DBValue<String>(sentForm.getemail()), new DBValue<String>(sentForm.getphoneNumber()), new DBValue<Double>(sentForm.getpHValue()), new DBValue<Double>(sentForm.getalcoholContent()), new DBValue<String>(toolBarController.getCurUser().getUsername()), new DBValue<String>("noUser"), new DBValue<String>("NoDateAprroved"), new DBValue<String>("NoDateExir"), new DBValue<String>("No issued date"), new DBValue<String>(sentForm.getVolume()), new DBValue<String>("No App Type"), new DBValue<String>("No Surrender Date"), new DBValue<String>("no qualification"), new DBValue<String>(sentForm.getnameOfApplicant())};

                //the values above are actually entered into the database (contained in form_data.db)
                driver.insert_vals("form_data", "stringified_ids_db.db", all_vals);
                //displays message after form has successfully been entered into the database
                String success = "Form successfully Saved.";
                submit_message.setTextFill(Color.web("#4BB543"));
                submit_message.setText(success);
        }


}
