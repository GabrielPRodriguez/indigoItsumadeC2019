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

        ToolBarController toolBarController = new ToolBarController();


        FXMLLoader newHomePaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Home.fxml"));
        Parent homePane = newHomePaneLoader.load();
        Scene homeScene = new Scene(homePane, 1289, 918);

        FXMLLoader loginResultLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Login_CreateAccount.fxml"));
        Parent loginCreatePane = loginResultLoader.load();
        Scene loginCreateScene = new Scene(loginCreatePane, 1289, 918);

        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SearchV2.fxml"));
        Parent firstPane = firstPaneLoader.load();
        Scene firstScene = new Scene(firstPane, 1289, 918);

        FXMLLoader formCheckerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/FormChecker.fxml"));
        Parent formCheckerPane = formCheckerPaneLoader.load();
        Scene formCheckerScene = new Scene(formCheckerPane, 1289, 918);

        // getting loader and a pane for the second scene
        FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("Boundaries/ResultsPage.fxml"));
        Parent secondPane = secondPageLoader.load();
        Scene secondScene = new Scene(secondPane, 1289, 918);

        //Loading the home pane
        FXMLLoader thirdPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/homepage.fxml"));
        Parent thirdPane = thirdPaneLoader.load();
        Scene thirdScene = new Scene(thirdPane, 1289, 918);
        ((FormCheckerController) formCheckerPaneLoader.getController()).setHome(thirdScene);


        //Loading the submission page
        FXMLLoader fourthPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SubmissionProject.fxml"));
        Parent fourthPane = fourthPaneLoader.load();
        Scene fourthScene = new Scene(fourthPane, 1289, 918);

        ( (FormSubController) fourthPaneLoader.getController()).setHomeScene(thirdScene);

        //Loading the login page
        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Login_CreateAccount.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 1289, 918);
        LoginController loginController = (LoginController) loginPaneLoader.getController();
        loginController.setFormCheckerScene(formCheckerScene); //this is the problem
        loginController.setHomeScene(homeScene);
        loginController.setFormSub(formCheckerScene);

        FormCheckerController formCheckControl = (FormCheckerController) formCheckerPaneLoader.getController();
        loginController.setFormCheck(formCheckControl);


        //injecting the first and second and login scenes into the controller of the home page
        HomeController thirdPaneController = (HomeController) thirdPaneLoader.getController();
        thirdPaneController.setSubmission(fourthScene);
        thirdPaneController.setSearch(firstScene);
        thirdPaneController.setLogin(loginScene);


        // injecting second scene into the controller of the first scene
        SearchController firstPaneController = (SearchController) firstPaneLoader.getController();
        firstPaneController.setDisplayScene(secondScene);//set display screen attribute
        firstPaneController.setHomePage(thirdScene); //set the home screen attribute


        // injecting first scene into the controller of the second scene
        SearchDisplayController secondPaneController = (SearchDisplayController) secondPageLoader.getController();
        secondPaneController.setSearchScene(firstScene);
        firstPaneController.dispController = secondPaneController;//set the controller attribute
        secondPaneController.setHomePage(thirdScene); //set the home screen attribute

        NewHomeController newHomeController = (NewHomeController) newHomePaneLoader.getController();
        newHomeController.setToolBarController(toolBarController);
        newHomeController.setHomeScene(homeScene);
        newHomeController.setFormSub(fourthScene);
        newHomeController.setLogin(loginScene);

        toolBarController.setFormCheck(formCheckerScene);
        toolBarController.setHomeScene(homeScene);
        toolBarController.setLogin(loginScene);
        toolBarController.setSubScene(fourthScene);






        primaryStage.setTitle("COLA SEARCH ENGINE");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }



}
