package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.sub_Form;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.stage.PopupWindow.AnchorLocation;

import javax.swing.text.View;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    private ToolBarController toolBarController;
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
    @FXML
    Text regText;
    @FXML
    Text typeText;
    @FXML
    Text fanText;
    @FXML
    Text percentText;
    @FXML
    Text originText;
    @FXML
    Text phText;
    @FXML
    Text vinText;
    @FXML
    Text appText;
    @FXML
    Text varText;
    @FXML
    Text nameText;
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
            try {
                this.DisplayedResults.add(new sub_Form(toolBarController.getResultsMap().get(i + ((currentPage - 1) * numResults)), i + 1 + (currentPage - 1) * numResults));
            }
            catch(IndexOutOfBoundsException e){

            }
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



    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTable();
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
    public void goSearch(ActionEvent actionEvent){toolBarController.goSearch(actionEvent);}

    @FXML
    public void goExit(ActionEvent actionEvent){toolBarController.goExit(actionEvent);}

    public void table_selected(Event event){
        sub_Form item = tableView.getSelectionModel().getSelectedItem();

        showSelectedBeverage(item);
        // toolBarController.getInfoController().updateList(item);
        // toolBarController.goDetails(event);
    }


    private void showSelectedBeverage(sub_Form oneBeverage) {
        /**
         Hey Grant here is where you are going to assign all the FXID and information
         I am passing you a sub_Form called "oneBeverage" that you can get all the
         information you need of the beverage that is selected

         Also I messed around with the Scene Builder application and you should not have
         enough space to present your data.

         If you look at this link https://drizly.com/smirnoff-no-21-vodka/p4683
         and scroll down to the Product Detail its a good exmple of how you can fir
         a lot of information close together
        */

        regText.setText(oneBeverage.getSummary().get(1));
        typeText.setText(oneBeverage.getSummary().get(4));
        fanText.setText(oneBeverage.getSummary().get(6));
        percentText.setText(oneBeverage.getSummary().get(11) + "%");
        originText.setText(oneBeverage.getSummary().get(2));
        phText.setText(oneBeverage.getSummary().get(9));
        vinText.setText(oneBeverage.getSummary().get(7));
        appText.setText(oneBeverage.getSummary().get(10));
        varText.setText(oneBeverage.getSummary().get(8));
        nameText.setText(oneBeverage.getSummary().get(5));
    }

    //create CSV function
    public void writeExcel() throws Exception {
        Writer writer = null;
        String fileTimestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        try {
            File file = new File(System.getProperty("user.home")+ "/Downloads/DATA" + fileTimestamp + ".CSV");
            writer = new BufferedWriter(new FileWriter(file)); //CSV library function
            // grab text versions of all displayed results
            for (int i = 0; i < DisplayedResults.size(); i++) {

                String text = DisplayedResults.get(i).returnAll();
                writer.write(text);
            }
        } catch (Exception ex) {
            System.out.println("Error exporting CSV");
            ex.printStackTrace();
        }
        finally {//when done, finish CSV creation

            writer.flush();
            writer.close();
        }
    }

}
