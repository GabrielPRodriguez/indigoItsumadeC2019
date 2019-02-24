package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import edu.wpi.cs3733c19.teamI.Entities.Form;
import edu.wpi.cs3733c19.teamI.Entities.PDFManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormSubSelectorController implements Initializable {
    ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    JFXButton singlePageButton;

    @FXML
    JFXButton multiPageButton;

    @FXML
    private void goNormalFormSub() throws IOException {
        toolBarController.goNormalFormSub();
    }

    @FXML
    private void goMultiFormSub() throws IOException{
        toolBarController.goMultiFormSub();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: Change to fileString
        parsePDF("/home/fareyaikram/Desktop/testFiles/test1.pdf");

    }
    public Form parsePDF(String fileString){
        Form myForm= new Form();
        PDFManager manager= new PDFManager();
        manager.readPDF(fileString);
        return myForm;

    }
}
