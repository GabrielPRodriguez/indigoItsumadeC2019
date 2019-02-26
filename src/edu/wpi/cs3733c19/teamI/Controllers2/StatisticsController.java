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


    int numResults = 0;

    SQLDriver driver = new SQLDriver();

    public void initialize() {
        //Combobox stuff
        xCombo.setPromptText("Select Category");
        yCombo.setPromptText("Select Number");

        //Category 1
        xCombo.getItems().add("Beverage Type");
        xCombo.getItems().add("Domestic vs Imported");


        yCombo.getItems().add("Total");
        yCombo.getItems().add("Most Recent");

        xCombo.setOnAction(e -> xComboChanged());
        yCombo.setOnAction(e -> yComboChanged());
    }

    public void xComboChanged() {
        statsGraph.getData().clear();


        if(xCombo.getValue().equals("Beverage Type")){
            statsGraph.getData().clear();

            int yVal1 = 0;
            int yVal2 = 0;
            int yVal3 = 0;


            if(numResults > 0) {
                try {
                    FrequencyResult result = driver.get_top_vals(numResults, "beverageType");
                    yVal1 = result.frequences.get("Wine");
                    yVal2 = result.frequences.get("Distilled Spirits");
                    yVal3 = result.frequences.get("Malt Beverage");
                } catch (Exception e) {
                    System.out.println("Exception");
                }
            }

            XYChart.Series xType1 = new XYChart.Series<>();
            xType1.setName("Wine");
            xType1.getData().add(new XYChart.Data("Type", yVal1));

            XYChart.Series xType2 = new XYChart.Series<>();
            xType2.setName("Spirits");
            xType2.getData().add(new XYChart.Data("Type", yVal2));

            XYChart.Series xType3 = new XYChart.Series<>();
            xType3.setName("Beer");
            xType3.getData().add(new XYChart.Data("Type", yVal3));

            xAxis.setLabel("Beverage Type");
            statsGraph.getData().addAll(xType1, xType2, xType3);
        }

        else if(xCombo.getValue().equals("Domestic vs Imported")){
            statsGraph.getData().clear();

            int yVal4 = 0;
            int yVal5 = 0;


            if(numResults > 0) {
                try {
                    FrequencyResult result = driver.get_top_vals(numResults, "domesticOrImported");
                    yVal4 = result.frequences.get("Domestic");
                    yVal5 = result.frequences.get("Import");

                } catch (Exception e) {
                    System.out.println("Exception");
                }
            }




            XYChart.Series xWhere1 = new XYChart.Series<>();
            xWhere1.setName("Domestic");
            xWhere1.getData().add(new XYChart.Data("Type", yVal4));

            XYChart.Series xWhere2 = new XYChart.Series<>();
            xWhere2.setName("Imported");
            xWhere2.getData().add(new XYChart.Data("Type", yVal5));

            xAxis.setLabel("Domestic vs Imported");
            statsGraph.getData().addAll(xWhere1, xWhere2);
        }

    }

    public void yComboChanged(){
        statsGraph.getData().clear();
        if(yCombo.getValue().equals("Total")){
            statsGraph.getData().clear();

            numResults = 73173;

            yAxis.setLabel("Total");
            xComboChanged();

        }
        else if(yCombo.getValue().equals("Most Recent")){
            statsGraph.getData().clear();


            numResults = 100;

            yAxis.setLabel("Most Recent");
            xComboChanged();
        }

    }

}
