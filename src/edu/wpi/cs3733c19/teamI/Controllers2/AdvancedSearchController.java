package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class AdvancedSearchController implements Initializable {

    @FXML
    JFXSpinner spin;

    @FXML
    JFXComboBox fieldSelector;

    @FXML
    JFXButton search;

    public void spinnerVisible()
    {
        spin.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spin.setMaxSize(0,0);

        Label formID = new Label("Form ID Number");
        Label repID = new Label("COLA Representative ID Number");
        Label plantRegistry = new Label("Plant Registry Number");
        Label domestic = new Label("Domestic");
        Label imported = new Label("Imported");
        Label serialNumber = new Label("Serial Number");
        Label brandName = new Label("Brand Name");
        Label beverageType = new Label("Type of Beverage");
        Label fancifulName = new Label("Name other than Brand Name");
        Label permitName = new Label("Permit Name");
        Label streetAddress = new Label("Street Address of Applicant");
        Label city = new Label("City of Applicant");
        Label state = new Label("State of Applicant");
        Label zip = new Label("Zip Code of Applicant");
        Label extraInfo = new Label("Any Information Embossed Onto the Packaging");
        Label dateOfApplicaiton = new Label("Application Date");
        Label formula = new Label("Formula of Alcohol");
        Label grapeVarietals = new Label("Grape Varietal(s)");
        Label vintage = new Label("Vintage Year");
        Label wineAppellation = new Label("Wine Appellation");
        Label email = new Label("Email Address of Applicant");
        Label phoneNumber = new Label("Phone Number of Applicant");
        Label pHValue = new Label("PH of Wine");
        Label alcoholContent = new Label("Alcohol Percentage");
        Label status = new Label("Status of Application");
        Label approvingUser = new Label("Approving User of TTB");
        Label approvalDate = new Label("Date of Application Approval");
        Label expirationDate = new Label("Expiration Date of Application");
        Label issuedDate = new Label("Date License was Issued");
        Label volume = new Label("Volume of Alcohol");
        Label appType = new Label("Type of Application");
        Label surrenderDate = new Label("Date of Application Surrender");
        Label qualification = new Label("Qualification of Application");

        ObservableList<Label> labelList =
                FXCollections.observableArrayList(
                        formID,
                        repID,
                        plantRegistry,
                        domestic,
                        imported,
                        serialNumber,
                        brandName,
                        beverageType,
                        fancifulName,
                        permitName,
                        streetAddress,
                        city,
                        state,
                        zip,
                        extraInfo,
                        dateOfApplicaiton,
                        formula,
                        grapeVarietals,
                        vintage,
                        wineAppellation,
                        email,
                        phoneNumber,
                        pHValue,
                        alcoholContent,
                        status,
                        approvingUser,
                        approvalDate,
                        expirationDate,
                        issuedDate,
                        volume,
                        appType,
                        surrenderDate,
                        qualification

                );

        fieldSelector.setItems(labelList);
    }

    private ToolBarController toolBarController;


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
    public void goWorkflow(ActionEvent actionEvent){toolBarController.goWorkflow(actionEvent);}

    @FXML
    public void goAbout(ActionEvent actionEvent){toolBarController.goAbout(actionEvent);}

    @FXML
    public void goSearch(ActionEvent actionEvent){toolBarController.goSearch(actionEvent);}

    @FXML
    public void goExit(ActionEvent actionEvent){toolBarController.goExit(actionEvent);}


}
