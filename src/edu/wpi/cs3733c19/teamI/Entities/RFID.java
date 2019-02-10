package edu.wpi.cs3733c19.teamI.Entities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.fazecast.jSerialComm.*;
import java.util.Enumeration;
public class RFID implements SerialPortDataListener{
    SerialPort Arduino = SerialPort.getCommPort("Com 3");

    public RFID() {
        Arduino.openPort();
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[Arduino.bytesAvailable()];
                int numRead = Arduino.readBytes(newData, newData.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        });
    }

}

