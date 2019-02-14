package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    private ToolBarController toolBarController;
    private Scene ResultsScene;

    @FXML
    JFXButton tb_loginButton;

    public void setToolBarController(ToolBarController toolBarController){
        this.toolBarController = toolBarController;
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

    public void setTb_logout() {
        tb_loginButton.setText("logout");
    }

    @FXML
    Circle yossef;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        Image image = new Image(new File("Assets/blue_pill_button.png").toURI().toString());
//        ImagePattern imagePattern = new ImagePattern(image);
//
//        yossef.setFill(imagePattern);
    }

    @FXML
    public void goExit(ActionEvent actionEvent){toolBarController.goExit(actionEvent);}
}
