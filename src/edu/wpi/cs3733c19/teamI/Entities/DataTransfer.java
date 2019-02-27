package edu.wpi.cs3733c19.teamI.Entities;

public class DataTransfer {
    private static DataTransfer ourInstance = new DataTransfer();

    public static DataTransfer getInstance() {
        return ourInstance;
    }

    public String UserName = "";

    public String LogButtonName = "Login";
    public int loginStatus = 0;

    public boolean upload = false;

    public String currentFormID = "";

    public String pdf_PlantReg = "";

    public String pdf_Address = "";

    public String pdf_Fancy = "";

    public String pdf_WineApp = "";

    public String pdf_Formula = "";

    public String pdf_BrandName = "";

    public String pdf_Mailing = "";

    public String pdf_Grape = "";

    private DataTransfer() {
    }
}
