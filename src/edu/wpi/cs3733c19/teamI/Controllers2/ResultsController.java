package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.sub_Form;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

    private ArrayList<HashMap<String, ReturnedValue>> resultsList;
    private String testString = "original";
    private int batches = 0; //variable to track number batches


    private final ObservableList<sub_Form> DisplayedResults = FXCollections.observableArrayList();
    ObjectProperty<ObservableList<sub_Form>> dispList = new SimpleObjectProperty<>(DisplayedResults);


    //Table Stuff

    @FXML
    TableView<sub_Form> tableView;

    @FXML
    JFXButton CSV;


    @FXML
    TableColumn BrandName;

    @FXML
    TableColumn ResultNumber;

    @FXML
    TableColumn AlchoholPercentage;

    @FXML
    TableColumn Domestic;

    @FXML
    TableColumn Type;

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
    JFXButton fourthPage;

    private int numResults = 10;
    private int currentPage = 1;

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


    public void  setResultsList(ArrayList<HashMap<String, ReturnedValue>> results){
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

        }
    }

    public void setTable(){
        //get results
        System.out.println("Updated");
        //convertToForms();
        //update columns on table view
        this.Domestic.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("domesticOrImported"));
        this.BrandName.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("brandName"));
        this.Type.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("beverageType"));
        this.AlchoholPercentage.setCellValueFactory(new PropertyValueFactory<sub_Form, String>("alcoholContent"));
        this.ResultNumber.setCellValueFactory(new PropertyValueFactory<sub_Form, Integer>("index"));
        //this.tableView.setItems(DisplayedResults);
        this.tableView.itemsProperty().bind(dispList);
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTable();
    }



    //create CSV function
    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
            File file = new File(System.getProperty("user.home")+ "/Downloads/DATA.CSV");
            writer = new BufferedWriter(new FileWriter(file)); //CSV library function
            // grab text versions of all displayed results
            for (int i = 0; i < DisplayedResults.size(); i++) {

                String text = DisplayedResults.get(i).returnAll();
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {//when done, finish CSV creation

            writer.flush();
            writer.close();
        }
    }

}
