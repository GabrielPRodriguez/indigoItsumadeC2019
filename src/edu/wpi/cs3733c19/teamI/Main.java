package edu.wpi.cs3733c19.teamI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Boundaries/SubmissionProject.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 850, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
