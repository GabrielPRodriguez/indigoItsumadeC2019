package edu.wpi.cs3733c19.teamI.Entities;

public class User {

    private String username;
    private String password;
    private Boolean isAdmin = false;
    enum userType
    {
        Standard, Company, TTBEmployee
    }

    private static User theUser;
    private User(){

    }

    private static class UserHelper{
        private static final User theUser = new User();
    }

    public static User getUser(){
        return UserHelper.theUser;
    }

}
