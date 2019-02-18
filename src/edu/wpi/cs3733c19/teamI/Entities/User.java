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

    private static User theUser;

    private User(String name, String pass, userPower type){
        username = name;
        password = pass;
        userType = type;
            }

    private static class UserHelper{
        private static final User theUser = new User("","",userPower.Standard);
    }

    public static User getUser(String name, String pass, userPower type){
        if(theUser == null){
            theUser = new User(name, pass, type);
        }
        return UserHelper.theUser;
    }



    public String getUsername() {
        return theUser.username;
    }

    public String getPassword() {
        return theUser.password;
    }

    public Boolean getAdmin() {
        return theUser.isAdmin;
    }

    public userPower getUserType() {
        return theUser.userType;
    }

    public int getAppsPer(){return theUser.appsPer;}

    public void setAppsPer(int newApps){theUser.appsPer = newApps; }

    public char getDelim() {
        return delim;
    }

    public void setDelim(char delim) {
        this.delim = delim;
    }

    public String getState() {
        return theUser.state;
    }

    public String getCity() {
        return theUser.city;
    }

    public String getZip() {
        return theUser.zip;
    }

    public String getStreet() {
        return theUser.street;
    }

    public String getFirstName() {
        return theUser.firstName;
    }

    public String getLastName() {
        return theUser.lastName;
    }

    public String getPhone() {
        return theUser.phone;
    }

    public String getRepId() {
        return theUser.repId;
    }
}
