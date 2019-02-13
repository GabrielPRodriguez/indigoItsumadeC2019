package edu.wpi.cs3733c19.teamI;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import edu.wpi.cs3733c19.teamI.Controllers2.*;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOParam;

public class Main extends Application {

    public static SearchResults Results = new SearchResults();
    private IIOParam SearchLoader;
    private Object SearchScene;
    private SerialPort comPort;
    public Scene formCheckerScene;
    public Parent formCheckerPane;
    public Thread mThread;


    public void PacketListener() {
        try {
            SerialPort comPort = SerialPort.getCommPorts()[0];
            comPort.openPort();
            comPort.addDataListener(this);
        }
        catch(Exception e){
            System.out.println("No peripheral connected");
        }
    }

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public int getPacketSize() { return 4; }

    @Override
    public void serialEvent(SerialPortEvent event)
    {
        try {
            if (!mThread.isAlive()) {
                //  comPort.removeDataListener();
                //   comPort.closePort();
            }
        }
        catch (NullPointerException n){
            // comPort.removeDataListener();
            // comPort.closePort();
        }
        String user = " ";
        byte[] newData = event.getReceivedData();
        System.out.println("Received data of size: " + newData.length);
        for (int i = 0; i < newData.length; ++i) {
            user += (char)newData[i];
            System.out.print((char) newData[i]);
        }
        System.out.println(user);
        if(user.equals(" ERIC")){
            System.out.println("Change?");
            Platform.runLater(new Runnable() {

                @Override
                public void run() {

                    finalStage.setScene(formCheckerScene);
                }
            });
            System.out.println("Continued?");
        }
        System.out.println("\n");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("couldnt sleep");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        ToolBarController toolBarController = new ToolBarController();
        FXMLLoader formCheckerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/WorkflowAgent.fxml"));
        Parent formCheckerPane = formCheckerPaneLoader.load();
        Scene formCheckerScene = new Scene(formCheckerPane, 1289, 918);
        toolBarController.setFormCheck(formCheckerScene);

        AgentWorkflowController agentWorkflowController = formCheckerPaneLoader.getController();
        agentWorkflowController.setToolBarController(toolBarController);

        FXMLLoader searchPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Advanced Search.fxml"));
        Parent searchPane = searchPaneLoader.load();
        Scene searchScene = new Scene(searchPane, 1289, 918);
        toolBarController.setSearchScene(searchScene);

        AdvancedSearchController advancedSearchController = searchPaneLoader.getController();
        advancedSearchController.setToolBarController(toolBarController);



        FXMLLoader resultsPageLoader = new FXMLLoader(getClass().getResource("Boundaries_2/SearchResults.fxml"));
        Parent resultPane = resultsPageLoader.load();
        Scene resultScene = new Scene(resultPane, 1289, 918);
        toolBarController.setResult(resultScene);

        ResultsController resultsController = resultsPageLoader.getController();
        resultsController.setToolBarController(toolBarController);

        toolBarController.setResultsController(resultsController);

        FXMLLoader subPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/FormSubmission.fxml"));
        Parent subPane = subPaneLoader.load();
        Scene subScene = new Scene(subPane, 1289, 918);
        toolBarController.setSubScene(subScene);

        FormSubmissionController formSubmissionController = subPaneLoader.getController();
        formSubmissionController.setToolBarController(toolBarController);


        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Login_CreateAccount.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 1289, 918);
        toolBarController.setLogin(loginScene);

        LoginAccountController loginAccountController = loginPaneLoader.getController();
        loginAccountController.setToolBarController(toolBarController);


        FXMLLoader aboutPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/About.fxml"));
        Parent aboutPane = aboutPaneLoader.load();
        Scene aboutScene = new Scene(aboutPane, 1289, 918);
        toolBarController.setAboutScene(aboutScene);

        AboutController aboutController = aboutPaneLoader.getController();
        aboutController.setToolBarController(toolBarController);

        FXMLLoader manufacturerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/WorkflowManufacturer.fxml"));
        Parent manufacturerPane = manufacturerPaneLoader.load();
        Scene manufacturerScene = new Scene(manufacturerPane, 1289,918);
        toolBarController.setPending(manufacturerScene);

        ManufacturerWorkflowController manufacturerWorkflowController = manufacturerPaneLoader.getController();
        manufacturerWorkflowController.setToolBarController(toolBarController);

        FXMLLoader detailedLoader = new FXMLLoader(getClass().getResource("Boundaries_2/DetailedSearchResults.fxml"));
        Parent detailedPane = detailedLoader.load();
        Scene detailedScene = new Scene(detailedPane,1289,918);
        toolBarController.setInfo(detailedScene);

        DetailedResultsController detailedResultsController = detailedLoader.getController();
        detailedResultsController.setToolBarController(toolBarController);


        FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("Boundaries_2/Home.fxml"));
        Parent homePane = homePaneLoader.load();
        Scene homeScene = new Scene(homePane, 1289, 918);
        toolBarController.setHomeScene(homeScene);


        NewHomeController newHomeController = homePaneLoader.getController();
        newHomeController.setToolBarController(toolBarController);






        Parent root = FXMLLoader.load(getClass().getResource("Boundaries_2/Home.fxml"));
        Scene startScene = new Scene(root);
        primaryStage.setTitle("COLA SEARCH ENGINE");
        primaryStage.setScene(homeScene);
        primaryStage.setMaximized(true);
        primaryStage.show();


        PacketListener();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("made it");
                comPort.removeDataListener();
                comPort.closePort();
            }
        }));

        mThread = Thread.currentThread();




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
        loginController.setSubmission(SubmissionScene);

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
