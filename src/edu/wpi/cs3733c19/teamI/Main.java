package edu.wpi.cs3733c19.teamI;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import edu.wpi.cs3733c19.teamI.Controllers2.LoginAccountController;
import edu.wpi.cs3733c19.teamI.Controllers2.ResultsController;
import edu.wpi.cs3733c19.teamI.Controllers2.SQLDriver;
import edu.wpi.cs3733c19.teamI.Controllers2.ToolBarController;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.DataField;
import edu.wpi.cs3733c19.teamI.Entities.SearchResults;
import edu.wpi.cs3733c19.teamI.Entities.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.imageio.IIOParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main extends Application implements SerialPortPacketListener {

    //Gabe was here
    ToolBarController toolBarController = ToolBarController.getInstance();
    public static SearchResults Results = new SearchResults();
    private IIOParam SearchLoader;
    private SerialPort comPort;
    public Thread mThread;
    private static Stage window;
    private LoginAccountController ctrl;




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

                        RFID_Login(RF_User, RF_Password, RF_Power);
                    }
                });
                //System.out.println("Continued?");
            }
            System.out.println("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //System.out.println("couldnt sleep");
            }
        }
        catch(NullPointerException n){

        }
    }


    public void RFID_Login(String username, String password, User.userPower power){
        if(!power.equals(null)) {
            User.userPower powerCreate = User.userPower.Standard;
            //TODO: eliminate following toggel button code, shold be handled by DB

            ArrayList<HashMap<String, ReturnedValue>> users = new ArrayList<>();
            SQLDriver loginDriver = new SQLDriver();
            LinkedList<String> param = new LinkedList<String>();
            param.add("email");
            HashMap<String, DataField> searchParam = new HashMap<>();
            searchParam.put("email", new DataField(username, "email"));
            try{
                users = loginDriver.get_user_data_by_value("credentials", "user_database.db", param, searchParam);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            if(users.get(0).get("role").to_double() == 1){
                powerCreate = User.userPower.TTBEmployee;
            }
            else if(users.get(0).get("role").to_double() == 2) {
                powerCreate = User.userPower.Company;
            }
            else if (users.get(0).get("role").to_double() == 3){
                powerCreate = User.userPower.Specialist;
            }
            else if (users.get(0).get("role").to_double() == 4){
                powerCreate= User.userPower.SuperAdmin;
            }
            else {
                powerCreate = User.userPower.Standard;
            }

            try
            {
                toolBarController.login(username, password, powerCreate, users.get(0).get("state").to_string(),
                        users.get(0).get("city").to_string(), users.get(0).get("zipCode").to_string(), users.get(0).get("streetAdress").to_string(),
                        users.get(0).get("firstName").to_string(), users.get(0).get("lastName").to_string(), users.get(0).get("phoneNumber").to_string(),
                        users.get(0).get("RepIDnum").to_string(), users.get(0).get("deliminator").to_string(), users.get(0).get("BeerScore").to_int(),
                        users.get(0).get("WineScore").to_int(), users.get(0).get("SpiritScore").to_int(), users.get(0).get("BarScore").to_int());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        window = primaryStage;
        Parent homeParent = FXMLLoader.load(getClass().getResource("Boundaries_2/Home.fxml"));
        Scene homeScene = new Scene(homeParent);
        FXMLLoader resultsPageLoader = new FXMLLoader(getClass().getResource("Boundaries_2/SearchResults.fxml"));
        FXMLLoader resultsPageLoader2 = new FXMLLoader(getClass().getResource("Boundaries_2/UserSearchResults.fxml"));
        Parent resultPane = resultsPageLoader.load();
        Parent resultPane2 = resultsPageLoader2.load();
        //Scene resultScene = new Scene(resultPane);

        toolBarController.setResultsController(resultsPageLoader.getController());
        toolBarController.setUserResultsController(resultsPageLoader2.getController());

        toolBarController.setSearchParent(resultPane);
        toolBarController.setSearchParent(resultPane2);
        window.setScene(homeScene);
        window.setMaximized(true);
        window.show();


        PacketListener();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println("made it");
                //comPort.removeDataListener();
                //comPort.closePort();
            }
        }));

        mThread = Thread.currentThread();



    }

    public static Stage getWindow() {
        return window;
    }

}
