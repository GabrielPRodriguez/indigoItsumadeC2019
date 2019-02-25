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
    private String RepIDnum;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String streetAdress;
    private String city;
    private String zipCode;
    private String state;
    private String deliminator;
    private String userEmail;
    private String password;
    private String rfid;
    private double userRole;


    public int getIndex(){return index;}
    public String getRepIDnum(){ return RepIDnum;}
    public String getFirstName(){ return firstName;}
    public String getLastName(){ return lastName;}
    public String getPhoneNumber() { return phoneNumber;}
    public String getStreetAdress(){ return streetAdress;}
    public String getUserCity(){ return city;}
    public String getUserState(){ return state;}
    public String getDeliminator() { return deliminator;}
    public String getUserEmail(){ return userEmail;}
    public String getPassword(){ return password;}
    public String getrfid(){ return rfid;}
    public double getUserRole(){ return userRole;}


    public void setIndex(int index) { this.index = index; }
    public void setFirstName(String firstName){ this.firstName = firstName;}
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail;}
    public void setUserRole(double userRole) {this.userRole = userRole; }
    public void setRepIDnum(String RepIDnum){ this.RepIDnum = RepIDnum;}
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber =  phoneNumber;}
    public void setStreetAdress(String streetAdress){ this.streetAdress =  streetAdress;}
    public void setUserCity(String city){ this.city =  city;}
    public void setUserState(String state){ this.state =  state;}
    public void setDeliminator(String deliminator) { this.deliminator =  deliminator;}
    public void setPassword(String password){ this.password =  password;}
    public void setrfid(String rfid){ this.rfid = rfid;}


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
        list_of_Param.add(this.RepIDnum);
        list_of_Param.add(this.firstName);
        list_of_Param.add(this.lastName);
        list_of_Param.add(this.userEmail);
        if (userRole == 0) {
            list_of_Param.add("stan");
        }
        else if (userRole == 1){
            list_of_Param.add("agen");
        }
        else if (userRole == 2){
            list_of_Param.add("manu");
        }
        else if (userRole == 3){
            list_of_Param.add("spec");
        }
        else{
            list_of_Param.add("admi");
        }
        list_of_Param.add(this.RepIDnum);
        list_of_Param.add(this.phoneNumber);
        list_of_Param.add(this.streetAdress);
        list_of_Param.add(this.city);
        list_of_Param.add(this.state);
        list_of_Param.add(this.deliminator);
        list_of_Param.add(this.password);
        list_of_Param.add(this.rfid);
        list_of_Param.add(this.zipCode);
        return(list_of_Param );

    }

    //constructor. This constructor takes a hasmap as an input, so we can quickly move between the DB query return type
    // and displayable values
    public Sub_User(HashMap<String, ReturnedValue> entry, int index){
        this.index = index;
        this.firstName = entry.get("firstName").to_string();
        this.lastName = entry.get("lastName").to_string();
        this.userEmail = entry.get("email").to_string();
        this.userRole = entry.get("role").to_double();
        this.RepIDnum = entry.get("RepIDnum").to_string();
        this.phoneNumber = entry.get("phoneNumber").to_string();
        this.streetAdress = entry.get("streetAdress").to_string();
        this.city = entry.get("city").to_string();
        this.state = entry.get("state").to_string();
        this.deliminator = entry.get("deliminator").to_string();
        this.password = entry.get("password").to_string();
        this.rfid = entry.get("rfid").to_string();
        this.zipCode = entry.get("zipCode").to_string();
        System.out.println();
    }
}

