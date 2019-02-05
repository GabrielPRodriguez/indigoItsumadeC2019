package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.Form;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.*;
import java.util.*;

import java.io.IOException;

public class FormCheckerController {

    @FXML
    Button submit_button;

    @FXML
    Button reject_button;

    @FXML
    Button save_button;

    @FXML
    Button specialist_button;

    @FXML
    Button choose_button1;

    @FXML
    Button choose_button2;

    @FXML
    Button choose_button3;


    @FXML
    Button refresh_button;

    @FXML
    Text formID_1;

    @FXML
    Text formID_2;

    @FXML
    Text formID_3;


    @FXML
    Text repID_text;

    @FXML
    Text plantRegistry_text;

    @FXML
    Text domesticImported_text;

    @FXML
    Text serialNum_text;

    @FXML
    Text productType_text;

    @FXML
    Text brandName_text;

    @FXML
    Text fancifulName_text;

    @FXML
    Text nameAddress_text;

    @FXML
    Text mailingAddress_text;

    @FXML
    Text formula_text;

    @FXML
    Text grapeVarietal_text;

    @FXML
    Text wineAppellation_text;

    @FXML
    Text winepH_text;

    @FXML
    Text vintage_text;

    @FXML
    Text alcoholContent_text;

    @FXML
    Text phoneNum_text;

    @FXML
    Text emailAddress_text;

    @FXML
    Text brandedInfo_text;

    @FXML
    Text applicationDate_text;

    @FXML
    Text applicantName_text;

    @FXML
    Text formStatus_text;
    String formStatus_string;

    @FXML
    private void chooseButtonHandler(ActionEvent choose) throws IOException, Exception{

        int formID = 0;
        if(choose.getSource() == choose_button1){
            formID = Integer.parseInt(formID_1.getText());

        }

        else if(choose.getSource() == choose_button2){
            formID = Integer.parseInt(formID_2.getText());

        }

        else if(choose.getSource() == choose_button3){
            formID = Integer.parseInt(formID_3.getText());

        }




        SQLDriver driver = new SQLDriver();
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "form_data.db", "formID", new DBValue<Integer>(formID));


        repID_text.setText(result.get("repId").to_string());
        plantRegistry_text.setText(result.get("plantRegistry").to_string());
        domesticImported_text.setText(result.get("domesticOrImported").to_string());
        serialNum_text.setText(result.get("serialNumber").to_string());
        productType_text.setText(result.get("beverageType").to_string());
        brandName_text.setText(result.get("brandName").to_string());
        fancifulName_text.setText(result.get("fancifulName").to_string());
        nameAddress_text.setText(result.get("nameAndAddress").to_string());
        mailingAddress_text.setText(result.get("mailingAddress").to_string());
        formula_text.setText(result.get("formula").to_string());
        grapeVarietal_text.setText(result.get("grapeVarietals").to_string());
        wineAppellation_text.setText(result.get("wineAppellation").to_string());
        winepH_text.setText(result.get("pHValue").to_string());
        vintage_text.setText(result.get("vintage").to_string());
        alcoholContent_text.setText(result.get("alcoholContent").to_string());
        phoneNum_text.setText(result.get("phoneNumber").to_string());
        emailAddress_text.setText(result.get("email").to_string());
        brandedInfo_text.setText(result.get("extraInfo").to_string());
        applicationDate_text.setText(result.get("dateOfApplication").to_string());
        applicantName_text.setText(result.get("nameOfApplicant").to_string());
        formStatus_string = (result.get("formStatus").to_string()); //I use two variables because I need the formStatus text as a string
        formStatus_text.setText(formStatus_string);



    }

    @FXML
    private void approveHandler(ActionEvent approve) throws IOException, Exception{

        formStatus_string = "approved";

        SQLDriver.setApprovalStatus(Integer.parseInt(formID_1.getText()), formStatus_string); //need to specify formID

    }

    @FXML
    private void rejectHandler(ActionEvent reject) throws IOException, Exception{

        formStatus_string = "rejected";

        SQLDriver.setApprovalStatus(Integer.parseInt(formID_1.getText()), formStatus_string);


    }


}
