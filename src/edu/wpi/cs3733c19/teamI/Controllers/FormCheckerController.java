package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.Form;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

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
    Button choose_button4;

    @FXML
    Button choose_button5;

    @FXML
    Button choose_button6;

    @FXML
    Button refresh_button;

    @FXML
    Text formID_1;

    @FXML
    Text formID_2;

    @FXML
    Text formID_3;

    @FXML
    Text formID_4;

    @FXML
    Text formID_5;

    @FXML
    Text formID_6;

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
    private void chooseButtonHandler(ActionEvent choose) throws IOException{

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

        else if(choose.getSource() == choose_button4){
            formID = Integer.parseInt(formID_4.getText());

        }

        else if(choose.getSource() == choose_button5){
            formID = Integer.parseInt(formID_5.getText());

        }

        else if(choose.getSource() == choose_button6){
            formID = Integer.parseInt(formID_6.getText());

        }

        //TODO: use given formID to pull info from database into a form object named pulledForm;
        Form pulledForm = new Form(); //either replace or use setters


        //display information on formChecker fxml

        repID_text.setText(pulledForm.getRepID());
        plantRegistry_text.setText(pulledForm.getPlantRegistry());
        domesticImported_text.setText(pulledForm.getDomesticOrImported());
        serialNum_text.setText(Integer.toString(pulledForm.getSerialNumber()));
        productType_text.setText(pulledForm.getBeverageType());
        brandName_text.setText(pulledForm.getBrandName());
        fancifulName_text.setText(pulledForm.getFancifulName());
        nameAddress_text.setText(pulledForm.getNameAndAddress());
        mailingAddress_text.setText(pulledForm.getMailingAddress());
        formula_text.setText(pulledForm.getFormula());
        grapeVarietal_text.setText(pulledForm.getGrapeVarietals());
        wineAppellation_text.setText(pulledForm.getWineAppellation());
        winepH_text.setText(Double.toString(pulledForm.getpHValue()));
        vintage_text.setText(pulledForm.getVintage());
        alcoholContent_text.setText(Double.toString(pulledForm.getAlcoholContent()));
        phoneNum_text.setText(pulledForm.getPhoneNumber());
        emailAddress_text.setText(pulledForm.getEmail());
        brandedInfo_text.setText(pulledForm.getExtraInfo());
        applicationDate_text.setText(pulledForm.getDateOfApplication());
        applicantName_text.setText(pulledForm.getNameOfApplicant());



    }

    @FXML
    private void approveHandler(ActionEvent approve) throws IOException{


    }

    @FXML
    private void rejectHandler(ActionEvent reject) throws IOException{

    }
}
