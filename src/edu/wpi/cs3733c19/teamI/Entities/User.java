package edu.wpi.cs3733c19.teamI.Entities;

public class User {
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

    private char delim;
        public enum userPower
        {
        Standard, Company, TTBEmployee, SuperAdmin
    }
    private int appsPer = 5;

    private static User curUser;

    private User(String name, String pass, userPower type, String theState, String theCity, String theZip, String theStreet, String theFirstName, String theLastName, String thePhone, String theRepID){
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


            }

    private static class UserHelper{
        private static final User curUser = new User("","",userPower.Standard, "", "", "", "", "", "", "", "");
    }

    public void logOutUser(){
        curUser = new User("", "", userPower.Standard, "", "","","","","","","");
    }

    public static User getUser(String name, String pass, userPower type, String theState, String theCity, String theZip, String theStreet, String theFirstName, String theLastName, String thePhone, String theRepID){
        if((curUser == null)|| curUser.username.equals("")){
            curUser = new User(name, pass, type, theState, theCity, theZip, theStreet, theFirstName, theLastName, thePhone, theRepID);
        }
        return UserHelper.curUser;
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

    public char getDelim() {
        return delim;
    }

    public void setDelim(char delim) {
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
