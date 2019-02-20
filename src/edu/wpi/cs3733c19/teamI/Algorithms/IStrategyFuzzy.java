package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers2.MongoDriver;
import edu.wpi.cs3733c19.teamI.Controllers2.SQLDriver;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface IStrategyFuzzy {
    // this interface includes a class called run to run the fuzzy search, its own sql driver class, and an Array List of hashmaps
    //TODO: Maybe we should create getters and setters but test the algorithms first

    void run(String searchString) throws Exception;
    ArrayList<HashMap<String, ReturnedValue>> matches = new ArrayList<HashMap<String, ReturnedValue>>();
    //SQLDriver querydata = new SQLDriver();
    MongoDriver querydata = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true");

    ArrayList<String> searchParameters = new ArrayList<String>(){
        {
            add("fancifulName");
            add("beverageType");
            add("extraInfo");
            add("brandName");
            //TODO: fix these so that they Actually match up with the fields in the database
        }

    };
    void setSearchParam(ArrayList<String> searchParam);
}

