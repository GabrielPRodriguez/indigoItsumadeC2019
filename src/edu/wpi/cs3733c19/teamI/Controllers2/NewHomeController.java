package edu.wpi.cs3733c19.teamI.Controllers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewHomeController {


    private ToolBarController toolBarController;
    private Scene AdvancedSearchScene;
    private Scene SearchResultScene;

    public void setAdvanceSearchScene(Scene advancedSearchScene) {
        this.AdvancedSearchScene = advancedSearchScene;
    }
    public void setSearchResultScene(Scene searchResultScene){this.SearchResultScene =searchResultScene;}

    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
    }

    @FXML
    public void goHome(ActionEvent actionEvent){
        toolBarController.goHome(actionEvent);

    }

    @FXML
    public void goSubmit(ActionEvent actionEvent){
        toolBarController.goSubmit(actionEvent);
    }

    @FXML
    public void goLogin(ActionEvent actionEvent){
        toolBarController.goLogin(actionEvent);
    }

    @FXML
    public void goAdvancedSearch(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(AdvancedSearchScene);
        primaryStage.setMaximized(true);

    }

    @FXML
    public void goSearch(ActionEvent actionEvent){
        //fuzzy search code here
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(SearchResultScene);
        primaryStage.setMaximized(true);
    }
}
