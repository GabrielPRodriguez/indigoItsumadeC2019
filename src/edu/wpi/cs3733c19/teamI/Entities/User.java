package edu.wpi.cs3733c19.teamI.Entities;

import edu.wpi.cs3733c19.teamI.Controllers2.SQLDriver;

public class User {

    //TODO: make setter for scores that change database

    private String username;
    private String password;
    private Boolean isAdmin = false;
    private userPower userType;
    private String state;
    private String city;
    private String zip;
    private String street;
    private String firstName;
    private String lastName;
    private String phone;
    private String repId;
    private int BeerScore;
    private int WineScore;
    private int SpiritScore;
    private int BarScore;

    private String delim;
        public enum userPower
        {
        Standard, Company, TTBEmployee, SuperAdmin, Specialist
    }
    private int appsPer = 5;

    private static User curUser;

    private User(String name, String pass, userPower type, String theState, String theCity, String theZip,
                 String theStreet, String theFirstName, String theLastName, String thePhone, String theRepID,
                 String delim, int theBeerScore, int theWineScore, int theSpiritScore, int theBarScore){
        username = name;
        password = pass;
        userType = type;
        state = theState;
        city = theCity;
        zip = theZip;
        street = theStreet;
        firstName = theFirstName;
        lastName = theLastName;
        phone = thePhone;
        repId = theRepID;
        BeerScore = theBeerScore;
        WineScore = theWineScore;
        SpiritScore = theSpiritScore;
        BarScore = theBarScore;
        if(delim.equals("")){
            this.delim = ",";
        }
        else this.delim = delim;


            }
/*
    private static class UserHelper{
        //private static User curUser = new User("","",userPower.Standard, "", "", "", "", "", "", "", "", "0");
    }*/

    public void logOutUser(){
        curUser = new User("", "", userPower.Standard, "", "","","","","","","", "?", 0, 0, 0, 0);
    }

    public void incrementBeerScore(){
        this.BeerScore++;
        System.out.println(this.BeerScore);

        try {
            SQLDriver.incrementBeerScore(repId, this.BeerScore);
        }
        catch(Exception e){

        }
    }

    public void incrementWineScore(){
        this.WineScore++;
        try {
            SQLDriver.incrementWineScore(repId, this.WineScore);
        }
        catch(Exception e){

        }
    }

    public void incrementSpiritScore(){
        this.SpiritScore++;
        try {
            SQLDriver.incrementSpiritScore(repId, this.SpiritScore);
        }
        catch(Exception e){

        }
    }

    public void incrementBarScore(){
        this.BarScore++;
        System.out.println(this.BarScore);
        try {
            System.out.println(repId.toString());
            SQLDriver.incrementBarScore(repId, this.BarScore);
            System.out.println("oh hey thereeeee");
        }
        catch(Exception e){

        }
    }

    public int getBeerScore(){
        return this.BeerScore;
    }
    public int getWineScore(){
        return this.WineScore;
    }
    public int getSpiritScore(){
        return this.SpiritScore;
    }
    public int getBarScore(){
        return this.BarScore;
    }


    public static User getUser(String name, String pass, userPower type, String theState, String theCity, String theZip,
                               String theStreet, String theFirstName, String theLastName,String thePhone, String theRepID,
                               String theDelim, int theBeerScore, int theWineScore, int theSpiritScore, int thebarScore){
        if((curUser == null)|| curUser.username.equals("")){
            curUser = new User(name, pass, type, theState, theCity, theZip, theStreet, theFirstName, theLastName, thePhone, theRepID, theDelim, theBeerScore, theWineScore, theSpiritScore, thebarScore);
        }
        return User.curUser;
    }



    public String getUsername() {
        return curUser.username;
    }

    public String getPassword() {
        return curUser.password;
    }

    public Boolean getAdmin() {
        return curUser.isAdmin;
    }

    public userPower getUserType() {
        return curUser.userType;
    }

    public int getAppsPer(){return curUser.appsPer;}

    public void setAppsPer(int newApps){curUser.appsPer = newApps; }

    public String getDelim() {
        return delim;
    }

    public void setDelim(String delim) {
        this.delim = delim;
    }

    public String getState() {
        return curUser.state;
    }

    public String getCity() {
        return curUser.city;
    }

    public String getZip() {
        return curUser.zip;
    }

    public String getStreet() {
        return curUser.street;
    }

    public String getFirstName() {
        return curUser.firstName;
    }

    public String getLastName() {
        return curUser.lastName;
    }

    public String getPhone() {
        return curUser.phone;
    }

    public String getRepId() {
        return curUser.repId;
    }
}
