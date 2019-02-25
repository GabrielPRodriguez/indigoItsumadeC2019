package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import edu.wpi.cs3733c19.teamI.Algorithms.SQLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.UserSearch;
import edu.wpi.cs3733c19.teamI.Algorithms.fuzzyContext;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.DBValue;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.DataTransfer;
import edu.wpi.cs3733c19.teamI.Entities.User;
import edu.wpi.cs3733c19.teamI.Entities.Sub_User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

    public class UserResultsController implements Initializable {
        private ToolBarController toolBarController = ToolBarController.getInstance();
        private ArrayList<HashMap<String, ReturnedValue>> resultsList;
        private String testString = "original";
        private int batches = 0; //variable to track number batches

        private final ObservableList<Sub_User> DisplayedResults = FXCollections.observableArrayList();
        ObjectProperty<ObservableList<Sub_User>> dispList = new SimpleObjectProperty<>(DisplayedResults);


        //Table Stuff

        @FXML
        TableView<Sub_User> tableView;

        @FXML
        JFXButton CSV;


        @FXML
        TableColumn FirstName;

        @FXML
        TableColumn ResultNumber;

        @FXML
        TableColumn Email;

        @FXML
        TableColumn Role;

        @FXML
        TableColumn LastName;

        @FXML
        JFXButton fiveResultsButton;

        @FXML
        JFXButton tenResultsButton;

        @FXML
        JFXButton fiftyResultsButton;

        @FXML
        JFXButton hundredResultsButton;

        @FXML
        JFXButton lowerResults;

        @FXML
        JFXButton higherResults;

        @FXML
        JFXButton firstPage;

        @FXML
        JFXButton secondPage;

        @FXML
        JFXButton thirdPage;

        @FXML
        TextField searchTextField;
        private fuzzyContext searchAlgorithmSelection = new fuzzyContext();

        @FXML
        JFXButton fourthPage;
        @FXML
        JFXTextField UserEmail;
        @FXML
        JFXTextField Password;
        @FXML
        JFXTextField firstName;
        @FXML
        JFXTextField lastName;
        @FXML
        JFXTextField delim;
        @FXML
        JFXTextField address;
        @FXML
        JFXTextField city;
        @FXML
        JFXTextField zip;
        @FXML
        JFXTextField state;
        @FXML
        JFXTextField phone;

        @FXML
        JFXComboBox delimDrop;

        Sub_User item;
        DataTransfer data;


        @FXML
        private void goHome() throws IOException {
            toolBarController.goHome();
        }

        @FXML
        Label export_message;

        private int numResults = 10;
        private int currentPage = 1;

        @FXML
        public void editAccount(){

        }

        @FXML
        private void perPageAction(ActionEvent actionEvent){
            if(actionEvent.getSource() == fiveResultsButton){
                this.numResults = 5;
                convertToForms(1);

            }
            if(actionEvent.getSource() == tenResultsButton){
                this.numResults = 10;
                convertToForms(1);

            }
            if(actionEvent.getSource() == fiftyResultsButton){
                this.numResults = 50;
                convertToForms(1);

            }
            if(actionEvent.getSource() == hundredResultsButton){
                this.numResults = 100;
                convertToForms(1);

            }

        }


        @FXML
        private void pageNumAction(ActionEvent actionEvent){
            if(actionEvent.getSource() == firstPage){
                currentPage = Integer.parseInt(firstPage.getText());

            }
            if(actionEvent.getSource() == secondPage){
                currentPage = Integer.parseInt(secondPage.getText());

            }
            if(actionEvent.getSource() == thirdPage){
                currentPage = Integer.parseInt(thirdPage.getText());

            }
            if(actionEvent.getSource() == fourthPage){
                currentPage = Integer.parseInt(fourthPage.getText());

            }
            convertToForms(2);
        }

        @FXML
        private void setHigherPages(ActionEvent event){
            int firstVal = Integer.parseInt(firstPage.getText())+4;
            firstPage.setText(String.valueOf(firstVal));

            int secondVal = Integer.parseInt(secondPage.getText())+4;
            secondPage.setText(String.valueOf(secondVal));

            int thirdVal = Integer.parseInt(thirdPage.getText())+4;
            thirdPage.setText(String.valueOf(thirdVal));

            int fourthVal = Integer.parseInt(fourthPage.getText())+4;
            fourthPage.setText(String.valueOf(fourthVal));
        }

        @FXML
        private void setLowerPages(ActionEvent event){

            int firstVal = Integer.parseInt(firstPage.getText())-4;
            if(firstVal >= 1){

                firstPage.setText(String.valueOf(firstVal));

                int secondVal = Integer.parseInt(secondPage.getText())-4;
                secondPage.setText(String.valueOf(secondVal));

                int thirdVal = Integer.parseInt(thirdPage.getText())-4;
                thirdPage.setText(String.valueOf(thirdVal));

                int fourthVal = Integer.parseInt(fourthPage.getText())-4;
                fourthPage.setText(String.valueOf(fourthVal));
            }

        }


        public void setResultsList(ArrayList<HashMap<String, ReturnedValue>> results){
            this.resultsList = results;
        }
        public void setTestString(String newString){
            this.testString=newString;
        }

        public void convertToForms(int newSearch){
            if(newSearch == 0){
                DisplayedResults.clear();
                currentPage = 1;
            }
            if(newSearch == 1){
                DisplayedResults.clear();
                if(currentPage > 1) currentPage--;
            }
            if(newSearch == 2){
                DisplayedResults.clear();
            }
            for(int i = 0; i<numResults; i++)
            {
                try {
                    this.DisplayedResults.add(new Sub_User(toolBarController.getUserResultsMap().get(i + ((currentPage - 1) * numResults)), i + 1 + (currentPage - 1) * numResults));
                }
                catch(IndexOutOfBoundsException e){

                }
            }
        }

        public void setAllDisplayedReseults(){
            DisplayedResults.clear();
            int index = 1;
            try{
                for (HashMap<String, ReturnedValue> i : toolBarController.getUserResultsMap()) {

                    this.DisplayedResults.add(new Sub_User(i, index));
                    index++;
                }
            }catch (IndexOutOfBoundsException e){

            }
        }

        public void setTable(){
            //get results
            //System.out.println("Updated");
            //convertToForms();
            //update columns on table view
            this.Role.setCellValueFactory(new PropertyValueFactory<Sub_User, String>("userRole"));
            this.FirstName.setCellValueFactory(new PropertyValueFactory<Sub_User, String>("firstName"));
            this.LastName.setCellValueFactory(new PropertyValueFactory<Sub_User, String>("lastName"));
            this.Email.setCellValueFactory(new PropertyValueFactory<Sub_User, String>("userEmail"));
            this.ResultNumber.setCellValueFactory(new PropertyValueFactory<Sub_User, Integer>("index"));
            //this.tableView.setItems(DisplayedResults);
            this.tableView.itemsProperty().bind(dispList);

        }


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            setTable();
            convertToForms(0);
            toolBarController = ToolBarController.getInstance();

            delimDrop.getItems().addAll(
                    "Comma",
                    "Colon",
                    "Semicolon",
                    "Pipe");

        }

        public void table_selected(Event event){
            try{
                item = tableView.getSelectionModel().getSelectedItem();
                //System.out.println(tableView.getItems());
                showSelectedBeverage(item);
                // toolBarController.getInfoController().updateList(item);
                // toolBarController.goDetails(event);
            }
            catch (Exception e){
                System.out.println("Item not found in table_selected method");
            }
        }

        public void goSearch(ActionEvent actionEvent) throws Exception {
            if (searchTextField.getText() == null || searchTextField.getText().trim().isEmpty()){
                // TODO Insert here anything you want the app to do when user click search and box is empty

            }
            else {
                searchAlgorithmSelection.setContext(new UserSearch());
                toolBarController.setResultsMapUser(searchAlgorithmSelection.run(searchTextField.getText().trim()));
                toolBarController.goUserSearch();

                // TODO:this will return what fuzzys return
                // TODO link the return of the fuzzy alghoriths to a listView on the next page (maybe just have field of hashmap that gets passed to the next scene)

            }

        }


        private void showSelectedBeverage(Sub_User oneBeverage) {
            firstName.setText(oneBeverage.getSummary().get(1));
            lastName.setText(oneBeverage.getSummary().get(2));
            UserEmail.setText(oneBeverage.getSummary().get(3));


            phone.setText(oneBeverage.getSummary().get(6));
            address.setText(oneBeverage.getSummary().get(7));
            city.setText(oneBeverage.getSummary().get(8));
            state.setText(oneBeverage.getSummary().get(9));
            delim.setText(oneBeverage.getSummary().get(10));
            Password.setText(oneBeverage.getSummary().get(11));

        }


        public String dropdownSelected(){
            String retString;
            try{
                if(delimDrop.getValue().toString().equals("Comma")){
                    return ",";
                }else if(delimDrop.getValue().toString().equals("Colon")){
                    return ":";
                }else if(delimDrop.getValue().toString().equals("Semicolon")){
                    return ";";
                }else if(delimDrop.getValue().toString().equals("Pipe")){
                    return "|";
                }

            }catch (Exception ex){
                return "";
            }
            return "";
        }

        public void writeExcel() throws Exception {
            export_message.setText("");
            Writer writer = null;
            String fileTimestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
            String delimiter = ",";
            setAllDisplayedReseults();

            if(!dropdownSelected().isEmpty()){
                delimiter = dropdownSelected();
            }else if (toolBarController.getCurUser() != null ){
                delimiter = String.valueOf(toolBarController.getCurUser().getDelim());
            }

            try {
                File file = new File(System.getProperty("user.home")+ "/Downloads/DATA" + fileTimestamp + ".CSV");
                writer = new BufferedWriter(new FileWriter(file)); //CSV library function
                // grab text versions of all displayed results
                for (int i = 0; i < DisplayedResults.size(); i++) {

                    String text = DisplayedResults.get(i).returnAll(delimiter);
                    writer.write(text);
                }

                export_message.setTextFill(Color.web("#4BB543"));
                export_message.setText("CSV Exported!");
            } catch (Exception ex) {
                //System.out.println("Error exporting CSV");
                ex.printStackTrace();
                export_message.setTextFill(Color.web("#FF0000"));
                export_message.setText("Error exporting CSV");
            }
            finally {//when done, finish CSV creation

                writer.flush();
                writer.close();
            }

        }
    }

