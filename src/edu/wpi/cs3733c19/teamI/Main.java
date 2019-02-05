package edu.wpi.cs3733c19.teamI;

import edu.wpi.cs3733c19.teamI.Controllers.HomeController;
import edu.wpi.cs3733c19.teamI.Controllers.SearchController;
import edu.wpi.cs3733c19.teamI.Controllers.SearchDisplayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Original From main
        // Parent root = FXMLLoader.load(getClass().getResource("Boundaries/SearchV2.fxml"));
        // primaryStage.setTitle("Hello World");
        // primaryStage.setScene(new Scene(root, 850, 800));
        // primaryStage.show();
        // End of Original from main


        // getting loader and a pane for the first scene.
        // loader will then give a possibility to get related controller
        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SearchV2.fxml"));
        Parent firstPane = firstPaneLoader.load();
        Scene firstScene = new Scene(firstPane, 1090, 916);

        // getting loader and a pane for the second scene
        FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("Boundaries/ResultsPage.fxml"));
        Parent secondPane = secondPageLoader.load();
        Scene secondScene = new Scene(secondPane, 1293, 922);

        //Loading the home pane
        FXMLLoader thirdPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/homepage.fxml"));
        Parent thirdPane = thirdPaneLoader.load();
        Scene thirdScene = new Scene(thirdPane, 850, 800);

        //Loading the submission page
        FXMLLoader fourthPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SubmissionProject.fxml"));
        Parent fourthPane = fourthPaneLoader.load();
        Scene fourthScene = new Scene(fourthPane, 900, 920);

        //injecting the first and second scenes into the controller of the home page
        HomeController thirdPaneController = (HomeController) thirdPaneLoader.getController();
        thirdPaneController.setSubmission(fourthScene);
        thirdPaneController.setSearch(firstScene);


        // injecting second scene into the controller of the first scene
        SearchController firstPaneController = (SearchController) firstPaneLoader.getController();
        firstPaneController.setDisplayScene(secondScene);

        // injecting first scene into the controller of the second scene
        SearchDisplayController secondPaneController = (SearchDisplayController) secondPageLoader.getController();
        secondPaneController.setSearchScene(firstScene);

        primaryStage.setTitle("Welcome to TTB");
        primaryStage.setScene(thirdScene);
        primaryStage.show();


    }



}
