package edu.wpi.cs3733c19.teamI;


import edu.wpi.cs3733c19.teamI.Controllers2.ToolBarController;
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

        ToolBarController toolBarController = new ToolBarController();

        Parent root = FXMLLoader.load(getClass().getResource("Boundaries_2/Home.fxml"));
        Scene homeScene = new Scene(root);
        primaryStage.setTitle("COLA SEARCH ENGINE");
        primaryStage.setScene(homeScene);
        primaryStage.setMaximized(true);
        primaryStage.show();




        /*
        FXMLLoader loginResultLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Login_CreateAccount.fxml"));
        Parent loginCreatePane = loginResultLoader.load();
        Scene loginCreateScene = new Scene(loginCreatePane);

        FXMLLoader formSubLoader = new FXMLLoader(getClass().getResource("Boundaries_2/FormSubmission.fxml"));
        Parent formSubPane = formSubLoader.load();
        Scene formSubScene = new Scene(formSubPane);

        FXMLLoader detailedResultLoader = new FXMLLoader(getClass().getResource("Boundaries_2/DetailedSearchResults.fxml"));
        Parent detailedResultPane = detailedResultLoader.load();
        Scene detailedResultScene = new Scene(detailedResultPane);

        FXMLLoader searchResultLoader = new FXMLLoader(getClass().getResource("Boundaries_2/SearchResults.fxml"));
        Parent searchResultPane = searchResultLoader.load();
        Scene searchResultScene = new Scene(searchResultPane);

        /*

        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/WorkflowAgent.fxml"));
        Parent firstPane = firstPaneLoader.load();
        Scene firstScene = new Scene(firstPane);

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
        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/Login.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 1289, 918);
        LoginController loginController = (LoginController) loginPaneLoader.getController();
        loginController.setFormCheckerScene(formCheckerScene); //this is the problem
        loginController.setHomeScene(thirdScene);

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

        */

        /*
        //controllers for search scene
        NewHomeController newHomeController = (NewHomeController) newHomePaneLoader.getController();
        SubmissionController submissionController = (SubmissionController) formSubLoader.getController();
        LoginAccountController loginController = (LoginAccountController) loginResultLoader.getController();
        DetailedResultsController detailedResultsController = (DetailedResultsController) detailedResultLoader.getController();
        ResultsController resultsController = (ResultsController) searchResultLoader.getController();

        newHomeController.setSearchResultScene(SearchResults);

        //inject toolbar controls into each scene


        newHomeController.setToolBarController(toolBarController);
        detailedResultsController.setToolBarController(toolBarController);
        submissionController.setToolBarController(toolBarController);

        loginController.setToolBarController(toolBarController);


        resultsController.setToolBarController(toolBarController);




        //toolBarController.setFormCheck(formCheckerScene);
        toolBarController.setHomeScene(homeScene);
        toolBarController.setLogin(loginCreateScene);
        toolBarController.setSubScene(formSubScene);



        */





    }



}
