package edu.wpi.cs3733c19.teamI.Tesseract;

import com.sun.jna.StringArray;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.net.URL;

public class TesseractReader {
    public TesseractReader() {
    }

    public String convert(String filePath) {
        String url = (getClass().getResource("tessdata")).getPath();
        org.apache.log4j.PropertyConfigurator.configure((url)+"/log4j.properties.txt");//"C:/Users/Carkin/indigoItsumadeC19/src/edu/wpi/cs3733c19/teamI/Tesseract/Tess4J/log4j.properties.txt");
        File image = new File(filePath);
        Tesseract tessInst = new Tesseract();
        try {
            String runPath = TesseractReader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            System.out.println(runPath);
            String[] brokenPath = runPath.split("/");
            //use this to define where the file will be, when you have an exec, make sure important folder is in the folder w/ executable
        }
        catch(Exception e){

        }
        //tessInst.setDatapath(url);
        tessInst.setDatapath("C:/Users/Carkin/Tess4J");//"C:/Users/Carkin/Tess4J");

        try {
            String result = tessInst.doOCR(image);
            System.out.println(result);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return("");
        }
    }

}
