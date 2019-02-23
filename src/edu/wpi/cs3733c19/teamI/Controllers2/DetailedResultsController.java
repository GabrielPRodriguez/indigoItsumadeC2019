package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Entities.sub_Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class DetailedResultsController {
    private ToolBarController toolBarController = ToolBarController.getInstance();

    @FXML
    ListView<String> info;

    @FXML
    Label ProductName;

    @FXML
    JFXButton back_button;

    @FXML
    ImageView image;



    ObservableList<String> items = FXCollections.observableArrayList();

    public void updateList(sub_Form form){
        info.setItems(form.getSummary());
        ProductName.setText(form.getSummary().get(5));

    }

    @FXML
    private void goSearch() throws IOException {
        toolBarController.goSearch();
    }

    @FXML
    private void goResults() throws IOException{
        toolBarController.goSearch();
    }

}
