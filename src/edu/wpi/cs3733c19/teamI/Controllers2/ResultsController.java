package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    private ToolBarController toolBarController;


   // @FXML
    //JFXTreeTableView<> listView;

  //  @FXML
   // JFXTreeTableView

    @FXML
    TableView<JFXButton> tableView;

    public void setUp() {
      //  for (int i = 0; i < 4; i++) listView.getItems().add(new Label("Item " + i + " DRANK " + i));
      //  listView.getStyleClass().add("mylistview");
        for (int i = 0; i < 4; i++) tableView.getItems().add(new JFXButton("Item " + i + " DRANK " + i));

    }



    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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






}
