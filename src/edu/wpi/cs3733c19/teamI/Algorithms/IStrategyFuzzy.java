package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.DataField;
import edu.wpi.cs3733c19.teamI.Controllers.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface IStrategyFuzzy {
    // this interface includes a class called run to run the fuzzy search, its own sql driver class, and an Array List of hashmaps
    //TODO: Maybe we should create getters and setters but test the algorithms first
    void run(String searchString);
    ArrayList<HashMap<String, ReturnedValue>> matches=new ArrayList<HashMap<String, ReturnedValue>>();
    SQLDriver querydata=new SQLDriver();
    ArrayList<String> searchParameters=new ArrayList<String>(){
        {
            add("fanciful_name");
            add("beverage_type");
            add("extra_info");
            //TODO: fix these so that they Actually match up with the fields in the database
        }

    };
}
