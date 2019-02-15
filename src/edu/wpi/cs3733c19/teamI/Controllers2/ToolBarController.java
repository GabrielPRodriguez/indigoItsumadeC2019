package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class ToolBarController implements Initializable{



    @FXML
    JFXButton tb_homeButton;

    @FXML
    JFXButton tb_aboutButton;

    @FXML
    JFXButton tb_submitButton;

    @FXML
    JFXButton tb_workflowButton;

    @FXML
    JFXButton tb_loginButton;

    @FXML
    JFXButton tb_exitButton;



    public void goHome()
    {

    }

    public void goAbout(ActionEvent event)
    {

    }

    public void goSubmit()
    {

    }

    public void goWorkflow()
    {

    }

    public void goLogin()
    {

    }

    public void goExit()
    {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
