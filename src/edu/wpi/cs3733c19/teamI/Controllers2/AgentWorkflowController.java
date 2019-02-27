package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.Mongo;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Controllers2.ToolBarController;
import edu.wpi.cs3733c19.teamI.Entities.FormWorkflow;
import edu.wpi.cs3733c19.teamI.Entities.User;
import edu.wpi.cs3733c19.teamI.Entities.sub_Form;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.XMLFormatter;

public class AgentWorkflowController implements Initializable {
    public int wineNum;
    public int beerNum;
    public int spiritsNum;
    public int barNum;
    public Image[] wines;
    public Image[] beers;
    public Image[] spirits;
    public Image[] bars;
    public boolean special = false;
    private ToolBarController toolBarController = ToolBarController.getInstance();

    // Tableview Fields
    private final ObservableList<FormWorkflow> DisplayedResults = FXCollections.observableArrayList();
    private ObjectProperty<ObservableList<FormWorkflow>> dispList = new SimpleObjectProperty<>(DisplayedResults);

    /**
     * Table Stuff
    */
    FormWorkflow item;

    @FXML
    TableView<FormWorkflow> tableView;

    @FXML
    TableColumn FancifulName;

    @FXML
    TableColumn BrandName;

    @FXML
    TableColumn ResultNumber;

    private int numResults = 10;
    private int currentPage = 1;


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

    String form1 = "";

    String form2 = "";

    String form3 = "";

    String form4 = "";

    String form5 = "";

    String form6 = "";

    String form7 = "";

    String form8 = "";

    String form9 = "";

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
    @FXML
    Button sendBack;
    @FXML
    Button forwardButton;
    @FXML
    JFXButton toBar;
    @FXML
    JFXButton test;
    @FXML
    JFXRadioButton wineXP;
    @FXML
    JFXRadioButton beerXP;
    @FXML
    JFXRadioButton spiritsXP;
    @FXML
    JFXRadioButton barXP;
    @FXML
    ImageView wineCount,beerCount,spiritsCount,barCount;

    @FXML
    public void testBottles(){
        User use = User.getUser("a","a",User.userPower.Standard,"a","a","a","a","a","a","a","a","a",1,1,1,1);
        if(wineXP.isSelected()){
            if(wineNum == 11){
                wineCount.setImage(wines[0]);
                wineNum = 0;
                use.incrementWineScore();
            }
            else{
                wineNum++;
                wineCount.setImage(wines[wineNum]);
                use.incrementWineScore();
            }
        }
        else if(beerXP.isSelected()){
            if(beerNum == 11){
                beerCount.setImage(beers[0]);
                beerNum = 0;
                use.incrementBeerScore();
            }
            else{
                beerNum++;
                beerCount.setImage(beers[beerNum]);
                use.incrementBeerScore();
            }
        }
        else if(spiritsXP.isSelected()){
            if(spiritsNum == 11){
                spiritsCount.setImage(spirits[0]);
                spiritsNum = 0;
                use.incrementSpiritScore();
            }
            else{
                spiritsNum++;
                spiritsCount.setImage(spirits[spiritsNum]);
                use.incrementSpiritScore();
            }
        }
        else if(barXP.isSelected()){
            if(barNum == 11){
                barCount.setImage(bars[0]);
                barNum = 0;
                use.incrementBarScore();
            }
            else{
                barNum++;
                barCount.setImage(bars[barNum]);
                use.incrementBarScore();
            }

        }
    }
    @FXML
    public void goToBar() throws IOException{
        toolBarController.goBar();
    }

