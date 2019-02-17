package edu.wpi.cs3733c19.teamI.Entities;

public class User {
    private String username;
    private String password;
    private Boolean isAdmin = false;
    private userPower userType;
    private String delim;
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

    public String getDelim() {
        return delim;
    }

    public void setDelim(String delim) {
        this.delim = delim;
    }
}
