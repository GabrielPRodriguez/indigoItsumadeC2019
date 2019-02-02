package edu.wpi.cs3733c19.teamI.Controllers;

import edu.wpi.cs3733c19.teamI.Entities.DataField;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class SearchController {
    private SearchResults Results = new SearchResults();

    @FXML
    Button performSearch;

    @FXML
    TextField brandNameField;

    @FXML
    protected void fillSearchParam()
    {
        LinkedList<DataField> userParam = new LinkedList<>();
        DataField brand = new DataField(brandNameField.getText(),"brandName");
        userParam.add(brand);
//
        Results.gatherSearchParam(userParam);

        System.out.println(Results.getParameters().getFirst());



    }


    private Scene firstScene;

    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }

    public void openFirstScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(firstScene);
    }



}
