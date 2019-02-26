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
    String wineIn = "../Assets/gameAssets/wine";
    String beerIn = "../Assets/gameAssets/beer";
    String spritIn = "../Assets/gameAssets/spirit";
    String barIn = "../Assets/gameAssets/bar";
    Image[] wines;
    Image[] beers;
    Image[] spirits;
    Image[] bars;
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
        wines = new Image[12];

        InputStream input = null;
        for(int b = 1;b < 13;b++){
            String inputString = "../Assets/gameAssets/wine";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                wines[b - 1] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }

        beers = new Image[12];
        input = null;
        for(int b = 0;b < 12;b++){
            String inputString = "../Assets/gameAssets/beer";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                beers[b] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }
        spirits = new Image[12];
        input = null;
        for(int b = 1;b < 13;b++){
            String inputString = "../Assets/gameAssets/spirit";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                spirits[b-1] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }

        bars = new Image[12];
        input = null;
        for(int b = 0;b < 12;b++){
            String inputString = "../Assets/gameAssets/bar";
            Integer e = b;
            String add = e.toString() + ".PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                bars[b] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }
        }

        User use = User.getUser("a","a",User.userPower.Standard,"a","a","a","a","a","a","a","a","a",1,1,1,1);
        beerX = use.getBeerScore();
        wineX = use.getWineScore();
        spiritsX = use.getSpiritScore();
        barX = use.getSpiritScore();
        Integer w = wineX;
        Integer b = beerX;
        Integer s = spiritsX;
        Integer ba = barX;
        
        //wineBottle.setImage(wineIn + w.toString());
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
