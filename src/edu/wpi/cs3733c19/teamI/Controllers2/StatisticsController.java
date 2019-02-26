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

    int y1 = 0;
    int y2 = 0;
    int y3 = 0;

    int y4 = 0;
    int y5 = 0;

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
                    System.out.println(numResults);
                    System.out.println(result.all_names.get(3));
                    System.out.println(result.frequences.get("Wine"));
                    System.out.println(result.frequences.get("Distilled Spirits"));
                    System.out.println(result.frequences.get("Malt Beverage"));
                    yVal1 = result.frequences.get("Wine");
                    yVal2 = result.frequences.get("Distilled Spirits");
                    yVal3 = result.frequences.get("Malt Beverage");
                } catch (Exception e) {
                    System.out.println("Exception");

                }
            }



            //int yVal1 = y1;
            //int yVal2 = y2;
           //int yVal3 = y3;





            XYChart.Series xType1 = new XYChart.Series<>();
            xType1.setName("Beer");
            xType1.getData().add(new XYChart.Data("Type", yVal1));

            XYChart.Series xType2 = new XYChart.Series<>();
            xType2.setName("Wine");
            xType2.getData().add(new XYChart.Data("Type", yVal2));

            XYChart.Series xType3 = new XYChart.Series<>();
            xType3.setName("Spirits");
            xType3.getData().add(new XYChart.Data("Type", yVal3));

            xAxis.setLabel("Beverage Type");
            statsGraph.getData().addAll(xType1, xType2, xType3);
        }

        else if(xCombo.getValue().equals("Domestic vs Imported")){
            statsGraph.getData().clear();

            int yVal4 = y4;
            int yVal5 = y5;

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

            y1 = 10;
            y2 = 10;
            y3 = 10;

            y4 = 10;
            y5 = 10;

            numResults = 50;

            yAxis.setLabel("Total");
            xComboChanged();

        }
        else if(yCombo.getValue().equals("Most Recent")){
            statsGraph.getData().clear();

            y1 = 20;
            y2 = 10;
            y3 = 30;

            y4 = 40;
            y5 = 50;

            numResults = 100;

            yAxis.setLabel("Most Recent");
            xComboChanged();
        }

    }

}
