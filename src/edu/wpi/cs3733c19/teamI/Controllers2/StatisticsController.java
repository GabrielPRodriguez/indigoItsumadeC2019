package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class StatisticsController {

    @FXML
    JFXComboBox<String> xCombo;

    @FXML
    JFXComboBox<String> yCombo;

    @FXML
    private BarChart<?, ?> statsGraph;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    String x = "";
    int y = 0;

    int yBeer = 1000;
    int yWine = 1000;
    int ySpirits = 1000;

    public void initialize() {
        //Combobox stuff
        xCombo.setPromptText("Select Category");
        yCombo.setPromptText("Select Number");

        //Category 1
        xCombo.getItems().add("Beverage Type");
        xCombo.getItems().add("Category 2");


        yCombo.getItems().add("Number 1");
        yCombo.getItems().add("Number 2");

        xCombo.setOnAction(e -> xComboChanged());
        yCombo.setOnAction(e -> yComboChanged());
    }

    public void xComboChanged(){


        if(xCombo.getValue().equals("Beverage Type")){

            XYChart.Series xdata = new XYChart.Series<>();
            xdata.getData().add(new XYChart.Data("Beer", yBeer));
            xdata.getData().add(new XYChart.Data("Wine", yWine));
            xdata.getData().add(new XYChart.Data("Spirits", ySpirits));

            statsGraph.getData().clear();
            xAxis.setLabel("Beverage Type");
            statsGraph.getData().addAll(xdata);
        }

        else if(xCombo.getValue().equals("Category 2")){
            statsGraph.getData().clear();
            xAxis.setLabel("Category 2");
            x = "Dillon";
            statsGraph.getData().addAll();
        }

    }

    public void yComboChanged(){

        XYChart.Series ydata = new XYChart.Series<>();
        ydata.getData().add(new XYChart.Data(x, 0));

        if(yCombo.getValue().equals("Number 1")){
            statsGraph.getData().clear();
            yAxis.setLabel("Number 1");
            statsGraph.getData().addAll(ydata);

        }
        else if(yCombo.getValue().equals("Number 2")){
            statsGraph.getData().clear();
            yAxis.setLabel("Number 2");
            statsGraph.getData().addAll(ydata);
        }

    }

}
