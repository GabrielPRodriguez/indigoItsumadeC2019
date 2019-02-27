package edu.wpi.cs3733c19.teamI.Controllers2;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javax.annotation.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class barController implements  Initializable{
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
    ImageView wineOld1;
    @FXML
    ImageView wineOld2;
    @FXML
    ImageView wineOld3;
    @FXML
    ImageView beerOld1;
    @FXML
    ImageView beerOld2;
    @FXML
    ImageView beerOld3;
    @FXML
    ImageView spiritOld1;
    @FXML
    ImageView spiritOld2;
    @FXML
    ImageView spiritOld3;
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
    ImageView barImage;
    @Override
    public void initialize(URL Location, ResourceBundle resources){
        wines = new Image[12];

        InputStream input = null;
        for(int b = 0;b <6;b++){
            String inputString = "../Assets/gameAssets/wine";
            Integer e = b;
            String add = e.toString() + "level.PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                wines[b] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }

        beers = new Image[12];
        input = null;
        for(int b = 0;b < 6;b++){
            String inputString = "../Assets/gameAssets/beer";
            Integer e = b;
            String add = e.toString() + "level.PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);beers[b] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }
        spirits = new Image[12];
        input = null;
        for(int b = 0;b < 6;b++){
            String inputString = "../Assets/gameAssets/spirit";
            Integer e = b;
            String add = e.toString() + "level.PNG";
            inputString += add;
            try {
                input = this.getClass().getResourceAsStream(inputString);
                Image image = new Image(input);
                spirits[b] = image;
            } catch (Exception x) {
                x.printStackTrace();
            }

        }

        bars = new Image[12];
        input = null;
        for(int b = 1;b < 4;b++){
            String inputString = "../Assets/gameAssets/bar";
            Integer e = b;
            String add = e.toString() + "level.PNG";
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
        barX = use.getBarScore();
        Integer w = wineX;
        Integer b = beerX;
        Integer s = spiritsX;
        Integer ba = barX;
        if(barX < 12){
            barX = 12;
        }
//        if(wineX < 12 ){
//            wineX = 12;
//        }
//        if(beerX  < 12){
//            beerX = 12;
//        }
//        if(spiritsX < 12){
//            spiritsX = 12;
//        }
        if(wineX > 71){
            wineX = 71;
        }
        if(beerX > 71){
            beerX = 71;
        }
        if(spiritsX > 71){
            spiritsX = 71;
        }
        if(barX > 36){
            barX = 36;
        }
        wineBottle.setImage(wines[wineX/12]);

        beerBottle.setImage(beers[beerX/12]);
        spiritBottle.setImage(spirits[spiritsX/12]);
        barImage.setImage(bars[barX/12]);
        if(barX > 24 && barX < 36){
            wineOld1.setOpacity(0);
            wineOld2.setOpacity(0);
            wineOld3.setOpacity(0);
            if(beerX >= 48){
                beerOld1.setImage(beers[(beerX -12)/12]);
                beerOld2.setImage(beers[(beerX -24)/12]);
                beerOld3.setImage(beers[(beerX - 36)/12]);
            }
            else if(beerX >= 36){
                beerOld1.setImage(beers[(beerX -12)/12]);
                beerOld2.setImage(beers[(beerX -24)/12]);
                beerOld3.setOpacity(0);
            }
            else if(beerX >= 24){
                beerOld1.setImage(beers[(beerX -12)/12]);
                beerOld2.setOpacity(0);
                beerOld3.setOpacity(0);
            }
            else{
                beerOld1.setOpacity(0);
                beerOld2.setOpacity(0);
                beerOld3.setOpacity(0);
            }
            if(spiritsX >= 48){
                spiritOld1.setImage(spirits[(spiritsX -12)/12]);
                spiritOld2.setImage(spirits[(spiritsX -24)/12]);
                spiritOld3.setImage(spirits[(spiritsX - 36)/12]);
            }
            else if(spiritsX >= 36){
                spiritOld1.setImage(spirits[(spiritsX -12)/12]);
                spiritOld2.setImage(spirits[(spiritsX -24)/12]);
                spiritOld3.setOpacity(0);
            }
            else if(spiritsX >= 24){
                spiritOld1.setImage(spirits[(spiritsX -12)/12]);
                spiritOld2.setOpacity(0);
                spiritOld3.setOpacity(0);
            }
            else{
                spiritOld1.setOpacity(0);
                spiritOld2.setOpacity(0);
                spiritOld3.setOpacity(0);
            }
        }
        else if(barX >= 36){
            if(wineX >= 48){
                wineOld1.setImage(wines[(wineX -12)/12]);
                wineOld2.setImage(wines[(wineX -24)/12]);
                wineOld3.setImage(wines[(wineX - 36)/12]);
            }
            else if(wineX >= 36){
                wineOld1.setImage(wines[(wineX -12)/12]);
                wineOld2.setImage(wines[(wineX -24)/12]);
                wineOld3.setOpacity(0);
            }
            else if(wineX >= 24){
                wineOld1.setImage(wines[(wineX -12)/12]);
                wineOld2.setOpacity(0);
                wineOld3.setOpacity(0);
            }
            else{
                wineOld1.setOpacity(0);
                wineOld2.setOpacity(0);
                wineOld3.setOpacity(0);
            }
            if(beerX >= 48){
                beerOld1.setImage(beers[(beerX -12)/12]);
                beerOld2.setImage(beers[(beerX -24)/12]);
                beerOld3.setImage(beers[(beerX - 36)/12]);
            }
            else if(beerX >= 36){
                beerOld1.setImage(beers[(beerX -12)/12]);
                beerOld2.setImage(beers[(beerX -24)/12]);
                beerOld3.setOpacity(0);
            }
            else if(beerX >= 24){
                beerOld1.setImage(beers[(beerX -12)/12]);
                beerOld2.setOpacity(0);
                beerOld3.setOpacity(0);
            }
            else{
                beerOld1.setOpacity(0);
                beerOld2.setOpacity(0);
                beerOld3.setOpacity(0);
            }
            if(spiritsX >= 48){
                spiritOld1.setImage(spirits[(spiritsX -12)/12]);
                spiritOld2.setImage(spirits[(spiritsX -24)/12]);
                spiritOld3.setImage(spirits[(spiritsX - 36)/12]);
            }
            else if(spiritsX >= 36){
                spiritOld1.setImage(spirits[(spiritsX -12)/12]);
                spiritOld2.setImage(spirits[(spiritsX -24)/12]);
                spiritOld3.setOpacity(0);
            }
            else if(spiritsX >= 24){
                spiritOld1.setImage(spirits[(spiritsX -12)/12]);
                spiritOld2.setOpacity(0);
                spiritOld3.setOpacity(0);
            }
            else{
                spiritOld1.setOpacity(0);
                spiritOld2.setOpacity(0);
                spiritOld3.setOpacity(0);
            }
        }
        else{
            wineOld1.setOpacity(0);
            wineOld2.setOpacity(0);
            wineOld3.setOpacity(0);
            beerOld1.setOpacity(0);
            beerOld2.setOpacity(0);
            beerOld3.setOpacity(0);
            spiritOld1.setOpacity(0);
            spiritOld2.setOpacity(0);
            spiritOld3.setOpacity(0);
        }
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
