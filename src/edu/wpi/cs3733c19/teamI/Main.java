package edu.wpi.cs3733c19.teamI;

import edu.wpi.cs3733c19.teamI.Controllers.SearchController;
import edu.wpi.cs3733c19.teamI.Controllers.SearchDisplayController;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static SearchResults Results = new SearchResults();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Parent root = FXMLLoader.load(getClass().getResource("Boundaries/SubmissionProject.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 850, 800));
//        primaryStage.show();


////        // Original From main
//         Parent root = FXMLLoader.load(getClass().getResource("Boundaries/SearchV2.fxml"));
//         primaryStage.setTitle("Hello World");
//         primaryStage.setScene(new Scene(root, 850, 800));
//         primaryStage.show();
//        // End of Original from main


        // getting loader and a pane for the first scene.
        // loader will then give a possibility to get related controller
        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SearchV2.fxml"));
        Parent firstPane = firstPaneLoader.load();
        Scene firstScene = new Scene(firstPane, 1090, 916);

        // getting loader and a pane for the second scene
        FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("Boundaries/ResultsPage.fxml"));
        Parent secondPane = secondPageLoader.load();
        Scene secondScene = new Scene(secondPane, 1293, 922);

        // injecting second scene into the controller of the first scene
        SearchController firstPaneController = (SearchController) firstPaneLoader.getController();
        firstPaneController.setDisplayScene(secondScene);


        // injecting first scene into the controller of the second scene
        SearchDisplayController secondPaneController = (SearchDisplayController) secondPageLoader.getController();
        secondPaneController.StringTest = firstPaneController.StringTest;
        secondPaneController.setSearchScene(firstScene);
        System.out.println(secondPaneController.StringTest);
        firstPaneController.dispController = secondPaneController;

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }



}
