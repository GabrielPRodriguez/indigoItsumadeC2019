package edu.wpi.cs3733c19.teamI.Entities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.fazecast.jSerialComm.*;
import java.util.Enumeration;
public class RFID{
    SerialPort Arduino = SerialPort.getCommPorts()[0];
    private int numRead = 0;
    private int messageLength = 7;
    private byte[] message = {0,0,0,0,0,0,0};

    public RFID() {
        Arduino.openPort();
        System.out.println("Opening port");
        Arduino.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[Arduino.bytesAvailable()];
                int Read = Arduino.readBytes(newData, newData.length);
                //System.out.println("Read " + numRead + " bytes.");
                //String message = newData.toString();
                message[numRead] = newData[0];
                numRead++;
                if(numRead == 3){
                    System.out.println("Message: " + (char)message[0] + (char)message[1] + (char)message[2] + (char)message[3] + (char)message[4] + (char)message[5] + (char)message[6]);
                    numRead = 0;
                }
                //System.out.println("Read " + (char)newData[0]);
            }
        });
    }
}




