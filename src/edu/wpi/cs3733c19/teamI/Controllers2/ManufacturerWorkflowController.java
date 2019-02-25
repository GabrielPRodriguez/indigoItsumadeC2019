package edu.wpi.cs3733c19.teamI.Controllers2;

import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.DataTransfer;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ManufacturerWorkflowController {
    public boolean special = false;
    private ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    JFXButton accept_button;

    @FXML
    JFXButton reject_button;

    @FXML
    JFXButton save_button;

    @FXML
    MenuItem refresh;

    @FXML
    JFXButton specialist_button;

    @FXML
    JFXButton choose_button1;

    @FXML
    JFXButton choose_button2;

    @FXML
    JFXButton choose_button3;

    @FXML
    JFXButton choose_button4;

    @FXML
    JFXButton choose_button5;

    @FXML
    JFXButton choose_button6;

    @FXML
    JFXButton choose_button7;

    @FXML
    JFXButton choose_button8;

    @FXML
    JFXButton choose_button9;

    @FXML
    JFXTextField qualifier;

    @FXML
    Label formID_1;

    @FXML
    Label formID_2;

    @FXML
    Label formID_3;

    @FXML
    Label formID_4;

    @FXML
    Label formID_5;

    @FXML
    Label formID_6;

    @FXML
    Label formID_7;

    @FXML
    Label formID_8;

    @FXML
    Label formID_9;

    @FXML
    JFXButton refresh_button;

    @FXML
    Label theUserName;

    @FXML
    JFXButton goHome;

    @FXML
    JFXTextField domestic_text;

    @FXML
    JFXTextField beverage_text;

    @FXML
    JFXTextField repID_text;

    @FXML
    JFXTextField permitName_text;

    @FXML
    JFXTextField plantRegistry_text;

    @FXML
    JFXTextField domesticImported_text;

    @FXML
    JFXTextField serialNum_text;

    @FXML
    JFXTextField productType_text;

    @FXML
    JFXTextField brandName_text;

    @FXML
    JFXTextField fancifulName_text;

    @FXML
    JFXTextArea nameAddress_text;

    @FXML
    JFXTextArea mailingAddress_text;

    @FXML
    JFXTextField formula_text;

    @FXML
    JFXTextField grapeVarietal_text;

    @FXML
    JFXTextField wineAppellation_text;

    @FXML
    JFXTextField winepH_text;

    @FXML
    JFXTextField vintage_text;

    @FXML
    JFXTextField alcoholContent_text;

    @FXML
    JFXTextField volume_text;

    @FXML
    JFXTextField phoneNum_text;

    @FXML
    JFXTextField emailAddress_text;

    @FXML
    JFXTextField brandedInfo_text;

    @FXML
    JFXTextField applicationDate_text;

    @FXML
    JFXTextField applicantName_text;

    @FXML
    JFXTextField formStatus_text;

    @FXML
    JFXTextField formID_text;

    @FXML
    JFXTextField status_text;

    @FXML
    JFXTextField approvalDate_text;

    @FXML
    JFXTextField expiryDate_text;

    @FXML
    JFXTextField issuedDate_text;

    @FXML
    JFXTextField surrenderDate_text;

    @FXML
    JFXTextField approvingUser_text;

    @FXML
    JFXTextField applicationType_text;

    @FXML
    JFXTextField formQualification_text; //won't fit

    @FXML
    JFXTextField streetAdress_text;

    @FXML
    JFXTextField city_text;

    @FXML
    JFXTextField state_text;

    @FXML
    JFXTextField zip_text;

    @FXML
    JFXTextField dateOfApplication_text;

    @FXML
    JFXTextField phoneNumber_text;

    @FXML
    JFXTextField email_text;

    @FXML
    JFXRadioButton wine_RadButton;

    @FXML
    JFXRadioButton beer_RadButton;

    @FXML
    JFXRadioButton liquor_RadButton;
    @FXML
    Text specialText;
    @FXML
    JFXRadioButton domestic_RadButton;
    @FXML
    JFXTextField commentBox;
    @FXML
    JFXRadioButton imported_RadButton;
    @FXML
    Text title;

    private HashMap<String, String> repToForm = new HashMap<>();

    @FXML
    public void update(){
        if(User.getUser("a","a", User.userPower.Specialist,"a","a","a","a",
                "a","a","a","a",",", 0, 0, 0, 0).getUserType().equals("Specialist")){
            specialText.setOpacity(1);
            commentBox.setPromptText("Add any comments as to why this particular form was rejected, accepted, or comments for corrections");
            special = true;
        }
        else{
            specialText.setOpacity(0);
            special = false;
        }
    }
    @FXML
    ListView listView;


    @FXML
    Label formStatusField;

    String formStatus_string;
    String currentFormID = "";


    @FXML
    public void goHelpWorkflow() throws IOException {
        toolBarController.goHelpWorkflow();
    }

    //pulls unread forms from the database to be selected
    @FXML
    private void pull_Forms() throws Exception{
        formID_1.setText("");
        formID_2.setText("");
        formID_3.setText("");
        formID_4.setText("");
        formID_5.setText("");
        formID_6.setText("");
        formID_7.setText("");
        formID_8.setText("");
        formID_9.setText("");
        choose_button1.setDisable(false);
        choose_button2.setDisable(false);
        choose_button3.setDisable(false);
        choose_button4.setDisable(false);
        choose_button5.setDisable(false);
        choose_button6.setDisable(false);
        choose_button7.setDisable(false);
        choose_button8.setDisable(false);
        choose_button9.setDisable(false);

       // SQLDriver driver = new SQLDriver();
        MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");

        ArrayList<HashMap<String, ReturnedValue>> filtered_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue> result:driver.select_all("stringified_ids_db.db", "form_data")){
                if (result.get("status").to_string().contains(toolBarController.getCurUser().getUsername())){
                    filtered_results.add(result);
                }
        }
        if(filtered_results.isEmpty()){
            System.out.println("nope");
        }
        System.out.println("process");
        HashMap<Integer, Label>test = new HashMap<Integer, Label>();
        test.put(1, formID_1);
        test.put(2, formID_2);
        test.put(3, formID_3);
        test.put(4, formID_4);
        test.put(5, formID_5);
        test.put(6, formID_6);
        test.put(7, formID_7);
        test.put(8, formID_8);
        test.put(9, formID_9);
        if (filtered_results.size() > 0){
            for (int i = 1; i < 10; i++){
                try{
                    HashMap<String, ReturnedValue>_temp = filtered_results.get(i-1);
                    test.get(i).setText(_temp.get("dateOfApplication").to_string() + ": " + _temp.get("fancifulName").to_string());
                    repToForm.put(test.get(i).getText(), _temp.get("formID").to_string());
                }
                catch(Exception e){
                    //pass
                }
            }

            if(formID_1.getText().equals("")){
                choose_button1.setDisable(true);
            }
            if(formID_2.getText().equals("")){
                choose_button2.setDisable(true);
            }
            if(formID_3.getText().equals("")){
                choose_button3.setDisable(true);
            }
            if(formID_4.getText().equals("")){
                choose_button4.setDisable(true);
            }
            if(formID_5.getText().equals("")){
                choose_button5.setDisable(true);
            }
            if(formID_6.getText().equals("")){
                choose_button6.setDisable(true);
            }
            if(formID_7.getText().equals("")){
                choose_button7.setDisable(true);
            }
            if(formID_8.getText().equals("")){
                choose_button8.setDisable(true);
            }
            if(formID_9.getText().equals("")){
                    choose_button9.setDisable(true);
            }

        }
        else{
            formID_1.setText("No forms to be approved");
            choose_button1.setDisable(true);
            choose_button2.setDisable(true); //buttons are disabled when there are no forms
            choose_button3.setDisable(true);
            choose_button4.setDisable(true);
            choose_button5.setDisable(true); //buttons are disabled when there are no forms
            choose_button6.setDisable(true);
            choose_button7.setDisable(true);
            choose_button8.setDisable(true); //buttons are disabled when there are no forms
            choose_button9.setDisable(true);
        }
    }

    @FXML
    private void chooseButtonHandler(ActionEvent choose) throws  Exception {

        //int formID = 0;
        if (choose.getSource() == choose_button1) {
            currentFormID = (repToForm.get(formID_1.getText()));

        } else if (choose.getSource() == choose_button2) {
            currentFormID = (repToForm.get(formID_2.getText()));

        } else if (choose.getSource() == choose_button3) {
            currentFormID = (repToForm.get(formID_3.getText()));

        }else if (choose.getSource() == choose_button4) {
            currentFormID = (repToForm.get(formID_4.getText()));

        }else if (choose.getSource() == choose_button5) {
            currentFormID = (repToForm.get(formID_5.getText()));

        }else if (choose.getSource() == choose_button6) {
            currentFormID = (repToForm.get(formID_6.getText()));

        }else if (choose.getSource() == choose_button7) {
            currentFormID = (repToForm.get(formID_7.getText()));

        }else if (choose.getSource() == choose_button8) {
            currentFormID = (repToForm.get(formID_8.getText()));

        }else if (choose.getSource() == choose_button9) {
            currentFormID = (repToForm.get(formID_9.getText()));

        }

        //Driver driver = new SQLDriver();
        MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "stringified_ids_db.db", "formID", new DBValue<String>(currentFormID));


        //setting the text for each field from values in the database

        repID_text.setText(result.get("repID").to_string());
        plantRegistry_text.setText(result.get("plantRegistry").to_string());
        domestic_text.setText(result.get("domesticOrImported").to_string());
        serialNum_text.setText(result.get("serialNumber").to_string());

        beverage_text.setText(result.get("beverageType").to_string());
        brandName_text.setText(result.get("brandName").to_string());
        fancifulName_text.setText(result.get("fancifulName").to_string());
        permitName_text.setText(result.get("permitName").to_string());
        //nameAddress_text.setText(result.get("nameAndAddress").to_string());
        //mailingAddress_text.setText(result.get("mailingAddress").to_string());

        streetAdress_text.setText(result.get("streetAddress").to_string());
        state_text.setText(result.get("state").to_string());
        city_text.setText(result.get("city").to_string());



        formula_text.setText(result.get("formula").to_string());
        grapeVarietal_text.setText(result.get("grapeVarietals").to_string());
        wineAppellation_text.setText(result.get("wineAppellation").to_string());
        winepH_text.setText(result.get("pHValue").to_string());
        vintage_text.setText(result.get("vintage").to_string());
        alcoholContent_text.setText(result.get("alcoholContent").to_string());
        phoneNumber_text.setText(result.get("phoneNumber").to_string());
        email_text.setText(result.get("email").to_string());
        brandedInfo_text.setText(result.get("extraInfo").to_string());

        qualifier.setText(result.get("qualifier").to_string());


        formStatus_string = (result.get("status").to_string()); //I use two variables because I need the formStatus text as a string
        if(formStatus_string.contains("specialist")||formStatus_string.contains("unread")){
            formStatusField.setText("waiting review");
        }
        else if(formStatus_string.contains("approved")){
            formStatusField.setText("Approved");
        }
        else if(formStatus_string.contains("reject")){
            formStatusField.setText("Rejected");
        }
        else{
            formStatusField.setText("Not Submitted");
        }

