package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Entities.DataField;
import edu.wpi.cs3733c19.teamI.Controllers.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface IStrategyFuzzy {
    // this interface includes a class called run to run the fuzzy search, its own sql driver class, and an Array List of hashmaps
    public void run(String searchString);
    ArrayList<HashMap<String, ReturnedValue>> matches=null;
    SQLDriver querydata=new SQLDriver();
}

