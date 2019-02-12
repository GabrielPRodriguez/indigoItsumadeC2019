package edu.wpi.cs3733c19.teamI.Entities;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import edu.wpi.cs3733c19.teamI.Controllers.LoginController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PacketListener implements SerialPortPacketListener
{


    private LoginController login_controller;

    private Stage primaryStage;

    private Scene userPage;

    public PacketListener(Stage primary, Scene page) {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.addDataListener(this);
        this.primaryStage = primary;
        this.userPage = page;
    }

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public int getPacketSize() { return 4; }

    @Override
    public void serialEvent(SerialPortEvent event)
    {
        String user = " ";
        byte[] newData = event.getReceivedData();
        System.out.println("Received data of size: " + newData.length);
        for (int i = 0; i < newData.length; ++i) {
            user += (char)newData[i];
            System.out.print((char) newData[i]);
        }
        System.out.println(user);
        if(user.equals("    ")){
            //do nothing
        }
        else{
            System.out.println("trying to open");
            login_controller.setFlag = 7;
           // login_controller.openDisplayScene();
        }
        System.out.println("\n");
    }

    public void setLogin_controller(LoginController login_controller) {
        this.login_controller = login_controller;
    }
}
