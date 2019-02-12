package edu.wpi.cs3733c19.teamI;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import edu.wpi.cs3733c19.teamI.Controllers.*;
import edu.wpi.cs3733c19.teamI.Entities.PacketListener;
import edu.wpi.cs3733c19.teamI.Entities.RFID;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements SerialPortPacketListener {

    private LoginController login_controller;

    private Stage finalStage;

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
        if(!mThread.isAlive()){
            comPort.removeDataListener();
            comPort.closePort();
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
//        comPort.removeDataListener();
//        comPort.closePort();
    }

    public static SearchResults Results = new SearchResults();

    public static void main(String[] args) {
        launch(args);
    }

    void changeScene(){
        finalStage.setScene(this.formCheckerScene);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.finalStage = primaryStage;
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("made it");
                        comPort.removeDataListener();
                        comPort.closePort();
                    }
                }));

        mThread = Thread.currentThread();



        FXMLLoader SearchLoader = new FXMLLoader(getClass().getResource("Boundaries/SearchV2.fxml"));
        Parent SearchPane = SearchLoader.load();
        Scene SearchScene = new Scene(SearchPane, 1289, 918);

        FXMLLoader formCheckerPaneLoader = new FXMLLoader(getClass().getResource("Boundaries/FormChecker.fxml"));
        this.formCheckerPane = formCheckerPaneLoader.load();
        this.formCheckerScene = new Scene(formCheckerPane, 1289, 918);
        //this.userPage = formCheckerScene;

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
        HomeController.setSearch(SearchScene);
        HomeController.setLogin(loginScene);


        // injecting second scene into the controller of the first scene
        SearchController SearchController = (SearchController) SearchLoader.getController();
        SearchController.setDisplayScene(ResultsPageScene);//set display screen attribute
        SearchController.setHomePage(HomeScene); //set the home screen attribute


        // injecting first scene into the controller of the second scene
        SearchDisplayController ResultsController = (SearchDisplayController) ResultsPageLoader.getController();
        ResultsController.setSearchScene(SearchScene);
        SearchController.setDispController(ResultsController);//set the controller attribute
        ResultsController.setHomePage(HomeScene); //set the home screen attribute

        primaryStage.setTitle("COLA SEARCH ENGINE");
        primaryStage.setScene(HomeScene);
        primaryStage.show();
        PacketListener();

    }



}
