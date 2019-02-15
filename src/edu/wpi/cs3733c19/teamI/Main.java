package edu.wpi.cs3733c19.teamI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application{





    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("TTB COLA App");


        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Home.fxml"));
        Parent homePane = homePageLoader.load();
        Scene homeScene = new Scene(homePane);
        primaryStage.setScene(homeScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();





    }

}