//        formStatus_text.setText(formStatus_string);
        volume_text.setText(result.get("volume").to_string());

        zip_text.setText(result.get("zip").to_string());
        dateOfApplication_text.setText(result.get("dateOfApplication").to_string());
        applicantName_text.setText(result.get("name").to_string());
    }


    public void editForm(){
        DataTransfer data = DataTransfer.getInstance();
        data.currentFormID = this.currentFormID;
        try {
            toolBarController.editForm();
        }
        catch (IOException e){

        }
    }



    @FXML
    public void sendBackHandler() throws IOException, Exception{
        formStatus_string = "commented";
        MongoDriver.setQualifier(currentFormID,commentBox.getText());
        MongoDriver.setApprovalStatus(currentFormID,formStatus_string);
        clearFields();

        pull_Forms();
        accept_button.setDisable(true);
        reject_button.setDisable(true);
    }
    @FXML
    public void forwardHandler() throws IOException, Exception{
        formStatus_string = "specialist";
        MongoDriver.setApprovalStatus(currentFormID,formStatus_string);
        clearFields();

        pull_Forms();
        accept_button.setDisable(true);
        reject_button.setDisable(true);
    }
    private void clearFields(){
        repID_text.clear();
        plantRegistry_text.clear();
        applicantName_text.clear();
        permitName_text.clear();
        serialNum_text.clear();
        brandName_text.clear();
        fancifulName_text.clear();
        domestic_text.clear();
        beverage_text.clear();
        alcoholContent_text.clear();
        volume_text.clear();
        formula_text.clear();
        grapeVarietal_text.clear();
        vintage_text.clear();
        wineAppellation_text.clear();
        winepH_text.clear();
        brandedInfo_text.clear();
        formID_text.clear();
        status_text.clear();
        approvalDate_text.clear();
        expiryDate_text.clear();
        issuedDate_text.clear();
        surrenderDate_text.clear();
        approvingUser_text.clear();
        applicationType_text.clear();
        formQualification_text.clear();
        streetAdress_text.clear();
        city_text.clear();
        state_text.clear();
        zip_text.clear();
        dateOfApplication_text.clear();
        phoneNumber_text.clear();
        email_text.clear();


    }





}
