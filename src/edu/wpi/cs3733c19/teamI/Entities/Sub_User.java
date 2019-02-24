package edu.wpi.cs3733c19.teamI.Entities;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

//this class is used to more easily represent data in our tableview on the results UI. Simply contains all attributes
//a form can have
//TODo evaluate if it is safe to switch this out for the standard form class
public class Sub_User {

//User attributes as strings

    private int index;
    private String firstName;
    private String lastName;
    private String userEmail;
    private double userRole;


    public int getIndex(){return index;}

    public String getFirstName(){ return firstName;}
    public String getLastName(){ return lastName;}
    public String getUserEmail(){ return userEmail;}
    public double getUserRole(){ return userRole;}


    public void setIndex(int index) { this.index = index; }

    public void setFirstName(String firstName){ this.firstName = firstName;}
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail;}
    public void setUserRole(double userRole) {this.userRole = userRole; }


    //this function is used to generate the CSV download. It returns every form field as a list of string
    // seperated by commas
    public String returnAll(String delim)
    {/*
        String CSV = this.repID + delim +this.plantRegistry + delim +this.domesticOrImported + delim +this.serialNumber + delim +this.beverageType + delim
                +this.brandName + delim +this.fancifulName + delim + this.vintage + delim +this.grapeVarietals
                + this.pHValue + delim + this.wineAppellation + delim + this.alcoholContent+ delim + this.phoneNumber+ delim +
                this.email + "\n";
        return CSV;*/
        return null;
    }

    public String returnColumnNames(String delim){/*
        String csvColumn = "RepID" + delim + "Plant Registry" + delim + "Domestic/Imported" + delim + "Serial Number" + delim + "Beverage Type" + delim
                + "Brand Name" + delim + "Fanciful Name" + delim + "Vintage" + delim + "Grape Varientals" + delim + "pH Value" + delim + "Wine Appellation"
                + delim + "Alcohol Content" + delim + "Phone Number" + delim + "Email";
        return csvColumn;*/
        return null;
    }

    public ObservableList<String> getSummary(){
        ObservableList<String> list_of_Param = FXCollections.observableArrayList();

        list_of_Param.add("First Name: " + this.firstName);
        list_of_Param.add("Last Name: " + this.lastName);
        list_of_Param.add("Email Address: " + this.userEmail);
        list_of_Param.add("User Role: " + this.userRole);

        return(list_of_Param );

    }

    //constructor. This constructor takes a hasmap as an input, so we can quickly move between the DB query return type
    // and displayable values
    public Sub_User(HashMap<String, ReturnedValue> entry, int index){
        this.userRole = entry.get("role").to_double();
        this.firstName = entry.get("firstName").to_string();
        this.lastName = entry.get("lastName").to_string();
        this.userEmail = entry.get("email").to_string();
        this.index = index;
    }
}

