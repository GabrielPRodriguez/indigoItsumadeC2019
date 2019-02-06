package edu.wpi.cs3733c19.teamI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

import java.io.IOException;

public class FormCheckerController implements Initializable {
    private LoginController loginCtrl;

    public void setLoginCtrl(LoginController loginCtrl) {
        this.loginCtrl = loginCtrl;
    }


    public void setUserName(String name){
        theUserName.setText(name);
    }

    Scene Home;

   public void setHome(Scene home)
    {
        Home = home;
    }


    @FXML
    Button submit_button;

    @FXML
    Button reject_button;

    @FXML
    Button save_button;

    @FXML
    MenuItem refresh;

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
    Label theUserName;

    @FXML
    Label formID_1;

    @FXML
    Label formID_2;

    @FXML
    Label formID_3;

    @FXML
    Button goHome;

    @FXML
    TextField repID_text;

    @FXML
    TextField plantRegistry_text;

    @FXML
    TextField domesticImported_text;

    @FXML
    TextField serialNum_text;

    @FXML
    TextField productType_text;

    @FXML
    TextField brandName_text;

    @FXML
    TextField fancifulName_text;

    @FXML
    TextArea nameAddress_text;

    @FXML
    TextArea mailingAddress_text;

    @FXML
    TextField formula_text;

    @FXML
    TextField grapeVarietal_text;

    @FXML
    TextField wineAppellation_text;

    @FXML
    TextField winepH_text;

    @FXML
    TextField vintage_text;

    @FXML
    TextField alcoholContent_text;

    @FXML
    TextField phoneNum_text;

    @FXML
    TextField emailAddress_text;

    @FXML
    TextArea brandedInfo_text;

    @FXML
    TextField applicationDate_text;

    @FXML
    TextField applicantName_text;

    @FXML
    TextField formStatus_text;
/*
    @FXML
    TextField approvalStatus_text;

    @FXML
    TextField approvingUser_text;

    @FXML
    TextField expirationDate_text;

*/

    String formStatus_string;
    int currentFormID = 0;

    @FXML
    private void pull_Forms(ActionEvent choose) throws IOException, Exception{
        formID_1.setText("");
        formID_2.setText("");
        formID_3.setText("");
        choose_button1.setDisable(false);
        choose_button2.setDisable(false);
        choose_button3.setDisable(false);
        SQLDriver driver = new SQLDriver();
        ArrayList<HashMap<String, ReturnedValue>>filtered_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:driver.select_all("form_data.db", "form_data")){
            if (result.get("status").to_string().equals("unread")){

                filtered_results.add(result);
            }
        }
        HashMap<Integer, Label>test = new HashMap<Integer, Label>();
        test.put(1, formID_1);
        test.put(2, formID_2);
        test.put(3, formID_3);
        if (filtered_results.size() > 0){
            for (int i = 1; i < 4; i++){
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

        }
        else{
            formID_1.setText("No forms to be approved");
            choose_button1.setDisable(true);
            choose_button2.setDisable(true);
            choose_button3.setDisable(true);
        }
    }

    @FXML
    private void chooseButtonHandler(ActionEvent choose) throws IOException, Exception{
        submit_button.setDisable(false);
        reject_button.setDisable(false);

        //int formID = 0;
        if(choose.getSource() == choose_button1){
            //System.out.println(formID_1.getText());
            currentFormID = Integer.parseInt(formID_1.getText());

        }

        else if(choose.getSource() == choose_button2){
            currentFormID = Integer.parseInt(formID_2.getText());

        }

        else if(choose.getSource() == choose_button3){
            currentFormID = Integer.parseInt(formID_3.getText());

        }




        SQLDriver driver = new SQLDriver();
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "form_data.db", "formID", new DBValue<Integer>(currentFormID));


        repID_text.setText(result.get("repID").to_string());
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
        formStatus_string = (result.get("status").to_string()); //I use two variables because I need the formStatus text as a string
        formStatus_text.setText(formStatus_string);




    }

    @FXML
    private void approveHandler() throws IOException, Exception{

        formStatus_string = "approved";

        SQLDriver.setApprovalStatus(currentFormID, formStatus_string);
        Date date = new Date();
        String theDate = date.toString();
        date.equals(date.getTime()+10000);
        String exDate = date.toString();
        SQLDriver.setApprovalDate(currentFormID, theDate);
        SQLDriver.setApprovingUser(currentFormID, this.theUserName.getText());
        SQLDriver.setExpirationDate(currentFormID, exDate);



        clearFields();
        pull_Forms(new ActionEvent());

        submit_button.setDisable(true);
        reject_button.setDisable(true);

    }

    @FXML
    private void rejectHandler() throws IOException, Exception{

        formStatus_string = "reject";

        SQLDriver.setApprovalStatus(currentFormID, formStatus_string);
        clearFields();

        pull_Forms(new ActionEvent());

        submit_button.setDisable(true);
        reject_button.setDisable(true);

    }

    private void clearFields(){
        repID_text.clear();
        plantRegistry_text.clear();
        applicantName_text.clear();
        applicationDate_text.clear();
        brandedInfo_text.clear();
        emailAddress_text.clear();
        phoneNum_text.clear();
        alcoholContent_text.clear();
        vintage_text.clear();
        winepH_text.clear();
        grapeVarietal_text.clear();
        wineAppellation_text.clear();
        formula_text.clear();
        mailingAddress_text.clear();
        fancifulName_text.clear();
        brandName_text.clear();
        productType_text.clear();
        serialNum_text.clear();
        domesticImported_text.clear();
        nameAddress_text.clear();
        formStatus_text.clear();

    }

    public void goHomePage(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(Home);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLoginCtrl(loginCtrl);
    }
}
