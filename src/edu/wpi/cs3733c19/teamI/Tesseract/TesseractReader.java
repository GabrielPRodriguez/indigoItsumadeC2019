package edu.wpi.cs3733c19.teamI.Tesseract;

import com.sun.jna.StringArray;
import edu.wpi.cs3733c19.teamI.Entities.DataTransfer;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TesseractReader {
    public TesseractReader() {
    }

    public String convert(String filePath) {
        String url = (getClass().getResource("tessdata")).getPath();
        org.apache.log4j.PropertyConfigurator.configure((url)+"/log4j.properties.txt");//"C:/Users/Carkin/indigoItsumadeC19/src/edu/wpi/cs3733c19/teamI/Tesseract/Tess4J/log4j.properties.txt");
        File image = new File(filePath);
        Tesseract tessInst = new Tesseract();
        String tessPath = "";
        try {
            String runPath = TesseractReader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            System.out.println(runPath);
            String[] brokenPath = runPath.split("/");
            tessPath = brokenPath[1] + "/" + brokenPath[2] + "/" + brokenPath[3] + "/Tess4J";
            System.out.println(tessPath);
            }
        catch(Exception e){

        }
        tessInst.setDatapath(tessPath);

        try {
            String result = tessInst.doOCR(image);
            System.out.println(result);
            try{
               // File file = new File(getClass().getResource("TesseractReader").getPath()+"/formInfo.txt");
               // file.getParentFile().mkdirs();
                FileWriter userWriter = new FileWriter("formInfo.txt", false);
                BufferedWriter outputStream = new BufferedWriter(userWriter);
                outputStream.write(result);
                outputStream.flush();
                outputStream.close();
            }
            catch (Exception e){
                System.out.println(e);

            }

            getFields(System.getProperty("user.dir") + "/formInfo.txt");
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return("");
        }
    }

    /*
        Rep ID No.:
        Plant Registry/ Basic Permit No.
        Name and Address of Applicant
        Brand Name:
        Mailing Address
        Fanciful Name:
        Grape Varietals
        Wine Appelation
        Phone Number
        Email Address
        Date of Application
        Print Name of Applicant or Authorized Age
         */
    //Add an instance of a form over here
    //1. Read the contents of the file
    public void getFields(String filePath){
       DataTransfer data = DataTransfer.getInstance();


        String repID;
        String plantRegistry;


    StringBuilder contentBuilder = new StringBuilder();
        try (
    BufferedReader br = new BufferedReader(new FileReader(filePath)))
    {

        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null)
        {
            contentBuilder.append(sCurrentLine).append("\n");
        }
    }
        catch (
    IOException e)
    {
        e.printStackTrace();
    }
    //file cleaning
    String fileContents=contentBuilder.toString();
    //Basically makes it into one long string
    fileContents=fileContents.replace("\n","").replace("\t","");
    fileContents=fileContents.toLowerCase();
    //extract first part

    //cat => REPIDno
    String holder=fileContents;
    Pattern pattern = Pattern.compile("approval(.*?)\\(see");
    Matcher matcher = pattern.matcher(holder);
    String RepID="";
        while (matcher.find()) {
        RepID=RepID+" "+matcher.group(1);
    }
    //grab the first substring
    String[] splitted = RepID.split(" ");
        if(splitted.length > 0) {
            RepID = splitted[1];
        }
        System.out.println("RepId "+RepID);
    //TODO:this rule changed between the 2 file (since it read lff in one and lf in the other, add a third test case here
    //dog=> Plant Registry
    String PlantRegistry="";
    holder=fileContents;
    pattern=Pattern.compile("lff(.*?) el dom");
    matcher=pattern.matcher(holder);
        while (matcher.find()) {
        PlantRegistry=PlantRegistry+matcher.group(1);
    }

        System.out.println("Plant Registry: "+PlantRegistry);
        data.pdf_PlantReg = PlantRegistry;

    String namenaddress="";
    //chicken=> name and address of applicant as shown on the permit
    pattern=Pattern.compile("label \\(required\\)(.*?)4.");
    matcher=pattern.matcher(fileContents);
        while (matcher.find()) {
        namenaddress=namenaddress+matcher.group(1);
    }
    splitted=namenaddress.split(" ");
        if(splitted.length > 0) {
            namenaddress = splitted[0];
        }
        System.out.println("Name and Address"+namenaddress);
        data.pdf_Address = namenaddress;

    //sloth=> fanciful name
    String fancifulName="";
    pattern=Pattern.compile("ifany\\)(.*?)9.");
    matcher=pattern.matcher(fileContents);
        while (matcher.find()) {
        fancifulName=fancifulName+matcher.group(1);
    }
        System.out.println("Fanciful name:"+fancifulName);
        data.pdf_Fancy = fancifulName;


    //hippo and giraffe=>phone number and email address
    String phone="";
    String email="";
    String temp="";
    pattern=Pattern.compile("rejection(.*?) ttb id");
    matcher=pattern.matcher(fileContents);
        while (matcher.find()) {
        temp=temp+matcher.group(1);
        System.out.println(matcher.group(1));
    }

    splitted=temp.split(" ");
        if(splitted.length>0){
             phone=splitted[0];
        }
        if(splitted.length > 1) {
            email = splitted[1];
        }
        System.out.println("Phone and email: "+phone+" "+email);


    temp="";
    String date="";
    String printname="";
    //bat snake=>Date of Application and print name
    pattern=Pattern.compile("print name of applicant or authorized agent(.*?)part");
    matcher=pattern.matcher(fileContents);
        while (matcher.find()) {
        temp=temp+matcher.group(1);
    }
    splitted=temp.split(" ");
        if(splitted.length > 0) {
            date = splitted[0];
        }
    printname=splitted[1];
        System.out.println("Date and Print Name:"+date+" "+ printname);


    //zebra =>wine appellation, this one breaks in the first test case (I may have to write one more test case)
    pattern=Pattern.compile("fill in state abbreviation\\)(.*?)c.");
    matcher=pattern.matcher(fileContents);
    String wineApp="";
        while (matcher.find()) {
        wineApp=matcher.group(1);
    }
        System.out.println("Wine App "+wineApp);
        data.pdf_WineApp = wineApp;

    String formula="";
    String grapeVarietals="";
    temp="";
    //lion pig=> formula and grape varietals
    pattern=Pattern.compile("box\\(es}\\)(.*?) 3");
    matcher=pattern.matcher(fileContents);
        while (matcher.find()) {
        temp=temp+matcher.group(1);
//                System.out.println(matcher.group(1));
    }
    splitted=temp.split(" ");
        if(splitted.length > 0) {
            formula = splitted[0];
        }
        if(splitted.length > 1) {
            grapeVarietals = splitted[1];
        }

        System.out.println("Formula +Grape:"+formula+" "+grapeVarietals);
        data.pdf_Formula = formula;
        data.pdf_Grape = grapeVarietals;
    //dolphin mouse=> brand name and mailing address
    String brandName="";
    String mailingAdd="";
    temp="";
    pattern=Pattern.compile("e \\(required\\)(.*?)7. ");
    matcher=pattern.matcher(fileContents);
        while (matcher.find()) {
        temp=temp+matcher.group(1);
    }
    splitted=temp.split(" ");
        if(splitted.length > 0) {
            brandName = splitted[0];
        }
        if(splitted.length > 1) {
            mailingAdd = splitted[1];
        }
        System.out.println("BrandName="+ brandName);
        data.pdf_BrandName = brandName;
        System.out.println("Mailing Addres="+ mailingAdd);
        data.pdf_Mailing = mailingAdd;

        data.upload = true;
}
    //
    public static String valueExtracter(Pattern P, String fileContents){
        Matcher matcher = P.matcher(fileContents);
        String myString=matcher.group(1);
        return myString;
    }

}
