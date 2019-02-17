package edu.wpi.cs3733c19.teamI;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import edu.wpi.cs3733c19.teamI.Controllers2.*;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOParam;
import java.io.IOException;

public class Main extends Application implements SerialPortPacketListener {

    public static SearchResults Results = new SearchResults();
    private IIOParam SearchLoader;
    private Object SearchScene;
    private SerialPort comPort;
    public Scene formCheckerScene;
    public Parent formCheckerPane;
    public Thread mThread;
    private ToolBarController ToolController = ToolBarController.getInstance();
    private LoginAccountController ctrl;

    public static Stage getRoot() {
        return root;
    }

    private static void setRoot(Stage root) {
        Main.root = root;
    }

    static Stage root;


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
            String user = " ";
            byte[] newData = event.getReceivedData();
            System.out.println("Received data of size: " + newData.length);
            for (int i = 0; i < newData.length; ++i) {
                user += (char) newData[i];
                System.out.print((char) newData[i]);
            }
            String RF_User;
            String RF_Password;
            User.userPower RF_Power;
            if (user.equals(" ERIC")) {
                RF_User = "eric@wpi.edu";
                RF_Password = "MZsTH5v6ZMNem/AR2WJYnQ==";
                RF_Power = User.userPower.TTBEmployee;
            }
            else if(user.equals(" BOSS")) {
                RF_User = "Ferguson@wpi.edu";
                RF_Password = "uL6WBrubtA3Tw0KodBS8uQ==";
                RF_Power = User.userPower.TTBEmployee;
            }
            else if(user.equals(" WONG")) {
                RF_User = "admin@admin.admin";
                RF_Password = "cV48VsuJMMuSXqnxsMu90w==";
                RF_Power = User.userPower.SuperAdmin;
            }
            else {
                RF_User = " ";
                RF_Password = " ";
                RF_Power = null;
            }

            if(!RF_User.equals(" ")){

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {

                        try {
                            RFID_Login(RF_User, RF_Password, RF_Power);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                System.out.println("Continued?");
            }
            System.out.println("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("couldnt sleep");
            }
        }
        catch(NullPointerException n){

        }
    }



    public void RFID_Login(String username, String password, User.userPower power) throws IOException {
        this.ToolController.loginRFID(username, password, power);
        ToolController.goLogin();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setRoot(primaryStage);
        Parent homeParent = FXMLLoader.load(getClass().getResource("Boundaries_2/Home.fxml"));
        Scene homeScene = new Scene(homeParent);
        primaryStage.setTitle("COLA SEARCH ENGINE");
        primaryStage.setScene(homeScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();



        PacketListener();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("made it");
//                comPort.removeDataListener();
 //               comPort.closePort();
            }
        }));

        mThread = Thread.currentThread();

        setRoot(primaryStage);

    }



}
