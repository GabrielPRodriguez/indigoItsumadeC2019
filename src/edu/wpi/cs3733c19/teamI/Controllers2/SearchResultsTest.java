package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchResultsTest implements Initializable {

    @FXML
    private JFXListView listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<50;i++)
        {
            Label label = new Label("DESCRIPTION" + i);

            label.setGraphic(new ImageView("edu/wpi/cs3733c19/teamI/Assets/placeholder_icon.png"));

            listView.getItems().add(label);
        }
    }
}
