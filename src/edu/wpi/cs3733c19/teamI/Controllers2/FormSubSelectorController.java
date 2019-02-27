package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import edu.wpi.cs3733c19.teamI.Tesseract.TesseractReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;


import java.io.File;
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

    @FXML
    private void goPDF() throws IOException{
        FileChooser chooseFile = new FileChooser();
        File selectedFile = chooseFile.showOpenDialog(null);
        chooseFile.setTitle("Please Choose an Image to Upload");

        if(selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            TesseractReader pdfConverter = new TesseractReader();
            pdfConverter.convert(filePath);
            toolBarController.goNormalFormSub();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
