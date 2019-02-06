package edu.wpi.cs3733c19.teamI;

import edu.wpi.cs3733c19.teamI.Controllers.*;
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
        /*
        Parent root = FXMLLoader.load(getClass().getResource("Boundaries/SubmissionProject.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 850, 800));
        primaryStage.show();
        */

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

        FXMLLoader formCheckerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/FormChecker.fxml"));
        Parent formCheckerPane = formCheckerPaneLoader.load();
        Scene formCheckerScene = new Scene(formCheckerPane, 1090, 916);




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

        //Loading the login page
        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/Login.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 900, 920);
        LoginController loginController = (LoginController) loginPaneLoader.getController();
        loginController.setFormCheckerScene(formCheckerScene); //this is the problem

        FormCheckerController formCheckControl = (FormCheckerController) formCheckerPaneLoader.getController();
       // formCheckControl.setLoginCtrl(loginController);
        loginController.setFormCheck(formCheckControl);


        //injecting the first and second and login scenes into the controller of the home page
        HomeController thirdPaneController = (HomeController) thirdPaneLoader.getController();
        thirdPaneController.setSubmission(fourthScene);
        thirdPaneController.setSearch(firstScene);
        thirdPaneController.setLogin(loginScene);



        // injecting second scene into the controller of the first scene
        SearchController firstPaneController = (SearchController) firstPaneLoader.getController();
        firstPaneController.setDisplayScene(secondScene);
        firstPaneController.setHomePage(thirdScene);

        // injecting first scene into the controller of the second scene
        SearchDisplayController secondPaneController = (SearchDisplayController) secondPageLoader.getController();
        secondPaneController.setSearchScene(firstScene);
        firstPaneController.dispController = secondPaneController;
        secondPaneController.setHomePage(thirdScene);

        primaryStage.setTitle("");
        primaryStage.setScene(thirdScene);
        primaryStage.show();
    }



}
