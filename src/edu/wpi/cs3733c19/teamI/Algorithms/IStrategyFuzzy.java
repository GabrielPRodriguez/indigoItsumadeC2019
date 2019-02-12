package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers2.SQLDriver;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;

public interface IStrategyFuzzy {
    // this interface includes a class called run to run the fuzzy search, its own sql driver class, and an Array List of hashmaps
    //TODO: Maybe we should create getters and setters but test the algorithms first

    void run(String searchString) throws Exception;
    ArrayList<HashMap<String, ReturnedValue>> matches = new ArrayList<HashMap<String, ReturnedValue>>();
    SQLDriver querydata = new SQLDriver();
    ArrayList<String> searchParameters = new ArrayList<String>(){
        {
            add("fancifulName");
            add("beverageType");
            add("extraInfo");
            //TODO: fix these so that they Actually match up with the fields in the database
        }

    };
}

