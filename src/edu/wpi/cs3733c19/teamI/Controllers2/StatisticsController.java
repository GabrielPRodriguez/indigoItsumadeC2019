package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class StatisticsController {

    @FXML
    BarChart statsGraph;

    @FXML
    JFXComboBox xCombo;

    @FXML
    JFXComboBox yCombo;

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();

    BarChart chart = new BarChart(xAxis, yAxis);
}
