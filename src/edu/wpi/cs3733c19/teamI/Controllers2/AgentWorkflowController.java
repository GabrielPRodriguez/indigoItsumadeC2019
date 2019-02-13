package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.sub_Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;

public class AgentWorkflowController {
    private ToolBarController toolBarController;
    private Scene ResultsScene;

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
    public void goAbout(ActionEvent actionEvent){toolBarController.goAbout(actionEvent); }

    @FXML
    public void goWorkflow(ActionEvent actionEvent){toolBarController.goWorkflow(actionEvent);}

    public void setUserName(String name){
        theUserName.setText(name);
    }

    Scene Home;

    public void setHome(Scene home)
    {
        Home = home;
    }


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
    Button choose_button7;

    @FXML
    Button choose_button8;

    @FXML
    Button choose_button9;

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
    JFXRadioButton domestic_RadButton;

    @FXML
    JFXRadioButton imported_RadButton;

    @FXML
    ListView listView;

    String formStatus_string;
    int currentFormID = 0;

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
        SQLDriver driver = new SQLDriver();
        ArrayList<HashMap<String, ReturnedValue>>filtered_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:driver.select_all("form_db_from_spreadsheet.db", "form_db")){
            //if (result.get("status").to_string().equals("unread")){

                filtered_results.add(result);
            //}
        }
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
                    //test.get(i).setText("Form "+_temp.get("formID").to_string().replace(".0", ""));
                    test.get(i).setText(_temp.get("formID").to_string().replace(".0", ""));
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
        accept_button.setDisable(false);
        reject_button.setDisable(false);

        //int formID = 0;
        if (choose.getSource() == choose_button1) {
            //System.out.println(formID_1.getText());
            currentFormID = Integer.parseInt(formID_1.getText());

        } else if (choose.getSource() == choose_button2) {
            currentFormID = Integer.parseInt(formID_2.getText());

        } else if (choose.getSource() == choose_button3) {
            currentFormID = Integer.parseInt(formID_3.getText());

        }else if (choose.getSource() == choose_button4) {
            currentFormID = Integer.parseInt(formID_4.getText());

        }else if (choose.getSource() == choose_button5) {
            currentFormID = Integer.parseInt(formID_5.getText());

        }else if (choose.getSource() == choose_button6) {
            currentFormID = Integer.parseInt(formID_6.getText());

        }else if (choose.getSource() == choose_button7) {
            currentFormID = Integer.parseInt(formID_7.getText());

        }else if (choose.getSource() == choose_button8) {
            currentFormID = Integer.parseInt(formID_8.getText());

        }else if (choose.getSource() == choose_button9) {
            currentFormID = Integer.parseInt(formID_9.getText());

        }

        SQLDriver driver = new SQLDriver();
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_db", "form_db_from_spreadsheet.db", "formID", new DBValue<Integer>(currentFormID));


        //setting the text for each field from values in the database

        repID_text.setText(result.get("repID").to_string());
        plantRegistry_text.setText(result.get("plantRegistry").to_string());
        //omesticImported_text.setText(result.get("domesticOrImported").to_string());
        serialNum_text.setText(result.get("serialNumber").to_string());
        //productType_text.setText(result.get("beverageType").to_string());
        brandName_text.setText(result.get("brandName").to_string());
        fancifulName_text.setText(result.get("fancifulName").to_string());
        //nameAddress_text.setText(result.get("nameAndAddress").to_string());
        //mailingAddress_text.setText(result.get("mailingAddress").to_string());

        System.out.println(result.toString());
        streetAdress_text.setText(result.get("streetAddress").to_string());

        formula_text.setText(result.get("formula").to_string());
        grapeVarietal_text.setText(result.get("grapeVarietals").to_string());
        wineAppellation_text.setText(result.get("wineAppellation").to_string());
        winepH_text.setText(result.get("pHValue").to_string());
        vintage_text.setText(result.get("vintage").to_string());
        alcoholContent_text.setText(result.get("alcoholContent").to_string());
        //phoneNum_text.setText(result.get("phoneNumber").to_string());
        //emailAddress_text.setText(result.get("email").to_string());
        brandedInfo_text.setText(result.get("extraInfo").to_string());
        //applicationDate_text.setText(result.get("dateOfApplication").to_string());
        applicantName_text.setText(result.get("nameOfApplicant").to_string());
        formStatus_string = (result.get("status").to_string()); //I use two variables because I need the formStatus text as a string
        //formStatus_text.setText(formStatus_string);
    }

/*
    @FXML
    TextField approvalStatus_text;

    @FXML
    TextField approvingUser_text;

    @FXML
    TextField expirationDate_text;

*/

}
