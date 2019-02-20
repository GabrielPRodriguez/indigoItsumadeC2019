package edu.wpi.cs3733c19.teamI.Entities;

public class DataTransfer {
    private static DataTransfer ourInstance = new DataTransfer();

    public static DataTransfer getInstance() {
        return ourInstance;
    }

    public String UserName = "";

    public String LogButtonName = "Login";
    public int loginStatus = 0;

    private DataTransfer() {
    }
}