package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.annotation.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class barController {
    public int beerX;
    public int wineX;
    public int spiritsX;
    public int barX;
    private ToolBarController toolBarController = ToolBarController.getInstance();
    @FXML
    ImageView wineBottle;
    @FXML
    ImageView beerBottle;
    @FXML
    ImageView spiritBottle;
    @FXML
    JFXButton goWork;
    @FXML
    public void goWork() throws IOException {
        toolBarController.goWorkflowAgent();
    }

    @FXML
    protected void initalize(URL Location, Resources resources){
        User use = User.getUser("a","a",User.userPower.Standard,"a","a","a","a","a","a","a","a","a",1,1,1,1);
        beerX = use.getBeerScore();
        wineX = use.getWineScore();
        spiritsX = use.getSpiritScore();
        barX = use.getSpiritScore();
        /*
            Set the images
        wineBottle.setImage("..Assets/gameAssests/(use.getXP/12).toString());
        beerBottle.setImage(getXp/12);
        spiritBottle.setImage(getXp/12);
        barImage.setImage(barX/12);
        if(barX/12 > 2){
            set the other 3 to the past 3 images
        }
         */

    }


}
