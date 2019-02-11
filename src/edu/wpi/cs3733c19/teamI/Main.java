package edu.wpi.cs3733c19.teamI;

import edu.wpi.cs3733c19.teamI.Controllers.*;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOParam;

public class Main extends Application {

    public static SearchResults Results = new SearchResults();
    private IIOParam SearchLoader;
    private Object SearchScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/SearchPage.fxml"));
        Parent firstPane = firstPaneLoader.load();
        Scene firstScene = new Scene(firstPane, 1289, 918);

        FXMLLoader formCheckerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/FormChecker.fxml"));
        Parent formCheckerPane = formCheckerPaneLoader.load();
        Scene formCheckerScene = new Scene(formCheckerPane, 1289, 918);

        // getting loader and a pane for the second scene
        FXMLLoader ResultsPageLoader = new FXMLLoader(getClass().getResource("Boundaries/ResultsPage.fxml"));
        Parent ResultsPagePane = ResultsPageLoader.load();
        Scene ResultsPageScene = new Scene(ResultsPagePane, 1289, 918);

        //Loading the home pane
        FXMLLoader HomepageLoader = new FXMLLoader(getClass().getResource("Boundaries/homepage.fxml"));
        Parent HomepagePane = HomepageLoader.load();
        Scene HomeScene = new Scene(HomepagePane, 1289, 918);
        ((FormCheckerController) formCheckerPaneLoader.getController()).setHome(HomeScene);


        //Loading the submission page
        FXMLLoader SubmissionLoader = new FXMLLoader(getClass().getResource("Boundaries/SubmissionProject.fxml"));
        Parent SubmissionPane = SubmissionLoader.load();
        Scene SubmissionScene = new Scene(SubmissionPane, 1289, 918);

        ((FormSubController) SubmissionLoader.getController()).setHomeScene(HomeScene);

        //Loading the login page
        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/Login.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 1289, 918);
        LoginController loginController = (LoginController) loginPaneLoader.getController();
        loginController.setFormCheckerScene(formCheckerScene); //this is the problem
        loginController.setHomeScene(HomeScene);

        FormCheckerController formCheckControl = (FormCheckerController) formCheckerPaneLoader.getController();
        loginController.setFormCheck(formCheckControl);


        //injecting the first and second and login scenes into the controller of the home page
        HomeController HomeController = (HomeController) HomepageLoader.getController();
        HomeController.setSubmission(SubmissionScene);
//        HomeController.setSearch(SearchScene);
        HomeController.setLogin(loginScene);


        // injecting second scene into the controller of the first scene
        SearchController SearchController = (SearchController) SearchLoader.getController();
        SearchController.setDisplayScene(ResultsPageScene);//set display screen attribute
        SearchController.setHomePage(HomeScene); //set the home screen attribute


        // injecting first scene into the controller of the second scene
        SearchDisplayController ResultsController = (SearchDisplayController) ResultsPageLoader.getController();
//        ResultsController.setSearchScene(SearchScene);
        SearchController.setDispController(ResultsController);//set the controller attribute
        ResultsController.setHomePage(HomeScene); //set the home screen attribute

        primaryStage.setTitle("CO_keyLA SEARCH ENGINE");
        primaryStage.setScene(HomeScene);
        primaryStage.show();
    }



}
