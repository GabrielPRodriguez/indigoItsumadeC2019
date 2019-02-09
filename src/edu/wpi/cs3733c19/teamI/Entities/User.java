package edu.wpi.cs3733c19.teamI.Entities;

public class User {

    private String username;
    private String password;
    private Boolean isAdmin = false;
    private userPower userType = userPower.Standard;
        public enum userPower
        {
        Standard, Company, TTBEmployee
    }

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



}
