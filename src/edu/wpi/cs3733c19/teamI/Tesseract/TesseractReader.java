package edu.wpi.cs3733c19.teamI.Tesseract;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.net.URL;

public class TesseractReader {
    public TesseractReader() {
    }

    public String convert(String filePath) {
        String url = (getClass().getResource("Tess4J")).getPath();
        org.apache.log4j.PropertyConfigurator.configure((url)+"/log4j.properties.txt");//"C:/Users/Carkin/indigoItsumadeC19/src/edu/wpi/cs3733c19/teamI/Tesseract/Tess4J/log4j.properties.txt");
        File image = new File(filePath);
        Tesseract tessInst = new Tesseract();
        tessInst.setDatapath(url);//"C:/Users/Carkin/Tess4J");

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