    @FXML
    public void update(){
        if(toolBarController.getCurUser().getUserType().equals(User.userPower.Specialist)){
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
        form1 = "";
        form2 = "";
        form3 = "";
        form4 = "";
        form5 = "";
        form6 = "";
        form7 = "";
        form8 = "";
        form9 = "";
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
        //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
        ArrayList<HashMap<String, ReturnedValue>>filtered_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:driver.select_all("stringified_ids_db.db", "form_data")){
            if(special){
                if (result.get("status").to_string().contains("specialist")){

                    filtered_results.add(result);
                }
            }
            else{
                if (result.get("status").to_string().contains("unread")){

                    filtered_results.add(result);
                }
            }
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

        HashMap<Integer, String>idMap = new HashMap<Integer, String>();
        idMap.put(1, form1);
        idMap.put(2, form2);
        idMap.put(3, form3);
        idMap.put(4, form4);
        idMap.put(5, form5);
        idMap.put(6, form6);
        idMap.put(7, form7);
        idMap.put(8, form8);
        idMap.put(9, form9);


        if (filtered_results.size() > 0){
            for (int i = 1; i < 10; i++){
                try{
                    HashMap<String, ReturnedValue>_temp = filtered_results.get(i-1);
                    //test.get(i).setText("Form "+_temp.get("formID").to_string().replace(".0", ""));
                    //test.get(i).setText(_temp.get("formID").to_string().replace(".0", ""));
                    test.get(i).setText(_temp.get("brandName").to_string() + ": " + _temp.get("fancifulName").to_string());
                    idMap.put(i, _temp.get("formID").to_string());
                }
                catch(Exception e){
                    //pass
                }
            }

            form1 = idMap.get(1);
            form2 = idMap.get(2);
            form3 = idMap.get(3);
            form4 = idMap.get(4);
            form5 = idMap.get(5);
            form6 = idMap.get(6);
            form7 = idMap.get(7);
            form8 = idMap.get(8);
            form9 = idMap.get(9);

            if(form1.equals("")){
                choose_button1.setDisable(true);
            }
            if(form2.equals("")){
                choose_button2.setDisable(true);
            }
            if(form3.equals("")){
                choose_button3.setDisable(true);
            }
            if(form4.equals("")){
                choose_button4.setDisable(true);
            }
            if(form5.equals("")){
                choose_button5.setDisable(true);
            }
            if(form6.equals("")){
                choose_button6.setDisable(true);
            }
            if(form7.equals("")){
                choose_button7.setDisable(true);
            }
            if(form8.equals("")){
                choose_button8.setDisable(true);
            }
            if(form9.equals("")){
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
        sendBack.setDisable(false);
        forwardButton.setDisable(false);

        //int formID = 0;
        if (choose.getSource() == choose_button1) {
            currentFormID = form1;

        } else if (choose.getSource() == choose_button2) {
            currentFormID = form2;

        } else if (choose.getSource() == choose_button3) {
            currentFormID = form3;

        }else if (choose.getSource() == choose_button4) {
            currentFormID = form4;

        }else if (choose.getSource() == choose_button5) {
            currentFormID = form5;

        }else if (choose.getSource() == choose_button6) {
            currentFormID = form6;

        }else if (choose.getSource() == choose_button7) {
            currentFormID = form7;

        }else if (choose.getSource() == choose_button8) {
            currentFormID = form8;

        }else if (choose.getSource() == choose_button9) {
            currentFormID = form9;

        }

        SQLDriver driver = new SQLDriver();

        //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
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


        formQualification_text.setText(result.get("qualifier").to_string());
        formula_text.setText(result.get("formula").to_string());
        grapeVarietal_text.setText(result.get("grapeVarietals").to_string());
        wineAppellation_text.setText(result.get("wineAppellation").to_string());
        winepH_text.setText(result.get("pHValue").to_string());
        vintage_text.setText(result.get("vintage").to_string());
        alcoholContent_text.setText(result.get("alcoholContent").to_string());
        phoneNumber_text.setText(result.get("phoneNumber").to_string());
        email_text.setText(result.get("email").to_string());
        brandedInfo_text.setText(result.get("extraInfo").to_string());


        formStatus_string = (result.get("status").to_string()); //I use two variables because I need the formStatus text as a string
        //formStatus_text.setText(formStatus_string);
        volume_text.setText(result.get("volume").to_string());

        zip_text.setText(result.get("zip").to_string());
        dateOfApplication_text.setText(result.get("dateOfApplication").to_string());
        applicantName_text.setText(result.get("name").to_string());
        String Name = (toolBarController.getCurUser().getFirstName() + " " + toolBarController.getCurUser().getLastName());
        approvingUser_text.setText(Name);



        formID_text.setText(result.get("formID").to_string());
    }

    @FXML
    private void approveHandler() throws IOException, Exception{
        SQLDriver driver = new SQLDriver();
        //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "stringified_ids_db.db", "formID", new DBValue<String>(currentFormID));
        formStatus_string = result.get("status").to_string().replace("unread", "");
        formStatus_string = formStatus_string.replace("specialist", "");
        testBottles();
        formStatus_string += "approved";

        driver.setApprovalStatus(currentFormID, formStatus_string);
        Date date = new Date();
        String theDate = date.toString();
        date.equals(date.getTime()+10000);
        String exDate = date.toString();
        driver.setApprovalDate(currentFormID, theDate);
        driver.setApprovingUser(currentFormID, this.approvingUser_text.getText());
        driver.setExpirationDate(currentFormID, exDate);
        if(special){
            driver.setQualifier(currentFormID,commentBox.getText());
        }



        clearFields();
        //pull_Forms();
        sendBack.setDisable(true);
        forwardButton.setDisable(true);
        accept_button.setDisable(true);
        reject_button.setDisable(true);

        // Calling the pull workforms
        convertToForms();

    }

    @FXML
    private void rejectHandler() throws IOException, Exception{
        if(special){
            if(commentBox.getText().equals("")){
                commentBox.setPromptText("This is a required field for specialist rejections");
                return;
            }
        }
        SQLDriver driver = new SQLDriver();
        driver.setQualifier(currentFormID,commentBox.getText());

        //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "stringified_ids_db.db", "formID", new DBValue<String>(currentFormID));
        formStatus_string = result.get("status").to_string().replace("unread", "");
        formStatus_string = formStatus_string.replace("specialist", "");
        formStatus_string += "reject";
        testBottles();
        driver.setApprovalStatus(currentFormID, formStatus_string);
        clearFields();

        //pull_Forms();
        sendBack.setDisable(true);
        forwardButton.setDisable(true);
        accept_button.setDisable(true);
        reject_button.setDisable(true);

        // Calling the pull workforms
        convertToForms();

    }
    @FXML
    public void sendBackHandler() throws IOException, Exception{
        SQLDriver driver = new SQLDriver();
        //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "stringified_ids_db.db", "formID", new DBValue<String>(currentFormID));
        formStatus_string = result.get("status").to_string().replace("unread", "");
        formStatus_string += "commented";
        driver.setQualifier(currentFormID,commentBox.getText());
        driver.setApprovalStatus(currentFormID,formStatus_string);
        clearFields();
        testBottles();
        //pull_Forms();
        sendBack.setDisable(true);
        forwardButton.setDisable(true);
        accept_button.setDisable(true);
        reject_button.setDisable(true);

        // Calling the pull workforms
        convertToForms();
    }
    @FXML
    public void forwardHandler() throws IOException, Exception{
        SQLDriver driver = new SQLDriver();
        //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "stringified_ids_db.db", "formID", new DBValue<String>(currentFormID));
        formStatus_string = result.get("status").to_string().replace("unread", "");
        formStatus_string += "specialist";
        driver.setApprovalStatus(currentFormID,formStatus_string);
        driver.setQualifier(currentFormID,commentBox.getText());
        clearFields();
        testBottles();
        //pull_Forms();
        sendBack.setDisable(true);
        forwardButton.setDisable(true);
        accept_button.setDisable(true);
        reject_button.setDisable(true);

        // Calling the pull workforms
        convertToForms();
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
        commentBox.clear();



    }

    /** start working on implementing tableview below
     *
     *
     *
     * */

    public ArrayList<HashMap<String, ReturnedValue>> pullFormsFromDB() throws Exception{
        Boolean specialist = toolBarController.getCurUser().getUserType().equals(User.userPower.Specialist);
        Boolean agent = toolBarController.getCurUser().getUserType().equals(User.userPower.TTBEmployee);
        Boolean admin = toolBarController.getCurUser().getUserType().equals(User.userPower.SuperAdmin);
        SQLDriver driver = new SQLDriver();
        //MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");
        ArrayList<HashMap<String, ReturnedValue>>filtered_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:driver.select_all("stringified_ids_db.db", "form_data")){
                if (result.get("status").to_string().contains("specialist") & (specialist || admin)){

                    filtered_results.add(result);
                }

            else{
                if (result.get("status").to_string().contains("unread") & (agent || admin)){

                    filtered_results.add(result);
                }
            }
        }

        return filtered_results;
    }

    public void convertToForms() throws Exception{

        ArrayList<HashMap<String, ReturnedValue>> resultsArrList = pullFormsFromDB();

        DisplayedResults.clear();
        currentPage = 1;

        sendBack.setDisable(true);
        forwardButton.setDisable(true);
        accept_button.setDisable(true);
        reject_button.setDisable(true);

        for(int i = 0; i < numResults; i++) {
            try {
                this.DisplayedResults.add(new FormWorkflow(resultsArrList.get(i + ((currentPage - 1) * numResults)), i + 1 + (currentPage - 1) * numResults));
            }
            catch(IndexOutOfBoundsException e){

            }
        }
    }

    public void table_selected(Event event){
        try{
            item = tableView.getSelectionModel().getSelectedItem();
            //System.out.println(tableView.getItems());
            showSelectedForm(item);
            // toolBarController.getInfoController().updateList(item);
            // toolBarController.goDetails(event);
        }
        catch (Exception e){
            System.out.println("Item not found in table_selected method");
        }
    }

    private void showSelectedForm(FormWorkflow selectedForm) {
        currentFormID = selectedForm.getForm_ID();

        accept_button.setDisable(false);
        reject_button.setDisable(false);
        sendBack.setDisable(false);
        forwardButton.setDisable(false);

        repID_text.setText(selectedForm.getRepID());
        plantRegistry_text.setText(selectedForm.getPlantRegistry());
        domestic_text.setText(selectedForm.getDomesticOrImported());
        serialNum_text.setText(selectedForm.getSerialNumber());

        beverage_text.setText(selectedForm.getBeverageType());
        brandName_text.setText(selectedForm.getBrandName());
        fancifulName_text.setText(selectedForm.getFancifulName());
        permitName_text.setText(selectedForm.getPermitname());
        //nameAddress_text.setText(result.get("nameAndAddress").to_string());
        //mailingAddress_text.setText(result.get("mailingAddress").to_string());

        streetAdress_text.setText(selectedForm.getStreetAddress());
        state_text.setText(selectedForm.getState());
        city_text.setText(selectedForm.getCity());


        formQualification_text.setText(selectedForm.getQualifier());
        formula_text.setText(selectedForm.getFormula());
        grapeVarietal_text.setText(selectedForm.getGrapeVarietals());
        wineAppellation_text.setText(selectedForm.getWineAppellation());
        winepH_text.setText(selectedForm.getpHValue());
        vintage_text.setText(selectedForm.getVintage());
        alcoholContent_text.setText(selectedForm.getAlcoholContent());
        phoneNumber_text.setText(selectedForm.getPhoneNumber());
        email_text.setText(selectedForm.getEmail());
        brandedInfo_text.setText(selectedForm.getExtraInfo());


        //formStatus_string = (selectedForm.getStatus()); //I use two variables because I need the formStatus text as a string
//
        //formStatus_text.setText(formStatus_string); //this is what we are testing

        volume_text.setText(selectedForm.getVolume());

        zip_text.setText(selectedForm.getZip());
        dateOfApplication_text.setText(selectedForm.getDateOfApplication());
        applicantName_text.setText(selectedForm.getName());

        //System.out.println("thing 1" + oneBeverage.getSummary().get(6));
        //System.out.println("thing 2 " + oneBeverage.getSummary().get(0));

    }



    public void setTable(){
        //get results
        //System.out.println("Updated");
        //convertToForms();
        //update columns on table view
        //this.Domestic.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("domesticOrImported"));
        this.BrandName.setCellValueFactory(new PropertyValueFactory<FormWorkflow, String>("brandName"));
        this.FancifulName.setCellValueFactory(new PropertyValueFactory<FormWorkflow, String>("fancifulName"));
        this.ResultNumber.setCellValueFactory(new PropertyValueFactory<FormWorkflow, Integer>("index"));
        //this.tableView.setItems(DisplayedResults);
        this.tableView.itemsProperty().bind(dispList);

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Start the tableview part of the app
        setTable();
        try {
            convertToForms();
        }catch(Exception ex){
            System.out.println("Did not work!!!!");
        }







        wines = new Image[12];

        InputStream input = null;
        for(int b = 1;b < 13;b++){
            String inputString = "../Assets/gameAssets/wine";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                wines[b - 1] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }

        beers = new Image[12];
        input = null;
        for(int b = 0;b < 12;b++){
            String inputString = "../Assets/gameAssets/beer";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                beers[b] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }
        spirits = new Image[12];
        input = null;
        for(int b = 1;b < 13;b++){
            String inputString = "../Assets/gameAssets/spirit";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                spirits[b-1] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }

        bars = new Image[12];
        input = null;
        for(int b = 0;b < 12;b++){
            String inputString = "../Assets/gameAssets/bar";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                bars[b] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
        User use = User.getUser("a","a",User.userPower.Standard,"a","a","a","a","a","a","a","a","a",1,1,1,1);
        wineCount.setImage(wines[use.getWineScore()%12]);
        beerCount.setImage(beers[use.getBeerScore()%12]);
        spiritsCount.setImage(spirits[use.getSpiritScore()%12]);
        barCount.setImage(bars[use.getBarScore()%12]);
        wineNum = use.getWineScore()%12;
        beerNum = use.getBeerScore()%12;
        spiritsNum = use.getSpiritScore()%12;
        barNum = use.getBarScore()%12;
        wineCount.setImage(wines[wineNum]);
        beerCount.setImage(beers[beerNum]);
        spiritsCount.setImage(spirits[spiritsNum]);
        barCount.setImage(bars[barNum]);

        if(toolBarController.getCurUser().getUserType().equals(User.userPower.Specialist)){
                specialText.setOpacity(1);
            commentBox.setPromptText("Add any comments as to why this particular form was rejected, accepted, or comments for corrections");
            special = true;
        }
        else{
            specialText.setOpacity(0);
            special = false;
        }



    }
}
